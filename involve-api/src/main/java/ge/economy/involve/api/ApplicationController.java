package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.ChampionshipDTO;
import ge.economy.involve.core.api.dto.NewsDTO;
import ge.economy.involve.core.api.dto.SportsmanDTO;
import ge.economy.involve.core.api.dto.StatisticDTO;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.api.request.eventsubscription.UnSubscribeEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.EventService;
import ge.economy.involve.core.services.EventSubscriptionService;
import ge.economy.involve.core.services.FileService;
import ge.economy.involve.core.services.NewsService;
import ge.economy.involve.core.services.OrganisationService;
import ge.economy.involve.core.services.ParameterService;
import ge.economy.involve.core.services.RefereeService;
import ge.economy.involve.core.services.ResultService;
import ge.economy.involve.core.services.SportsmanService;
import ge.economy.involve.core.services.StatisticService;
import ge.economy.involve.core.services.TrainerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/app"})
public class ApplicationController {
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private SportsmanService sportsmanService;
    @Autowired
    private RefereeService refereeService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private EventService eventService;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private EventSubscriptionService eventSubscriptionService;

    @ResponseBody
    @RequestMapping({"/get-version"})
    public Response getVersion() {
        return Response.withData("v0.0.1");
    }

    @ResponseBody
    @RequestMapping({"/get-sportsmans"})
    public Response getSportsmans(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("genderId") int genderId, @RequestParam("regionId") int regionId,
                                  @RequestParam("cityId") int cityId, @RequestParam(required = false) String name, @RequestParam(required = false) String firstLetter,
                                  @RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.sportsmanService.searchSportsmans(name, firstLetter, sportTypeId, genderId, regionId, cityId, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-referees"})
    public Response getReferees(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("categoryId") int categoryId, @RequestParam("genderId") int genderId,
                                @RequestParam(required = false) String name, @RequestParam(required = false) String firstLetter, @RequestParam("regionId") int regionId,
                                @RequestParam("cityId") int cityId, @RequestParam("start") int start,
                                @RequestParam("limit") int limit) {
        return Response.withData(this.refereeService.searchReferees(name, firstLetter, sportTypeId, categoryId, genderId, regionId, cityId, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-trainers"})
    public Response getTrainers(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("categoryId") int categoryId, @RequestParam("genderId") int genderId,
                                @RequestParam(required = false) String name, @RequestParam(required = false) String firstLetter, @RequestParam("regionId") int regionId, @RequestParam("cityId") int cityId, @RequestParam("start") int start,
                                @RequestParam("limit") int limit) {
        return Response.withData(this.trainerService.searchTrainers(name, firstLetter, sportTypeId, categoryId, genderId, regionId, cityId, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-sportsman-by-id"})
    public Response getSportsmanById(@RequestParam("sportsmanId") int sportsmanId) {
        return Response.withData(this.sportsmanService.getSportsmanById(sportsmanId));
    }

    @ResponseBody
    @RequestMapping({"/get-referee-by-id"})
    public Response getRefereeById(@RequestParam("refereeId") int refereeId) {
        return Response.withData(this.refereeService.getRefereeById(refereeId));
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-by-id"})
    public Response getTrainerById(@RequestParam("trainerId") int trainerId) {
        return Response.withData(this.trainerService.getTrainerById(trainerId));
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-qualifications"})
    public Response getTrainerQualifications() {
        return Response.withData(this.parameterService.getTrainerQualifications());
    }

    @ResponseBody
    @RequestMapping({"/get-referee-categories"})
    public Response getRefereeCategories() {
        return Response.withData(this.parameterService.getRefereeCategories());
    }

    @ResponseBody
    @RequestMapping({"/get-sport-types"})
    public Response getSportTypes() {
        return Response.withData(this.parameterService.getSportTypesWithDescription());
    }

    @ResponseBody
    @RequestMapping({"/get-sportType-by-id"})
    public Response getSportTypeById(@RequestParam int itemId) {
        return Response.withData(this.parameterService.getSportTypeById(itemId));
    }

    @ResponseBody
    @RequestMapping({"/get-regions"})
    public Response getRegions() {
        return Response.withData(this.parameterService.getRegions());
    }

    @ResponseBody
    @RequestMapping({"/get-genders"})
    public Response getGenders() {
        return Response.withData(this.parameterService.getGenders());
    }

    @ResponseBody
    @RequestMapping({"/get-ranks"})
    public Response getRanks() {
        return Response.withData(this.parameterService.getRank());
    }

    @ResponseBody
    @RequestMapping({"/get-cities"})
    public Response getCities() {
        return Response.withData(this.parameterService.getCities());
    }

    @ResponseBody
    @RequestMapping({"/get-region-statistic"})
    public Response getRegionStatistic() {
        return Response.withData(this.statisticService.getRegionStatistic(StatisticDTO.STATISTIC_SPORTSMAN));
    }

    @ResponseBody
    @RequestMapping({"/get-all-region-statistic"})
    public Response getAllRegionStatistic() {
        return Response.withData(this.statisticService.getAllRegionStatistic());
    }

    @ResponseBody
    @RequestMapping({"/get-statistic-by-region"})
    public Response getRegionStatistic(@RequestParam int regionId) {
        return Response.withData(this.statisticService.getStatisticByRegion(regionId));
    }

    @ResponseBody
    @RequestMapping({"/get-main-statistic"})
    public Response getMainStatistic() {
        return Response.withData(this.statisticService.getMainStatistic());
    }

    @RequestMapping({"/get-file"})
    @ResponseBody
    public void getFile(HttpServletResponse response, @RequestParam String identifier)
            throws IOException {
        response.getOutputStream().write(this.fileService.readFile(identifier.split("\\.")[0]));
    }

    @ResponseBody
    @RequestMapping({"/get-statistic"})
    public Response getStatistic(@RequestParam("regionId") int regionId, @RequestParam("sportTypeId") int sportTypeId, @RequestParam("genderId") int genderId, @RequestParam("rankId") int rankId, @RequestParam("rangeId") int rangeId) {
        return Response.withData(this.statisticService.getStatistic(regionId, sportTypeId, genderId, rankId, rangeId));
    }

    @ResponseBody
    @RequestMapping({"/get-news"})
    public Response getNews(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-news-by-id"})
    public Response getNews(@RequestParam("newsId") int newsId) {
        return Response.withData(this.newsService.getNewsById(newsId));
    }

    @ResponseBody
    @RequestMapping({"/get-research"})
    public Response getResearch(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_RESEARCH, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-analisys"})
    public Response getAnalisys(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_ANALISYS, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-histories"})
    public Response getHistories(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_HISTORY, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-documentations"})
    public Response getDocumentations(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_DOCUMENTATION, start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-events"})
    public Response getEvents(@RequestParam String startDate, @RequestParam String endDate, @RequestParam int sportTypeId, @RequestParam int eventTypeId) {
        return Response.withData(this.eventService.getEventsByDate(startDate, endDate, sportTypeId, eventTypeId));
    }

    @ResponseBody
    @RequestMapping({"/get-organisations"})
    public Response getOrganisation(@RequestParam int start, @RequestParam int limit) {
        return Response.withData(this.organisationService.getOrganisations(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-champions"})
    public Response getChampions(@RequestParam(required = false) String name, @RequestParam int championshipTypeId, @RequestParam int sportTypeId, @RequestParam int regionId, @RequestParam int genderId, @RequestParam int cityId) {
        List<SportsmanDTO> sportsmans = new ArrayList();
        if (championshipTypeId == ChampionshipDTO.CHAMPIONSHIP_OLIMPIC) {
            sportsmans = this.resultService.searchChampionsByType(name, ChampionshipDTO.CHAMPIONSHIP_OLIMPIC, sportTypeId, genderId, regionId, cityId);
        } else if (championshipTypeId == ChampionshipDTO.CHAMPIONSHIP_EOUROPEAN) {
            sportsmans = this.resultService.searchChampionsByType(name, ChampionshipDTO.CHAMPIONSHIP_EOUROPEAN, sportTypeId, genderId, regionId, cityId);
        } else if (championshipTypeId == ChampionshipDTO.CHAMPIONSHIP_WORLD) {
            sportsmans = this.resultService.searchChampionsByType(name, ChampionshipDTO.CHAMPIONSHIP_WORLD, sportTypeId, genderId, regionId, cityId);
        } else if (championshipTypeId == ChampionshipDTO.CHAMPIONSHIP_OLIMPIC_PRIZE) {
            sportsmans = this.resultService.getOlimpicPrizeWinners(name, sportTypeId, genderId, regionId, cityId);
        }
        return Response.withData(sportsmans);
    }

    @ResponseBody
    @RequestMapping({"/get-olimpic-champions"})
    public Response getOlimpicChampions() {
        return Response.withData(this.resultService.getChampionsByType(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC));
    }

    @ResponseBody
    @RequestMapping({"/get-european-champions"})
    public Response getEuropeanChampions() {
        return Response.withData(this.resultService.getChampionsByType(ChampionshipDTO.CHAMPIONSHIP_EOUROPEAN));
    }

    @ResponseBody
    @RequestMapping({"/get-world-champions"})
    public Response getWorldChampions() {
        return Response.withData(this.resultService.getChampionsByType(ChampionshipDTO.CHAMPIONSHIP_WORLD));
    }

    @ResponseBody
    @RequestMapping({"/get-olimpic-prize-winners"})
    public Response getOlimpicPrizeWinners() {
        return Response.withData(this.resultService.getOlimpicPrizeWinners(null, 0, 0, 0, 0));
    }

    @ResponseBody
    @RequestMapping({"/get-event-types"})
    public Response getEventTypes() {
        return Response.withData(this.eventService.getEventTypes());
    }

    @ResponseBody
    @RequestMapping({"/get-result-by-sport-type"})
    public Response getResultBySportTypeId(@RequestParam int sportTypeId) {
        return Response.withData(this.resultService.getResultBySportTypeId(sportTypeId));
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
        this.eventSubscriptionService.subscribeEvent(subscribeEventRequest);
        return Response.ok();
    }

    @ResponseBody
    @RequestMapping({"/unsubscribe-event"})
    public Response unSubscribeEvent(@RequestParam String email) {
        UnSubscribeEventRequest unSubscribeEventRequest = new UnSubscribeEventRequest();
        unSubscribeEventRequest.setEmail(email);
        this.eventSubscriptionService.unSubscribeEvent(unSubscribeEventRequest);
        return Response.ok();
    }

    @ResponseBody
    @RequestMapping({"/checking-near-events"})
    public Response checkingNearEvent() {
        this.eventSubscriptionService.checkingNearEvents();
        return Response.ok();
    }
}
