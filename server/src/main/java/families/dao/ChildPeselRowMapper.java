package families.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper implementation for a Child class.
 * Returns a String object with the PESEL value from the ResultSet data of the query result's row  
 */
public class ChildPeselRowMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet row, int rowNum) throws SQLException {
		String pesel = row.getString("PESEL");
		return pesel;
	}
}
