package com.example.myapplication;

import java.util.HashMap;

public class User {
    private String name;
    private String firstName;
    private String mail;
    private String password;
    private String passwordC;
    private HashMap<String, String> errors;

    User(){
        this.name = "";
        this.firstName = "";
        this.mail = "";
        this.password = "";
        this.passwordC = "";
        this.errors= new HashMap<String,String>();
    }

    public boolean setName(String name){
        this.name = name;

        this.errors.remove("name");
        if(this.name.isEmpty()){
            this.errors.put("name", "Nom est vide");
            return false;
        }else if (this.name.length()<3){//regex
            this.errors.put("name","Name est incorrect");
            return false;
        }
        return true;
    }

    public boolean setFirstName(String firstName){
        this.firstName = firstName;

        this.errors.remove("firstname");
        if(this.firstName.isEmpty()){
            this.errors.put("firstName", "First name est vide");
            return false;
        }else if (this.firstName.length()<3){//regex
            this.errors.put("firstName","First name est incorrect");
            return false;
        }
        return true;
    }

    public boolean setMail(String mail){
        this.mail=mail;

        this.errors.remove("mail");
        if (this.mail.isEmpty()){
            this.errors.put("mail","Mail est vide");
            return false;
        }else if (mail.length()<3) {//regex
            this.errors.put("mail", "Mail est incorrect");
            return false;
        }
        return true;
    }

    public boolean setPassword(String password){
        this.password = password;

        this.errors.remove("pwd");
        if (this.password.isEmpty()){
            errors.put("pwd","Password est vide");
            return false;
        }else if (this.password.length()<3){//regex
            errors.put("pwd","Password est incorrect");
            return false;
        }
        return true;
    }

    public boolean setPasswordC(String passwordC){
        this.passwordC = passwordC;

        this.errors.remove("pwdC");
        if (this.passwordC != this.password){
            this.errors.put("pwdC", "Password et la confirmation ne sont pas les meme");
            return false;
        }
        return true;
    }

    public boolean inputControlLogin(String mail, String password){
        this.errors.clear();
        boolean mail_valid = this.setMail(mail);
        boolean pwd_valid = this.setPassword(password);

        return (mail_valid &&
                pwd_valid);
    }

    public boolean inputControlSingin(String mail, String password, String passwordC,
                                      String name, String firstName){
        this.errors.clear();
        boolean mail_valid = this.setMail(mail);
        boolean pwd_valid = this.setPassword(password);
        boolean pwdC_valid = this.setPasswordC(passwordC);
        boolean name_valid = this.setName(name);
        boolean firstName_valid = this.setFirstName(firstName);

        return (mail_valid &&
                pwd_valid &&
                pwdC_valid &&
                name_valid &&
                firstName_valid);
    }



   public HashMap<String, String> getErrors(){
        return this.errors;
   }



}
