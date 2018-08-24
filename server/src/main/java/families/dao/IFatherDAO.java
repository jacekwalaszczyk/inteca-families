package families.dao;

import java.util.List;
import families.Father;

/**
 * Interface for a DAO class containing data methods implementation for a Father class 
 */
public interface IFatherDAO {

	/**
	 * Returns a list with Father objects containing all Father entities stored in DB
	 */
    List<Father> getAllFathers();

	/**
	 * Returns a Father object containing a Father's data stored in DB with a given id
	 */
    Father getFatherById(int fatherId);

	/**
	 * Stores in DB the data from a given Father object.
	 */
    void addFather(Father father);

	/**
	 * Checks if a father with a given id exists in DB 
	 */
    boolean fatherExists(int fatherId);
}
