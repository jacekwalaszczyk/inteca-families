package families.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import families.Family;

/**
 * RowMapper implementation for a Family class.
 * Builds a Family object based on the ResultSet data from a particular query result's row  
 */
public class FamilyRowMapper implements RowMapper<Family> {
	@Override
	public Family mapRow(ResultSet row, int rowNum) throws SQLException {
		Family family = new Family(0);
		family.setId(row.getInt("ID"));
		return family;
	}
}
