package com.lifeknight.autoboop.utilities;

import net.minecraft.util.EnumChatFormatting;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Text {
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
