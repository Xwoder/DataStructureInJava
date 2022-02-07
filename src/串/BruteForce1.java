package 串;

import Tools.tools.Asserts;

public class BruteForce1 {

    public static void main(String[] args) {
        BruteForce1 bruteForce = new BruteForce1();
        Asserts.test(bruteForce.indexOf("Hello World", "or") == 7);
        Asserts.test(bruteForce.indexOf("Hello World", "abc") == -1);
        Asserts.test(bruteForce.indexOf("or", "Hello World") == -1);
        Asserts.test(bruteForce.indexOf("or", "") == -1);
    }

    public int indexOf(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }

        int textLength = text.length();
        int patternLength = pattern.length();

        if (textLength <= 0 || patternLength <= 0 || patternLength > textLength) {
            return -1;
        }

        int ti = 0;
        int pi = 0;

        while (pi < patternLength && ti - pi <= textLength - patternLength) {
            final char ct = text.charAt(ti);
            final char cp = pattern.charAt(pi);
            if (ct == cp) {
                ++ti;
                ++pi;
            } else {
                ti -= pi - 1;
                pi = 0;
            }
        }

        return (pi < patternLength) ? -1 : ti - pi;
    }
}
