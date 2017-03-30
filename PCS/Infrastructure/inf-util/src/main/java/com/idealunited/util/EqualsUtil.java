package com.idealunited.util;

/**
 * Collected methods which allow easy implementation of
 * <code>equals</code>. The implementation is based on well-known <i>Effective Java</i>.
 * It is required to override <em>hashCode</em> method 
 * when override <em>equals</em>.
 * 
 *
 */
public final class EqualsUtil {
    // equals
    private EqualsUtil() {

    }

    /**
     * boolean
     */
    static public boolean areEqual(boolean aThis, boolean aThat) {
        // System.out.println("boolean");
        return aThis == aThat;
    }

    /**
     * char
     */
    static public boolean areEqual(char aThis, char aThat) {
        // System.out.println("char");
        return aThis == aThat;
    }

    /**
     * long (including byte, short, and int)
     */
    static public boolean areEqual(long aThis, long aThat) {
        /*
         * Implementation Note Note that byte, short, and int are handled by
         * this method, through implicit conversion.
         */
        // System.out.println("long");
        return aThis == aThat;
    }

    /**
     * float
     */
    static public boolean areEqual(float aThis, float aThat) {
        // System.out.println("float");
        return Float.floatToIntBits(aThis) == Float.floatToIntBits(aThat);
    }

    /**
     * double
     */
    static public boolean areEqual(double aThis, double aThat) {
        // System.out.println("double");
        return Double.doubleToLongBits(aThis) == Double.doubleToLongBits(aThat);
    }

    /**
     * Possibly-null object field. <p/> Includes type-safe enumerations and
     * collections, but does not include arrays. 
     */
    static public boolean areEqual(Object aThis, Object aThat) {
        // System.out.println("Object");
        return aThis == null ? aThat == null : aThis.equals(aThat);
    }

    /**
     * string
     */
    static public boolean areEqual(String aThis, String aThat) {
        // System.out.println("String");
        aThis = trim(aThis);
        aThat = trim(aThat);
        return aThis == null ? aThat == null : aThis.equals(aThat);
    }

    /**
     * to trim string
     */
    static private String trim(String s) {
        if (s == null) {
            return null;
        }

        return s.trim();
    }
}
