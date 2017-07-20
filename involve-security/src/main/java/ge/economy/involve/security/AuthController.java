package ge.economy.involve.security;

import ge.economy.involve.security.api.User;
import ge.economy.involve.security.api.SecurityAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nino
 */
@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private SecurityAPI securityAPI;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        if (user == null) {
            return "login";
        } else {
            return "redirect:" + securityAPI.getHomePage();
        }
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public Object verify(@RequestParam(value = "redirect", required = false) String redirect, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> params = new HashMap<>();

        redirect = (redirect != null && !redirect.isEmpty()) ? redirect : securityAPI.getHomePage();
        securityAPI.getLoginParameters().stream().forEach((p) -> {
            params.put(p, request.getParameter(p));
        });

        if (securityAPI.isTwoStepVerification()) {
            if (request.getParameterMap().containsKey(securityAPI.getTwoStepVerificationParam())) {
                User user = securityAPI.getUser(params);
                if (user != null) {
                    request.getSession().setAttribute(AuthInterceptor.CURRENT_USER, user);
                } else {
                    return securityAPI.wrapErrorMessage(ErrorCodes.INCORRECT_CREDENTIALS);
                }

                return securityAPI.wrapResponse(redirect);
            } else {
                return securityAPI.sendTwoStepVerificationCode(params);
            }
        } else {
            User user = securityAPI.getUser(params);
            if (user != null) {
                request.getSession().setAttribute(AuthInterceptor.CURRENT_USER, user);
            } else {
                return securityAPI.wrapErrorMessage(ErrorCodes.INCORRECT_CREDENTIALS);
            }

            return securityAPI.wrapResponse(redirect);
        }
    }


    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(HttpSession session) {
        session.removeAttribute(AuthInterceptor.CURRENT_USER);
        session.invalidate();
        return "redirect:" + securityAPI.getLoginPage();
    }

    @RequestMapping(value = "/status", method = {RequestMethod.POST})
    @ResponseBody
    public Object status(HttpSession session, HttpServletResponse response) throws IOException {
        User u = (User) session.getAttribute(AuthInterceptor.CURRENT_USER);
        if (u != null) {
            return securityAPI.wrapResponse(u);
        }
        return securityAPI.wrapErrorMessage(ErrorCodes.UNAUTHORIZED);
    }

    @ResponseBody
    @RequestMapping(value = "/get-user", method = {RequestMethod.POST})
    public Object getUser(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        return securityAPI.wrapResponse(user);
    }
}
