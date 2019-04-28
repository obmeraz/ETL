package by.bsuir.kravchenko.filter;

import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.entity.RoleType;
import by.bsuir.kravchenko.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/main.jsp", "/jsp/result.jsp"})
public class UserPageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(PagePath.ERROR);
            request.getSession().setAttribute("message", "You must login,if you want to start use this page");
        }
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
