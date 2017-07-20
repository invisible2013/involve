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
    @RequestMapping({"/save-sport-type"})
    public Response saveSportType(@RequestBody AddSportTypeRequest addSportTypeRequest)
    {
        return Response.withData(this.parameterService.saveSportType(addSportTypeRequest));
    }


    @RequestMapping({"/add-sport-type-file"})
    @ResponseBody
    private Response addSportTypeFile(@RequestParam("sportTypeId") int sportTypeId, @RequestParam("fileTypeId") int fileTypeId, @RequestParam("file") MultipartFile multipartFile)
    {
        this.parameterService.addSportTypeFile(sportTypeId, fileTypeId, multipartFile);
        return Response.withData(Boolean.valueOf(true));
    }


    @RequestMapping({"/delete-sport-type-file"})
    @ResponseBody
    private Response deleteSportTypeFile(@RequestParam int itemId)
    {
        this.parameterService.deleteSportTypeFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
