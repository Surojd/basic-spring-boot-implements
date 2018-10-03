/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techinherit.basic.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterPage extends OncePerRequestFilter {

    public FilterPage() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String proto = request.getHeader("x-forwarded-proto");
        if (proto != null && proto.equals("http")) {
            response.sendRedirect("https://" + request.getServerName() + request.getRequestURI());
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
