package ge.economy.involve.api;

import ge.economy.involve.core.api.dto.NewsDTO;
import ge.economy.involve.core.api.request.AddNewsRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/news"})
public class NewsController
{
    @Autowired
    private NewsService newsService;

    @ResponseBody
    @RequestMapping({"/save-news"})
    public Response saveNews(@RequestBody AddNewsRequest request)
    {
        request.setTypeId(NewsDTO.NEWS);
        return Response.withData(this.newsService.saveNews(request));
    }

    @ResponseBody
    @RequestMapping({"/get-news"})
    public Response getNews()
    {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS, 0, 100));
    }

    @ResponseBody
    @RequestMapping({"/delete-news"})
    public Response deleteNews(@RequestParam int itemId)
    {
        this.newsService.deleteNews(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/save-research"})
    public Response saveResearch(@RequestBody AddNewsRequest request)
    {
        request.setTypeId(NewsDTO.NEWS_RESEARCH);
        return Response.withData(this.newsService.saveNews(request));
    }

    @ResponseBody
    @RequestMapping({"/get-research"})
    public Response getResearch()
    {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_RESEARCH, 0, 100));
    }

    @ResponseBody
    @RequestMapping({"/save-analisys"})
    public Response saveAnalisys(@RequestBody AddNewsRequest request)
    {
        request.setTypeId(NewsDTO.NEWS_ANALISYS);
        return Response.withData(this.newsService.saveNews(request));
    }

    @ResponseBody
    @RequestMapping({"/get-analisys"})
    public Response getAnalisys()
    {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_ANALISYS, 0, 100));
    }

    @ResponseBody
    @RequestMapping({"/save-history"})
    public Response saveHistory(@RequestBody AddNewsRequest request)
    {
        request.setTypeId(NewsDTO.NEWS_HISTORY);
        return Response.withData(this.newsService.saveNews(request));
    }

    @ResponseBody
    @RequestMapping({"/get-histories"})
    public Response getHistories()
    {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_HISTORY, 0, 100));
    }

    @ResponseBody
    @RequestMapping({"/save-documentation"})
    public Response saveDocumentation(@RequestBody AddNewsRequest request)
    {
        request.setTypeId(NewsDTO.NEWS_DOCUMENTATION);
        return Response.withData(this.newsService.saveNews(request));
    }

    @ResponseBody
    @RequestMapping({"/get-documentations"})
    public Response getDocumentations()
    {
        return Response.withData(this.newsService.getNewsByType(NewsDTO.NEWS_DOCUMENTATION, 0, 100));
    }

    @RequestMapping({"/add-file"})
    @ResponseBody
    private Response addNewsImage(@RequestParam("newsId") int newsId, @RequestParam("fileTypeId") int fileTypeId, @RequestParam("file") MultipartFile multipartFile)
    {
        this.newsService.addNewsFile(newsId, fileTypeId, multipartFile);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/set-main-file"})
    public Response setMainFile(@RequestParam int itemId)
    {
        this.newsService.setMainFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @RequestMapping({"/delete-file"})
    @ResponseBody
    private Response deleteNewsFile(@RequestParam int itemId)
    {
        this.newsService.deleteNewsFile(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @ResponseBody
    @RequestMapping({"/get-news-files"})
    public Response getSportsmanFiles(@RequestParam int itemId)
    {
        return Response.withData(this.newsService.getNewsFiles(itemId));
    }
}
