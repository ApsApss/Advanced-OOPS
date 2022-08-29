package com.patternmatch;

/**
 * 
 * This class represents a sequence of wild characters (*) and it matches with 0
 * or many characters
 */
public class WildSequenceMatcher implements PatternMatcher {
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
	 * @return true when there exists 0 or many characters in the targetString at
	 *         the given position and current handler is at end of the chain, and
	 *         also true when it searches for the next sub pattern (pattern after *
	 *         character) by iterating till the end of the substring from the
	 *         wildCharPosition by passing request to the next handler, if it
	 *         doesn't find the sub pattern match in the target string then it
	 *         returns false
	 */
	@Override
	public boolean validateCharacter(String targetString, int wildCharPosition) {
		boolean isValidated = true;
		if (next != null) {
			isValidated = false;
			for (int currentIndex = wildCharPosition; currentIndex < targetString.length(); currentIndex++) {
				if (next.validateCharacter(targetString, currentIndex)) {
					isValidated = true;
				}
			}
		}
		return isValidated;
	}
}
