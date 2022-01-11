package com.edu.cs.go.bet.auth.config;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SteamOpenIdIdentifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new FilteredRequest(request), response);
        // save user
    }

    static class FilteredRequest extends HttpServletRequestWrapper {

        /* These are the characters allowed by the Javascript validation */
        static String allowedChars = "+-0123456789#*";

        public FilteredRequest(ServletRequest request) {
            super((HttpServletRequest) request);
        }

        public String sanitize(String input) {
            return "https://steamcommunity.com/openid";
        }

        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            if ("openid_identifier".equals(paramName)) {
                value = sanitize(value);
            }
            return value;
        }

        public String[] getParameterValues(String paramName) {
            String[] values = super.getParameterValues(paramName);
            if ("openid_identifier".equals(paramName)) {
                for (int index = 0; index < values.length; index++) {
                    values[index] = sanitize(values[index]);
                }
            }
            return values;
        }
    }
}
