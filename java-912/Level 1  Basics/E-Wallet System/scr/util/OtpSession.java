package util;

import java.util.Random;

/**
 * Utility managing temporary One-Time Password (OTP) lifecycle and time expiry limits.
 */
public class OtpSession {
    private final int code;
    private final long creationTime;
    private int attempts;

    private static final long EXPIRATION_TIME_MS = 60_000; // 60 Seconds
    private static final int MAX_ATTEMPTS = 3;

    /**
     * Generates a random 4-digit OTP code and records timestamp.
     */
    public OtpSession() {
        this.code = 1000 + new Random().nextInt(9000);
        this.creationTime = System.currentTimeMillis();
        this.attempts = 0;
    }

    /**
     * Retrieves generated OTP code.
     *
     * @return 4-digit code.
     */
    public int getCode() { return code; }

    /**
     * Verifies if OTP session has exceeded the 60-second limit.
     *
     * @return True if expired, false otherwise.
     */
    public boolean isExpired() {
        return (System.currentTimeMillis() - creationTime) > EXPIRATION_TIME_MS;
    }

    /**
     * Validates input OTP code against active code and attempt limits.
     *
     * @param inputCode User-submitted OTP digits.
     * @return True if matching, throws Exception on failure or expiration.
     * @throws Exception If session expired or exceeded max attempts.
     */
    public boolean validate(int inputCode) throws Exception {
        if (isExpired()) {
            throw new Exception("OTP code expired (Timeframe limit: 60 seconds exceeded).");
        }
        attempts++;
        if (attempts > MAX_ATTEMPTS) {
            throw new Exception("Maximum OTP attempts limit reached.");
        }
        return this.code == inputCode;
    }
}