package dao;
/*
 * プログラム名		:ユニフォーム受注システム
 * プログラムの説明	:ユニフォームの注文を管理するプログラム
 * 						管理者のDAO
 * 作成者：大久保嵩琉
 * 作成日：20240620
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Owner;

public class OwnerDAO {

	// JDBCドライバ内部のDriverクラスパス
	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	// 接続するMySQLデータベースパス
	private static final String URL = "jdbc:mariadb://localhost/uniformdb";
	// データベースのユーザー名
	private static final String USER = "root";
	//データベースのパスワード
	private static final String PASSWD = "root123";
	
	//DB接続
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL,USER,PASSWD);
			return con;
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Connection con = getConnection();
		System.out.println("con=" + con);
		con.close();
	}
	
	//管理者IDとパスワードが一致するものを検索
	public Owner selectByOwneridAndPassword(String ownerid ,String password){
		Connection con = null;
		Statement smt = null;
		
		//結果を格納するオブジェクト
		Owner owner = new Owner();
		
		//検索用SQL文
		//注文所法の全情報を取得する
		String sql = "SELECT * FROM ownerinfo WHERE ownerid='" + ownerid + "' AND password = '" + password+"';";
		
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();
			
			//executeQuary()メソッドを利用し、SQL文を発行し結果を取得
			ResultSet rs = smt.executeQuery(sql);
			
			//結果をオブジェクトに格納
			if(rs.next()) {
				owner.setOwnerid(rs.getString("ownerid"));
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			if(smt != null) {
				try {smt.close();}catch(SQLException ignore) {}
			}if(con != null) {
				try {con.close();}catch(SQLException ignore) {}
			}
		}
		
		return owner;
	}
}
