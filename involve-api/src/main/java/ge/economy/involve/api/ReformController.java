package ge.economy.involve.api;

import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.FileService;
import ge.economy.involve.core.services.ParameterService;
import ge.economy.involve.core.services.ReformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Response getSportsmans(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("genderId") int genderId, @RequestParam("regionId") int regionId,
                                  @RequestParam("cityId") int cityId, @RequestParam(required = false) String name, @RequestParam(required = false) String firstLetter,
                                  @RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(reformService.getReforms(start, limit));
    }


    @ResponseBody
    @RequestMapping({"/get-reform-by-id"})
    public Response getReformById(@RequestParam("reformId") int reformId) {
        return Response.withData(reformService.getReformById(reformId));
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
