package families;

import java.util.Date;

/**
 * A class containing data of a child. 
 * Most of attributes and methods are taken from the Person class, the Child class adds a Sex attribute.
 */
public class Child extends Person {

	private String sex;
	
	/**
	 * A constructor which assigns all attributes of a Child class.
	 * It needs a identifier of a family which this child belongs to. 
	 */
	public Child(int id, String firstName, String secondName, String pesel, Date birthDate, String sex) {
		super(id, firstName, secondName, pesel, birthDate);
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
