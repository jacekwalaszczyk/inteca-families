package families.dbservice;

import families.FamilyContent;

/**
 * Interface for a DB service class implementing 
 * data access methods for a FamilyContent class
 *
 */
public interface IFamilyContentDBService {
	/**
	 * A method giving the whole data of the family with the given id.
	 */
    FamilyContent readFamilyContent(int familyId);

	/**
	 * A method storing the whole data of a new family in DB and assigning the id to it.
	 */
	FamilyContent storeFamilyContent(FamilyContent familyContent);
}
