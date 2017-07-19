<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="javax.script.ScriptEngine"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!doctype html>
<html lang="en">
    <head>
        <title>SO question 2204870</title>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
        <script src="js/global.js"></script>
        <link rel="stylesheet" href="css/global.css">
    </head>
    <body>
        <ul>
            <li><a href="home">Home</a></li>
            <li><a href="about">About</a></li>
            <li><a href="product">Product</a></li>
            <li><a href="mail">Contact</a></li>
        </ul>
        <%

        out.println("<br>"+request.getRequestURL());
        out.println("<br>"+request.getRequestURI());
        out.println("<br>"+request.getContextPath());
        out.println("<br>"+request.getScheme());
        out.println("<br>"+request.getServerName());
        out.println("<br>"+request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
        out.println("<br>"+request.getHeader("User-Agent"));
        out.println("<br>"+request.getLocalName());
        out.println("<br>");

        StringBuffer javascript = null;
        ScriptEngine runtime = null;

        try {
            runtime = new ScriptEngineManager().getEngineByName("javascript");
            javascript = new StringBuffer();

            javascript.append("1+1");

            String result = runtime.eval(javascript.toString()).toString();

            System.out.println("Result: " + result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        %>
        
 <%
out.print(request.getServerName()) ;
%>
 
        <%@ include file="index.jsp" %> 
        
         
    </body>
</html>