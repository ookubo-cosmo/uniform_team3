package servlet;

import java.io.IOException;

import bean.Item;
import bean.Order;
import dao.ItemDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.SendMail;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			Order order = new Order();
			ItemDAO itemDao = new ItemDAO();
			OrderDAO orderDao = new OrderDAO();

			//リクエストスコープから情報を取得(orderの変数全部(配送状況・入金状況以外))
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String itemid = request.getParameter("itemid");
			String quantity = request.getParameter("quantity");
			String note = request.getParameter("note");
			
			//注文量＞在庫数ならば
			if(Integer.parseInt(quantity)>itemDao.selectByItemid(Integer.parseInt(itemid)).getStock()) {
				error = "在庫数を超えています。";
				return;
			}

			//orderオブジェクトに格納
			order.setName(name);
			order.setAddress(address);
			order.setEmail(email);
			order.setItemid(Integer.parseInt(itemid));
			order.setQuantity(Integer.parseInt(quantity));
			order.setNote(note);
			order.setDeposit_status(1);
			order.setShipment_status(1);
			
			//DBにorderオブジェクトを登録
			orderDao.insert(order);
			
			//order.getItemidからitemを取得
			Item item = itemDao.selectByItemid(Integer.parseInt(itemid));
			
			//在庫数の変更
			item.setStock(item.getStock() - Integer.parseInt(quantity));
			itemDao.update(item);

			request.setAttribute("order", order);

			//メール送信(注文完了の場合、mail_statusは1)
			int mail_status = 1;
			SendMail objMail = new SendMail();
			objMail.sendMail(order, mail_status);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入できませんでした。";
			cmd = "toitemlist";
		} finally {

			request.setAttribute("cmd", cmd);

			if (!(error.equals(""))) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/purchased").forward(request, response);
			}
		}
	}
}