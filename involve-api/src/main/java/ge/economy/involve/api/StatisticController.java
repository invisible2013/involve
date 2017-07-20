package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.StatisticDTO;
import ge.economy.involve.core.api.request.AddStatisticRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/statistic"})
public class StatisticController
{
    @Autowired
    private StatisticService statisticService;

    @ResponseBody
    @RequestMapping({"/save-sportsman-statistic"})
    public Response saveSportsmanStatistic(@RequestBody AddStatisticRequest request)
    {
        request.setTypeId(StatisticDTO.STATISTIC_SPORTSMAN);
        return Response.withData(this.statisticService.saveStatistic(request));
    }

    @ResponseBody
    @RequestMapping({"/get-sportsman-statistic"})
    public Response getSportsmanStatistic(@RequestParam int start, @RequestParam int limit)
    {
        return Response.withData(this.statisticService.getStatisticByType(StatisticDTO.STATISTIC_SPORTSMAN, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/delete-statistic"})
    public Response deleteStatistic(@RequestParam int itemId)
    {
        this.statisticService.deleteStatistic(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/save-referee-statistic"})
    public Response saveRefereeStatistic(@RequestBody AddStatisticRequest request)
    {
        request.setTypeId(StatisticDTO.STATISTIC_REFEREE);
        return Response.withData(this.statisticService.saveStatistic(request));
    }

    @ResponseBody
    @RequestMapping({"/get-referee-statistic"})
    public Response getRefereeStatistic(@RequestParam int start, @RequestParam int limit)
    {
        return Response.withData(this.statisticService.getStatisticByType(StatisticDTO.STATISTIC_REFEREE, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/save-trainer-statistic"})
    public Response saveTrainerStatistic(@RequestBody AddStatisticRequest request)
    {
        request.setTypeId(StatisticDTO.STATISTIC_TRAINER);
        return Response.withData(this.statisticService.saveStatistic(request));
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-statistic"})
    public Response getTrainerStatistic(@RequestParam int start, @RequestParam int limit)
    {
        return Response.withData(this.statisticService.getStatisticByType(StatisticDTO.STATISTIC_TRAINER, start, limit));
    }
}
