package com.patternmatch;

/**
 * A pattern, specified as a string, each character in the pattern is created as
 * chain of PatternMatcher objects and head is the first object in the pattern
 * 
 */
public class Match {
	private PatternMatcher head;
	private String pattern;

	/**
	 * @throws NullPointerException if the pattern is null
	 * @param pattern, the String whose characters are to be created as a chain of
	 *                 character objects
	 */
	public Match(String pattern) {
		if (pattern != "") {
			this.pattern = pattern;
			PatternMatcher current = getPatternMatcher(0);
			head = current;
			for (int i = 1; i < pattern.length(); i++) {
				PatternMatcher next = getPatternMatcher(i);
				current.setNext(next);
				current = next;
			}
		}
	}

	/**
	 * 
	 * @param currentCharacterIndex
	 * @return PatternMatcher object based on type of pattern character
	 */
	private PatternMatcher getPatternMatcher(int currentCharacterIndex) {
		switch (pattern.charAt(currentCharacterIndex)) {
		case '.':
			return new WildCharacterMatcher();
		case '*':
			return new WildSequenceMatcher();
		default:
			return new CharacterMatcher(pattern.charAt(currentCharacterIndex));
		}
	}

	/**
	 * 
	 * @param targetString
	 * @return first index of target string if a match for pattern is found in the
	 *         targetString otherwise -1 (not found)
	 */
	public int findFirstIndex(String targetString) {
		for (int firstIndex = 0; firstIndex < targetString.length(); firstIndex++) {
			if (head.validateCharacter(targetString, firstIndex)) {
				return firstIndex;
			}
		}
		return -1;
	}
}
