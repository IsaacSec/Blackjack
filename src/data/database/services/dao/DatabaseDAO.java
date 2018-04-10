package data.database.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DatabaseDAO {
	
	private NamedParameterJdbcTemplate dataAccess;

	@Autowired
	public void setDataSource(DataSource src) {

		this.dataAccess = new NamedParameterJdbcTemplate(src);
	}
	
	
	public List doQuery(String sql, SqlParameterSource paramSource, RowMapper rowMapper){
		
		return this.dataAccess.query(sql, paramSource, rowMapper);
	}
	
	public int insert(String sql, SqlParameterSource paramSource) {
		
		return this.dataAccess.update(sql, paramSource);
		
	}
	
	
	
}
