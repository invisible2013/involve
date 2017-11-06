package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.api.request.*;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.FileService;
import ge.economy.involve.core.services.InitiateService;
import ge.economy.involve.core.services.ReformService;
import ge.economy.involve.security.AuthController;
import ge.economy.involve.security.AuthInterceptor;
import ge.economy.involve.security.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/initiate"})
public class InitiateController {

    @Autowired
    private InitiateService initiateService;


    @ResponseBody
    @RequestMapping({"/get-initiates"})
    public Response getInitiate(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(initiateService.getInitiates(start, limit));
    }


    @RequestMapping({"/delete-initiate"})
    @ResponseBody
    private Response deleteSession(@RequestParam int itemId) {
        initiateService.deleteInitiate(itemId);
        return Response.withData(true);
    }


    @ResponseBody
    @RequestMapping({"/save-issue"})
    public Response saveIssue(@RequestBody AddInitiateRequest request, HttpServletRequest servletRequest) {
        User u = (User) servletRequest.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        if (u != null) {
            UserDTO dto = (UserDTO) u.getUserData();
            request.setUserId(dto.getId());
        }
        return Response.withData(initiateService.saveIssue(request));
    }

    @ResponseBody
    @RequestMapping({"/get-issues"})
    public Response getIssues(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(initiateService.getIssues(start, limit));
    }


    @RequestMapping({"/delete-issue"})
    @ResponseBody
    private Response deleteIssue(@RequestParam int itemId) {
        initiateService.deleteIssue(itemId);
        return Response.withData(true);
    }


}
