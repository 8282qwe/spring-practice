<%@ page import="emaillist.repository.EmailListRepository" %>
<%@ page import="emaillist.vo.EmailListVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");

    String firstName = request.getParameter("fn");
    String lastName = request.getParameter("ln");
    String email = request.getParameter("email");

    EmailListRepository dao = new EmailListRepository();
    EmailListVo vo = new EmailListVo();
    vo.setFirstName(firstName);
    vo.setLastName(lastName);
    vo.setEmail(email);

    dao.insert(vo);

    response.sendRedirect("/emaillist01");
%>
