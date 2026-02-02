package dev.gmorikawa.toshokan.infrastructure.http.filters;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class QueryFiltersAttributeFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getRequestURI().startsWith("/api/authors")) {
            if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
                Map<String, String[]> filters = httpRequest.getParameterMap();
                System.out.println("Filters: " + filters);
            }
        }

        chain.doFilter(request, response);
    }

}
