package org.projectComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Encode {

    public static List<Character> charsForEncoding;
    public static List<Character> encodingChars;
    public static List<String> encodingLines;
    public static final List<Character> littleAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> bigAlphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    public static List <String> textEncode(List<String> list, int key) {
        charsForEncoding = toCharArray(list);
        encodingChars = encoding(charsForEncoding, key);
        encodingLines = encodingLines(encodingChars, list);

return encodingLines;
    }
    public static List<Character> toCharArray(List<String> list) {
        List<Character> charArray = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            for (int j = 0; j < line.length(); j++) {
                charArray.add(line.charAt(j));
            }
        }
        return charArray;
    }
    public static List<Character> encoding(List<Character> list, int key) {
        int readyKey = key-1;
        List<Character> encodingText = new ArrayList<>();
        List<Character> withKeyLittleAlphabet = littleAlphabet;
        List<Character> withKeyBigAlphabet = bigAlphabet;
        for (int i = 0; i < list.size(); i++) {
            char oneSymbol = list.get(i);
            if(Character.isAlphabetic(oneSymbol)) {
                for (int j = 0; j < littleAlphabet.size(); j++) {
                    if (list.get(i) == littleAlphabet.get(j)) {
                        Collections.rotate(withKeyLittleAlphabet, readyKey);
                        encodingText.add(withKeyLittleAlphabet.get(j));
                        break;
                    } else if (list.get(i) == bigAlphabet.get(j)) {
                        Collections.rotate(withKeyBigAlphabet, readyKey);
                        encodingText.add(withKeyBigAlphabet.get(j));
                        break;
                    }

                }
            }
            else {
                encodingText.add(list.get(i));
            }
        }
        return encodingText;
    }
    public static List<String> encodingLines(List<Character> encodingChars, List<String> firstText) {
        List<String> encodingText = new ArrayList<>();
        StringBuilder line = new StringBuilder(0);
        int count = 0;
        for (int i = 0; i < firstText.size(); i++) {
            String textLine = firstText.get(i);
            for (int j = 0; j < textLine.length(); j++) {
                line.append(encodingChars.get(count));
                count++;
            }
            encodingText.add(line.toString());
            line.setLength(0);
        }
      return encodingText;
    }
}
