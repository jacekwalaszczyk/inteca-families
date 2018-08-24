package families.dao;

import java.util.List;
import families.Family;

/**
 * Interface for a DAO class containing data methods implementation for a Family class 
 */
public interface IFamilyDAO {

	/**
	 * Returns a list with Family objects containing all Family entities stored in DB
	 */
	List<Family> getAllFamilies();
	
	/**
	 * Returns a Family object containing a Family's data stored in DB with a given id
	 */
    Family getFamilyById(int familyId);
    
	/**
	 * Stores in DB the data from a given Family object.
	 * Returns a Family object containing the id value assigned by DB 
	 */
    Family addFamily(Family family);
    
	/**
	 * Checks if a family with a given id exists in DB 
	 */
	boolean familyExists(int familyId);
}
