package com.example.myapplication.classes;

import java.util.HashMap;

public class User {
    private String name;
    private String mail;
    private String password;
    private String passwordC;
    private String numeroTel;
    private int age;
    private HashMap<Error, String> errors;

    public User(){
        this.name = "";
        this.mail = "";
        this.password = "";
        this.passwordC = "";
        this.numeroTel = "";
        this.age=0;
        this.errors= new HashMap<Error,String>();
    }

    public boolean setName(String name){
        this.name = name;

        this.errors.remove(Error.NAME);
        if(this.name.isEmpty()){
            this.errors.put(Error.NAME, "Nom est vide");
            return false;
        }else if (this.name.length()<3){//regex
            this.errors.put(Error.NAME,"Name est incorrect");
            return false;
        }
        return true;
    }

    public boolean setNumeroTel(String numeroTel){
        this.numeroTel = numeroTel;

        this.errors.remove(Error.NUMERO_TELEPHONE);
        if(this.numeroTel.isEmpty()){
            this.errors.put(Error.NUMERO_TELEPHONE, "Numéro de telephone est vide");
            return false;
        }else if (this.numeroTel.length()<10){//regex
            this.errors.put(Error.NUMERO_TELEPHONE,"Numero Telephone est incorrect");
            return false;
        }
        return true;
    }

    public boolean setAge(int age){
        this.age = age;

        this.errors.remove(Error.AGE);
        if(this.age < 0) {
            this.errors.put(Error.AGE, "Age Incorrect");
            return false;
        }
        return true;
    }

    public boolean setMail(String mail){
        this.mail=mail;

        this.errors.remove(Error.MAIL);
        if (this.mail.isEmpty()){
            this.errors.put(Error.MAIL,"Mail est vide");
            return false;
        }else if (mail.length()<3) {//regex
            this.errors.put(Error.MAIL, "Mail est incorrect");
            return false;
        }
        return true;
    }

    public boolean setPassword(String password){
        this.password = password;

        this.errors.remove(Error.PASSWORD);
        if (this.password.isEmpty()){
            errors.put(Error.PASSWORD,"Password est vide");
            return false;
        }else if (this.password.length()<3){//regex
            errors.put(Error.PASSWORD,"Password est incorrect");
            return false;
        }
        return true;
    }

    public boolean setPasswordC(String passwordC){
        this.passwordC = passwordC;

        this.errors.remove(Error.PASSWORD_COMFIRMATION);
        if (this.passwordC != this.password){
            this.errors.put(Error.PASSWORD_COMFIRMATION, "Password et la confirmation ne sont pas les meme");
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

    public void clear(){
        this.name = "";
        this.mail = "";
        this.password = "";
        this.passwordC = "";
        this.numeroTel = "";
        this.age=0;
        this.errors.clear();
    }
    public boolean read(String mail, String password){

        return true;
    }

    public String getQeury(){
        return "";
    }
    public boolean delete(){

        return true;
    }
    public boolean save(){

        return true;
    }

    public boolean inputControlSingin(String mail, String password, String passwordC,
                                      String name, String numeroTel, int age){
        this.errors.clear();
        boolean mail_valid = this.setMail(mail);
        boolean pwd_valid = this.setPassword(password);
        boolean pwdC_valid = this.setPasswordC(passwordC);
        boolean name_valid = this.setName(name);
        boolean numeroTel_valid = this.setNumeroTel(numeroTel);
        boolean age_valid = this.setAge(age);
        return (mail_valid &&
                pwd_valid &&
                pwdC_valid &&
                name_valid &&
                numeroTel_valid &&
                age_valid);
    }



   public HashMap<Error, String> getErrors(){
        return this.errors;
   }



}
