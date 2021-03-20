package com.example.minibank.commons.utils;

import java.time.LocalDate;

import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.leftPad;

public class IdGenerator {

    private static int counter = 1;

    public static String generateAccountNumber() {

        StringBuilder accountNumber = new StringBuilder("10");

        final LocalDate now = LocalDate.now();

        accountNumber.append(String.format("%ty", now));

        final int dayOfMonth = now.getDayOfMonth();

        accountNumber.append(dayOfMonth <= 9 ? "0" + dayOfMonth : dayOfMonth);

        for (int i = 0; i < 7; i++) {
            int n = nextInt(0, 10);
            accountNumber.append(n);
        }

        return accountNumber.toString() + getCounter();
    }

    private static String getCounter() {
        if (counter > 999) {
            counter = 1;
        } else {
            int nextInt = nextBoolean() ? 1 : 2;
            counter = counter + nextInt;
        }

        final String counterString = counter + "";

        return leftPad(counterString, 3, "0");
    }

    public static String generateReferenceCode() {
        StringBuilder referenceCode = new StringBuilder();
        referenceCode.append(Long.toHexString(System.currentTimeMillis()));
        referenceCode.append(getCounter());
        return referenceCode.toString().toUpperCase();
    }
}
