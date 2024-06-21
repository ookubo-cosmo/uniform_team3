package servlet;

/*
 * プログラム名		:ユニフォーム受注システム
 * プログラムの説明	:ユニフォームの注文を管理するプログラム
 * 						受注一覧を表示する機能
 * 作成者：大久保嵩琉
 * 作成日：20240620
 */
import java.io.IOException;
import java.util.ArrayList;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラー内容を受け取るerror変数を宣言し初期化
		String error = "";
		//エラー時の遷移先を指定するcmd変数を宣言し初期化
		String cmd = "";
		//OrderDAOクラスをインスタンス化
		OrderDAO orderObj = new OrderDAO();
		//OrderListをインスタンス化
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			//商品一覧を全件検索
			list = orderObj.selectAll();
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示を行えませんでした。";
			cmd = "login";
		} finally {
			//エラーがなければ受注一覧を表示
			if (error.equals("")) {
				//受注状況をorderlistという名前でスコープに登録
				request.setAttribute("orderlist", list);
				//受注一覧画面に遷移
				request.getRequestDispatcher("/view/orderlist.jsp").forward(request, response);
			} else {
				//エラー画面からの遷移先をスコープにセット
				request.setAttribute("cmd", cmd);
				//エラー内容をスコープにセット
				request.setAttribute("error", error);
				//エラー画面に遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}