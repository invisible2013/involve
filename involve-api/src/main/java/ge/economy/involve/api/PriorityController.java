package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddPriorityRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.InitiateService;
import ge.economy.involve.core.services.PriorityService;
import ge.economy.involve.security.AuthInterceptor;
import ge.economy.involve.security.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/priority"})
public class PriorityController {

    @Autowired
    private PriorityService priorityService;


    @ResponseBody
    @RequestMapping({"/get-priorities"})
    public Response getPriorities(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(priorityService.getPriorities(start, limit));
    }


    @RequestMapping({"/delete-priority"})
    @ResponseBody
    private Response deletePriority(@RequestParam int itemId) {
        priorityService.deletePriority(itemId);
        return Response.withData(true);
    }


    @ResponseBody
    @RequestMapping({"/save-priority"})
    public Response saveIssue(@RequestBody AddPriorityRequest request, HttpServletRequest servletRequest) {
        User u = (User) servletRequest.getSession().getAttribute(AuthInterceptor.CURRENT_USER);
        if (u != null) {
            UserDTO dto = (UserDTO) u.getUserData();
            request.setUserId(dto.getId());
        }
        return Response.withData(priorityService.savePriority(request));
    }



}
