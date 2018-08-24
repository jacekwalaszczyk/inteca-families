package families.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import families.Child;

/**
 * Repository DAO class containing data methods for a Child class.
 * A JDBC is used for DB operations. 
 */
@Transactional
@Repository
public class ChildDAO implements IChildDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	/**
	 * A method which returns a Child object containing data from DB of a child entity with a given PESEL value 
	 */
	@Override
	public Child getChildByPesel(String pesel) {
		String sql = "SELECT ID, FirstName, SecondName, PESEL, BirthDate, Sex FROM Child WHERE PESEL = ?";
		RowMapper<Child> rowMapper = new ChildRowMapper();
		Child child = jdbcTemplate.queryForObject(sql, rowMapper, pesel);
		return child;
	}
	
	/**
	 * Returns a list of Child objects which contains data from DB of all children belonging to the family with a given id 
	 */
	@Override
    public List<Child> getChildrenById(int id) {
		String sql = "SELECT ID, FirstName, SecondName, PESEL, BirthDate, Sex FROM Child WHERE ID = ?";
		RowMapper<Child> rowMapper = new ChildRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, id);    	
    };
    
	/**
	 * Returns a collection of Child objects containing data of all child entities stored in DB  
	 */
	@Override
	public List<Child> getAllChildren() {
		String sql = "SELECT ID, FirstName, SecondName, PESEL, BirthDate, Sex FROM Child";
		RowMapper<Child> rowMapper = new ChildRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);    	
	}
	
	/**
	 * A method for searching children of a family.
	 * Returns a collection of PESEL values which identifies children belonging to the family with a given id. 
	 */
	@Override
	public List<String> searchChild(int id){
		String sql = "SELECT PESEL FROM Child WHERE ID = ?";
		RowMapper<String> rowMapper = new ChildPeselRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, id);    			
	};
	
	/**
	 * A method for searching children matching given criteria.
	 * You can set one, several or all parameters as a searching filter.
	 * The string parameters are treated as a beginning of a target string (used LIKE "...%" SQL phrase)
	 * Returns a collection of unique id values identifying families which found children belong to. 
	 */
	@Override
    public List<Integer> searchChild(String firstName, String secondName, String pesel, Optional<Date> o_birthDate, Optional<String> o_sex) {		
		Date birthDate;
		String sex = "";
		String sql = "SELECT distinct ID FROM Child WHERE FirstName LIKE ? AND SecondName LIKE ? AND PESEL LIKE ? ";
		String sex_suffix = "AND Sex = ?";
		String birthDate_suffix = "AND BirthDate = ? ";
		RowMapper<Integer> rowMapper = new ChildIdRowMapper();

		// Adjust string parameters to the LIKE clause format 
		firstName += "%";
		secondName += "%";
		pesel += "%";
		
		// Build query parameters regarding on given search criteria and then run the appropriate query
		if (o_sex.isPresent()) {sex = o_sex.get();}
		
		if (!o_birthDate.isPresent()) {
			if (sex.isEmpty()) {
				return this.jdbcTemplate.query(sql, rowMapper, firstName, secondName, pesel); 				
			}
			return this.jdbcTemplate.query(sql + sex_suffix, rowMapper, firstName + "%", secondName + "%", pesel + "%", sex); 				
		}
		
		sql += birthDate_suffix;
		birthDate = o_birthDate.get();
		
		if  (sex.isEmpty()) {
			return this.jdbcTemplate.query(sql, rowMapper, firstName + "%", secondName + "%", pesel + "%", birthDate); 							
		}
		
		return this.jdbcTemplate.query(sql + sex_suffix, rowMapper, firstName + "%", secondName + "%", pesel + "%", birthDate, sex); 
    }
    
	/**
	 * A method for adding a child to the family.
	 * The child's id value has to point at existing family.
	 */
	@Override
	public void addChild(Child child) {
		String sql = "INSERT INTO Child (ID, FirstName, SecondName, PESEL, BirthDate, Sex) values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, child.getId(), child.getFirstName(), child.getSecondName(), child.getPESEL(), child.getBirthDate(), child.getSex());		
	}

	/**
	 * Updates child's data in DB.
	 * The child's id value has to point at existing family and the PESEL value have to point at existing child.
	 */
	@Override
	public void updateChild(Child child) {
		String sql = "UPDATE Child SET FirstName=?, SecondName=?, BirthDate=?, Sex=? WHERE PESEL=?";
		jdbcTemplate.update(sql, child.getFirstName(), child.getSecondName(), child.getBirthDate(), child.getSex(), child.getPESEL());		
	};
	
	/**
	 * Deletes child's data from DB.
	 * The given PESEL value have to point at existing child.
	 */
	@Override
	public void deleteChild(String pesel) {
		String sql = "DELETE FROM Child WHERE PESEL=?";
		jdbcTemplate.update(sql, pesel);		
	};
	
	/**
	 * Checks if a child with a given PESEL exists in DB 
	 */
	@Override
	public boolean childExists(String pesel) {
		String sql = "SELECT count(*) FROM Child WHERE PESEL = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, pesel);
		if (count == 0) {
    		return false;
		} else {
			return true;
		}
	}

}
