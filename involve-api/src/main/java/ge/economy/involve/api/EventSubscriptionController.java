package ge.economy.involve.api;

import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.api.request.eventsubscription.UnSubscribeEventRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.EventSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/event-subscription"})
public class EventSubscriptionController
{
    @Autowired
    private EventSubscriptionService eventSubscriptionService;

    @ResponseBody
    @RequestMapping({"/subscribe-event"})
    public Response subscribeEvent(@RequestBody SubscribeEventRequest subscribeEventRequest)
    {
        this.eventSubscriptionService.subscribeEvent(subscribeEventRequest);
        return Response.ok();
    }

    @ResponseBody
    @RequestMapping({"/unsubscribe-event"})
    public Response unSubscribeEvent(@RequestBody UnSubscribeEventRequest unSubscribeEventRequest)
    {
        this.eventSubscriptionService.unSubscribeEvent(unSubscribeEventRequest);
        return Response.ok();
    }

    @ResponseBody
    @RequestMapping({"/checking-near-events"})
    public Response checkingNearEvent()
    {
        this.eventSubscriptionService.checkingNearEvents();
        return Response.ok();
    }
}
