package com.cipher;

public class Encryptor {

    public static String encrypt(String text, Integer[] key) {
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

    public static String permutate(String text, Integer[] key) {
        String permutatedText = "";
        int keyLength = key.length;
        while (text.length() > 0) {
            String permutateText = "";
            if ((text.length() / keyLength) > 0) {
                String tempStr = (String) text.subSequence(0, keyLength);
                for (int i = 0; i < keyLength; i++) {
                    permutateText += tempStr.charAt(key[i]);
                }
                text = text.substring(keyLength);
            } else {
                int j = 0;
                while (j < keyLength) {
                    if (key[j] < text.length()) {
                        permutateText += text.charAt(key[j]);
                    }
                    j++;
                }
                text = "";
            }
            permutatedText += permutateText;
        }
        return permutatedText;
    }
}
