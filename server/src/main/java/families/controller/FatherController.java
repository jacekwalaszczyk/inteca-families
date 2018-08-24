package families.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import families.dbservice.IFatherDBService;
import families.Father;

/**
 * The REST controller with Web methods connected with a Father class.
 * The routing path for this controller is api/fathers
 */
@RestController
@RequestMapping("api/fathers")
public class FatherController {

	@Autowired
	private IFatherDBService fatherDBService;

	/**
	 * Returns a collection of Father objects containing data of all father entities stored in DB  
	 */
	@GetMapping
	Collection<Father> readFathers() {
		return this.fatherDBService.getAllFathers();
	}

	/**
	 * Returns a Father object containing data from DB of a father entity with a given id 
	 */
	@GetMapping("/{id}")
	public Father ReadFather(@PathVariable int id) {
		return this.fatherDBService.getFatherById(id);
	}

	/**
	 * Public service for a creation of a new father entity in DB.
	 * The id attribute of a given father object contains the identifier of a family which a father belongs to.
	 * Returns a Http response with a HttpStatus containing the result of addition's try. 
	 */
	@PostMapping
	public ResponseEntity<Void> AddFatherToFamily(@RequestBody Father father) {
        boolean flag = fatherDBService.AddFatherToFamily(father);
        if (flag == false) {
	       return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}
