package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddResultRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/result"})
public class ResultController
{
    @Autowired
    private ResultService resultService;

    @ResponseBody
    @RequestMapping({"/save-result"})
    public Response saveResult(@RequestBody AddResultRequest request)
    {
        return Response.withData(this.resultService.saveResult(request));
    }

    @ResponseBody
    @RequestMapping({"/get-results"})
    public Response getResults()
    {
        return Response.withData(this.resultService.getResults());
    }

    @ResponseBody
    @RequestMapping({"/delete-result"})
    public Response deleteResult(@RequestParam int itemId)
    {
        this.resultService.deleteResult(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
