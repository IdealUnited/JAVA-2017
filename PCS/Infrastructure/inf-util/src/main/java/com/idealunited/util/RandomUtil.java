package com.idealunited.util;

import java.util.Random;

/**
 * Utility class to generate random value or string.
 */
public final class RandomUtil {

    private static Random random = new Random();

    /**
     * All methods are static; we do not expect anyone to instantiate this class
     */
    private RandomUtil() {
    }

    /**
     * Returns an <code>int</code> value with a positive sign, greater than or
     * equal to <code>0</code> and less than <code>10,000,000</code>.
     * Returned values are chosen pseudorandomly with (approximately) uniform
     * distribution from that range.
     * 
     * @return an <code>int</code> value within the range
     */
    public static int random() {
        return (int) (Math.random() * 10000000D);
    }

    /**
     * Returns an <code>int</code> value, greater than or
     * equal to <em>lower</em> and less than or equal to <em>upper</em>.
     * 
     * @param lower
     *            The min value
     * @param upper
     *            The max value
     * @return an <code>int</code> value within the range
     */
    public static int random(int lower, int upper) {
        if (lower < 0 || upper < 0) {
            throw new IllegalArgumentException();
        }
        
        int len = String.valueOf(upper).length();
        int base = 1;
        for (int i = 0; i < len; i++)
            base *= 10;

        int num;
        do
            num = (int) (Math.random() * (double) base);
        while (num < lower || num > upper);
        return num;
    }

    /**
     * Returns a <code>String</code> with <em>len</em> characters randomly
     * selected from "23456789ABCDEFGHIJKLMNOQRSTUWXYZ" set.
     * 
     * @param len
     *            The desired length of random string
     * @return a <code>String</code> with size <em>len</em>
     */
    public static String random(int len) {
        String sys = "23456789ABCDEFGHIJKLMNQRSTUWXYZ";
        // Random random = new Random();
        String re = "";
        for (int i = 0; i < len; i++) {
            int n = random.nextInt(31);
            re += sys.substring(n, n + 1);
        }
        return re;
    }
    public static String randomDegital(int len) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
    /**
     * Constructs a random string of printable characters.
     * 
     * @param len
     *            The desired length of the random string.
     * @return String of size n holding random printable characters.
     */
    public static String randomString(int len) {
        String result = "";

        for (int i = 0; i < len; i++) {
            result = result + randomChar();
        }
        return result;
    }

    /**
     * Generate a single random, printable character
     * 
     * @return character with ascii value 32 - 126
     */
    public static char randomChar() {
        /*
         * printable characters are ascii 32 - 126 (that's 95 characters) so we
         * generate 0-95 and add 32 to get this range
         */
        int val = random.nextInt(95) + 32;
        /**
         * 不要0/o,1/i等不容易辨认的字符
         */
        if(val == 48 || val == 79 || val == 105 || val == 111)
            val ++;
        return (char) val;
    }
}
