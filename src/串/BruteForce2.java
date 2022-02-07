package 串;

import Tools.tools.Asserts;

public class BruteForce2 {

    public static void main(String[] args) {
        BruteForce2 bruteForce = new BruteForce2();
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
        int tiUpLimit = textLength - patternLength;

        for (; ti <= tiUpLimit; ti++) {
            int pi = 0;
            for (; pi < patternLength; pi++) {
                final char ct = text.charAt(ti + pi);
                final char cp = pattern.charAt(pi);
                if (ct != cp) {
                    break;
                }
            }

            if (pi >= patternLength) {
                return ti;
            }

        }

        return -1;
    }
}
