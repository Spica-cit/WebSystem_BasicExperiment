package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
	
	static {
		try {
			Class.forName("org.h2.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Pokemon> findAll(){
		List<Pokemon> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT * FROM pokemon";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				int id = rs.getInt("番号");
				String name = rs.getString("名前");
				int attack = rs.getInt("攻撃力");
				int defense = rs.getInt("防御力");
				int stamina = rs.getInt("体力");
				int cp = rs.getInt("最大CP");
				Pokemon p = new Pokemon(id, name, attack, defense, stamina, cp);
				list.add(p);
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
	
	public List<Pokemon> pikachu(){
		List<Pokemon> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT 攻撃力,防御力,体力 FROM pokemon WHERE 名前 = 'ピカチュウ';";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				int attack = rs.getInt("攻撃力");
				int defense = rs.getInt("防御力");
				int stamina = rs.getInt("体力");
				Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
				list.add(p);
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
	
	public List<Pokemon> threeLetters(){
		List<Pokemon> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT 名前 FROM pokemon WHERE CHAR_LENGTH(名前) = 3";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				String name = rs.getString("名前");
				Pokemon p = new Pokemon(0, name, 0, 0, 0, 0);
				list.add(p);
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
	
	public List<Pokemon> max(){
		List<Pokemon> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT MAX(攻撃力),MAX(防御力),MAX(体力) FROM pokemon";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				int attack = rs.getInt("MAX(攻撃力)");
				int defense = rs.getInt("MAX(防御力)");
				int stamina = rs.getInt("MAX(体力)");
				Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
				list.add(p);
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
	
	public List<Pokemon> denki(){
		List<Pokemon> list = new ArrayList<>();
		String url = "jdbc:h2:tcp://localhost/./s2132023";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,"user","pass");
			String sql = "SELECT pokemon.番号,名前,攻撃力,防御力,体力,最大CP,タイプ FROM pokemon JOIN type ON pokemon.番号 = type.番号 WHERE タイプ = 'でんき'";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				int id = rs.getInt("番号");
				String name = rs.getString("名前");
				int attack = rs.getInt("攻撃力");
				int defense = rs.getInt("防御力");
				int stamina = rs.getInt("体力");
				int cp = rs.getInt("最大CP");
				String type = rs.getString("タイプ");
				Pokemon p = new Pokemon(id, name, attack, defense, stamina, cp, type);
				list.add(p);
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
