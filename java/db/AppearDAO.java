package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AppearDAO {
	
	static {
		try {
			Class.forName("org.h2.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insert(int number, int shicode) {
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "INSERT INTO appear(番号,市コード) VALUES(?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setInt(1, number);
			pre.setInt(2, shicode);
			int result = pre.executeUpdate();
			if(result == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean insert(int number, int shicode, int year, int month, int day, int hour, int minute, int second) {
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "INSERT INTO appear(番号,市コード,日付,時刻) VALUES(?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			
			String datestr = String.format("%4d-%02d-%02d", year, month, day);
			Date date = Date.valueOf(datestr);
			
			String timestr = String.format("%02d:%02d:%02d", hour, minute, second);
			Time time = Time.valueOf(timestr);
			
			pre.setInt(1, number);
			pre.setInt(2, shicode);
			pre.setDate(3, date);
			pre.setTime(4, time);
			int result = pre.executeUpdate();
			if(result == 1)return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean delete(int id) {
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "DELETE FROM appear WHERE ID = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setInt(1, id);
			
			int result = pre.executeUpdate();
			if(result == 1)return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public List<Appear> findAll(){
		List<Appear> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT ID,APPEAR.番号,POKEMON.名前,ken.県名,shi.市名,日付,時刻 FROM APPEAR LEFT JOIN POKEMON ON APPEAR.番号 = POKEMON.番号 LEFT JOIN SHI ON APPEAR.市コード = SHI.市コード LEFT JOIN KEN ON SHI.県コード = KEN.県コード";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int number = rs.getInt("番号");
				String name = rs.getString("名前");
				String type = rs.getString("タイプ");
				String ken = rs.getString("県名");
				String shi = rs.getString("市名");
				Date date = rs.getDate("日付");
				Time time = rs.getTime("時刻");
				Appear a = new Appear(id, number, name, type, ken, shi, date, time);
				list.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
	}
	
	public List<Appear> findAll(String item,String order){
		List<Appear> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT ID,APPEAR.番号,POKEMON.名前,TYPE.タイプ,ken.県名,shi.市名,日付,時刻 FROM APPEAR LEFT JOIN POKEMON ON APPEAR.番号 = POKEMON.番号 LEFT JOIN SHI ON APPEAR.市コード = SHI.市コード LEFT JOIN KEN ON SHI.県コード = KEN.県コード LEFT JOIN TYPE ON APPEAR.番号 = TYPE.番号 ORDER BY " + item + " " + order;
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int number = rs.getInt("番号");
				String name = rs.getString("名前");
				String type = rs.getString("タイプ");
				String ken = rs.getString("県名");
				String shi = rs.getString("市名");
				Date date = rs.getDate("日付");
				Time time = rs.getTime("時刻");
				Appear a = new Appear(id, number, name, type, ken, shi, date, time);
				list.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
	}
	
	
}
