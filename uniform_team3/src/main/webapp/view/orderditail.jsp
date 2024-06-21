<%@page contenttype="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Item,bean.Order"%>
<%
Order order = (Order) request.getAttribute("order");
%>

<html>
<head>
<title>受注情報詳細</title>
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
</head>
<%@include file="/./common/header.jsp"%>
<body>
	<form action="<%=request.getContextPath()%>/itemlist">
		<table style="text-align: center; margin: auto;">
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
				<td><%=order.getItemid()%></td>
			</tr>
			<tr>
				<td>購入個数</td>
				<td><%=order.getQuantity()%></td>
			</tr>
			<tr>
				<td>合計金額</td>
				<td><%=total%></td>
			</tr>
			<tr>
				<td>備考</td>
				<td><%=order.getNote()%></td>
			</tr>

			<select name="deposit_status">
				<option Value="1">未納</option>
				<option Value="2">入金済み</option>
			</select>
			<select name="shipment_status">
				<option Value="3">発送済み</option>
				<option Value="4">未発送</option>
			</select>

			<input type="submit" Value="更新">

		</table>
	</form>
</body>
</html>