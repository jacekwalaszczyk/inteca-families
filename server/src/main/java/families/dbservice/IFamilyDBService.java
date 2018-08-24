package families.dbservice;

import java.util.List;
import families.Family;

/**
 * A DB service interface with the data access methods for a Family class
 */
public interface IFamilyDBService {
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
}
