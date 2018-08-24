package families.dbservice;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import families.Child;


/**
 * A DB service interface with the data access methods for a Child class
 */
public interface IChildDBService {

	/**
	 * Returns a collection of Child objects containing data of all child entities stored in DB  
	 */
    List<Child> getAllChildren();

	/**
	 * Returns a list of Child objects which contains data from DB of all children belonging to the family with a given id 
	 */
    List<Child> getChildrenById(int id);

	/**
	 * A public service which returns a Child object containing data from DB of a child entity with a given PESEL value 
	 */
    Child getChildByPesel(String pesel);

	/**
	 * A public service for searching children of a family.
	 * Returns a collection of PESEL values which identifies children belonging to the family with a given id. 
	 */
    List<String> searchChild(int id);

	/**
	 * A public service for searching children matching given criteria.
	 * You can set one, several or all parameters as a searching filter.
	 * The string parameters are treated as a beginning of a target string (used LIKE "...%" SQL phrase)
	 * Returns a collection of unique id values identifying families which found children belong to. 
	 */
    List<Integer> searchChild(String firstName, String secondName, String pesel, Optional<Date> birthDate, Optional<String> sex); 		

	/**
	 * Updates child's data in DB.
	 * The child's id value has to point at existing family and the PESEL value have to point at existing child.
	 */
    void updateChild(Child child);

	/**
	 * Deletes child's data from DB.
	 * The given PESEL value have to point at existing child.
	 */
    void deleteChild(String pesel);

	/**
	 * A public service for adding a child to the family.
	 * The child's id value has to point at existing family.
	 * Returns a result of a addition's try. 
	 */
    boolean AddChildToFamily(Child child);
}
