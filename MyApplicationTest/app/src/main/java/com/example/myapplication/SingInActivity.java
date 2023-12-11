package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.User;
import com.example.myapplication.classes.Utils;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.util.Map;

public class SingInActivity extends AppCompatActivity {

    /* Submit Button */
    Button btn_submit;

    /* Object */
    User user;

    /* Variable */
    String sMail;
    String sPassword;
    String sPasswordC;
    String sName;
    String sNumeroTel;
    int nAge;


    /* Edit Text */
    EditText mail ;
    EditText password ;
    EditText passwordC ;
    EditText name ;
    EditText numeroTel ;
    Slider age;


    /* TextView Error */
    TextView error_mail;
    TextView error_pwd;
    TextView error_pwdC;
    TextView error_name;
    TextView error_numeroTel;
    TextView error_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_singin1);
        setContentView(R.layout.activity_singin2);
        setContentView(R.layout.activity_singin3);

        /* back button */
        final Button btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingInActivity.this.finish();
            }
        });



        /* Button submit */

        btn_submit = (Button) findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              age.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                  @SuppressLint("RestrictedApi")
                  @Override
                  public void onStartTrackingTouch(@NonNull Slider slider) {
                      //Responds when slider's touch event is being started
                  }

                  @SuppressLint("RestrictedApi")
                  @Override
                  public void onStopTrackingTouch(@NonNull Slider slider) {
                      //Responds to when slider's touch event is being stopped

                  }


              });

              age.addOnChangeListener(new Slider.OnChangeListener() {
                  @SuppressLint("RestrictedApi")
                  @Override
                  public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        //Responds to when slider's value is changed
                      if (fromUser)
                        slider.getValue();
                  }
              });

                initField();
                initAttribut();
                user = new User();



                if (!user.inputControlSingin(sMail, sPassword, sPasswordC,
                                             sName, sNumeroTel, nAge)){

                    displayFieldError();

                }else{
                    /* mettre les informations dans le buffer(comme une dict") */
                    Bundle params = new Bundle();
                    params.putString("mail", mail.getText().toString());
                    params.putString("password", password.getText().toString());

                    /* Mettre le buffer dans une variable global myIntent et le passer en parametre a une autre fenetre */
                    Intent myIntent = new Intent(SingInActivity.this, HomeActivity.class);
                    myIntent.putExtras(params);
                    startActivity(myIntent);

                }
            }

        });/* End submit Button */
    }/* End onCreate*/

    public void initField(){
        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);
        passwordC = (EditText) findViewById(R.id.passwordC);
        numeroTel = (EditText) findViewById(R.id.numeroTel);
        name = (EditText) findViewById(R.id.name);
        age = (Slider) findViewById(R.id.age);
    }

    public void initAttribut(){
        this.sMail= mail.getText().toString();
        this.sPassword = password.getText().toString();
        this.sPasswordC = passwordC.getText().toString();
        this.sName = name.getText().toString();
        this.sNumeroTel = numeroTel.getText().toString();
        initAge();
    }

    public void displayFieldError(){
        //Display Errors
        for(Map.Entry<Error, String> entry : user.getErrors().entrySet()){
            if (entry.getKey() == Error.MAIL)
                this.error_mail = Utils.champErrorVisible(error_mail, entry.getValue());
            else if(entry.getKey() == Error.PASSWORD)
                this.error_pwd = Utils.champErrorVisible(error_pwd, entry.getValue());
            else if (entry.getKey() == Error.PASSWORD_COMFIRMATION)
                this.error_pwdC = Utils.champErrorVisible(error_pwdC, entry.getValue());
            else if (entry.getKey() == Error.NAME)
                this.error_name = Utils.champErrorVisible(error_name, entry.getValue());
            else if (entry.getKey() == Error.NUMERO_TELEPHONE)
                this.error_numeroTel = Utils.champErrorVisible(error_numeroTel, entry.getValue());
            else if (entry.getKey() == Error.AGE)
                this.error_age = Utils.champErrorVisible(error_age, entry.getValue());
        }
    }


    public void initAge(){
        this.age.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                nAge = (int) value;
                return nAge + "ans";
            }
        });
    }
 }
