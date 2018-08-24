package families.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import families.FamilyContent;
import families.dbservice.IFamilyContentDBService;

/**
 * The REST controller with Web methods connected with a FamilyContent class.
 * The routing path for this controller is api/families/content
 */
@RestController
@RequestMapping("api/families/content")
public class FamilyContentController {

	@Autowired
	private IFamilyContentDBService familyContentDBService;

/**
 *  Returns the whole data of a family with a given id
 *  That means a FamilyContent object which includes a Family object, a Father Object
 *  and a list of children objects for this family
 */
	@GetMapping("/{id}")
	public FamilyContent ReadFamily (@PathVariable int id) {
		return this.familyContentDBService.readFamilyContent(id);
	}

	/**
	 * A method storing the whole data of a new family in DB and assigning the id to it.
	 */
	@PostMapping
	public ResponseEntity<FamilyContent> CreateFamily (@RequestBody FamilyContent familyContent) {
		familyContent = this.familyContentDBService.storeFamilyContent(familyContent);
        if (familyContent == null) {
	       return new ResponseEntity<FamilyContent>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FamilyContent>(familyContent, HttpStatus.CREATED);
	}	
}
