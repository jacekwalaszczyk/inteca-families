package families.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import families.dbservice.IChildDBService;
import families.Child;

/**
 * The REST controller with Web methods connected with a Child class.
 * The routing path for this controller is api/children
 */
@RestController
@RequestMapping("api/children")
public class ChildController {

	@Autowired
	private IChildDBService childDBService;

	/**
	 * Returns a collection of Child objects containing data of all child entities stored in DB  
	 */
	@GetMapping
	Collection<Child> readChildren() {
		return this.childDBService.getAllChildren();
	}

	/**
	 * Returns a list of Child objects which contains data from DB of all children belonging to the family with a given id 
	 */
	@GetMapping("/id/{id}")
	Collection<Child> readChildrenById(@PathVariable("id") int id) {
		return this.childDBService.getChildrenById(id);
	}
	
	/**
	 * A public service which returns a Child object containing data from DB of a child entity with a given PESEL value 
	 */
	@GetMapping("/pesel/{pesel}")
	public Child ReadChild(@PathVariable("pesel") String pesel) {
		return this.childDBService.getChildByPesel(pesel);
	}
	
	
	/**
	 * A public service for searching children of a family.
	 * Returns a collection of PESEL values which identifies children belonging to the family with a given id. 
	 */
	@GetMapping("/search/{id}")
	public Collection<String> SearchChild(@PathVariable("id") int id)
	{
		return this.childDBService.searchChild(id);
	}

	/**
	 * A public service for searching children matching given criteria.
	 * You can set one, several or all parameters as a searching filter.
	 * The string parameters are treated as a beginning of a target string (used LIKE "...%" SQL phrase)
	 * Returns a collection of unique id values identifying families which found children belong to. 
	 */
	@GetMapping("/search")
	public Collection<Integer> SearchChild(@RequestParam(value="firstName", defaultValue="") String firstName,
			                               @RequestParam(value="secondName", defaultValue="") String secondName,
			                               @RequestParam(value="pesel", defaultValue="") String pesel, 
			                               @RequestParam(value="birthDate", required=false) Optional<Date> birthDate,
			                               @RequestParam(value="sex", required=false) Optional<String> sex)
	{
		return this.childDBService.searchChild(firstName, secondName, pesel, birthDate, sex);
	}
	
	/**
	 * A public service for adding a child to the family.
	 * The child's id value has to point at existing family.
	 * Returns a Http result of a addition's try. 
	 */
	@PostMapping
	public ResponseEntity<Void> AddChildToFamily(@RequestBody Child child) {
        boolean flag = childDBService.AddChildToFamily(child);
        if (flag == false) {
	       return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Updates child's data in DB.
	 * The child's id value has to point at existing family and the PESEL value have to point at existing child.
	 * Returns a Http result of a update's try. 
	 */
	@PutMapping
	ResponseEntity<Child> updateChild(@RequestBody Child child) {
		childDBService.updateChild(child);
		return new ResponseEntity<Child>(child, HttpStatus.OK);
	}
	
	/**
	 * Deletes child's data from DB.
	 * The given PESEL value have to point at existing child.
	 * Returns a Http result of a deletion. 
	 */
	@DeleteMapping("/delete/{pesel}")
	ResponseEntity<Void> deleteArticle(@PathVariable("pesel") String pesel) {
		childDBService.deleteChild(pesel);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
