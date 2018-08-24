package families.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import families.Child;

/**
 * RowMapper implementation for a Child class.
 * Builds a Child object based on the ResultSet data from a particular query result's row  
 */
public class ChildRowMapper implements RowMapper<Child> {
	@Override
	public Child mapRow(ResultSet row, int rowNum) throws SQLException {
		Child child = new Child(row.getInt("ID"), row.getString("FirstName"), row.getString("SecondName"), row.getString("PESEL"), row.getDate("BirthDate"), row.getString("Sex"));		
		return child;
	}
}
