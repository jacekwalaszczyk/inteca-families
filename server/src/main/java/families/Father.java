package families;

import java.util.Date;

/**
 * A class containing data of a father. 
 * All attributes and methods are taken from the Person class.
 */
public class Father extends Person {

	/**
	 * A constructor which assigns all father's attributes.
	 * It needs a identifier of a family which a father belongs to. 
	 */
	public Father(int id, String firstName, String secondName, String pesel, Date birthDate) {
		super(id, firstName, secondName, pesel, birthDate);
	}

}
