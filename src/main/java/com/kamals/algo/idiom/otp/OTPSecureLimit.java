package com.kamals.algo.idiom.otp;

public interface OTPSecureLimit
{
    String resendOTP(String id, String otpType, int otpLength);

    String generateOTP(String id, String otpType, int otpLength);

    boolean verifyOtp(String id, String otpType, String otp);
}
