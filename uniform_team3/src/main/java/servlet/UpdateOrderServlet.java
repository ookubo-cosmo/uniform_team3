package servlet;

import java.io.IOException;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String error = "";
		String cmd = "";

		try {

			//orderidを取得
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			
			//shipment_statusを取得
			int shipment_status = Integer.parseInt(request.getParameter("shipment_status"));
			
			//deposit_statusを取得
			int deposit_status = Integer.parseInt(request.getParameter("deposit_status"));

			//OrderDAOをインスタンス化
			OrderDAO orderDao = new OrderDAO();

			//受注情報を検索し、戻り値としてOrderオブジェクトを取得
			Order order = orderDao.selectByOrderid(orderid);
			
			// 受注情報のエラーチェック
			if (order == null) {
				error = "変更対象の注文が存在しない為、変更は出来ませんでした。";
				cmd = "toorderlist";
				
			}else {
				//setterメソッドでshipment_status,deposit_statusを設定
				order.setShipment_status(shipment_status);
				order.setDeposit_status(deposit_status);
				
				//orderのオブジェクトを引数として、更新メソッドを呼び出す
				orderDao.update(order);
			}
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、受注情報の変更は出来ません。";
			cmd = "toorderlist";
		} finally {
			if (!(error.equals(""))) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {

				request.getRequestDispatcher("/view/orderlist").forward(request, response);
			}
		}
	}
}
