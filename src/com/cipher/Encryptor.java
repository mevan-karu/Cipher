package com.cipher;

public class Encryptor {

    public static String encrypt(String text, int[] key) {
        return permutate(substitute(text), key);
    }

    private static String substitute(String text) {
        String substitutedText = "";
        for (int i = 0; i < text.length(); i++) {
            int asciiVal = (int) text.charAt(i) + (text.length() - i);
            substitutedText += Character.toString((char) asciiVal);
        }
        return substitutedText;
    }

    private static String permutate(String text, int[] key) {
        String permutatedText = "";
        while (text.length() > 0) {
            String permutatedResult = "";
            String tmpStr = (String) text.subSequence(0, key.length);
            for (int i = 0; i < key.length; i++) {
                permutatedResult += tmpStr.charAt(key[i]);
            }
            permutatedText += permutatedResult;
            text = text.substring(key.length);

        }
        return permutatedText;
    }
}
