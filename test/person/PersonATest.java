package person;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersonATest {

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
	public void ConstructorTest() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("None Name");
		PersonA pA = new PersonA("");
		
		pA = new PersonA("1");
		PersonA p1 = new PersonA(" 1 ");
		assertEquals(pA.getName(), p1.getName());
	}

	/**
	 * Test addknows
	 * 
	 * Assuming knowsNum is reliable as it only calls {@code size} of {@code Set}.
	 * */
	@Test
	public void addKnowsTest() {
		PersonA p1 = new PersonA("Amy");
		PersonA p2 = new PersonA("Bob");
		
		expectedEx.expect(IllegalArgumentException.class);
		p1.addKnows(p1);
		
		assertEquals(0, p1.knowsNum());
		p1.addKnows(p2);
		assertEquals(1, p1.knowsNum());
	}
	
	/**
	 * Test addknows
	 * 
	 * Assuming knowsNum is reliable as it only calls {@code size} of {@code Set}.
	 * */
	@Test
	public void isKnowsTest() {
		PersonA p1 = new PersonA("Amy");
		PersonA p2 = new PersonA("Bob");
		
		p1.addKnows(p2);
		assertEquals(true, p1.isKnows(p2));
		assertEquals(true, p1.isKnows(new PersonA("Bob")));
	}
	
}



