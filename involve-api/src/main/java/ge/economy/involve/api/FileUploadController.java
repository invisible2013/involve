package ge.economy.involve.api;

import ge.economy.involve.core.services.FileService;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/upload"})
public class FileUploadController
{
    @Autowired
    private FileService fileService;

    @RequestMapping({"/file-upload"})
    public String fileUpload()
    {
        return "file-upload";
    }

    @RequestMapping({"/upload-simple-file"})
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile)
    {
        return this.fileService.saveFile(multipartFile, UUID.randomUUID().toString());
    }

    @RequestMapping({"/get-file"})
    @ResponseBody
    public void getFile(HttpServletResponse response, @RequestParam String identifier)
            throws IOException
    {
        response.getOutputStream().write(this.fileService.readFile(identifier.split("\\.")[0]));
    }
}
