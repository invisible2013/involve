package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddOrganisationRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/organisation"})
public class OrganisationController
{
    @Autowired
    private OrganisationService organisationService;

    @ResponseBody
    @RequestMapping({"/save-organisation"})
    public Response saveOrganisation(@RequestBody AddOrganisationRequest addOrganisationRequest)
    {
        return Response.withData(this.organisationService.saveOrganisation(addOrganisationRequest));
    }

    @ResponseBody
    @RequestMapping({"/get-organisations"})
    public Response getOrganisations()
    {
        return Response.withData(this.organisationService.getOrganisations());
    }

    @ResponseBody
    @RequestMapping({"/delete-organisation"})
    public Response deleteSportsman(@RequestParam int itemId)
    {
        this.organisationService.deleteOrganisation(itemId);
        return Response.withData(Boolean.valueOf(true));
    }

    @RequestMapping({"/add-image"})
    @ResponseBody
    private Response addOrganisationImage(@RequestParam("organisationId") int organisationId, @RequestParam("file") MultipartFile multipartFile)
    {
        this.organisationService.addOrganisationImage(organisationId, multipartFile);
        return Response.withData(Boolean.valueOf(true));
    }
}
