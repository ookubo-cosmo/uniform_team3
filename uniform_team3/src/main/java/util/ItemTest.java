package util;

import java.util.ArrayList;

import bean.Item;
import dao.ItemDAO;



public class ItemTest {
	public static void main(String[] args) {
		ItemDAO itemObj = new ItemDAO();
		ArrayList<Item> list = itemObj.selectAll();
		
	}
}
