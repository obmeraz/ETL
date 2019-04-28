package by.bsuir.kravchenko.filter;

import by.bsuir.kravchenko.command.CommandConstant;
import by.bsuir.kravchenko.command.CommandType;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.entity.RoleType;
import by.bsuir.kravchenko.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class ControllerFilter implements Filter {
    private RoleConfigurator roleConfigurator = new RoleConfigurator();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String command = request.getParameter(CommandConstant.COMMAND);
        if (command == null || command.isEmpty() || !roleConfigurator.checkCommandName(command)) {
            request.getSession().setAttribute("message","You don't have enough permission");
            response.sendRedirect(PagePath.ERROR);
        } else {
            CommandType type = CommandType.valueOf(command.toUpperCase());
            User user = (User) request.getSession().getAttribute("user");
            RoleType roleType;
            if (user == null) {
                roleType = RoleType.GUEST;
            } else {
                roleType = user.getRole();
            }
            if (!roleConfigurator.checkRolePrivileges(roleType, type)) {
                request.getSession().setAttribute("message","You don't have enough permission");
                response.sendRedirect(PagePath.ERROR);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}