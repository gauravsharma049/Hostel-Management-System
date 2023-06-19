package com.hostel.service;

public interface PasswordChange {
    public void resetPassword(int userId, String oldPassword, String newPassword);
    public void forgotPassword();
    /*
    * request for password change
    * send otp on email
    * match the otp
    * generate a password and send on email
    * ask to reset the password manually
    */
}
