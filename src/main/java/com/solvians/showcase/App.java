package com.solvians.showcase;

/**
 * Hello world!
 */
public class App {
    public App(String threads, String quotes) {

    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Expected at least 2 arguments number of threads and number of quotes. But got: " + args.length
            );
        }

        try {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            if (threads <= 0 || quotes <= 0) {
                throw new IllegalArgumentException("Both thread and quote counts must be positive integers.");
            }

            CertificateUpdateGenerator generator = new CertificateUpdateGenerator(threads, quotes);
            generator.generateQuotes().forEach(System.out::println);

        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
