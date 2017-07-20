package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddRefereeRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/referee"})
public class RefereeController
{
    @Autowired
    private RefereeService refereeService;

    @ResponseBody
    @RequestMapping({"/save-referee"})
    public Response saveReferee(@RequestBody AddRefereeRequest addRefereeRequest)
    {
        return Response.withData(this.refereeService.saveReferee(addRefereeRequest));
    }

    @ResponseBody
    @RequestMapping({"/get-referees"})
    public Response getReferees()
    {
        return Response.withData(this.refereeService.getReferees());
    }

    @ResponseBody
    @RequestMapping({"/get-referee-by-id"})
    public Response getRefereeById(@RequestParam int refereeId)
    {
        return Response.withData(this.refereeService.getRefereeWithDependencyInfo(refereeId));
    }

    @ResponseBody
    @RequestMapping({"/get-referee-sport-types"})
    public Response getRefereeSportTypes(@RequestParam int refereeId)
    {
        return Response.withData(this.refereeService.getRefereeSportTypes(refereeId));
    }

    @ResponseBody
    @RequestMapping({"/delete-referee"})
    public Response deleteReferee(@RequestParam int itemId)
    {
        this.refereeService.deleteRefereeById(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
