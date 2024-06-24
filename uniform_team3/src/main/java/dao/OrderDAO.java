package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Order;

public class OrderDAO {

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
	
	//注文情報をDBに格納するメソッド
	public void insert(Order order) {
		Connection con = null;
		Statement smt = null;
		
		//更新用SQL文
		//DBに注文情報を格納する
		String sql = "INSERT INTO orderinfo VALUES(NULL,'"+ order.getName() + "','"+ order.getAddress() +"','"
				+ order.getEmail() + "'," + order.getItemid() + "," + order.getQuantity() + ",CURDATE(),'" 
				+ order.getNote() + "'," + order.getShipment_status() + ","  + order.getDeposit_status() + ");";
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
	
	//全注文情報を取得するメソッド
	public ArrayList<Order> selectAll(){
		Connection con = null;
		Statement smt = null;
		
		//結果を格納するオブジェクト
		ArrayList<Order> list = new ArrayList<Order>();
		
		//検索用SQL文
		//注文情報の受注ID、氏名、商品ID、注文量、注文日、入金情報、発送状況を取得する
		String sql = "SELECT orderid,name,itemid,quantity,order_date,deposit_status,shipment_status"
				+ " FROM orderinfo;" ;
		
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();
			
			//executeQuary()メソッドを利用し、SQL文を発行し結果を取得
			ResultSet rs = smt.executeQuery(sql);
			
			//結果をlistに格納
			while(rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getInt("orderid"));
				order.setName(rs.getString("name"));
				order.setItemid(rs.getInt("itemid"));
				order.setQuantity(rs.getInt("quantity"));
				order.setOrder_date(rs.getTimestamp("order_date"));
				order.setDeposit_status(rs.getInt("deposit_status"));
				order.setShipment_status(rs.getInt("shipment_status"));
				list.add(order);
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
		
		return list;
	}
	
	//注文情報の詳細をオブジェクトに格納するメソッド
	public Order selectByOrderid(int orderid){
		Connection con = null;
		Statement smt = null;
		
		//結果を格納するオブジェクト
		Order order = new Order();
		
		//検索用SQL文
		//注文所法の全情報を取得する
		String sql = "SELECT * FROM orderinfo WHERE orderid=" + orderid + ";";
		
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			
			//ConnectionオブジェクトのcreateStatement()メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();
			
			//executeQuary()メソッドを利用し、SQL文を発行し結果を取得
			ResultSet rs = smt.executeQuery(sql);
			
			//結果をオブジェクトに格納
			if(rs.next()) {
				order.setOrderid(rs.getInt("orderid"));
				order.setName(rs.getString("name"));
				order.setAddress(rs.getString("address"));
				order.setEmail(rs.getString("email"));
				order.setItemid(rs.getInt("itemid"));
				order.setQuantity(rs.getInt("quantity"));
				order.setOrder_date(rs.getTimestamp("order_date"));
				order.setNote(rs.getString("note"));
				order.setShipment_status(rs.getInt("shipment_status"));
				order.setDeposit_status(rs.getInt("deposit_status"));
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
		
		return order;
	}
	
	public void update(Order order) {
		Connection con = null;
		Statement smt = null;
		
		//更新用SQL文
		//DBの注文情報を変更する
		String sql = "UPDATE orderinfo SET "
				+ "shipment_status="	+ order.getShipment_status() 
				+ ",deposit_status="	+ order.getDeposit_status()
				+ " WHERE orderid="		+ order.getOrderid() +";";
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
