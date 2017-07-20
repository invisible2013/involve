package ge.economy.involve.security;

import ge.economy.involve.security.api.User;
import ge.economy.involve.security.api.SecurityAPI;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nino
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public static final String CURRENT_USER = "current_user";

    @Autowired
    private SecurityAPI securityAPI;


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {

        String uri = request.getRequestURI();
        User user = (User) request.getSession().getAttribute(CURRENT_USER);

        if (user == null && request.getHeader("X-Requested-With") == null) {

            // getRequestURI() contains root path, uri also contains root path, that's why one must be removed
            if (uri.startsWith(request.getContextPath())) {
                uri = uri.replace(request.getContextPath(), "");
            }

            if (uri.length() > 0 && !uri.equals("/")) {
                response.sendRedirect(securityAPI.getLoginPage() + "?redirect=" + uri);
            } else {
                response.sendRedirect(securityAPI.getLoginPage());
            }

            return false;
        } else if (user == null) {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            String responseBody = mapper.writeValueAsString(securityAPI.wrapErrorMessage(ErrorCodes.UNAUTHORIZED));
            response.getWriter().write(responseBody);
            return false;
        }
        return true;
    }
}
