<%@page contentType="text/html; charset=UTF-8"%>

<%
//リクエストスコープからmessageを取得
String message = (String)request.getAttribute("message");

String ownerid = "";
String password = "";

//クッキーを取得
Cookie[] ownerCookie = request.getCookies();

//クッキーがある場合、ownerid,passwordを取得
if (ownerCookie != null){
for (int i = 0 ; i < ownerCookie.length ; i++){
if (ownerCookie[i].getName().equals("ownerid")){
ownerid = ownerCookie[i].getValue();
}
if (ownerCookie[i].getName().equals("password")){
password = ownerCookie[i].getValue();
}
}
}

%>

<html>
<head>
<title>管理者ログイン画面</title>
<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div id="wrap">


<%@ include file="/common/header.jsp" %>
<div id="menu">
<div class="container">
<div id="nav">
<a href ="<%=request.getContextPath()%>/itemlist" >[商品一覧]</a>
</div>
<div id="page_title">
<h2>管理者ログイン</h2>
</div>
</div>
</div>

<h3 align="center">
<%if (message != null) { %><!-- 管理者情報が間違ってる場合、メッセージを出力 -->
<%=message %>
<%} %>
</h3>
<div id="main" class="container">
<form action="<%=request.getContextPath()%>/login" method="post">
<table class="login" align="center">
<tr>
<th>ユーザー</th>
<td><input type="text" name="ownerid" value=<%=ownerid %>></td>
</tr>
<tr>
<th>パスワード</th>
<td><input type="password" name="password" value=<%=password %>></td>
</tr>
</table>
<input type="submit" align="center" value="ログイン">
</form>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>