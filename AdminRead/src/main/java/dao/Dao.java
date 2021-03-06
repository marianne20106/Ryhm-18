package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Question;

import java.sql.Connection;

public class Dao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;

	public Dao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public boolean getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new SQLException(e);
				}
				conn = DriverManager.getConnection(url, user, pass);
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public ArrayList<Question> readAllQuestion() {
		ArrayList<Question> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from KYSYMYKSET");
			while (RS.next()) {
				Question f = new Question();
				f.setId(RS.getInt("KYSYMYS_ID"));
				f.setQuestion(RS.getString("KYSYMYS"));
				list.add(f);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}



	public ArrayList<Question> deleteQuestion(String KYSYMYS_ID) {
		try {
			String sql="delete from KYSYMYKSET where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, KYSYMYS_ID);
			pstmt.executeUpdate();
			return readAllQuestion();
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	public ArrayList<Question> AddTableData(String KYSYMYS) {
	    String sql = "INSERT INTO KYSYMYKSET (KYSYMYS) VALUES (?)";
	    try {

	    	PreparedStatement pstmt=conn.prepareStatement(sql);
	        pstmt.setString(2, KYSYMYS);
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        return null;
	    }
	    return null;
	}

	public ArrayList<Question> updateQuestion(Question f) {
		try {
			String sql="update KYSYMYKSET set KYSYMYS=? where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, f.getQuestion());
			pstmt.setInt(2, f.getId());
			pstmt.executeUpdate();
			return readAllQuestion();
		}
		catch(SQLException e) {
			return null;
		}
	}
	public Question readQuestion(String id) {
		Question f=null;
		try {
			String sql="select * from KYSYMYKSET where KYSYMYS_ID=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				f=new Question();
				f.setId(RS.getInt("KYSYMYS_ID"));
				f.setQuestion(RS.getString("KYSYMYS"));
			}
			return f;
		}
		catch(SQLException e) {
			return null;
		}
	}
}