package person;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import person.PersonB;

public class PersonBTest {

	/**
	 * Test that assertions are enabled
	 * */
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	/**
	 * Test constructor
	 * */
	@Test
	public void testConstructor() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("None Name");
		PersonB pB = new PersonB("");
		
		PersonB p1 = new PersonB("1");
		pB = new PersonB(" 1 ");
		assertEquals(p1.getName(), pB.getName());
	}
}
