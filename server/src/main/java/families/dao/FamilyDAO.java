package families.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Math.toIntExact;

import families.Family;

/**
 * Repository DAO class containing data methods for a Family class.
 * A JDBC is used for DB operations. 
 */
@Transactional
@Repository
public class FamilyDAO implements IFamilyDAO {

	    private SimpleJdbcInsert insertFamily;
	    
		@Autowired
	    private JdbcTemplate jdbcTemplate;
		
		/**
		 * Sets a SimpleJdbcInsert object for a automated service of a Family.id identifier  
		 */
		@Autowired
		public void setDataSource(DataSource dataSource) {
			this.insertFamily = new SimpleJdbcInsert(dataSource).withTableName("Family").usingGeneratedKeyColumns("ID");
		}
		
		/**
		 * Returns a Family object with a given id containing a family entity data stored in DB  
		 */
		@Override
		public Family getFamilyById(int familyId) {
			String sql = "SELECT ID FROM Family WHERE ID = ?";
			RowMapper<Family> rowMapper = new FamilyRowMapper();
			Family family = jdbcTemplate.queryForObject(sql, rowMapper, familyId);
			return family;
		}
		
		/**
		 * Returns a list with Family objects containing all family entities data stored in DB  
		 */
		@Override
		public List<Family> getAllFamilies() {
			String sql = "SELECT ID FROM Family";
			RowMapper<Family> rowMapper = new FamilyRowMapper();
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		
		/**
		 * Stores the Family object's data in DB  
		 */
		@Override
		public Family addFamily(Family family) {
		   	SqlParameterSource parameters = new BeanPropertySqlParameterSource(family);
	        long genId = (long) insertFamily.executeAndReturnKey(parameters);
	        family.setId(toIntExact(genId));
	        return family;
		}

		/**
		 * Checks if a family entity with a given id exists in DB  
		 */
		@Override
		public boolean familyExists(int familyId) {
			String sql = "SELECT count(*) FROM Family WHERE ID = ?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, familyId);
			if (count == 0) {
	    		return false;
			} else {
				return true;
			}
		}

}
