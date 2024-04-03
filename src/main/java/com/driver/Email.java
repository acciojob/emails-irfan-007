package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        boolean oneUppercase=false;
        boolean oneLowercase=false;
        boolean oneDigit=false;
        boolean oneSpecialCharacter=false;

        if(password!=oldPassword)
            return;
        if(newPassword.length()<8)
            return;
        for(int i=0;i<newPassword.length();i++){
            char ch=newPassword.charAt(i);
            if(Character.isDigit(ch))
                oneDigit=true;
            else if(Character.isAlphabetic(ch) && Character.isUpperCase(ch))
                oneUppercase=true;
            else if(Character.isAlphabetic(ch) && Character.isLowerCase(ch))
                oneLowercase=true;
            else
                oneSpecialCharacter=true;
        }
        if(oneUppercase && oneLowercase && oneDigit && oneSpecialCharacter){
            // valid password
            password=newPassword;
            System.out.println("password changed");
        }
    }
}

