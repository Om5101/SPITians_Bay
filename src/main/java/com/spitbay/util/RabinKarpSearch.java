package com.spitbay.util;

public class RabinKarpSearch {
    private static final int PRIME = 101; // A prime number for hash calculation

    public static boolean search(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return false;
        }

        int patternLength = pattern.length();
        int textLength = text.length();
        long patternHash = calculateHash(pattern, patternLength);
        long textHash = calculateHash(text, patternLength);

        // Check for match at the beginning
        if (patternHash == textHash && checkEqual(text, 0, pattern)) {
            return true;
        }

        // Calculate hash for remaining text
        for (int i = patternLength; i < textLength; i++) {
            textHash = recalculateHash(text, i - patternLength, i, textHash, patternLength);
            if (patternHash == textHash && checkEqual(text, i - patternLength + 1, pattern)) {
                return true;
            }
        }

        return false;
    }

    private static long calculateHash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long recalculateHash(String str, int oldIndex, int newIndex, long oldHash, int patternLength) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }

    private static boolean checkEqual(String text, int start, String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (text.charAt(start + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
} 