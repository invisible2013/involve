/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.involve.security;

import ge.economy.involve.security.api.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nino
 */
public class RightInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        HandlerMethod method = (HandlerMethod) handler;
        User user = (User) request.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        HasRight hasRight = method.getMethod().getAnnotation(HasRight.class);
        if (hasRight != null) {
            String[] rights = hasRight.rights();
            for (String right : rights) {
                if (!user.getRights().contains(right)) {
                    response.sendError(403);
                    return false;
                }
            }
        }
        return true;
    }
}
