package fr.istic.vv;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeraUtils {

    private final static Map<String, Integer> allowedRomanValues = new LinkedHashMap<>() {{
                                                                    put("M", 1000);
                                                                    put("CM", 900);
                                                                    put("D", 500);
                                                                    put("CD", 400);
                                                                    put("C", 100);
                                                                    put("XC", 90);
                                                                    put("L", 50);
                                                                    put("XL", 40);
                                                                    put("X", 10);
                                                                    put("IX", 9);
                                                                    put("V", 5);
                                                                    put("IV", 4);
                                                                    put("I", 1);
                                                                }};
    public static boolean isValidRomanNumeral(String value) {
        return value.matches("^M{0,3}(CM|D|CD)?C{0,3}(XC|L|XL)?X{0,3}(IX|V|IV)?I{0,3}$");
    }

    public static int parseRomanNumeral(String numeral) {
        if (!isValidRomanNumeral(numeral)) throw new IllegalArgumentException();

        int res = 0;
        String currentKey = "";

        for (int i = 0; i < numeral.length(); i++) {
            if (numeral.charAt(i) == 'I') {
                currentKey = String.valueOf(numeral.charAt(i));
                if (i == numeral.length() - 1) {
                    res += allowedRomanValues.get(currentKey);
                } else if (numeral.charAt(i + 1) != 'V' && numeral.charAt(i + 1) != 'X') {
                    res += allowedRomanValues.get(currentKey);
                    currentKey = "";
                }
            } else if (numeral.charAt(i) == 'X') {
                if (!currentKey.equals("")) { // This if statement was missing before (testToRomanNumeralBackToInt)
                    currentKey += String.valueOf(numeral.charAt(i));
                    res += allowedRomanValues.get(currentKey);
                    currentKey = "";
                } else {
                    currentKey = String.valueOf(numeral.charAt(i));
                    if (i == numeral.length() - 1) {
                        res += allowedRomanValues.get(currentKey);
                    } else if (numeral.charAt(i + 1) != 'L' && numeral.charAt(i + 1) != 'C') {
                        res += allowedRomanValues.get(currentKey);
                        currentKey = "";
                    }
                }
            } else if (numeral.charAt(i) == 'C') {
                if (!currentKey.equals("")) { // This if statement was missing before (testToRomanNumeralBackToInt)
                    currentKey += String.valueOf(numeral.charAt(i));
                    res += allowedRomanValues.get(currentKey);
                    currentKey = "";
                } else {
                    currentKey = String.valueOf(numeral.charAt(i));
                    if (i == numeral.length() - 1) {
                        res += allowedRomanValues.get(currentKey);
                    } else if (numeral.charAt(i + 1) != 'D' && numeral.charAt(i + 1) != 'M') {
                        res += allowedRomanValues.get(currentKey);
                        currentKey = "";
                    }
                }
            } else {
                currentKey += String.valueOf(numeral.charAt(i));
                res += allowedRomanValues.get(currentKey);
                currentKey = ""; // This line was missing causing a NullPointerException (testToRomanNumeralBackToInt)
            }
        }

        return res;
    }

    public static String toRomanNumeral(int number) {
        if (number <= 0 || number >= 4000) throw new IllegalArgumentException();

        String res = "";

        for (String romanNumber : allowedRomanValues.keySet()) {
            while (number >= allowedRomanValues.get(romanNumber)) { // Was '>' instead of '>=' (testToRomanNumeralBackToInt)
                res += romanNumber;
                number -= allowedRomanValues.get(romanNumber);
            }
        }

        return res;
    }

}
