package EWallet.Model.Services.impl;

import EWallet.Model.Services.DataValidation;

public class DataValidationImpl implements DataValidation {
    @Override
    public boolean validateUserName(String userName) {
        //TODO check if username size >= 3 and first char is upper and return true
        // else return false
        if (userName.length() >= 3 && Character.isUpperCase(userName.charAt(0))){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean validatePassword(String password) {
        //TODO check if password size >= 6 and contain numbers , char upper, char lower
        // and special char and return true

//        if (password.length() < 6){
//            return false;
//        }
//
//        boolean hasUpper = false;
//        boolean hasLower = false;
//        boolean hasNumbers = false;
//        boolean hasSpecialCharater = false;
//        String specialCharater = "!@#$%^&*()-_=+[]{};:',.<>?/|`~";
//
//        for (char ch : password.toCharArray()){
//            if (Character.isUpperCase(ch)){
//                hasUpper = true;
//            } else if (Character.isLowerCase(ch)) {
//                hasLower = true;
//            } else if (Character.isDigit(ch)) {
//                hasNumbers = true;
//            } else if (specialCharater.contains(String.valueOf(ch))) {
//                hasSpecialCharater = true;
//            }
//            if (hasLower && hasUpper && hasNumbers && hasSpecialCharater){
//                return true;
//            }
//        }
//        return hasLower && hasUpper && hasNumbers && hasSpecialCharater;


        boolean correctPassword = password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/].*");
        if (correctPassword){
            return true;
        }else {
            return false;
        }










    }
}
