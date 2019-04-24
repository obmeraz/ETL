package by.bsuir.kravchenko.controller;

public class Router {
    private String pagePath;
    private RouterType route = RouterType.FORWARD;

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    String getPagePath() {
        return pagePath;
    }

    RouterType getRoute() {
        return route;
    }

    public void setRedirectRoute() {
        this.route = RouterType.REDIRECT;
    }

    public enum RouterType {
        FORWARD,
        REDIRECT
    }
}
