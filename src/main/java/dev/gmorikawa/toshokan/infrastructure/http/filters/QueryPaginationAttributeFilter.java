package dev.gmorikawa.toshokan.infrastructure.http.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;

import dev.gmorikawa.toshokan.shared.query.Pagination;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class QueryPaginationAttributeFilter implements Filter {

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
            try {
                Integer page = Integer.valueOf(httpRequest.getParameter("page"));
                Integer limit = Integer.valueOf(httpRequest.getParameter("limit"));

                Pagination pagination = new Pagination(page, limit);

                request.setAttribute("pagination", pagination);
            } catch (NumberFormatException e) {
                System.out.println("Invalid pagination parameters for authors.");
            } catch (NullPointerException e) {
                
            }
        }

        chain.doFilter(request, response);
    }

}
