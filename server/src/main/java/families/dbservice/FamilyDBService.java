package families.dbservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import families.dao.IFamilyDAO;
import families.Family;

/**
 * A DB service class implementing data access methods for a Family class
 */
@Service
public class FamilyDBService implements IFamilyDBService {
	
	/**
	 * The Family DAO methods are used in this service for a DB access implementation 
	 */
	@Autowired
	private IFamilyDAO familyDAO;

	/**
	 * Returns a list with Family objects containing all Family entities stored in DB
	 */
	@Override
    public List<Family> getAllFamilies() {
		return familyDAO.getAllFamilies();
	};

	/**
	 * Returns a Family object containing a Family's data stored in DB with a given id
	 */
	@Override
    public Family getFamilyById(int familyId) {
		return familyDAO.getFamilyById(familyId);
	};

	/**
	 * Stores in DB the data from a given Family object.
	 * Returns a Family object containing the id value assigned by DB 
	 */
	@Override
    public Family addFamily(Family family) {
		if (familyDAO.familyExists(family.getId())) {return null;}; 

	    return familyDAO.addFamily(family);
	};

}
