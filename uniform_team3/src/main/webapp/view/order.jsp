<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,bean.Item,bean.Item,dao.ItemDAO
"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文画面</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/view/css/style.css">
<%
//商品DAOをインスタンス化
ItemDAO itemDao = new ItemDAO();
//商品リストを取得
ArrayList<Item> itemList = itemDao.selectAll();
//在庫数を管理する変数を宣言して初期化
String Stocks = "";
//全ての在庫数を,区切りでStringに
for(int i = 0;i<itemList.size();i++){
	Stocks = Stocks + itemList.get(i).getStock()+ ",";
}
%>
<script>
//選ばれた商品に応じた在庫数を表示する
function updateValues(selectedValue){
	//在庫数の文字列を取得
	var Stocks = "<%= Stocks%>";
	//","区切りの文字列を配列に
	const stocksstr = Stocks.split(",");
	//<select>タグだと、indexが1多いので1引く
	const index = parseInt(selectedValue)-1;
	//idがstockの要素に在庫数を入れる
	document.getElementById("stock").innerText = stocksstr[index]+"枚";
}
</script>
</head>
<body>

	<div id="wrap">
		<h1 style="text-align: center">注文画面</h1>
		<%@include file="../common/header.jsp"%>
		<%@include file="../common/logo.jsp"%>
		<main class="order" style="margin-left:400px;">
			<div class="h1Border"></div>
			<form action="${pageContext.request.contextPath}/order" method="post">
				<div>
					<label for="email">メールアドレス:</label><br>
					<input type="email" id="email"
						name="email"
						pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
						required><br>
				</div>

				<div>
					<label for="name">お名前</label><br> <input type="text" id="name"
						name="name" required><br>
				</div>

				<div>
					<label for="address">住所</label><br>
					<textarea id="address" name="address" required></textarea>
					<br>
				</div>

				<div>
					<label for="itemid">商品の種類</label><br>
					<select id="itemid" name="itemid" onchange="updateValues(this.value)" required><br>
						<%-- 商品一覧を動的に生成する --%>
						<option value="">選択してください</option>
						<% 
                        for (Item item : itemList) { %>
						<option value="<%= item.getItemid()%>">
							<%= item.getItemName() %>
						</option>
						<% } %>
					</select>
					
				</div>
				<div>
					<label for="quantity">購入個数</label><br> <input type="number"
						id="quantity" name="quantity" min="1" required><br>
				</div>
				<div>
					<label>在庫個数</label><br>
					<spen id="stock"></spen><br><br>
				</div>

				<div>
					<label for="note">備考</label><br>
					<textarea id="note" name="note"></textarea>
					<br>
				</div>

				<button type="submit">購入確定</button>
				
			</form>
			<br><a href ="<%=request.getContextPath()%>/itemlist" style="margin-right:400px">商品一覧に戻る</a>
		</main>
		<footer style="position: static;">
<div class="container">
<h4>Copyright&copy; 2024 All Right Reserved.</h4>
</div>
</footer>

	</div>
	
	
</body>
</html>