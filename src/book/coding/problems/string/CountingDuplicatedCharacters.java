package book.coding.problems.string;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.util.Objects.isNull;

/*Write a program that counts duplicate
        characters from a given string.*/
public class CountingDuplicatedCharacters {


    public static void main(String[] args) {

        Map<String, Integer> result = countDuplicatedCharacters("TESTETESTE\uD83D\uDE01");
        Map<String, Long> resultStream = countDuplicatedCharactersStream("TESTETESTE\uD83D\uDE01");
        System.out.println(result);
        System.out.println(resultStream);
    }

    public static Map<String, Integer> countDuplicatedCharacters(String str) {
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            int cp = str.codePointAt(i);
            String chr = valueOf(Character.toChars(cp));

            if (Character.charCount(cp) == 2) { // 2 means a surrogate pair
                i++;
            }
            result.compute(chr, (k, v) -> (isNull(v)) ? 1 : ++v);
        }
        return result;
    }

    public static Map<String, Long> countDuplicatedCharactersStream(String str) {
         return str.codePoints().mapToObj(c -> String.valueOf(Character.toChars(c)) )
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
