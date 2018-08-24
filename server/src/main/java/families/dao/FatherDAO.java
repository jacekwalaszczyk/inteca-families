package families.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import families.Father;

/**
 * Repository DAO class containing data methods for a Father class.
 * A JDBC is used for DB operations. 
 */
@Transactional
@Repository
public class FatherDAO implements IFatherDAO{

	@Autowired
    private JdbcTemplate jdbcTemplate;

	/**
	 * Returns a Father object containing a Father's data stored in DB with a given id
	 */
	@Override
	public Father getFatherById(int fatherId) {
		String sql = "SELECT ID, FirstName, SecondName, PESEL, BirthDate FROM Father WHERE ID = ?";
		RowMapper<Father> rowMapper = new FatherRowMapper();
		Father father = jdbcTemplate.queryForObject(sql, rowMapper, fatherId);
		return father;
	}
	
	/**
	 * Returns a list with Father objects containing all Father entities stored in DB
	 */
	@Override
	public List<Father> getAllFathers() {
		String sql = "SELECT ID, FirstName, SecondName, PESEL, BirthDate FROM Father";
		RowMapper<Father> rowMapper = new FatherRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	/**
	 * Stores in DB the data from a given Father object.
	 */
	@Override
	public void addFather(Father father) {
		String sql = "INSERT INTO Father (ID, FirstName, SecondName, PESEL, BirthDate) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, father.getId(), father.getFirstName(), father.getSecondName(), father.getPESEL(), father.getBirthDate());		
	}
	
	/**
	 * Checks if a father with a given id exists in DB 
	 */
	@Override
	public boolean fatherExists(int fatherId) {
		String sql = "SELECT count(*) FROM Father WHERE ID = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, fatherId);
		if (count == 0) {
    		return false;
		} else {
			return true;
		}
	}
}
