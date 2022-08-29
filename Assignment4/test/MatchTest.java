import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.patternmatch.Match;

public class MatchTest {
	@Test
	public void wildSequenceAtBeginingOfPatternTest() {
		Match wildSequenceMatch = new Match("*bat");
		assertEquals(-1, wildSequenceMatch.findFirstIndex("ababacat"));
		assertEquals(0, wildSequenceMatch.findFirstIndex("atcbabat"));
		wildSequenceMatch = new Match("*");
		assertEquals(0, wildSequenceMatch.findFirstIndex("a"));
		assertEquals(0, wildSequenceMatch.findFirstIndex("abcdefgh"));
	}

	@Test
	public void wildSequenceAtEndingOfPatternTest() {
		Match wildSequenceMatch = new Match("bat*");
		assertEquals(-1, wildSequenceMatch.findFirstIndex("ababacat"));
		assertEquals(3, wildSequenceMatch.findFirstIndex("ababat"));
		assertEquals(3, wildSequenceMatch.findFirstIndex("ababats"));
	}

	@Test
	public void wildSequenceAtMiddleOfPatternTest() {
		Match wildSequenceMatch = new Match("ma*t");
		assertEquals(-1, wildSequenceMatch.findFirstIndex("ababacat"));
		wildSequenceMatch = new Match("b*at");
		assertEquals(1, wildSequenceMatch.findFirstIndex("ababaat"));
		wildSequenceMatch = new Match("c*at");
		assertEquals(4, wildSequenceMatch.findFirstIndex("abkacbcat"));
	}

	@Test
	public void wildSequenceWithZeroCharacterTest() {
		Match wildSequenceMatch = new Match("m*t");
		assertEquals(-1, wildSequenceMatch.findFirstIndex("m"));
		assertEquals(5, wildSequenceMatch.findFirstIndex("abkacmt"));
		assertEquals(0, wildSequenceMatch.findFirstIndex("mt"));
	}

	@Test
	public void wildCharacterAtBeginingOfPatternTest() {
		Match wildCharacterMatch = new Match(".bat");
		assertEquals(-1, wildCharacterMatch.findFirstIndex("ababacat"));
		assertEquals(0, wildCharacterMatch.findFirstIndex("abatvbat"));
	}

	@Test
	public void wildCharacterAtEndingOfPatternTest() {
		Match wildCharacterMatch = new Match("bat.");
		assertEquals(-1, wildCharacterMatch.findFirstIndex("ababacat"));
		assertEquals(-1, wildCharacterMatch.findFirstIndex("ababat"));
		assertEquals(3, wildCharacterMatch.findFirstIndex("ababats"));
	}

	@Test
	public void wildCharacterAtMiddleOfPatternTest() {
		Match wildCharacterMatch = new Match("ma.t");
		assertEquals(-1, wildCharacterMatch.findFirstIndex("ababacat"));
		wildCharacterMatch = new Match("c.t");
		assertEquals(4, wildCharacterMatch.findFirstIndex("cacacat"));
		assertEquals(-1, wildCharacterMatch.findFirstIndex("cacact"));
		wildCharacterMatch = new Match(".");
		assertEquals(0, wildCharacterMatch.findFirstIndex("a"));
		wildCharacterMatch = new Match("c.*t");
		assertEquals(3, wildCharacterMatch.findFirstIndex("bobcat"));
		assertEquals(-1, wildCharacterMatch.findFirstIndex("bobct"));
		assertEquals(3, wildCharacterMatch.findFirstIndex("bobctat"));
	}

	@Test
	public void characterTest() {
		Match literalMatch = new Match("abc");
		assertEquals(7, literalMatch.findFirstIndex("bababababc"));
		assertEquals(0, literalMatch.findFirstIndex("abc"));
		assertEquals(-1, literalMatch.findFirstIndex("cb"));
	}

	@Test
	public void emptyTargetStringTest() {
		Match patternWithEmptyStringMatch = new Match(".");
		assertEquals(-1, patternWithEmptyStringMatch.findFirstIndex(""));
		patternWithEmptyStringMatch = new Match("a");
		assertEquals(-1, patternWithEmptyStringMatch.findFirstIndex(""));
		patternWithEmptyStringMatch = new Match("*");
		assertEquals(-1, patternWithEmptyStringMatch.findFirstIndex(""));
	}

	@Test(expected = NullPointerException.class)
	public void emptyPatternTest() {
		Match patternWithEmptyStringMatch = new Match(null);
		assertEquals(-1, patternWithEmptyStringMatch.findFirstIndex(""));
	}
}
