package book.coding.problems.string;

import edu.princeton.cs.algs4.In;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class FirstNonRepeatedCharacter {

    private static final int EXTENDED_ASCII_CODES = 256;

    /*Write a program that returns the
    first non-repeated character from a given string.*/
    public static void main(String[] args) {

        char result = firstNonRepeatedCharacter("TTTTEAXAA");

        char resultMap = firstNonRepeatedCharacterLinkedHashMap("TTTTEAXAA");

        System.out.println(valueOf(result));
        System.out.println(valueOf(resultMap));
    }

    public static char firstNonRepeatedCharacter(String str) {
        int[] flags = new int[EXTENDED_ASCII_CODES];

        for (int i = 0; i < flags.length; i++) {
            flags[i] = -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (flags[ch] == -1) {
                flags[ch] = i;
            } else {
                flags[ch] = -2;
            }
        }

        int position = Integer.MAX_VALUE;

        for (int i = 0; i < EXTENDED_ASCII_CODES; i++) {
            if (flags[i] >= 0) {
                position = Math.min(position, flags[i]);
            }
        }

        return position == Integer.MAX_VALUE ? Character.MIN_VALUE : str.charAt(position);
    }

    public static char firstNonRepeatedCharacterLinkedHashMap(String str) {
        Map<Character, Integer> result = new LinkedHashMap<>();

        for (Character ch: str.toCharArray()) {
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }

        for (Map.Entry<Character, Integer> entry: result.entrySet()) {
            if (entry.getValue() == 1) {
               return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }
}
