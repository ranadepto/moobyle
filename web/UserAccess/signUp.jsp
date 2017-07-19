<%@page import="CommonClasses.Email"%>
<%@page import="UserAccess.UserAccessModel"%>
<%@page import="UserAccess.UserAccessDAO"%>
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
		String name, email, password, confirmPassword;
		name = request.getParameter("Name");
		email = request.getParameter("Email");
		password = request.getParameter("Password");
		confirmPassword = request.getParameter("confirmPassword");

		UserAccessModel userAccessModel = new UserAccessModel();
		int status = userAccessModel.addNewUser(name, email, password);

		if (status > 0)
		{
			new Email().send(email, "New Account On Moobyle",
					"Dear Mr./Mrs. " + name
							+ "\nYour new account on Moobyle has been created successfully. Here is your login information-\n\nUsername: "
							+ email + "\nPassword: " + password + "\n\n\n\nThanks & Regards,\nThe Moobyle Team");
			UserAccessDAO userAccessInfo = userAccessModel.getUserAccessInfo(email, password);
			if (userAccessInfo != null)
			{
				session.setAttribute("userAccessInfo", userAccessInfo);
				session.setAttribute("msg",
						"Hello " + userAccessInfo.getName()
								+ "!\\nNew account has been created succcessfully and you have logged in automatically! Please check your mail "
								+ email + " for detail information.");
			}
			else
			{
				session.setAttribute("msg", "Sorry, invalid credentials!");
			}
		}
		else
		{
			session.setAttribute("msg", "Sorry, registration failed! Try again.");
		}

		try
		{
			response.sendRedirect(session.getAttribute("lastPage").toString());
		}
		catch(Exception e){}
	%>

</body>
</html>