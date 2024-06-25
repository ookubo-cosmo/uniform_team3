<!-- 
プログラム名		:ユニフォーム受注システム
プログラムの説明	:ユニフォームの注文を管理するプログラム

画面の概要:			注文一覧を表示する
作成者：大久保嵩琉
作成日：20240621
 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Item,bean.Item,dao.ItemDAO
"%>

<%
//リクエストスコープからのデータの取得
ArrayList<Item> itemList = (ArrayList<Item>) request.getAttribute("itemList");
//ItemDAOをインスタンス化
ItemDAO itemDAO = new ItemDAO();
%>

<html>
<head>
<title>商品一覧</title>
<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
</head>

<%@ include file="/common/header.jsp" %>

<body>
<div id="menu">
				<div class="container">
					<div id="nav">
							<a href="<%=request.getContextPath()%>/view/login.jsp">管理者ログイン</a>
					</div>
					<%@include file="../common/logo.jsp"%>
					<div id="page_title">
						<h2>商品一覧</h2>
					</div>
					
				</div>
			</div>
	<div id="main" class="container">
	<h3>
		<a href="<%=request.getContextPath()%>/view/order.jsp" align="center">注文画面へ</a>
	</h3>
	<table class="list-table">
		<thead>
			<tr>
				<th>商品NO</th>
				<th>商品名</th>
				<th>在庫数</th>
				<th>金額</th>
				<th>商品画像</th>
			</tr>
		</thead>
					<!-- 書籍情報を全て表示 -->
			<tbody>
			
				<%
				if (itemList != null) {
					for (int i = 0; i < itemList.size(); i++) {
				%>
				<tr>
					<td style="text-align: center; width: 100"><%=itemList.get(i).getItemid()%></td>
					<td style="text-align: center; width: 100"><%=itemList.get(i).getItemName()%></td>
					<td style="text-align: center; width: 100"><%=itemList.get(i).getStock()%></td>
					<td style="text-align: center; width: 100"><%=itemList.get(i).getPrice()%></td>
					<td><img src="<%=request.getContextPath()%>/file/<%=itemList.get(i).getImage()%>"></td>
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