package families.dbservice;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import families.Child;
import families.dao.IChildDAO;
import families.dao.IFatherDAO;

/**
 * A DB service class implementing data access methods for a Child class
 */
@Service
public class ChildDBService implements IChildDBService {

	/**
	 * The Father DAO method fatherExists is used to validate the Child's id identifier 
	 */
	@Autowired
	private IFatherDAO fatherDAO;

	/**
	 * The Child DAO methods are used in this service for a DB access implementation 
	 */
	@Autowired
	private IChildDAO childDAO;

	/**
	 * Returns a collection of Child objects containing data of all child entities stored in DB  
	 */
	@Override
	public List<Child> getAllChildren(){    	
		return childDAO.getAllChildren();
    };
    
	/**
	 * Returns a list of Child objects which contains data from DB of all children belonging to the family with a given id 
	 */
	@Override
	public List<Child> getChildrenById(int id){    	
		return childDAO.getChildrenById(id);
    };

	/**
	 * A public service which returns a Child object containing data from DB of a child entity with a given PESEL value 
	 */
	@Override
    public Child getChildByPesel(String pesel) {
		return childDAO.getChildByPesel(pesel);    	
    };
    
	/**
	 * A public service for searching children of a family.
	 * Returns a collection of PESEL values which identifies children belonging to the family with a given id. 
	 */
	@Override
	public List<String> searchChild(int id) {
		return childDAO.searchChild(id);		
	};
	
	/**
	 * A public service for searching children matching given criteria.
	 * You can set one, several or all parameters as a searching filter.
	 * The string parameters are treated as a beginning of a target string (used LIKE "...%" SQL phrase)
	 * Returns a collection of unique id values identifying families which found children belong to. 
	 */
	@Override
	public List<Integer> searchChild(String firstName, String secondName, String pesel, Optional<Date> birthDate, Optional<String> sex) { 		
		if (firstName.isEmpty() && secondName.isEmpty() && pesel.isEmpty() && (!birthDate.isPresent()) && (!sex.isPresent())) {return null;};
		
		return childDAO.searchChild(firstName, secondName, pesel, birthDate, sex);
    }
    
	/**
	 * Updates child's data in DB.
	 * The child's id value has to point at existing family and the PESEL value have to point at existing child.
	 */
    @Override
    public void updateChild(Child child) {
    	childDAO.updateChild(child);
    };
    
	/**
	 * Deletes child's data from DB.
	 * The given PESEL value have to point at existing child.
	 */
	@Override
    public void deleteChild(String pesel) {
    	childDAO.deleteChild(pesel);
    };
    
	/**
	 * A public service for adding a child to the family.
	 * The child's id value has to point at existing family.
	 * Returns a result of a addition's try. 
	 */
	@Override
    public synchronized boolean AddChildToFamily(Child child) {
        if (fatherDAO.fatherExists(child.getId()) == false) { return false; }         
        if (childDAO.childExists(child.getPESEL())) { return false; }
        
        childDAO.addChild(child);
        return true;
    };
	
}
