package families.dbservice;

import java.util.List;
import families.Father;

/**
 * A DB service interface with the data access methods for a Father class
 */
public interface IFatherDBService {
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
	 * Returns a value informing about the addition's try result. 
	 */
    boolean AddFatherToFamily(Father father);
}
