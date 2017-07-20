package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddSportsmanFileRequest;
import ge.economy.involve.core.api.request.AddSportsmanRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/sportsman"})
public class SportsmanController
{
    @Autowired
    private SportsmanService sportsmanService;

    @ResponseBody
    @RequestMapping({"/save-sportsman"})
    public Response saveSportsman(@RequestBody AddSportsmanRequest addSportsmanRequest)
    {
        return Response.withData(this.sportsmanService.saveSportsman(addSportsmanRequest));
    }

    @ResponseBody
    @RequestMapping({"/get-sportsmans"})
    public Response getSportsmans(@RequestParam int start, @RequestParam int limit)
    {
        return Response.withData(this.sportsmanService.getSportsmans(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-sportsman-alphabet"})
    public Response getSportsmanAlphabet()
    {
        return Response.withData(this.sportsmanService.getSportsmansAlphabet());
    }

    @ResponseBody
    @RequestMapping({"/get-sportsman-files"})
    public Response getSportsmanFiles(@RequestParam int sportsmanId)
    {
        return Response.withData(this.sportsmanService.getSportsmanFiles(sportsmanId));
    }

    @ResponseBody
    @RequestMapping({"/delete-sportsman"})
    public Response deleteSportsman(@RequestParam int sportsmanId)
    {
        this.sportsmanService.deleteSportsman(sportsmanId);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/set-main-file"})
    public Response setMainFile(@RequestParam int itemId)
    {
        this.sportsmanService.setMainFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @RequestMapping({"/add-image"})
    @ResponseBody
    private Response addSportsmanImage(@RequestParam("sportsmanId") int sportsmanId, @RequestParam("fileTypeId") int fileTypeId, @RequestParam("fileUrl") String fileUrl, @RequestParam("file") MultipartFile multipartFile)
    {
        this.sportsmanService.addSportsmanImage(sportsmanId, fileTypeId, fileUrl, multipartFile);
        return Response.withData(Boolean.valueOf(true));
    }

    @RequestMapping({"/save-file-url"})
    @ResponseBody
    private Response saveFileUrl(@RequestBody AddSportsmanFileRequest addSportsmanFileRequest)
    {
        this.sportsmanService.addSportsmanImage(addSportsmanFileRequest.getSportsmanId().intValue(), addSportsmanFileRequest
                .getFileTypeId().intValue(), addSportsmanFileRequest
                .getFileUrl(), null);
        return Response.withData(Boolean.valueOf(true));
    }

    @RequestMapping({"/delete-file"})
    @ResponseBody
    private Response deleteSportsmanFile(@RequestParam int itemId)
    {
        this.sportsmanService.deleteSportsmanFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
