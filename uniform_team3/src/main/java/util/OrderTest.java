package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;

import bean.Order;
import dao.OrderDAO;

public class OrderTest {
	public static void main(String[] args) {
		OrderDAO orderObj = new OrderDAO();
		ArrayList<Order> list = orderObj.selectAll();
		//System.out.println(list.get(0).getOrderid());
		//System.out.println(list.get(0).getOrder_date());
		Date date = list.get(0).getOrder_date();
		LocalDate ldate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
		//System.out.println(ldate);
		LocalDate startdate = ldate.withDayOfMonth(1);
		LocalDate lastdate = ldate.with(TemporalAdjusters.lastDayOfMonth());
		//System.out.println(startdate);
		//System.out.println(lastdate);
		System.out.println(!(startdate.isAfter(ldate) || lastdate.isBefore(ldate)));
	}
}
