package person;

/**
 * {@code PersonB} just records his own name and knows nothing about the outside world
 * */
public class PersonB {
	
	/* Private fields */
	private final String name;
	
	
	/* Public methods */
	/**
	 * Constructs a {@code PersonB} whose name is <i>nameString</i>.
	 * We will automatically strip the spaces at both ends of the nameString to get a concise name. 
	 * 
	 * <p><strong>Requires</strong>: nameString is not none.</p> 
	 * @exception {@code IllegalArgumentException} if nameString is empty
	 * */
	public PersonB(String nameString) {
		String temp = nameString.trim();
		if (temp == "") {
			throw new IllegalArgumentException("None Name");
		}
		else {
			name = temp;
		}
	}

	/**
	 * Get the name of PersonB
	 * 
	 * @return {@code this.name}.
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>Compares this string to the specified object. 
	 * The result is {@code true} if and only if the argument is not null 
	 * and is a {@code PersonB} object that has the same name as this object. </p>
	 * 
	 * @param obj an Object The object to compare this {@code PersonB} against
	 * @return {@code true} if the given object represents a {@code PersonB} 
	 * equivalent to this person, {@code false} otherwise.
	 * */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PersonB))
			return false;
		if (obj == this)
			return true;
		return ((PersonB)obj).getName().equals(this.name);
	}
	
	@Override
	public int hashCode() {
		return name.length() + (int)(name.charAt(0));
	}
	
	public static void main(String[] args) {
		PersonB one = new PersonB("one");
		PersonB copyone = new PersonB(" one ");
		System.out.println(copyone.getName() + ", " + one.getName());
	}
	
}
