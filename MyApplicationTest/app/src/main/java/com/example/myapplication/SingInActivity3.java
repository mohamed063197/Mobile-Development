package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class SingInActivity3 extends Activity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin3);
        Bundle params = getIntent().getExtras();
        user = new User();
        user.setMail(params.getString("mail"));
        user.setPassword(params.getString("password"));
         /*
            Button submit
         */
        //le boutton doit etre constant et non modifiable.
        Button btn_submit = (Button) findViewById(R.id.singInSubmit);
        TextInputLayout nameLayout = findViewById(R.id.singInNameLayout);
        TextInputLayout phoneLayout = findViewById(R.id.singInPhoneLayout);

        TextInputEditText name = findViewById(R.id.singInName);
        TextInputEditText phone = findViewById(R.id.singInPhone);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameLayout.setError(null);
                phoneLayout.setError(null);


                if (!user.inputControlSingInStep2(name.getText().toString(), phone.getText().toString(), 12)){
                    //Display Errors
                    for(Map.Entry<Error, String> entry : user.getErrors().entrySet()){
                        if (entry.getKey() == Error.NAME)
                            nameLayout.setError(entry.getValue());
                        else if(entry.getKey() == Error.NUMERO_TELEPHONE)
                            phoneLayout.setError(entry.getValue());
                    }
                }else{
                    nameLayout.setError(null);
                    phoneLayout.setError(null);
                    if (!user.dbControlSingInStep2(SingInActivity3.this)){
                        for(Map.Entry<Error, String> entry : user.getDbErrors().entrySet()){
                            if (entry.getKey() == Error.NUMERO_TELEPHONE)
                                phoneLayout.setError(entry.getValue());
                        }
                    }else{
                        if (user.save(SingInActivity3.this)) {
                            Toast.makeText(SingInActivity3.this, "Enregistrement succes", Toast.LENGTH_LONG).show();
                            GlobalVariables.setUserIdCourant(user.getId());
                            Intent myIntent = new Intent(SingInActivity3.this, HomeActivity.class);
                            startActivity(myIntent);

                        }else {
                            Toast.makeText(SingInActivity3.this, "Enregistrement echec", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });
    }
}
