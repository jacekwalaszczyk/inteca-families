package families.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import families.Father;

/**
 * RowMapper implementation for a Father class.
 * Builds a Father object based on the ResultSet data from a particular query result's row  
 */
public class FatherRowMapper implements RowMapper<Father> {
	@Override
	public Father mapRow(ResultSet row, int rowNum) throws SQLException {
		Father father = new Father(row.getInt("ID"), row.getString("FirstName"), row.getString("SecondName"), row.getString("PESEL"), row.getDate("BirthDate"));		
		return father;
	}
}
