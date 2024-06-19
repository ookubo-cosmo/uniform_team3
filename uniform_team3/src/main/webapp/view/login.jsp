<!-- 
プログラム名：		書籍管理プログラムWeb版 Ver2.0
プログラムの説明：	
					
画面の概要:			
作成者：大久保嵩琉
作成日：
 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%
//リクエストスコープからのデータの取得
String message = (String) request.getAttribute("message");
%>

<html>
<head>
<title>書籍管理メニュー画面</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" />
</head>
<body>
	<header>
		<%@include file="../common/header.jsp"%>
	</header>
	<div style="margin-bottom: 50px"></div>
	<hr
		style=" height: 2px; background-color: black; width: 950px">
	<div style="margin-bottom: 350px;margin-top: 50px">
	<%if(message!=null) {%>
		<p style="margin-left:30%;"><%= message%></p>
		<%} %>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<table style="margin:  auto">
				<tr>
					<th class="color" style="width: 150px;">ユーザー</th>
					<td><input type=text size="30" name="userid"
						style="width: 200px; height: 25px;"></input></td>
				</tr>
				<tr>
					<th class="color" style="width: 150px;">パスワード</th>
					<td><input type=password size="30" name="password"
						style="width: 200px; height: 25px;"></input></td>
				</tr>
				<tr>
					<td height="100px" colspan=2 style="text-align: center">
						<input type="submit" value="ログイン">
					</td>
				</tr>
			</table>
		</form>

	</div>
	<footer>
		<%@include file="../common/footer.jsp"%>
	</footer>
</body>
</html>