package person;

import java.util.HashSet;
import java.util.Set;

/**
 * {@code PersonA} knows everybody he knows.
 * We use Set to represent this relationship.
 * */
public class PersonA {
	
	/* Private fields */
	private final String name;
	private Set<PersonA> knows;
	
	/* Public methods */
	
	/**
	 * Constructs a {@code PersonA} whose name is <i>nameString</i>.
	 * We will automatically strip the spaces at both ends of the nameString to get a concise name. 
	 * 
	 * <p><strong>Requires</strong>: nameString is not none.</p> 
	 * @exception IllegalArgumentException if nameString is empty
	 * */
	public PersonA(String nameString) {
		String temp = nameString.trim();
		if (temp == "")
			throw new IllegalArgumentException("None Name");
		else {
			knows = new HashSet<PersonA>();
			name = temp;
		}
	}

	/**
	 * Get the name of PersonA
	 * 
	 * @return {@code this.name}.
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * Build a new relationship representing {@code this} knows {@code PersonA} pA
	 * 
	 * @param pA An instance of {@code PersonA}, but not {@code this} PersonA. 
	 * @exception IllegalArgumentException if pA is the same person as {@code this}.
	 * */
	public void addKnows(PersonA pA) {
		if (pA.name == name)
			throw new IllegalArgumentException("That a person knows himself seems to be confusing.");
		knows.add(pA);
	}
	
	/**
	 * Return the number of people this person knows. If this
     * person knows more than {@code Integer.MAX_VALUE} people, returns
     * {@code Integer.MAX_VALUE}.
	 * 
	 * @return the number of people this person knows
	 * */
	public int knowsNum() {
		return knows.size();
	}
	
	/**
	 * Determine whether {@code this} knows {@code PersonA } pA.
	 * 
	 * @param pA An instance of {@code PersonA}, but not {@code this} PersonA. 
	 * @return if pA in {@code this.knows}, return {@code true}; else return {@code false}.
	 * @exception IllegalArgumentException if pA is the same person as {@code this}.
	 * */
	public boolean isKnows(PersonA pA) {
		if (pA.name == name)
			throw new IllegalArgumentException("Not defined relationship between a person and itself.");
		boolean flags = false;
		for (PersonA eA : knows) {
			if (eA.name == pA.name)
				flags = true;
		}
		return flags;
	}
	
	
}
