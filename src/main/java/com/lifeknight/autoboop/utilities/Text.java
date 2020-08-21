package com.lifeknight.autoboop.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text {
    public static List<String> returnStartingEntries(String[] strings, String input, boolean ignoreCase) {
        if (input == null || input.isEmpty()) return Arrays.asList(strings);
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            if (ignoreCase) {
                if (string.toLowerCase().startsWith(input.toLowerCase())) result.add(string);
            } else {
                if (string.startsWith(input)) result.add(string);
            }
        }
        return result;
    }

    public static String formatCapitalization(String input, boolean keepFirstCapitalized) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = input.length() - 1; i > 0; i--) {
            char toInsert;
            char previousChar = input.charAt(i - 1);
            if (previousChar == Character.toUpperCase(previousChar)) {
                toInsert = Character.toLowerCase(input.charAt(i));
            } else {
                toInsert = input.charAt(i);
            }
            stringBuilder.insert(0, toInsert);
        }

        return stringBuilder.insert(0, keepFirstCapitalized ? input.charAt(0) : Character.toLowerCase(input.charAt(0))).toString();
    }

	public static String parseTextToIndexOfTextAfter(String text, String firstIndexText, String secondIndexText) {
        if (text.contains(firstIndexText) && text.contains(secondIndexText)) {
			return text.substring((firstIndexText.indexOf(firstIndexText) + firstIndexText.length() + 1), (text.indexOf(secondIndexText) - 1));
        }
        return null;
	}
}
