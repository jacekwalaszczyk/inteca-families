package families.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import families.Father;
import families.dao.IFamilyDAO;
import families.dao.IFatherDAO;

/**
 * A DB service class implementing data access methods for a Father class
 */
@Service
public class FatherDBService implements IFatherDBService {

	/**
	 * The Family DAO method familyExists is used to validate the Father's id identifier 
	 */
	@Autowired
	private IFamilyDAO familyDAO;

	/**
	 * The Father DAO methods are used in this service for a DB access implementation 
	 */
	@Autowired
	private IFatherDAO fatherDAO;

	/**
	 * Returns a list with Father objects containing all Father entities stored in DB
	 */
	@Override
	public List<Father> getAllFathers(){    	
		return fatherDAO.getAllFathers();
    };
    
	/**
	 * Returns a Father object containing a Father's data stored in DB with a given id
	 */
	@Override
    public Father getFatherById(int fatherId) {
		return fatherDAO.getFatherById(fatherId);    	
    };
    
	/**
	 * Stores in DB the data from a given Father object.
	 * Returns a value informing about the addition's try result. 
	 */
	@Override
    public synchronized boolean AddFatherToFamily(Father father) {
        if (familyDAO.familyExists(father.getId()) == false) { return false; }         
        if (fatherDAO.fatherExists(father.getId())) { return false; }
        
        fatherDAO.addFather(father);
        return true;
    };

}
