package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model class representing an individual financial or system transaction history entry.
 */
public class Transaction {
    private final String type;
    private final double amount;
    private final String details;
    private final String timestamp;

    /**
     * Constructs a new Transaction log entry with auto-generated timestamp.
     *
     * @param type    The category of transaction (e.g., DEPOSIT, WITHDRAW, TRANSFER).
     * @param amount  The monetary value involved in the transaction.
     * @param details Additional description or contextual metadata.
     */
    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }

    /**
     * Formats transaction details into a readable string representation.
     *
     * @return Formatted string containing timestamp, type, amount, and details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %-15s | Amount: %-8.2f EGP | Details: %s",
                timestamp, type, amount, details);
    }
}