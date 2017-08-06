package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddReformFileRequest;
import ge.economy.involve.core.api.request.AddReformRequest;
import ge.economy.involve.core.api.request.AddSessionPollRequest;
import ge.economy.involve.core.api.request.AddSessionRequest;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.FileService;
import ge.economy.involve.core.services.InitiateService;
import ge.economy.involve.core.services.ReformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        return Response.withData(initiateService.getInitiate(start, limit));
    }


    @RequestMapping({"/delete-initiate"})
    @ResponseBody
    private Response deleteSession(@RequestParam int itemId) {
        initiateService.deleteInitiate(itemId);
        return Response.withData(true);
    }


}
