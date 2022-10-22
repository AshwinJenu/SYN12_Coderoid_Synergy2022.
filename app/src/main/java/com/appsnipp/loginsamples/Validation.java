package com.appsnipp.loginsamples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public  boolean emailValidate(String email){

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher emailMatcher = emailPattern.matcher(email);
        System.out.println("email"+emailMatcher.matches());
        return emailMatcher.matches();
    }

    public  boolean phoneValidate(String phone){

        String regex = "^[0-9]{10}$";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        System.out.println("phone"+phoneMatcher.matches());
        return phoneMatcher.matches();
    }
}
