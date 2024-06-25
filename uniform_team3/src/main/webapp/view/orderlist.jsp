<!-- 
プログラム名		:ユニフォーム受注システム
プログラムの説明	:ユニフォームの注文を管理するプログラム

画面の概要:			注文一覧を表示する
作成者：大久保嵩琉
作成日：20240621
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
//Itemをインスタンス化
Item item = new Item();

%>

<html>
<head>
<title>受注一覧</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/view/css/style.css">
</head>

<header>
	<% //@include file="../common/header.jsp"%>
</header>
<body>
	<a href="<%=request.getContextPath()%>/logout">ログアウト</a>　
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
							//入金済みのもので
							if(orderList.get(i).getDeposit_status() == 2){
								//受注日を取得し初期化
								LocalDate orderDate = orderList.get(i).getOrder_date().toLocalDateTime().toLocalDate();
								
								
								//受注日が今月の期間内であれば
								if(!(lastMonthstartdate.isAfter(orderDate) || lastMonthenddate.isBefore(orderDate))){
									//注文ごとの合計金額を取得し
									item = itemDAO.selectByItemid(orderList.get(i).getItemid());
									//先月の合計金額に加算
									lsatMonthSales += item.getPrice()*orderList.get(i).getQuantity();
								}
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
							//入金済みのもので
							if(orderList.get(i).getDeposit_status()==2){
								//受注日を取得し初期化
								LocalDate orderDate = orderList.get(i).getOrder_date().toLocalDateTime().toLocalDate();
								//受注日が今月の期間内であれば
								if(!(startdate.isAfter(orderDate) || enddate.isBefore(orderDate))){
									//注文ごとの合計金額を取得し
									item = itemDAO.selectByItemid(orderList.get(i).getItemid());
									//今月の合計金額に加算
									thisMonthSales += item.getPrice()*orderList.get(i).getQuantity();
								}
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
			<!-- 注文情報を全て表示 -->
			<tbody>
			
				<%
				if (orderList != null) {
					for (int i = 0; i < orderList.size(); i++) {
				%>
				<tr>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getOrderid()%></td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getName()%></td>
					<td style="text-align: center; width: 100">
						<%
							//商品IDから商品一覧オブジェクトを取得
							item = itemDAO.selectByItemid(orderList.get(i).getItemid());
							//商品名を表示
							out.println(item.getItemName());
						%>
					
					</td>
					<td style="text-align: center; width: 100"><%=orderList.get(i).getQuantity()%></td>
					<td style="text-align: center; width: 100"><!-- 合計金額 -->
						<!-- 商品IDから単価を取得して個数を掛ける -->
						<% 
						
						//この行の商品IDからItemオブジェクトを取得
						item = itemDAO.selectByItemid(orderList.get(i).getItemid());
						//この行の単価と個数を掛けた値を画面に表示
						out.println(item.getPrice()*orderList.get(i).getQuantity());
						%>
					</td>
					
					
					<td style="text-align: center; width: 100"><%=orderList.get(i).getOrder_date()%></td>
					<td style="text-align: center; width: 100">
						<%
							if(orderList.get(i).getDeposit_status() == 1){
								out.println("入金待ち");
							}else{
								out.println("入金済");
							}
						%>
					</td>
					<td style="text-align: center; width: 100">
						<%
							if(orderList.get(i).getShipment_status() == 1){
								out.println("未");
							}else if(orderList.get(i).getShipment_status() == 2){
								out.println("発送準備中");
							}else{
								out.println("発送済");
							}
						%>
					</td>
					<td style="text-align: center;">
						<table style="width: 100%">
							<td style="text-align: center;">
							<a href="<%=request.getContextPath()%>/orderDetail?orderid=<%=orderList.get(i).getOrderid()%>">詳細</a></td>
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