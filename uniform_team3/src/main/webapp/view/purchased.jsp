<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Item,bean.Order"%>

<%
Item item = (Item) request.getAttribute("item");
Order order = (Order) request.getAttribute("order");
%>

<html>
<head>
<title>購入確認</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<%@include file="../common/header.jsp"%>
<%@include file="../common/logo.jsp"%>
<body>
	<div id="wrap">
		<div id="menu">
			<div class="container">
				<div id="page_title ">
					<h2>購入確認</h2>
				</div>
			</div>
		</div>

		<div class="container">
			<h2>注文が完了しました。</h2>
			<table>
				<h3>お届け先</h3>
				<tr>
					<td>名前：</td>
					<td><%=order.getName()%></td>
				</tr>
				<tr>
					<td>住所：</td>
					<td><%=order.getAddress()%></td>
				</tr>
			</table>
			<table>
				<h3>注文内容</h3>
				<tr>
					<td>商品名：</td>
					<td><%=item.getItemName()%></td>
				</tr>
				<tr>
					<td>個数：</td>
					<td><%=order.getQuantity()%></td>
				</tr>
				<tr>
					<td>合計：</td>
					<td><%=item.getPrice() * order.getQuantity()%></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/itemlist">商品一覧に戻る</a><br>
		</div>
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>
</html>