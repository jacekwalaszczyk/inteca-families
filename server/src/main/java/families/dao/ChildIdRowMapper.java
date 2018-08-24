package families.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper implementation for a Child class.
 * Returns a Integer object of the id from the ResultSet data of the query result's row  
 */
public class ChildIdRowMapper implements RowMapper<Integer> {
	@Override
	public Integer mapRow(ResultSet row, int rowNum) throws SQLException {
		Integer id = row.getInt("ID");
		return id;
	}
}
