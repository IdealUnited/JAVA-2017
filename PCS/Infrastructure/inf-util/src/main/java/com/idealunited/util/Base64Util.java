package com.idealunited.util;

import org.apache.commons.codec.binary.Base64;

/**
 * This class acts as a wrapper for commons-codec <code>Base64</code> to
 * provide Base64 encoding and decoding as defined by RFC 2045.
 * 
 * 
 */
public final class Base64Util {

    /**
     * Make it private to prevent initialization from calling constructor.
     * 
     */
    private Base64Util() {
    }

    /**
     * Encodes binary data (hex octets) using the base64 algorithm but does not
     * chunk the output.
     * 
     * @param binaryData
     *            binary data to encode
     * @return Base64 encoded data
     */
    public static byte[] encode(final byte[] binaryData) {
        return Base64.encodeBase64(binaryData);
    }

    /**
     * Encodes binary data using the base64 algorithm, optionally chunking the
     * output into 76 character blocks.
     * 
     * @param binaryData
     *            binary data to encode
     * @param isChunked
     *            if isChunked is true this encoder will chunk the base64 output
     *            into 76 character blocks
     * @return Base64 encoded data
     */
    public static byte[] encode(final byte[] binaryData, 
                                final boolean isChunked) {
        return Base64.encodeBase64(binaryData, isChunked);
    }

    /**
     * Decodes Base64 data into binary data (hex octets).
     * 
     * @param base64Data
     *            Base64 data
     * @return Array containing decoded data
     */
    public static byte[] decode(final byte[] base64Data) {
        return Base64.decodeBase64(base64Data);
    }

    /**
     * Tests a given byte array to see if it contains only valid base64
     * characters.
     * 
     * @param data
     *            byte array to test
     * @return true if all bytes are valid base64 characters or if the byte
     *         array is empty; false, otherwise
     */
    public static boolean isArrayByteBase64(final byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return Base64.isArrayByteBase64(data);
    }

    /**
     * Tests a give string to see if it is a valid base64 encoded string.
     * 
     * @param base64String
     *            string to test
     * @return true if string is valid base64 encoded or if string is empty;
     *         false, otherwise
     */
    public static boolean isStringBase64(final String base64String) {
        if (base64String == null) {
            throw new IllegalArgumentException();
        }        
        return Base64.isArrayByteBase64(base64String.getBytes());
    }
}
