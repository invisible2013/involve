package ge.economy.involve.api.security;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.UserService;
import ge.economy.involve.security.ErrorCodes;
import ge.economy.involve.security.api.SecurityAPI;
import ge.economy.involve.security.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mindia on 9/25/16.
 */
@Service
public class SecurityIMPL implements SecurityAPI {

    @Autowired
    private UserService userService;

    @Override
    public User getUser(Map<String, Object> map) {

        String username = map.get("email").toString().toLowerCase();
        String password = map.get("password").toString();

        UserDTO userDTO = userService.getUser(username, password);

        if (userDTO == null)
            return null;

        User user = new User();
        user.setUserData(userDTO);

        return user;
    }

    @Override
    public List<String> getLoginParameters() {
        return Arrays.asList("email", "password");
    }

    @Override
    public String getLoginPage() {
        return "login";
    }

    @Override
    public String getHomePage() {
        return "home";
    }

    @Override
    public boolean isTwoStepVerification() {
        return false;
    }

    @Override
    public String getTwoStepVerificationParam() {
        return null;
    }

    @Override
    public boolean sendTwoStepVerificationCode(Map<String, Object> map) {
        return false;
    }

    @Override
    public Object wrapErrorMessage(ErrorCodes errorCode) {
        Response response = new Response();
        response.setErrorCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return response;
    }

    @Override
    public Object wrapResponse(Object data) {
        return Response.withData(data);
    }
}
