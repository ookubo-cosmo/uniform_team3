package servlet;

import java.io.IOException;

import bean.Item;
import bean.Order;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchased")
public class PurchasedServlet extends HttpServlet {

	String error = "";
	String cmd = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			
			//注文情報を取得
			Order order = (Order)request.getAttribute("order");
			
			//ItemDAOをインスタンス化
			ItemDAO objItem = new ItemDAO();

			//関連メソッド呼び出し
			Item item = objItem.selectByItemid(order.getOrderid());

			//リクエストスコープに格納
			request.setAttribute("item", item);
			request.setAttribute("order",order);

		} catch (IllegalStateException e) {
			error = "DB接続エラーのため、購入できません。";
			cmd = "toitemlist";
		} finally {

			if (error.equals("")) {
				request.getRequestDispatcher("/view/purchased.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
