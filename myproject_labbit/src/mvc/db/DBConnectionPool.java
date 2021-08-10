package mvc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionPool {
	String driver;
	String url;
	String user;
	String pass;	
	
	private static DBConnectionPool dbcp = null;
	
	//Connection ��ü�� ����Ʈ ���·� �����ϴ� �÷���
	private List<Connection> connList = new ArrayList<Connection>();
	
	private DBConnectionPool() throws ClassNotFoundException {
		this("com.mysql.jdbc.Driver",
			 "jdbc:mysql://localhost:3306/labbit",
			 "root",
			 "1q2w");
	}
	
	private DBConnectionPool(String driver, String url, String user, String pass) throws ClassNotFoundException {
		this.url	= url;
		this.user	= user;
		this.pass	= pass;
		Class.forName(driver);
	}	
	
	//�̱��� ����
	public static DBConnectionPool getInstance() throws ClassNotFoundException{		
		if(dbcp == null) {
			dbcp = new DBConnectionPool();
		}
		return dbcp;		
	}
	
	//Ŀ�ؼ� ��û�� ������ Connection ��ü �����ϱ�
	public Connection getConnection() throws SQLException {
		//����Ʈ�� Ŀ�ؼ��� ������ 0��° ��ü �����ֱ�
		//�����ֱ� ���� ��ȿ�� üũ(���� �ð��� ������ �������� ������ �������� ����)
		if(connList.size() > 0) {
			Connection conn = connList.get(0);
			if(conn.isValid(10)) {
				return conn;
			}
		}
		//����Ʈ�� ��ü�� ������ ���� ���� ����
		return DriverManager.getConnection(url, user, pass);
	}
	
	//����Ŀ��� Ŀ�ؼ� Ǯ�� ��ȯ�ϱ�
	public void returnConnection(Connection conn) {
		connList.add(conn);
	}
	
	//Ŀ�ؼ�Ǯ ���� - ����Ʈ�� ��� Ŀ�ؼ� ���� �����ϱ�
	public void closeAll() throws SQLException {
		for(Connection conn : connList) { conn.close(); }
	}
}
