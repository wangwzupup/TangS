<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="prc" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% Date date = new Date(); %>
<% request.setAttribute("date",date); %>
<html>
    <head>

    </head>

    <body>
        <h1 style="text-align: center">大爷来查点啥呀！</h1>
        <div style="text-align: center">
            <form action="http://localhost:8888/tss/result.jsp" method="post">
                <input type="text" name="querys"/>
                <input type="submit" value="点击搜索" />
            </form>
        </div>
    </body>
</html>
