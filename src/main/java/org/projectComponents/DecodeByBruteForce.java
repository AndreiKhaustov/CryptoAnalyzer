package org.projectComponents;

import java.util.*;

public class DecodeByBruteForce {
    public static List<Character> charsForDecoding;
    public static List<Character> charsOfComparingText;
    public static Map<Character, Integer> StatisticDataOfTextComparing;
    public static Map<Character, Integer> StatisticDataOfMainText;
    public static List<Character> decodingChars;
    public static List<String> decodingLines;
    public static int searchKey;
    public static final List<Character> littleAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static final List<Character> bigAlphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    public static final List<Character> commonAlphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    public static List<String> startBruteForce(List<String> text, List<String> textForComparing) {
        charsOfComparingText = Decode.toCharArray(textForComparing);
        StatisticDataOfTextComparing = getStatisticData(charsOfComparingText);
        charsForDecoding = Decode.toCharArray(text);
        StatisticDataOfMainText = getStatisticData(charsForDecoding);
        searchKey = searchingKey(StatisticDataOfMainText, StatisticDataOfTextComparing);
        decodingChars = Decode.decoding(charsForDecoding, searchKey);
        decodingLines = Decode.decodingLines(decodingChars,text);
        return decodingLines;
    }



    public static Map<Character, Integer> getStatisticData(List<Character> list) {
        Map<Character, Integer> statistic = new HashMap<>();
        Map<Character, Integer> percentStatistic = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < littleAlphabet.size(); i++) {
            statistic.put(littleAlphabet.get(i), 0);
        }
        for (int i = 0; i < bigAlphabet.size(); i++) {
            statistic.put(bigAlphabet.get(i), 0);
        }
        for (int i = 0; i < list.size(); i++) {
            for (Map.Entry<Character, Integer> charFromAlphabet : statistic.entrySet()) {
                if (list.get(i).equals(charFromAlphabet.getKey())) {
                    Integer number = charFromAlphabet.getValue();
                    number++;
                    statistic.put(charFromAlphabet.getKey(), number);
                }
            }

        }
        for (Map.Entry<Character, Integer> value : statistic.entrySet()) {
            sum = sum + value.getValue();
        }
        for (Map.Entry<Character, Integer> value : statistic.entrySet()) {
            double abc = (value.getValue()*100)/sum;
            int a = (int) abc;
            percentStatistic.put(value.getKey(), a);
        }
        return statistic;
    }

    public static int searchingKey(Map<Character, Integer> mainTextStatistic, Map<Character, Integer> comparingTextStatistic) {
        List<Integer> possibleKeys = new ArrayList<>();
        List<Integer> repeatOfKeys = new ArrayList<>();
        int mainCharacter = 0;
        int comparingCharacter = 0;
        int key = 0;
        Character mainChar = null;
        Character comparingChar = null;
        int indexComparing = 0;
        int indexMain = 0;
        for(Map.Entry<Character, Integer> value1 : mainTextStatistic.entrySet()){
            if(value1.getValue()>mainCharacter){
                mainCharacter = value1.getValue();
                mainChar = value1.getKey();
            }
        }
        for(Map.Entry<Character, Integer> value : comparingTextStatistic.entrySet()){
            if(value.getValue()>comparingCharacter){
                comparingCharacter = value.getValue();
                comparingChar = value.getKey();
            }
        }
        for (int i = 0; i < littleAlphabet.size(); i++) {
            if(comparingChar.equals(littleAlphabet.get(i))){
                indexComparing = i;
            }
            else if(comparingChar.equals(bigAlphabet.get(i))){
                indexComparing = i;
            }
        }
        for (int i = 0; i < littleAlphabet.size(); i++) {
            if(mainChar.equals(littleAlphabet.get(i))){
                indexMain = i;
            }
            else if(mainChar.equals(bigAlphabet.get(i))){
                indexMain = i;
            }
        }
        int probablyKey = indexComparing-indexMain+1;
        key = littleAlphabet.size()+probablyKey;


        return key;
    }


}
