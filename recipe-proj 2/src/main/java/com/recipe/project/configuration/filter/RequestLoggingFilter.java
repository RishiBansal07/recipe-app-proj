package com.recipe.project.configuration.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestLoggingFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public RequestLoggingFilter() {
    }

    public static void setLogger(Logger passedLogger) {
        log = passedLogger;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            this.logStartRequest((HttpServletRequest) request);
            chain.doFilter(request, response);
        } finally {
            this.logRequestAndResponse((HttpServletRequest) request, (HttpServletResponse) response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    private void logStartRequest(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Before ");
        this.addRequestDescription(buffer, request);
        log.info(buffer.toString());
    }

    private void logRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(response.getStatus());
        buffer.append("    ");
        this.addRequestDescription(buffer, request);
        buffer.append(" [");
        this.addHeaders(buffer, request);
        buffer.append("]");
        log.info(buffer.toString());
    }

    private void addRequestDescription(StringBuffer buffer, HttpServletRequest request) {
        buffer.append(request.getMethod());
        buffer.append(" ");
        buffer.append(request.getRequestURI());
        if (null != request.getQueryString()) {
            buffer.append("?");
            buffer.append(request.getQueryString());
        }

    }

    private void addHeaders(StringBuffer headers, HttpServletRequest request) {
        int count = 0;

        for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements(); ++count) {
            if (count > 0) {
                headers.append("; ");
            }

            String name = (String) headerNames.nextElement();
            headers.append(name);
            headers.append(": ");
            this.addHeaderValues(headers, request.getHeaders(name));
        }

    }

    private void addHeaderValues(StringBuffer buffer, Enumeration<String> headers) {
        for (int count = 0; headers.hasMoreElements(); ++count) {
            if (count > 0) {
                buffer.append(", ");
            }

            buffer.append((String) headers.nextElement());
        }

    }
}
