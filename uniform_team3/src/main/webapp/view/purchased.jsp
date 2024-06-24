<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Item,bean.Order"%>

<%Item item = (Item) request.getAttribute("item");
	Order order = (Order) request.getAttribute("order");
	%>

<html>
<head>
<title>購入確認</title>
<link href="<%=request.getContextPath()%>/css/style.css"rel="stylesheet" />
</head>
<%@include file="/./common/header.jsp"%>
<body>
	<h1>購入確認</h1>
	<h2>注文が完了しました。</h2>

	<div style="margin: auto;">
		お届け先<br>
		名前：<%=order.getName()%><br>
		住所：<%=order.getAddress()%><br>
		<br>
		注文内容<br>
		商品名：<%=item.getItemName() %><br>
		個数：<%=order.getQuantity() %><br>
		合計：<%=item.getPrice()*order.getQuantity() %><br>
		<br>
		<a href="<%=request.getContextPath()%>/itemlist">商品一覧に戻る</a>
	</div>
</body>
</html>