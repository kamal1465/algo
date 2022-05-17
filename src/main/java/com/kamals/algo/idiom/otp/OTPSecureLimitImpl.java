package com.kamals.algo.idiom.otp;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class OTPSecureLimitImpl implements OTPSecureLimit
{
    private static final int GENERATE_OTP_MAX_LIMIT = 4;
    private static final int GENERATE_OTP_LIMIT_DURATION = 3600;
    private static final int SEND_OTP_MAX_LIMIT = 3;
    private static final int VERIFY_OTP_MAX_ATTEMPT = 3;
    private static final int OTP_VALIDITY_SECONDS = 180;

    @Override
    public String resendOTP(String id, String otpType, int otpLength)
    {
        String otp = null;
        String key = id + "_" + otpType;
        String sendKey = key + "_Send";
        boolean lock = false;
        try
        {
            lock = RILHazelcastClient.getMap("default").tryLock(key);
            if (!lock)
            {
                System.out.println("Concurrent request received for " + key + ". Returning");
                return null;
            }
            otp = RILHazelcastClient.get("default", key);
            if (otp == null)
            {
                return generateOTP(id, otpType, otpLength);
            }
            String sendCountStr = RILHazelcastClient.get("default", sendKey);
            int sendCount = sendCountStr != null ? Integer.valueOf(sendCountStr) : 1;
            if (sendCount < SEND_OTP_MAX_LIMIT)
            {
                System.out.println("Re-sending OTP for: " + key + " count:" + sendCount);
                sendCount++;
                RILHazelcastClient.update("default", sendKey, String.valueOf(sendCount), OTP_VALIDITY_SECONDS);
            }
            else
            {
                System.out.println("Send OTP Limit exceeded for: " + key + " count:" + sendCount);
                otp = null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Hazelcast otp send error occurred" + e);
        }
        finally
        {
            if (lock)
            {
                RILHazelcastClient.getMap("default").unlock(key);
            }
        }
        return otp;
    }

    @Override
    public String generateOTP(String id, String otpType, int otpLength)
    {
        String otp = null;
        String key = id + "_" + otpType;
        String attemptKey = key + "_Attempt";
        String limitKey = key + "_Limit";
        String sendKey = key + "_Send";
        boolean lock = false;

        try
        {
            lock = RILHazelcastClient.getMap("default").tryLock(key);
            if (!lock)
            {
                System.out.println("Concurrent request received for " + limitKey + ". Returning");
                return null;
            }
            String otpLimitStr = RILHazelcastClient.get("default", limitKey);
            int otpLimit = otpLimitStr != null ? Integer.valueOf(otpLimitStr) : 0;
            if (otpLimit < GENERATE_OTP_MAX_LIMIT)
            {
                System.out.println("Generating OTP for: " + key + " len:" + otpLength);
                otp = OTP(otpLength);
                otpLimit++;
                RILHazelcastClient.update("default", limitKey, String.valueOf(otpLimit), GENERATE_OTP_LIMIT_DURATION);
                RILHazelcastClient.set("default", key, otp, OTP_VALIDITY_SECONDS, TimeUnit.SECONDS);
                RILHazelcastClient.set("default", attemptKey, "0", OTP_VALIDITY_SECONDS, TimeUnit.SECONDS);
                RILHazelcastClient.set("default", sendKey, "1", OTP_VALIDITY_SECONDS, TimeUnit.SECONDS);
            }
            else
            {
                System.out.println("Generate OTP Limit exceeded for:" + key);
            }
            System.out.println("End of generateOTP method:");
        }
        catch (Exception e)
        {
            System.out.println("Hazelcast otp generation error occurred" + e);
        }
        finally
        {
            if (lock)
            {
                RILHazelcastClient.getMap("default").unlock(key);
            }
        }
        return otp;
    }

    private String OTP(int len)
    {
        String numbers = "0123456789";
        SecureRandom rndm_method = new SecureRandom();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return String.valueOf(otp);
    }

    @Override
    public boolean verifyOtp(String id, String otpType, String otp)
    {
        System.out.println("Entered into VerfiyOtp method");
        String key = id + "_" + otpType;
        String attemptKey = key + "_Attempt";
        int attempt = 0;
        try
        {
            String attemptString = RILHazelcastClient.get("default", attemptKey);
            String otpVer = RILHazelcastClient.get("default", key);
            attempt = attemptString != null ? Integer.valueOf(attemptString) : 0;
            attempt++;
            if (attempt > VERIFY_OTP_MAX_ATTEMPT)
            {
                System.out.println("OTP attempts exceeded max attempt:" + attempt + " for " + key);
                RILHazelcastClient.delete("default", key);
            }
            else if (otp.equals(otpVer))
            {
                RILHazelcastClient.delete("default", key);
                RILHazelcastClient.delete("default", attemptKey);
                return true;
            }
            RILHazelcastClient.set("default", attemptKey, Integer.toString(attempt), OTP_VALIDITY_SECONDS, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            System.out.println("Hazelcast Timeout : Exception while verifying OTP " + e);
        }

        System.out.println("Verification Failed:" + attempt);
        return false;
    }
}