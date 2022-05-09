package person;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


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
	
	/**
	 * Test equals
	 * */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		PersonB personA = new PersonB("Mike");
		PersonB personA2 = new PersonB("Mike");
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
		PersonB mike = new PersonB("Mike");
		PersonB mike2 = new PersonB("Mike");

		/* test contains method */
		Set<PersonB> group = new HashSet<>();
		group.add(mike);
		assertEquals(true, group.contains(mike2));
		
		/* test containsKey */
		Map<PersonB, String> dict = new HashMap<>();
		dict.put(mike, "Mike");
		assertEquals(true, dict.containsKey(mike2));
	}
}
