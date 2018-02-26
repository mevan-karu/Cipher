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

    private static String repermutate(String text, Integer[] key) {
        String repermutatedText = "";
        while (text.length() > 0) {
            String repermutateText = "";
            String tempStr = (String) text.subSequence(0, 4);

            for (int m = 0; m < 4; m++) {
                for (int k = 0; k < key.length; k++) {
                    if (m == key[k]) {
                        repermutateText += tempStr.charAt(k);
                    }
                }
            }
            repermutatedText += repermutateText;
            text = text.substring(4);
        }
        return repermutatedText;
    }

}
