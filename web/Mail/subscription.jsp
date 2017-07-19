<%@page import="Mail.MailModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		String email, action, msg = null;
		action = request.getParameter("action");
		email = request.getParameter("Email");

		MailModel mail = new MailModel();

		if (action.equals("subscribe"))
		{
			msg = mail.addNewSubscription(email, request);
		}
		else if (action.equals("unsubscribe"))
		{
			msg = mail.unsubscribe(email);
			session.setAttribute("lastPage", "home");
		}

		session.setAttribute("msg", msg);
		try
		{
			response.sendRedirect(session.getAttribute("lastPage").toString());
		}
		catch(Exception e){}
	%>

</body>
</html>