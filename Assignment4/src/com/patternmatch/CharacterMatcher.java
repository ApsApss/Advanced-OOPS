package com.patternmatch;

/**
 * 
 * This class represents a alphabetic or any character other than . or *
 */
public class CharacterMatcher implements PatternMatcher {
	private PatternMatcher next;
	private char expectedCharacter;

	/**
	 * 
	 * @param expectedCharacter, is a character in pattern to be matched with the
	 *                           targetString character
	 */
	CharacterMatcher(char expectedCharacter) {
		this.expectedCharacter = expectedCharacter;
	}

	/**
	 * sets next object (character in pattern) in the pattern chain
	 * 
	 * @param next
	 */
	public void setNext(PatternMatcher next) {
		this.next = next;
	}

	/**
	 * @param targetString
	 * @param currentCharacterIndex
	 * @return true if there is expected character at the currentCharacterIndex
	 *         position in the targetString and next object in the chain doesn't
	 *         exists and also returns true if the next object in the chain exists
	 *         and the request is successfully handled by the next object handler in
	 *         the chain, otherwise false
	 */
	public boolean validateCharacter(String targetString, int currentCharacterIndex) {
		boolean isValidated = currentCharacterIndex < targetString.length()
				&& targetString.charAt(currentCharacterIndex) == expectedCharacter;
		if (next != null) {
			isValidated = isValidated && next.validateCharacter(targetString, currentCharacterIndex + 1);
		}
		return isValidated;
	}
}
