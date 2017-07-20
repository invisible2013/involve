package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddSportTypeRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/parameter"})
public class ParameterController
{
    @Autowired
    private ParameterService parameterService;

    @ResponseBody
    @RequestMapping({"/get-sport-types"})
    public Response getSportTypes()
    {
        return Response.withData(this.parameterService.getSportTypes());
    }

    @ResponseBody
    @RequestMapping({"/get-sport-types-with-description"})
    public Response getSportTypesWithDescription()
    {
        return Response.withData(this.parameterService.getSportTypesWithDescription());
    }

    @ResponseBody
    @RequestMapping({"/save-sport-type"})
    public Response saveSportType(@RequestBody AddSportTypeRequest addSportTypeRequest)
    {
        return Response.withData(this.parameterService.saveSportType(addSportTypeRequest));
    }

    @ResponseBody
    @RequestMapping({"/get-regions"})
    public Response getRegions()
    {
        return Response.withData(this.parameterService.getRegions());
    }

    @ResponseBody
    @RequestMapping({"/get-genders"})
    public Response getGenders()
    {
        return Response.withData(this.parameterService.getGenders());
    }

    @ResponseBody
    @RequestMapping({"/get-statistic-categories"})
    public Response getStatisticCategories(@RequestParam int typeId)
    {
        return Response.withData(this.parameterService.getStatisticCategories(typeId));
    }

    @ResponseBody
    @RequestMapping({"/get-ranks"})
    public Response getRanks()
    {
        return Response.withData(this.parameterService.getRank());
    }

    @ResponseBody
    @RequestMapping({"/get-ranges"})
    public Response getRanges()
    {
        return Response.withData(this.parameterService.getRanges());
    }

    @ResponseBody
    @RequestMapping({"/get-cities"})
    public Response getCities(@RequestParam int regionId)
    {
        return Response.withData(this.parameterService.getCitiesByRegion(regionId));
    }

    @ResponseBody
    @RequestMapping({"/get-referee-categories"})
    public Response getRefereeCategories()
    {
        return Response.withData(this.parameterService.getRefereeCategories());
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-qualifications"})
    public Response getTrainerQualifications()
    {
        return Response.withData(this.parameterService.getTrainerQualifications());
    }

    @ResponseBody
    @RequestMapping({"/get-awards"})
    public Response getAwards()
    {
        return Response.withData(this.parameterService.getAwards());
    }

    @ResponseBody
    @RequestMapping({"/get-championship-types"})
    public Response getChampionshipTypes()
    {
        return Response.withData(this.parameterService.getChampionshipTypes());
    }

    @ResponseBody
    @RequestMapping({"/get-championship-sub-types"})
    public Response getChampionshipSubTypes(@RequestParam int typeId)
    {
        return Response.withData(this.parameterService.getChampionshipSubTypes(typeId));
    }

    @ResponseBody
    @RequestMapping({"/get-event-types"})
    public Response getEventTypes()
    {
        return Response.withData(this.parameterService.getEventTypes());
    }

    @RequestMapping({"/add-sport-type-file"})
    @ResponseBody
    private Response addSportTypeFile(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("fileTypeId") int fileTypeId, @RequestParam("file") MultipartFile multipartFile)
    {
        this.parameterService.addSportTypeFile(sportTypeId, fileTypeId, multipartFile);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/get-sport-type-files"})
    public Response getSportTypeFiles(@RequestParam int itemId)
    {
        return Response.withData(this.parameterService.getSportTypeFiles(itemId));
    }

    @RequestMapping({"/delete-sport-type-file"})
    @ResponseBody
    private Response deleteSportTypeFile(@RequestParam int itemId)
    {
        this.parameterService.deleteSportTypeFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
