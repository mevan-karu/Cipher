package com.cipher;

public class Decryptor {

    public static String decrypt(String text, Integer[] key) {
        return resubstitute(repermutate(text, key));
    }

    private static String resubstitute(String text) {
        String resubstitutedText = "";
        for (int i = 0; i < text.length(); i++) {
            int asciiVal = (int) text.charAt(i) - (text.length() - i);
            resubstitutedText += (Character.toString((char) asciiVal));
        }
        return resubstitutedText;
    }

    public static String repermutate(String text, Integer[] key) {
        String repermutatedText = "";
        int keyLength = key.length;
        while (text.length() > 0) {
            String repermutateText = "";
            if ((text.length() / keyLength) > 0) {
                String tempStr = (String) text.subSequence(0, keyLength);
                for (int m = 0; m < keyLength; m++) {
                    for (int k = 0; k < keyLength; k++) {
                        if (m == key[k]) {
                            repermutateText += tempStr.charAt(k);
                        }
                    }
                }
                text = text.substring(keyLength);
            } else {
                for (int m = 0; m < text.length(); m++) {
                    for (int k = 0; k < keyLength; k++) {
                        if ((key[k] < text.length()) && (m == key[k])) {
                            repermutateText += text.charAt(k);
                        }
                    }
                }
                text = "";
            }
            repermutatedText += repermutateText;
        }
        return repermutatedText;
    }

}
