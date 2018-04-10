package data.database.services;

import data.database.model.User;
import data.database.services.dao.DatabaseDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class UserController {
	
	private DatabaseDAO dao;

	public DatabaseDAO getDao() {
		return dao;
	}

	public void setDao(DatabaseDAO dao) {
		this.dao = dao;
	}
	
	public List<User> getUser(int id) {
		
		String sql = "Select * from users WHERE id = :id";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return dao.doQuery(sql, params, new UserRowMapper( ));
	}
	
	public List searchUser(String un, String pass) {
		
		return null;
	}
	
	class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet result, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setId(result.getInt("id"));
			user.setName(result.getString("name"));
			user.setUsername(result.getString("userName"));
			user.setPassword(result.getString("password"));
			return user;
		}
		
		
	}
	

}
