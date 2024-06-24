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
	
	String error = "";
	String cmd = "";


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Order order = new Order();
		try {
			error = "";
			cmd = "";
			
			request.setCharacterEncoding("UTF-8");
			
			//orderidを取得
			int orderid = Integer.parseInt(request.getParameter("orderid"));

			//OrderDAOをインスタンス化
			OrderDAO orderDao = new OrderDAO();

			//受注情報を検索し、戻り値としてOrderオブジェクトを取得
			order = orderDao.selectByOrderid(orderid);

			// 詳細情報のエラーチェック
			if (order == null) {
				error = "表示対象の注文が存在しない為、詳細情報は表示出来ませんでした。";
				cmd = "toorderlist";
			}

			
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、受注情報の詳細情報は表示出来ません。";
			cmd = "toorderlist";
		} finally {
			if (!(error.equals(""))) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				//リクエストスコープにorderを格納
				request.setAttribute("order", order);
				request.getRequestDispatcher("/view/orderdetail.jsp").forward(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//orderidを取得
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		
		try {
			//OrderDAOをインスタンス化
			OrderDAO orderDao = new OrderDAO();

			//受注情報を検索し、戻り値としてOrderオブジェクトを取得
			Order order = orderDao.selectByOrderid(orderid);

			// 詳細情報のエラーチェック
			if (order == null) {
				error = "表示対象の注文が存在しない為、詳細情報は表示出来ませんでした。";
				cmd = "toorderlist";
			}
			order.setShipment_status(Integer.parseInt(request.getParameter("shipment_status")));
			order.setDeposit_status(Integer.parseInt(request.getParameter("deposit_status")));
			orderDao.update(order);
		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、受注情報の詳細情報は表示出来ません。";
			cmd = "toorderlist";
		} finally {
			
		}
		request.getRequestDispatcher("/orderlist").forward(request,response);
	}
	
}
