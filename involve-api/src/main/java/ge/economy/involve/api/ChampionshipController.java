package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddChampionshipRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/championship"})
public class ChampionshipController
{
    @Autowired
    private ChampionshipService championshipService;

    @ResponseBody
    @RequestMapping({"/save-championship"})
    public Response saveChampionship(@RequestBody AddChampionshipRequest request)
    {
        return Response.withData(this.championshipService.saveResult(request));
    }

    @ResponseBody
    @RequestMapping({"/get-championships"})
    public Response getChampionships()
    {
        return Response.withData(this.championshipService.getChampionships());
    }

    @ResponseBody
    @RequestMapping({"/get-championships-alphabet"})
    public Response getChampionshipsAlphabet()
    {
        return Response.withData(this.championshipService.getChampionshipsAlphabet());
    }

    @ResponseBody
    @RequestMapping({"/delete-championship"})
    public Response deleteResult(@RequestParam int itemId)
    {
        this.championshipService.deleteChampionship(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
