package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate {
    private final long timestamp;
    private final String isin;
    private final double bidPrice;
    private final int bidSize;
    private final double askPrice;
    private final int askSize;

    /**
     * Constructs a new {@code CertificateUpdate} instance with randomized values.
     * Fields include:
     * Current timestamp in milliseconds
     * Randomly generated ISIN
     * Bid price between 100.00 and 200.00 (rounded to 2 decimal places)
     * Bid size between 1,000 and 5,000
     * Ask price between 100.00 and 200.00 (rounded to 2 decimal places)
     * Ask size between 1,000 and 10,000
     */
    public CertificateUpdate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        this.timestamp = System.currentTimeMillis();
        this.isin = ISINGenerator.generateISIN();
        this.bidPrice = round(random.nextDouble(100.00, 200.00));
        this.bidSize = random.nextInt(1000, 5001);
        this.askPrice = round(random.nextDouble(100.00, 200.00));
        this.askSize = random.nextInt(1000, 10001);
    }

    /**
     * Rounds a floating-point value to two decimal places.
     *
     * @param value the input value to round
     * @return the rounded value with two decimal precision
     */
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Returns a comma-separated string representation of the certificate update.
     * Format: {@code timestamp,ISIN,bidPrice,bidSize,askPrice,askSize}
     *
     * @return the formatted string representing this update
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f,%d",
                timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}
