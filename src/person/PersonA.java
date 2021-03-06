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
	private final Set<PersonA> knows;
	
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
		return knows.contains(pA);
	}
	
	/**
	 * <p>Compares this string to the specified object. 
	 * The result is {@code true} if and only if the argument is not null 
	 * and is a {@code PersonA} object that has the same name as this object. </p>
	 * 
	 * @param obj an Object The object to compare this {@code PersonA} against
	 * @return {@code true} if the given object represents a {@code PersonA} 
	 * equivalent to this person, {@code false} otherwise.
	 * */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersonA))
			return false;
		if (obj == this)
			return true;
		return ((PersonA)obj).getName().equals(this.name);
	}
	
	@Override
	public int hashCode() {
		return name.length() + (int)(name.charAt(0));
	}
	
}
