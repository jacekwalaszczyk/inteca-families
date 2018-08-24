package families;

import java.util.Date;

/**
 * An abstract parent class which contains common methods and properties
 * for members of a family. It is extended to form a real classes of a father and a child
 */
public abstract class Person {
	/**
	 * A identifier of family which this person belongs to
	 */
    private int id;
    
	/**
	 * A first name of a person
	 */
    private String FirstName;

	/**
	 * A second name of a person
	 */
    private String SecondName;

	/**
	 * A polish government identifier of a person. It is also used as a person's identifier in the archive.
	 */
    private String PESEL;

	/**
	 * A birth date of a person
	 */
    private Date BirthDate;


	/**
	 * Constructs a class and assigns all attributes
	 */
    Person(int id, String firstName, String secondName, String pesel, Date birthDate) {
    	this.id = id;
    	this.FirstName = firstName;
    	this.SecondName = secondName;
    	this.PESEL = pesel;
    	this.BirthDate = birthDate;
    }
    
	public Integer getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.FirstName;
	}

	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	public String getSecondName() {
		return this.SecondName;
	}

	public void setSecondName(String secondName) {
		this.SecondName = secondName;
	}

	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String pesel) {
		PESEL = pesel;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

}
