package ge.economy.involve.api;

import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/users"})
@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping({"/get-users"})
    @ResponseBody
    public Response getUsers()
    {
        return Response.withData(this.userService.getUsers());
    }

    @RequestMapping({"/get-user-groups"})
    @ResponseBody
    public Response getUserGroups()
    {
        return Response.withData(this.userService.getUsers());
    }

    @RequestMapping({"/get-user-statuses"})
    @ResponseBody
    public Response getUserStatuses()
    {
        return Response.withData(this.userService.getUsers());
    }
}
