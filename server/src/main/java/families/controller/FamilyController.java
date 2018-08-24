package families.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import families.dbservice.IFamilyDBService;
import families.Family;

/**
 * The REST controller with Web methods connected with a Family class. The
 * routing path for this controller is api/families
 */
@RestController
@RequestMapping("api/families")
public class FamilyController {

	@Autowired
	private IFamilyDBService familyDBService;

	/**
	 * Returns a collection of Family objects containing data of all family entities
	 * stored in DB
	 */
	@GetMapping
	Collection<Family> readFamilies() {
		return this.familyDBService.getAllFamilies();
	}

	/**
	 * Returns a Family object containing data from DB of a family entity with a
	 * given id
	 */
	@GetMapping("/{id}")
	Family readFamilyById(@PathVariable int id) {
		return this.familyDBService.getFamilyById(id);
	}

	/**
	 * Public service for a creation of a new family entity in DB.
	 * Returns a value of identifier assigned to this family by DB 
	 */
	@PostMapping
	public Family CreateFamily() {
		Family family = new Family(0);
		return familyDBService.addFamily(family);
	}

}
