package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/event"})
public class EventController
{
    @Autowired
    private EventService eventService;

    @ResponseBody
    @RequestMapping({"/save-event"})
    public Response saveEvent(@RequestBody AddEventRequest request)
    {
        return Response.withData(this.eventService.saveEvent(request));
    }

    @ResponseBody
    @RequestMapping({"/get-events"})
    public Response getEvents()
    {
        return Response.withData(this.eventService.getEvents());
    }

    @ResponseBody
    @RequestMapping({"/delete-event"})
    public Response deleteEvent(@RequestParam int itemId)
    {
        this.eventService.deleteEvent(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
