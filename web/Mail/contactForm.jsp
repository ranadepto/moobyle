<%@page import="Mail.MailModel"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="Mail.MailDAO"%>
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
		String Name, Email, Telephone, message;
		Name = request.getParameter("Name");
		Email = request.getParameter("Email");
		Telephone = request.getParameter("Telephone");
		message = request.getParameter("message");
		
		MailDAO mailDAO=new MailDAO(Name, Email, Telephone, message, Timestamp.valueOf(LocalDateTime.now()));
		MailModel mail=new MailModel();
		
		if(mail.addNewContactForm(mailDAO)!=0)
		{
			session.setAttribute("msg", "Thanks for your submission Mr./Mrs. "+Name+". Our team will contact you asap.");
		}
		else
		{
			session.setAttribute("contactForm", mailDAO);
			session.setAttribute("msg", "Failed to submit your request. Please try again.");
		}
		
		try
		{
			response.sendRedirect(session.getAttribute("lastPage").toString());
		}
		catch(Exception e){}
			
	%>

</body>
</html>