package mvc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mvc.db.DBConnectionPool;

public abstract class DAO {	
	protected Connection conn			= null;
	protected PreparedStatement pstmt 	= null;
	protected ResultSet rs				= null;
	protected DBConnectionPool dbcp 	= null;
}
