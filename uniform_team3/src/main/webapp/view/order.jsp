<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page
	import="java.util.ArrayList,bean.Item,bean.Item,dao.ItemDAO
"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文画面</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/view/css/style.css">
<script>
//商品が選択された際に在庫数を表示する
function updateStock(){
var itemid = document.getElementById("itemid").value;
var itemList = <%= request.getAttribute("itemList") %>;//商品リストをJavascriptの配列として取得する
for(var i = 0; i < itemList.length; i++){
if (itemList[i].itemid == itemid){
document.getElementById("stock").innerText = "在庫数:" + itemList[i].stock;
break;
}
}
}

window.onload = function(){
updaateStock();
};
    </script>
</head>
<body>
<%
ItemDAO itemDao = new ItemDAO();
ArrayList<Item> itemList = itemDao.selectAll();
%>
	<div class="wrapper">
		<h1 style="text-align: center">注文画面</h1>
		<%@include file="../common/logo.jsp"%>
		<main class="order">
			<div class="h1Border"></div>
			<form action="${pageContext.request.contextPath}/order" method="post">
				<div>
					<label for="email">メールアドレス:</label> <input type="email" id="email"
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
					<label for="itemid">商品の種類</label> 
					<select id="itemid" name="itemid"
						required><br>
						<%-- 商品一覧を動的に生成する --%>
						<option value="">選択してください</option>
						<%-- 商品一覧を動的に生成 --%>
						<option value="">選択してください</option>
						<% 
                        for (Item item : itemList) { %>
						<option value="<%= item.getItemid() %>">
							<%= item.getItemName() %>
						</option>
						<% } %>
					</select>
					<spen id="stock"></spen>
				</div>
				<div>
					<label for="quantity">購入個数</label><br> <input type="number"
						id="quantity" name="quantity" min="1" required><br>
				</div>

				<div>
					<label for="note">備考</label><br>
					<textarea id="note" name="note"></textarea>
					<br>
				</div>

				<button type="submit">購入確定</button>
			</form>
			<br> <a href="itemlist.jsp">商品一覧に戻る</a>
		</main>
	</div>
</body>
</html>