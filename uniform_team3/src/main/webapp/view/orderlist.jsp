<!-- 
プログラム名：		書籍管理プログラムWeb版 Ver1.0
プログラムの説明：	書籍の書籍番号、タイトル、価格を管理するプログラム
					主な機能は、書籍の登録、削除、変更と一覧の表示
					
画面の概要:			書籍情報一覧を表示し、それらに対する操作を受け付ける
作成者：大久保嵩琉
作成日：20240508
 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Order,bean.Item,dao.ItemDAO,java.time.LocalDate,
				java.time.temporal.TemporalAdjusters,java.time.LocalDateTime
"%>

<%
//リクエストスコープからのデータの取得
ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orderlist");
//ItemDAOをインスタンス化
ItemDAO itemDAO = new ItemDAO();
%>

<html>
<head>
<title>受注一覧</title>
</head>

<header>
	<% //@include file="../common/header.jsp"%>
</header>
<body>

	<a href="tologout">ログアウト</a>
	<h1>受注管理システム</h1>
	<hr>
	<table align="right">
		<thead>
			<tr>
				<th>先月の売り上げ</th><!-- 製作途中 -->
				<td>
					<%
						//先月の売り上げ変数を初期化
						int lsatMonthSales = 0;
						LocalDate today = LocalDate.now();
						LocalDate lastMonth = today.minusMonths(1);
						//先月の月初で初期化
						LocalDate lastMonthstartdate = lastMonth.withDayOfMonth(1);
						//先月の月末で初期化
						LocalDate lastMonthenddate = lastMonth.with(TemporalAdjusters.lastDayOfMonth());
						//受注一覧情報を全て
						for(int i = 0 ; i < orderList.size();i++){
							//受注日を取得し初期化
							LocalDate orderDate = orderList.get(i).getOrder_date().toLocalDateTime().toLocalDate();
							//受注日が今月の期間内であれば
							if(!(lastMonthstartdate.isAfter(orderDate) || lastMonthenddate.isBefore(orderDate))){
								//注文ごとの合計金額を取得し
								Item item = itemDAO.selectByItemid(orderList.get(i).getItemid());
								//今月の合計金額に加算
								lsatMonthSales += item.getPrice()*orderList.get(i).getQuantity();
							}
						}
						//今月の合計金額を表示
						out.print(lsatMonthSales);
					%>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>今月の売り上げ</th><!-- 製作途中 -->
				<td>
					<%
						//今月の売り上げ変数を初期化
						int thisMonthSales = 0;
						//今月の月初で初期化
						LocalDate startdate = today.withDayOfMonth(1);
						//今月の月末で初期化
						LocalDate enddate = today.with(TemporalAdjusters.lastDayOfMonth());
						//受注一覧情報を全て
						for(int i = 0 ; i < orderList.size();i++){
							//受注日を取得し初期化
							LocalDate orderDate = orderList.get(i).getOrder_date().toLocalDateTime().toLocalDate();
							//受注日が今月の期間内であれば
							if(!(startdate.isAfter(orderDate) || enddate.isBefore(orderDate))){
								//注文ごとの合計金額を取得し
								Item item = itemDAO.selectByItemid(orderList.get(i).getItemid());
								//今月の合計金額に加算
								thisMonthSales += item.getPrice()*orderList.get(i).getQuantity();
							}
						}
						//今月の合計金額を表示
						out.print(thisMonthSales);
					%>
				</td>
			</tr>
		</tbody>
	</table>

	<!-- 一覧表示 -->
	<div style="margin-bottom: 250px">

		<table style="margin: auto;">
			<thead>
				<tr>
					<th>受注NO</th>
					<th>氏名</th>
					<th>商品種類</th>
					<th>個数</th>
					<th>合計金額</th>
					<th>発注日</th>
					<th>入金状況</th>
					<th>発送状況</th>
					<th>操作</th>
				</tr>
			</thead>
			<!-- 書籍情報を全て表示 -->
			<tbody>
			
				<%
				if (orderList != null) {
					for (int i = 0; i < orderList.size(); i++) {
				%>
				<tr>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getOrderid()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getName()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getItemid()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getQuantity()%></td>
					<td style="text-align: center; width: 100"><!-- 合計金額 -->
						<!-- 商品IDから単価を取得して個数を掛ける -->
						<% 
						
						//この行の商品IDからItemオブジェクトを取得
						Item item = itemDAO.selectByItemid(orderList.get(i).getItemid());
						//この行の単価と個数を掛けた値を画面に表示
						out.println(item.getPrice()*orderList.get(i).getQuantity());
						%>
					</td>
					
					
					<td style="text-align: center; width: 100"><%=orderList.get(i).getOrder_date()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getDeposit_status()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getShipment_status()%></td>
					<td style="text-align: center;">
						<table style="width: 100%">
							<td style="text-align: center;"><a
								href="#">詳細</a></td>
						</table>
					</td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>