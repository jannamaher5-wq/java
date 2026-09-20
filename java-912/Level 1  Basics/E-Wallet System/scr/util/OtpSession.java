package util;

import java.util.Random;

/**
 * util.OtpSession Class
 * Manages OTP code generation, expiration time (60 seconds), and attempt thresholds.
 */
public class OtpSession {
    private final int code;
    private final long creationTime;
    private int attempts;
    private static final long EXPIRATION_TIME_MS = 60000; // 60 Seconds
    private static final int MAX_ATTEMPTS = 3;

    public OtpSession() {
        Random random = new Random();
        this.code = 1000 + random.nextInt(9000);
        this.creationTime = System.currentTimeMillis();
        this.attempts = 0;
    }

    public int getCode() { return code; }

    public boolean isExpired() {
        return (System.currentTimeMillis() - creationTime) > EXPIRATION_TIME_MS;
    }

    public boolean validate(int inputCode) throws Exception {
        if (isExpired()) {
            throw new Exception("OTP code expired! Verification timeframe exceeded 60 seconds.");
        }

        attempts++;
        if (attempts > MAX_ATTEMPTS) {
            throw new Exception("Maximum OTP attempts exceeded (Limit: 3 attempts).");
        }

        return this.code == inputCode;
    }
}
