package com.idealunited.util;

import java.lang.reflect.Array;

/**
 * Collected methods which allow easy implementation of
 * <code>hashCode</code>. The implementation is based on well-known <i>Effective Java</i>. 
 * It is required to override <em>hashCode</em> method 
 * when override <em>equals</em>.
 * 
 * @see <code>EqualsUtil.java</code>
 * <p>
 * Example use case:
 * 
 * <pre>
 * public int hashCode() {
 *     int result = HashCodeUtil.HASH_SEED;
 *     //collect the contributions of various fields
 *     result = HashCodeUtil.hash(result, fPrimitive);
 *     result = HashCodeUtil.hash(result, fObject);
 *     result = HashCodeUtil.hash(result, fArray);
 *     return result;
 * }
 * </pre>
 */
public final class HashCodeUtil {

    private HashCodeUtil() {
        
    }

    /**
     * An initial value for a <code>hashCode</code>, to which is added
     * contributions from fields. Using a non-zero value decreases collisons of
     * <code>hashCode</code> values.
     */
    public static final int HASH_SEED = 23;

    /**
     * booleans.
     */
    public static int hash(int seed, boolean aBoolean) {
        //System.out.println("boolean...");
        return firstTerm(seed) + (aBoolean ? 1 : 0);
    }

    /**
     * chars.
     */
    public static int hash(int seed, char aChar) {
        //System.out.println("char...");
        return firstTerm(seed) + (int) aChar;
    }

    /**
     * ints.
     */
    public static int hash(int seed, int aInt) {
        /*
         * Implementation Note Note that byte and short are handled by this
         * method, through implicit conversion.
         */
        //System.out.println("int...");
        return firstTerm(seed) + aInt;
    }

    /**
     * longs.
     */
    public static int hash(int seed, long aLong) {
        //System.out.println("long...");
        return firstTerm(seed) + (int) (aLong ^ (aLong >>> 32));
    }

    /**
     * floats.
     */
    public static int hash(int seed, float aFloat) {
        return hash(seed, Float.floatToIntBits(aFloat));
    }

    /**
     * doubles.
     */
    public static int hash(int seed, double aDouble) {
        return hash(seed, Double.doubleToLongBits(aDouble));
    }

    /**
     * <code>aObject</code> is a possibly-null object field, and possibly an
     * array. <p/> If <code>aObject</code> is an array, then each element may
     * be a primitive or a possibly-null object.
     */
    public static int hash(int seed, Object obj) {
        int result = seed;
        if (obj == null) {
            result = hash(result, 0);
        } else if (!isArray(obj)) {
            result = hash(result, obj.hashCode());
        } else {
            int length = Array.getLength(obj);
            for (int idx = 0; idx < length; ++idx) {
                Object item = Array.get(obj, idx);
                // recursive call!
                result = hash(result, item);
            }
        }
        return result;
    }

    // / PRIVATE ///
    private static final int ODD_PRIME_NUMBER = 37;

    private static int firstTerm(int seed) {
        return ODD_PRIME_NUMBER * seed;
    }

    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

}
