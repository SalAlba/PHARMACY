package com.pharmacy.web.converter;

public class AccountNumberConverter {
	private final int WIDTH_OF_FIRST_GROUP = 2, WIDTH_OF_REMAINING_GROUP = 4;

	private String makeSpacesBetweenGroups(String value) {
		if (value.length() < 2)
			return value;

		return (makeFirstGroup(value) + makeRemainingGroups(value)).trim();
	}

	private String makeFirstGroup(String value) {
		return value.substring(0, WIDTH_OF_FIRST_GROUP) + " ";
	}

	private String makeRemainingGroups(String value) {
		String valueWithSpaces = "";
		int positionInString = WIDTH_OF_FIRST_GROUP;

		for (; value.length() >= positionInString
				+ WIDTH_OF_REMAINING_GROUP; positionInString += WIDTH_OF_REMAINING_GROUP) {
			valueWithSpaces += value.substring(positionInString, positionInString + WIDTH_OF_REMAINING_GROUP) + " ";
		}

		if (value.length() > positionInString)
			valueWithSpaces += value.substring(positionInString);

		return valueWithSpaces;
	}

	public String convertToString(String value) {

		if (value.equals(""))
			return value;

		String valueWithoutSpaces = value.replace(" ", "");

		try {
			Long.parseLong(valueWithoutSpaces);
		} catch (NumberFormatException ex) {
			throw ex;
		}

		return valueWithoutSpaces;
	}

	public String convertToFriendlyString(String value) {
		return makeSpacesBetweenGroups(value);
	}

}