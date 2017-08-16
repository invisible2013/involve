package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.api.request.AddChampionshipRequest;
import ge.economy.involve.core.api.request.AddUserRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.UserService;
import ge.economy.involve.security.AuthInterceptor;
import ge.economy.involve.security.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping({"/users"})
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/get-users"})
    @ResponseBody
    public Response getUsers() {
        return Response.withData(userService.getUsers());
    }

    @RequestMapping({"/get-user-groups"})
    @ResponseBody
    public Response getUserGroups() {
        return Response.withData(userService.getUserGroups());
    }

    @RequestMapping({"/get-user-statuses"})
    @ResponseBody
    public Response getUserStatuses() {
        return Response.withData(userService.getUserStatuses());
    }

    @RequestMapping({"/get-user-types"})
    @ResponseBody
    public Response getUserTypes() {
        return Response.withData(userService.getUserTypes());
    }

    @RequestMapping({"/get-genders"})
    @ResponseBody
    public Response getGenders() {
        return Response.withData(userService.getGenders());
    }

    @RequestMapping({"/save-user"})
    @ResponseBody
    public Response saveUser(@RequestBody AddUserRequest request) {
        return Response.withData(userService.saveUser(request));
    }

    @RequestMapping({"/get-user-by-id"})
    @ResponseBody
    public Response getUserById(@RequestParam int itemId) {
        return Response.withData(userService.getUserById(itemId));
    }

    @RequestMapping({"/change-password"})
    @ResponseBody
    public Response changePssword(@RequestParam String password, HttpServletRequest servletRequest) {
        User u = (User) servletRequest.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        UserDTO dto = new UserDTO();
        if (u != null) {
            dto = (UserDTO) u.getUserData();
        }
        userService.updateUserPassword(dto.getId(), password);
        return Response.withData(true);
    }

    @RequestMapping({"/delete-user"})
    @ResponseBody
    public Response deleteUser(@RequestParam int itemId) {
        userService.deleteUser(itemId);
        return Response.withData(true);
    }
}
