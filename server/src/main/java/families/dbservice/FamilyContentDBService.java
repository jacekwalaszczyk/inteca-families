package families.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import families.Child;
import families.Family;
import families.FamilyContent;
import families.Father;

/**
 * A DB service class implementing data access 
 * methods for a FamilyContent class
 */
@Service
public class FamilyContentDBService implements IFamilyContentDBService{

	@Autowired
	private IFamilyDBService familyDBService;

	@Autowired
	private IFatherDBService fatherDBService;
	
	@Autowired
	private IChildDBService childDBService;

	// single TransactionTemplate shared amongst all methods in this instance
    private final TransactionTemplate transactionTemplate;

    // use constructor-injection to supply the PlatformTransactionManager
    public FamilyContentDBService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }
    
	/**
	 * A method giving the whole family data for the family with the given id.
	 * Uses other DB services to read data for particular classes:
	 * FamilyDBService, FatherDBService and ChildDBService
	 */
	@Override	
	public FamilyContent readFamilyContent(int familyId) {
		Family family = familyDBService.getFamilyById(familyId);
		Father father = fatherDBService.getFatherById(familyId);
		FamilyContent familyContent = new FamilyContent(family, father);
		List<String> pesels = childDBService.searchChild(familyId);
		
		pesels.forEach(pesel -> familyContent.addChild(childDBService.getChildByPesel(pesel)));
		return familyContent;
	};

	/**
	 * A method storing the whole data of a new family in DB and assigning the id to it.
	 */
	@Override	
	public FamilyContent storeFamilyContent(FamilyContent familyContent) {
		if (familyContent == null) {return familyContent;}			
		
        return (FamilyContent) transactionTemplate.execute(new TransactionCallback<FamilyContent>() {
            // the code in this method executes in a transactional context
            public FamilyContent doInTransaction(TransactionStatus status) {
		
        		Family family;
        		Father father;
        		List<Child> children;
        		FamilyContent new_familyContent;
        		int familyId;
        		
        		
        		family = familyContent.getFamily();
        		father = familyContent.getFather();
        		children = familyContent.getChildren();
        		if ((family == null) || (father == null) || (children == null)) {return null;}
        		if (familyContent.getFamily().getId() > 0) {return null;}

        		try {
	        		family = familyDBService.addFamily(family);
					if (family == null) {return null;}
					if (family.getId() == 0) {return null;}
					
					familyId = family.getId();
					father = new Father(familyId, father.getFirstName(), father.getSecondName(), father.getPESEL(), father.getBirthDate());
					if (fatherDBService.AddFatherToFamily(father) == false) { throw new RuntimeException();} // UNDO TRANSACTION
					new_familyContent = new FamilyContent(family, father);
					
					for (Child v_child : children) {
						Child child;
						
						child = new Child(familyId, v_child.getFirstName(), v_child.getSecondName(), v_child.getPESEL(), v_child.getBirthDate(), v_child.getSex());
						if (childDBService.AddChildToFamily(child) == false) {throw new RuntimeException();}  // UNDO TRANSACTION
						new_familyContent.addChild(child);
					}					
					return new_familyContent;
					
        		} catch (Exception ex) {
                    status.setRollbackOnly();
                    return null;
                }
            }
        });
	};
}
