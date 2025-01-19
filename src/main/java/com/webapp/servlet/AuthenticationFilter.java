package com.webapp.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isStaticResource = httpRequest.getRequestURI().matches(".*(\\.css)$");
        isStaticResource = isStaticResource || httpRequest.getRequestURI().endsWith("/jsp/index.jsp");

        boolean isStudentURI = httpRequest.getRequestURI().endsWith("/jsp/student.jsp")
                || httpRequest.getRequestURI().endsWith("/jsp/student-means.jsp")
                || httpRequest.getRequestURI().endsWith("/jsp/student-schedule.jsp")
                || httpRequest.getRequestURI().endsWith("/jsp/student-exams.jsp")
                || httpRequest.getRequestURI().endsWith("/get-student-schedule")
                || httpRequest.getRequestURI().endsWith("/get-student-statistics")
                || httpRequest.getRequestURI().endsWith("get-student-exams")
                || httpRequest.getRequestURI().endsWith("/js/show-student-sessions.js")
                || httpRequest.getRequestURI().endsWith("/js/show-student-exams.js")
                || httpRequest.getRequestURI().endsWith("/js/get-student-result.js");

        boolean isStudent = (session != null)
                && (session.getAttribute("userID") != null)
                && !session.getAttribute("userID").equals("admin");

        boolean isAdmin = (session != null)
                && (session.getAttribute("userID") != null)
                && session.getAttribute("userID").equals("admin");


        if (isLoginRequest || isStaticResource) {
            chain.doFilter(request, response);
        } else if (isAdmin) {
            chain.doFilter(request, response);
        } else if (isStudent && isStudentURI) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/index.jsp");
        }
    }
}


