package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class RomanNumeralTest {

    @Provide
    Arbitrary<String> validRomanNumeral() {
        return Arbitraries.of("I", "IV", "VIII", "IX", "XIV", "XVI", "XIX", "XCIX", "CV", "MI", "MMCCLXXXIX", "MMMDCCIV");
    }

    @Provide
    Arbitrary<String> invalidRomanNumeral() {
        return Arbitraries.strings().withChars('A', 'Z', 'E', 'R', 'T', 'Y', 'U', 'O', 'P').ofMinLength(1);
    }

    @Property
    boolean testIsValidRomanNumeral1(@ForAll("validRomanNumeral") String validRomanNumber) {
        return RomanNumeraUtils.isValidRomanNumeral(validRomanNumber);
    }

    @Property
    boolean testIsValidRomanNumeral2(@ForAll("invalidRomanNumeral") String invalidRomanNumber) {
        return !RomanNumeraUtils.isValidRomanNumeral(invalidRomanNumber);
    }

    @Property
    boolean testToRomanNumeralIsValidRomanNumeral(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);

        return RomanNumeraUtils.isValidRomanNumeral(romanNumeral);
    }

    @Property
    boolean testToRomanNumeralBackToInt(@ForAll @IntRange(min = 1, max = 3999) int number) {
        String romanNumeral = RomanNumeraUtils.toRomanNumeral(number);

        return number == RomanNumeraUtils.parseRomanNumeral(romanNumeral);
    }

    @Property
    boolean testToRomanNumeralException1(@ForAll @IntRange(min = -4000, max = 0) int number) {
        try {
            RomanNumeraUtils.toRomanNumeral(number);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Property
    boolean testToRomanNumeralException2(@ForAll @IntRange(min = 4000, max = 8000) int number) {
        try {
            RomanNumeraUtils.toRomanNumeral(number);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Property
    boolean testParseRomanNumeralException1(@ForAll("invalidRomanNumeral") String invalidRomanNumber) {
        try {
            RomanNumeraUtils.parseRomanNumeral(invalidRomanNumber);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
