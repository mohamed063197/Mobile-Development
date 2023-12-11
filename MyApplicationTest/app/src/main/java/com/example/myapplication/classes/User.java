package com.example.myapplication.classes;

import android.content.ContentValues;
import android.content.Context;
import android.util.Pair;
import java.util.ArrayList;

import java.util.HashMap;

public class User {
    private long id;
    private String name;
    private String mail;
    private String password;
    private String passwordC;
    private String numeroTel;
    private int age;
    private HashMap<Error, String> errors;
    private HashMap<Error, String> dbErrors;
    private final static String MAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,}$";
    private final static String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{6,}$";



    public User(){
        this.id = 0;
        this.name = "";
        this.mail = "";
        this.password = "";
        this.passwordC = "";
        this.numeroTel = "";
        this.age=0;
        this.errors= new HashMap<Error,String>();
        this.dbErrors = new HashMap<Error,String>();
    }

    /*
    CONTROL METHODS
     */
    public boolean inputControlLogin(String mail, String password){
        this.errors.clear();
        boolean mail_valid = this.setMail(mail);
        boolean pwd_valid = this.setPassword(password);

        return (mail_valid &&
                pwd_valid);
    }

    public boolean dbControlLogin(Context context){
        this.dbErrors.clear();
        boolean db_mail_valid = this.readByMailPwd(context);
        return (db_mail_valid);
    }

    public void clear(){
        this.id = 0;
        this.name = "";
        this.mail = "";
        this.password = "";
        this.passwordC = "";
        this.numeroTel = "";
        this.age=0;
        this.errors.clear();
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
    public HashMap<Error, String> getDbErrors(){
        return this.dbErrors;
    }

    /*
SAVE METHODS
 */
   public boolean save(Context context){//modifie ou enregistre selon id
        MyDatabase db = new MyDatabase(context);
        Pair<Boolean, Long> resultAdd;
        boolean resultUpdate;
        if (this.id == 0){
            resultAdd = db.addUser(this);
            return resultAdd.first;
        } else if (this.id != 0){
            resultUpdate = db.updateUser(this);
            return resultUpdate;
        }
        return false;
   }
/*
READ METHODS
 */
    public static ArrayList<User> readAll(Context content){
        MyDatabase db = new MyDatabase(content);
        return db.readAllUsers();
    }
   public boolean read(Context context){
        this.dbErrors.remove(Error.MAIL);
       this.dbErrors.remove(Error.PASSWORD);
        MyDatabase db = new MyDatabase(context);
        boolean find = db.readUser(this);
        if (!find){
            this.dbErrors.put(Error.MAIL,"mail ou le mot de passe n'existe pas");
            return false;
        }
        return true;
   }
    public boolean readByMailPwd(Context context){
        this.dbErrors.remove(Error.MAIL);
        this.dbErrors.remove(Error.PASSWORD);
        MyDatabase db = new MyDatabase(context);
        boolean find = db.readUserByMailPwd(this);
        if (!find){
            this.dbErrors.put(Error.MAIL,"mail ou le mot de passe n\'existe pas");
            return false;
        }
        return true;
    }
    /*
    DELETE METHODS
     */
   public boolean delete(Context context){
       MyDatabase db = new MyDatabase(context);
       return db.deleteUser(this);
   }


    /*
       SET METHODS
        */
    public void setId(long id){
        this.id=id;
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
        }else if (this.numeroTel.length() != 10){//regex
            this.errors.put(Error.NUMERO_TELEPHONE,"Numero Telephone est incorrect");
            return false;
        }
        return true;
    }

    public boolean setAge(int age){
        this.age = age;
        this.errors.remove(Error.AGE);
        if(this.age <= 0) {
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
        }else if (!(this.mail.matches(MAIL_PATTERN))){//regex
            this.errors.put(Error.MAIL, "mail incorrect");
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
        }else if (!(this.password.matches(PASSWORD_PATTERN))){//regex
            this.errors.put(Error.PASSWORD, "Doit contenir au mois une majuscule et un caractère spacial et au minimum 6 caractères");
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

   //GETTER
    public long getId(){
        return id;
    }
   public String getName() {
       return name;
   }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordC() {
        return passwordC;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public int getAge() {
        return age;
    }

}
