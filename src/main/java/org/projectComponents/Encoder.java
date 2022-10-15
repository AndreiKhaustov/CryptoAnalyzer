package org.projectComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Encoder {

    public static final List<Character> littleAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> bigAlphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    public static String encodeText(String srcText, int key) {

        List<Character> convertedChars = encodeChars(srcText, key);
        String convertedText = convertToText(convertedChars, srcText);

        return convertedText;
    }

    public static String decodeText(String srcText, int key) {

        List<Character> convertedChars = encodeChars(srcText, key * -1);
        String convertedText = convertToText(convertedChars, srcText);

        return convertedText;
    }

    public static List<Character> encodeChars(String srcText, int key) {
        List<Character> encodedChars = new ArrayList<>();

        for (int i = 0; i < srcText.length(); i++) {
            char oneSymbol = srcText.charAt(i);
            if (Character.isAlphabetic(oneSymbol)) {
                for (int j = 0; j < littleAlphabet.size(); j++) {
                    if (srcText.charAt(i) == littleAlphabet.get(j)) {
                        Collections.rotate(littleAlphabet, key);
                        encodedChars.add(littleAlphabet.get(j));
                        break;
                    } else if (srcText.charAt(i) == bigAlphabet.get(j)) {
                        Collections.rotate(bigAlphabet, key);
                        encodedChars.add(bigAlphabet.get(j));
                        break;
                    }
                }
            } else {
                encodedChars.add(srcText.charAt(i));
            }
        }
        return encodedChars;
    }

    public static String convertToText(List<Character> convertedChars, String srcText) {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < convertedChars.size(); i++) {
            encodedText.append(convertedChars.get(i));
        }
        return encodedText.toString();
    }
}
