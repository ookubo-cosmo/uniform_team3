<!-- 
プログラム名：		書籍管理プログラムWeb版 Ver1.0
プログラムの説明：	書籍の書籍番号、タイトル、価格を管理するプログラム
					主な機能は、書籍の登録、削除、変更と一覧の表示
					
画面の概要:			書籍情報一覧を表示し、それらに対する操作を受け付ける
作成者：大久保嵩琉
作成日：20240508
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
</head>

<header>
	<%
	//@include file="../common/header.jsp"
	%>
</header>
<body>
	<a href="tologout">管理者ログイン</a>
	<h1>商品一覧</h1>
	<hr>
	<h3>
		<a href="toorderlist">注文画面へ</a>
	</h3>
	<table border="1" style="border-collapse: collapse">
		</thead>
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
					<td style="text-align: center; width: 100"><%=itemList.get(i).getImage()%></td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
	</table>
</body>
</html>