package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.classes.Error;
import com.example.myapplication.classes.GlobalVariables;
import com.example.myapplication.classes.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    Button btn_submit;
    TextInputEditText ie_mail;
    TextInputEditText ie_password;
    TextInputEditText ie_password_c;
    TextInputEditText ie_name;
    TextInputEditText ie_phone;
    TextInputLayout ie_mail_layout;
    TextInputLayout ie_password_layout;
    TextInputLayout ie_password_c_layout;
    TextInputLayout ie_name_layout;
    TextInputLayout ie_phone_layout;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create instance */
        super.onCreate(savedInstanceState);

        /* Init */
        setContentView(R.layout.activity_edit_profile);

        /* Hooks */
        initHooks();

        /* Get User */
        if(getUser()){
            objectToUI();
        }

        /* Toolbar */
        initToolbar();

        /* Submit - Action */

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User tmp_user = new User();
                initErrorsUI();
                if (!tmp_user.inputControlUpdate(ie_mail.getText().toString(),ie_password.getText().toString(),
                                             ie_password_c.getText().toString(),ie_name.getText().toString(),
                                             ie_phone.getText().toString(), 12)){
                    //Display Errors
                    for(Map.Entry<Error, String> entry : tmp_user.getErrors().entrySet()){
                        if (entry.getKey() == Error.MAIL)
                            ie_mail_layout.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD)
                            ie_password_layout.setError(entry.getValue());
                        else if(entry.getKey() == Error.PASSWORD_COMFIRMATION)
                            ie_password_c_layout.setError(entry.getValue());
                        else if(entry.getKey() == Error.NAME)
                            ie_name_layout.setError(entry.getValue());
                        else if(entry.getKey() == Error.NUMERO_TELEPHONE)
                            ie_phone_layout.setError(entry.getValue());
                    }
                }else{
                    initErrorsUI();
                    if (!tmp_user.dbInputControlUpdate(EditProfileActivity.this, user.getMail(),user.getNumeroTel())){
                        for(Map.Entry<Error, String> entry : tmp_user.getDbErrors().entrySet()){
                            if (entry.getKey() == Error.MAIL)
                                ie_mail_layout.setError(entry.getValue());
                            else if (entry.getKey() == Error.NUMERO_TELEPHONE)
                                ie_phone_layout.setError(entry.getValue());
                        }
                    }else{
                        tmp_user.setId(user.getId());
                        if (tmp_user.save(EditProfileActivity.this)) {
                            Toast.makeText(EditProfileActivity.this, "Update success", Toast.LENGTH_LONG).show();
                            EditProfileActivity.this.finish();
                        }else {
                            Toast.makeText(EditProfileActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

        });

    }

    public void initHooks(){
        /* toolbar */
        toolbar = (MaterialToolbar) findViewById(R.id.topAppBarBack);
        /* submit */
        btn_submit = (Button) findViewById(R.id.edit_profile_submit);
        /* edit text */
        ie_mail = (TextInputEditText) findViewById(R.id.edit_profile_mail);
        ie_password = (TextInputEditText) findViewById(R.id.edit_profile_password);
        ie_password_c = (TextInputEditText) findViewById(R.id.edit_profile_password_c);
        ie_name = (TextInputEditText) findViewById(R.id.edit_profile_name);
        ie_phone = (TextInputEditText) findViewById(R.id.edit_profile_phone);
        /* layout */
        ie_mail_layout = (TextInputLayout) findViewById(R.id.edit_profile_mail_layout);
        ie_password_layout = (TextInputLayout) findViewById(R.id.edit_profile_password_layout);
        ie_password_c_layout = (TextInputLayout) findViewById(R.id.edit_profile_password_c_layout);
        ie_name_layout = (TextInputLayout) findViewById(R.id.edit_profile_name_layout);
        ie_phone_layout = (TextInputLayout) findViewById(R.id.edit_profile_phone_layout);
    }

    public void initToolbar(){
        toolbar.setTitle(R.string.profile_edit);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Handle navigation icon press */
                Toast.makeText(EditProfileActivity.this, "sortie sans sauvegarder !", Toast.LENGTH_SHORT).show();
                EditProfileActivity.this.finish();
            }
        });
    }

    public boolean getUser(){
        user = new User();
        user.setId(GlobalVariables.getUserIdCourant());
        return user.read(EditProfileActivity.this);
    }
    public void objectToUI(){
        ie_mail.setText(user.getMail());
        ie_password.setText(user.getPassword());
        ie_password_c.setText(user.getPassword());
        ie_name.setText(user.getName());
        ie_phone.setText(user.getNumeroTel());
    }

    public void initErrorsUI(){
        ie_mail_layout.setError(null);
        ie_password_layout.setError(null);
        ie_password_c_layout.setError(null);
        ie_name_layout.setError(null);
        ie_phone_layout.setError(null);
    }
}