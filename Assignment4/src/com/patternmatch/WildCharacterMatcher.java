package com.patternmatch;

/**
 * 
 * This class represents single wild character type(.)
 */
public class WildCharacterMatcher implements PatternMatcher {
	private PatternMatcher next;

	/**
	 * sets next object in the pattern chain
	 * 
	 * @param next
	 */
	@Override
	public void setNext(PatternMatcher next) {
		this.next = next;
	}

	/**
	 * @param targetString
	 * @param wildCharPosition
	 * @return true when the wildCharPosition is less than targetString length which
	 *         means that there exists a character in the provided index of
	 *         targetString and also true when the next object in the chain exists
	 *         and the request is handled by it's handler successfully otherwise
	 *         false
	 */
	@Override
	public boolean validateCharacter(String targetString, int wildCharPosition) {
		boolean isValidated = wildCharPosition < targetString.length();
		if (next != null) {
			isValidated = isValidated && next.validateCharacter(targetString, wildCharPosition + 1);
		}
		return isValidated;
	}
}
