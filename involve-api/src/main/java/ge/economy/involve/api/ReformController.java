package ge.economy.involve.api;

import ge.economy.involve.core.api.request.*;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.execptions.MainObjectNotFoundException;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.FileService;
import ge.economy.involve.core.services.ParameterService;
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
@RequestMapping({"/reform"})
public class ReformController {

    @Autowired
    private ReformService reformService;

    @Autowired
    private FileService fileService;


    @ResponseBody
    @RequestMapping({"/get-reforms"})
    public Response getReforms(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(reformService.getReforms(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-reform"})
    public Response getReform(@RequestParam int itemId) {
        return Response.withData(reformService.getReform(itemId));
    }

    @ResponseBody
    @RequestMapping({"/get-reform-types"})
    public Response getSportsmans() {
        return Response.withData(reformService.getReformTypes());
    }


    @ResponseBody
    @RequestMapping({"/save-reform"})
    public Response saveReform(@RequestBody AddReformRequest addReformRequest) {
        return Response.withData(reformService.saveReform(addReformRequest));
    }


    @ResponseBody
    @RequestMapping({"/save-session"})
    public Response saveSession(@RequestBody AddSessionRequest request) {
        try {
            return Response.withData(reformService.saveSession(request));
        } catch (MainObjectNotFoundException ex) {
            return Response.withError(ex.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/save-session-poll"})
    public Response saveSessionPoll(@RequestBody AddSessionPollRequest request) {
        return Response.withData(reformService.saveSessionPoll(request));
    }


    @ResponseBody
    @RequestMapping({"/get-reform-by-id"})
    public Response getReformById(@RequestParam("reformId") int reformId) {
        return Response.withData(reformService.getReformById(reformId));
    }

    @ResponseBody
    @RequestMapping({"/get-reform-files"})
    public Response getReformFiles(@RequestParam int itemId) {
        return Response.withData(reformService.getReformFiles(itemId));
    }

    @ResponseBody
    @RequestMapping({"/get-reform-sessions"})
    public Response getReformSessions(@RequestParam int itemId) {
        return Response.withData(reformService.getReformSessions(itemId));
    }

    @ResponseBody
    @RequestMapping({"/get-all-sessions"})
    public Response getAllSessions(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(reformService.getAllSessions(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-session-polls"})
    public Response getSessionPolls(@RequestParam int itemId) {
        return Response.withData(reformService.getSessionPolls(itemId));
    }

    @RequestMapping({"/delete-reform"})
    @ResponseBody
    private Response deleteReform(@RequestParam int itemId) {
        this.reformService.deleteReform(itemId);
        return Response.withData(true);
    }

    @RequestMapping({"/delete-session"})
    @ResponseBody
    private Response deleteSession(@RequestParam int itemId) {
        this.reformService.deleteSession(itemId);
        return Response.withData(true);
    }

    @RequestMapping({"/delete-session-poll"})
    @ResponseBody
    private Response deleteSessionPoll(@RequestParam int itemId) {
        this.reformService.deleteSessionPoll(itemId);
        return Response.withData(true);
    }


    @RequestMapping({"/add-image"})
    @ResponseBody
    private Response addReformImage(@RequestParam int itemId, @RequestParam int fileTypeId, @RequestParam String fileName, @RequestParam("file")
            MultipartFile multipartFile) {
        reformService.addReformImage(itemId, fileTypeId, fileName, multipartFile);
        return Response.withData(true);
    }

    @RequestMapping({"/add-session-image"})
    private Response addSessionImage(@RequestParam int itemId, @RequestParam("file") MultipartFile multipartFile) {
        reformService.addSessionImage(itemId, multipartFile);
        return Response.withData(true);
    }

    @RequestMapping({"/save-file-name"})
    @ResponseBody
    private Response saveFileUrl(@RequestBody AddReformFileRequest addReformFileRequest) {
        reformService.addReformImage(addReformFileRequest.getItemId(), addReformFileRequest.getFileTypeId(), addReformFileRequest.getFileName(), null);
        return Response.withData(true);
    }


    @RequestMapping({"/delete-reform-file"})
    @ResponseBody
    private Response deleteReformFile(@RequestParam int itemId) {
        this.reformService.deleteReformFile(itemId);
        return Response.withData(true);
    }


    @RequestMapping({"/get-file"})
    @ResponseBody
    public void getFile(HttpServletResponse response, @RequestParam String identifier)
            throws IOException {
        response.getOutputStream().write(fileService.readFile(identifier.split("\\.")[0]));
    }


    @ResponseBody
    @RequestMapping({"/subscribe-event"})
    public Response subscribeEvent(@RequestParam String email, @RequestParam String sportTypes) {
        SubscribeEventRequest subscribeEventRequest = new SubscribeEventRequest();
        subscribeEventRequest.setEmail(email);
        List<Integer> sportTypeList = new ArrayList();
        String[] stringArray = sportTypes.split(",");
        for (String s : stringArray) {
            sportTypeList.add(Integer.valueOf(Integer.parseInt(s)));
        }
        subscribeEventRequest.setSportTypes(sportTypeList);
        //this.eventSubscriptionService.subscribeEvent(subscribeEventRequest);
        return Response.ok();
    }


}
