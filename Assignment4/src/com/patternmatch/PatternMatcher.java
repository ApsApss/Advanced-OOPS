package com.patternmatch;

public interface PatternMatcher {
	public void setNext(PatternMatcher next);

	public boolean validateCharacter(String targetString, int expectedCharacterIndex);
}
