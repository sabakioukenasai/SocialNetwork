package person;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
	/**
	 * Test equals
	 * */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		PersonA personA = new PersonA("Mike");
		PersonA personA2 = new PersonA("Mike");
		String mikeString = new String("Mike");

		/* Null obj case */
		assertEquals(false, personA.equals(null));
		
		/* not a PersonA case */
		assertEquals(false, personA.equals(mikeString));
		
		/* different name case */
		assertEquals(false, personA.equals(new PersonA("Bob")));

		/* itself case */
		assertEquals(true, personA.equals(personA));
		
		/* same name case */
		assertEquals(true, personA.equals(personA2));
	}
	
	/**
	 * Test contains of Set
	 * 
	 * <p>Test whether we can use the {@code contains} method of the {@code Set}  
	 * to determine whether an instance of {@code PersonA} is in the set, and the
	 * {@code containsKey} method of the {@code HashMap} to determine whether an 
	 * instance of {@code PersonA} is in the key set.  </p>
	 * */
	@Test
	public void useContainsTest() {
		PersonA mike = new PersonA("Mike");
		PersonA mike2 = new PersonA("Mike");

		/* test contains method */
		Set<PersonA> group = new HashSet<>();
		group.add(mike);
		assertEquals(true, group.contains(mike2));
		
		/* test containsKey */
		Map<PersonA, String> dict = new HashMap<>();
		dict.put(mike, "Mike");
		assertEquals(true, dict.containsKey(mike2));
	}
}



