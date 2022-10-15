package org.projectComponents;

import java.util.*;

public class KeyFinder {

    public static final List<Character> littleAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> bigAlphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    public static int findKey(String srcText,String referenceText) {
        Map<Character,Integer> statOfCompText = getStatisticData(referenceText);
        Map<Character,Integer> statOfSrcText = getStatisticData(srcText);

        return findKey(statOfSrcText, statOfCompText);
    }


    public static Map<Character, Integer> getStatisticData(String text) {
        Map<Character, Integer> statistic = new HashMap<>();
        for (Character character : littleAlphabet) {
            statistic.put(character, 0);
        }
        for (Character character : bigAlphabet) {
            statistic.put(character, 0);
        }
        for (int i = 0; i < text.length(); i++) {
            if(Character.isAlphabetic(text.charAt(i))) {
                statistic.merge(text.charAt(i), 1, (oldValue, newValue) -> oldValue + newValue);
            }
        }
        return statistic;
    }

    public static int findKey(Map<Character, Integer> statOfSrcText, Map<Character, Integer> statOfCompText) {

        int countOfSrcChar = 0;
        int countOfCompChar = 0;
        Character srcChar = null;
        Character compChar = null;
        int indexOfCompChar = 0;
        int indexOfsrcChar = 0;
        for (Map.Entry<Character, Integer> value1 : statOfSrcText.entrySet()) {
            if (value1.getValue() > countOfSrcChar) {
                countOfSrcChar = value1.getValue();
                srcChar = value1.getKey();
            }
        }
        for (Map.Entry<Character, Integer> value : statOfCompText.entrySet()) {
            if (value.getValue() > countOfCompChar) {
                countOfCompChar = value.getValue();
                compChar = value.getKey();
            }
        }
        for (int i = 0; i < littleAlphabet.size(); i++) {
            if (compChar.equals(littleAlphabet.get(i))) {
                indexOfCompChar = i;
            } else if (compChar.equals(bigAlphabet.get(i))) {
                indexOfCompChar = i;
            }
        }
        for (int i = 0; i < littleAlphabet.size(); i++) {
            if (srcChar.equals(littleAlphabet.get(i))) {
                indexOfsrcChar = i;
            } else if (srcChar.equals(bigAlphabet.get(i))) {
                indexOfsrcChar = i;
            }
        }
       int key = littleAlphabet.size()-indexOfsrcChar+indexOfCompChar;
        return key;
    }

}
