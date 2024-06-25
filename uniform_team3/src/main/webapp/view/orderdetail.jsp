<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Item,bean.Order,dao.ItemDAO"%>
<%
Order order = (Order) request.getAttribute("order");
ItemDAO itemDAO = new ItemDAO();
Item item = itemDAO.selectByItemid(order.getItemid());
%>

<html>
<head>
<title>受注情報詳細</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
<div id="wrap">
<%@include file="../common/header.jsp"%>
<%@include file="../common/logo.jsp"%>
	<div id="menu">
		<div class="container">
			<div id="nav">
				<a href="<%=request.getContextPath()%>/orderlist">受注一覧へ</a>
			</div>
			<div id="page_title">
				<h2>受注情報詳細</h2>
			</div>
		</div>
	</div>
	<div id="main" class="container">
		<form action="<%=request.getContextPath()%>/orderDetail" method="post">
			<table class="input-table">
				<tbody>
					<tr>
						<td>オーダーID</td>
						<td><%=order.getOrderid()%></td>
					</tr>
					<tr>
						<td>名前</td>
						<td><%=order.getName()%></td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><%=order.getEmail()%></td>
					</tr>
					<tr>
						<td>住所</td>
						<td><%=order.getAddress()%></td>
					</tr>
					<tr>
						<td>商品の種類</td>
						<td><%=item.getItemName()%></td>
					</tr>
					<tr>
						<td>購入個数</td>
						<td><%=order.getQuantity()%></td>
					</tr>
					<tr>
						<td>合計金額</td>
						<td>
							<%
							out.println(item.getPrice() * order.getQuantity());
							%>
						</td>
					</tr>
					<tr>
						<td>備考</td>
						<td><%=order.getNote()%></td>
					</tr>
					<tr>
						<td>入金情報</td>
						<td><select name="deposit_status" id="deposit_status">
								<option Value="1"
									<%if (order.getDeposit_status() == 1) {
										out.print("selected");
									}%>>未納
								</option>
								<option Value="2"
									<%if (order.getDeposit_status() == 2) {
										out.print("selected");
									}%>>入金済み
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>発送状況</td>
						<td><select name="shipment_status" id="shipment_status">
								<option Value="1"
									<%if (order.getShipment_status() == 1) {
										out.print("selected");
										}%>>未
								</option>
								<option Value="2"
									<%if (order.getShipment_status() == 2) {
										out.print("selected");
										}%>>発送準備中
								</option>
								<option Value="3"
									<%if (order.getShipment_status() == 3) {
										out.print("selected");
									}%>>発送済み
								</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="orderid" value="<%=order.getOrderid()%>">
			<input type="submit" Value="更新">
		</form>
		</div>
		<%@ include file="/common/footer.jsp" %>
	</div>
</body>
</html>