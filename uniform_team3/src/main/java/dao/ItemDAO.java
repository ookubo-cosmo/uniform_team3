package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Item;

public class ItemDAO {

	// JDBCドライバ内部のDriverクラスパス
	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	// 接続するMySQLデータベースパス
	private static final String URL = "jdbc:mariadb://localhost/uniformdb";
	// データベースのユーザー名
	private static final String USER = "root";
	//データベースのパスワード
	private static final String PASSWD = "root123";

	//DB接続
	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(RDB_DRIVE);
			con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//指定された商品データを検索しオブジェクトに格納するメソッド
	public Item selectByItemid(int itemid) {

		Connection con = null;
		Statement smt = null;

		//検索した情報を格納するオブジェクト
		Item item = new Item();

		try {

			//検索用のSQL文
			String sql = "SELECT * FROM iteminfo WHERE itemid = '" + itemid + "'";

			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = ItemDAO.getConnection();
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuary()メソッドを利用し、SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//結果セットから書籍データを取り出し、Bookオブジェクトに格納
			if (rs.next()) {
				item.setItemid(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemName"));
				item.setPrice(rs.getInt("price"));
				item.setStock(rs.getInt("stock"));
				item.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//Statementオブジェクトをクローズ
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			//Connectionオブジェクトをクローズ
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return item;
	}

	//全商品情報を取得するメソッド
	public ArrayList<Item> selectAll() {

		Connection con = null;
		Statement smt = null;

		//結果を格納するオブジェクト
		ArrayList<Item> list = new ArrayList<Item>();
		
		try {

			//検索用SQL文
			String sql = "SELECT * FROM iteminfo ORDER BY itemid";

			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = ItemDAO.getConnection();
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuary()メソッドを利用し、SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);
			
			//結果をlistに格納
			while (rs.next()) {
				Item item = new Item();
				item.setItemid(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemName"));
				item.setPrice(rs.getInt("price"));
				item.setStock(rs.getInt("stock"));
				item.setImage(rs.getString("image"));
				list.add(item);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;

	}
	
	public void update(Item item) {
		Connection con = null;
		Statement smt = null;
		
		//更新用SQL文
		//DBの商品情報を変更する
		String sql = "UPDATE iteminfo SET stock=" + item.getStock() 
				+ " WHERE itemid=" + item.getItemid();
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();
			
			//executeUpdate()メソッドを利用し、SQL文を発行
			smt.executeUpdate(sql);
			
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			if(smt != null) {
				try {smt.close();}catch(SQLException ignore) {}
			}if(con != null) {
				try {con.close();}catch(SQLException ignore) {}
			}
		}
	}

}
