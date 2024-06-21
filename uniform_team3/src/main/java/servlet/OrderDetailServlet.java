package servlet;

import java.io.IOException;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String error = "";
		String cmd = "";

		try {

			//orderidを取得
			int orderid = Integer.parseInt(request.getParameter("orderid"));

			//OrderDAOをインスタンス化
			OrderDAO orderDao = new OrderDAO();

			//受注情報を検索し、戻り値としてOrderオブジェクトを取得
			Order order = orderDao.selectByOrderid(orderid);

			// 詳細情報のエラーチェック
			if (order == null) {
				error = "表示対象の注文が存在しない為、詳細情報は表示出来ませんでした。";
				cmd = "toorderlist";
			}

			//リクエストスコープにorderを格納
			request.setAttribute("order", order);
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、受注情報の詳細情報は表示出来ません。";
			cmd = "toorderlist";
		} finally {
			if (!(error.equals(""))) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/view/orderdetail").forward(request, response);
			}
		}
	}
}
