package servlet;

import java.io.IOException;

import bean.Owner;
import dao.OwnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		
		String error = "";
		String cmd = "";
		
		try {
			 //OwnerDAOをインスタンス化
			 OwnerDAO ownerDao = new OwnerDAO();
			 
			 //ownerid,passwordを取得
			 String ownerid = request.getParameter("ownerid");
			 String password = request.getParameter("password");
			 
			 //管理者情報を検索し、戻り値としてOwnerオブジェクトを取得
			 Owner owner = ownerDao.selectByOwneridAndPassword(ownerid, password);
			 
			 //管理者情報の確認
			 if(owner.getOwnerid() != null) {
				 
				 //管理者情報がある場合、セッションスコープにownerとして登録
				 HttpSession session = request.getSession();
				 session.setAttribute("owner",owner);
				 
				 //クッキーにownerid,passwordを5日間登録
				 Cookie owneridCookie = new Cookie("ownerid", owner.getOwnerid());
				 owneridCookie.setMaxAge(60 * 60 * 24 * 5);
				 response.addCookie(owneridCookie);
				 
				 Cookie passwordCookie = new Cookie("password", owner.getPassword());
				 passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				 response.addCookie(passwordCookie);
				 
			 }else {
				 request.setAttribute("message", "入力データが間違っています！");
				 request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			 }
		}catch(IllegalStateException e) {
			error ="DB接続エラーの為、ログインは出来ません。";
			cmd = "logout";	
			
		}finally {
			if(error != "") {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			
			 request.getRequestDispatcher("/orderlist").forward(request,response);
		}
	}
}
