<%@page import="UserAccess.UserAccessDAO"%>
<%@page import="UserAccess.UserAccessModel"%>
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
		String email, password;
		email = request.getParameter("Email");
		password = request.getParameter("Password");

		UserAccessDAO userAccessInfo = new UserAccessModel().getUserAccessInfo(email, password);
		if (userAccessInfo != null)
		{
			session.setAttribute("userAccessInfo", userAccessInfo);
			session.setAttribute("msg", "Hello " + userAccessInfo.getName() + "!\\nWelcome Back!");
		}
		else
		{
			session.setAttribute("msg", "Sorry, invalid credentials!");
		}

		try
		{
			response.sendRedirect(session.getAttribute("lastPage").toString());
		}
		catch(Exception e){}
	%>

</body>
</html>