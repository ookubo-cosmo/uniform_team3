package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Item;
import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itemlist")
public class ItemListServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		try {
			ItemDAO itemObj = new ItemDAO();
			ArrayList<Item> list = itemObj.selectAll();
			request.setAttribute("itemList", list);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示を行えませんでした。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/itemlist.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}