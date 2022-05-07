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
	
	public static void main(String[] args) {
		PersonB one = new PersonB("one");
		PersonB copyone = new PersonB(" one ");
		System.out.println(copyone.getName() + ", " + one.getName());
	}
	
}
