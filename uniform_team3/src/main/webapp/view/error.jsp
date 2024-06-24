<!-- 
/*
 * プログラム名		:ユニフォーム受注システム
 * プログラムの説明	:ユニフォームの注文を管理するプログラム
 * 						
 * 作成者：大久保嵩琉
 * 作成日：20240624
 */
 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
String pagetest;
%>


<html>
<head>
<title>Error</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" />
</head>
<header>
	<%@include file="../common/header.jsp"%>
</header>
<body>
	<div>
		<table style="text-align: center; margin: auto;">
			<tr>
				<td>●●エラー</td>
			</tr>

			<!-- エラー内容 -->
			<tr>
				<td><%=error%></td>
			</tr>
			<tr>
				<!-- cmdの内容がmenuだったらメニューに遷移それ以外なら一覧表示画面に遷移 -->
				<td><a href=
						<%if (cmd.equals("menu")) {%>
							"./view/menu.jsp"
						<%} else if(cmd.equals("list")){%>
							"./list"
						<%}else{%>
							"./logout"
						<% }%>
						">
						<%
						if (cmd.equals("menu")) {
						%> メニューに戻る <%
						} else if(cmd.equals("list")) {
						%> 一覧表示に戻る <%
						} else{
						%> ログイン画面へ <%
						}
						%> 
				</a></td>
			</tr>
		</table>
	</div>
</body>
<footer>
	<% //include file="../common/footer.jsp"%>
</footer>
</html>