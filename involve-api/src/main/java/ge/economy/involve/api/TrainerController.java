package ge.economy.involve.api;

import ge.economy.involve.core.api.request.AddTrainerRequest;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/trainer"})
public class TrainerController
{
    @Autowired
    private TrainerService trainerService;

    @ResponseBody
    @RequestMapping({"/save-trainer"})
    public Response saveTrainer(@RequestBody AddTrainerRequest addTrainerRequest)
    {
        return Response.withData(this.trainerService.saveTrainer(addTrainerRequest));
    }

    @ResponseBody
    @RequestMapping({"/get-trainers"})
    public Response getTrainers()
    {
        return Response.withData(this.trainerService.getTrainers());
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-by-id"})
    public Response getTrainerById(@RequestParam int trainerId)
    {
        return Response.withData(this.trainerService.getTrainerWithDependencyInfo(trainerId));
    }

    @ResponseBody
    @RequestMapping({"/get-trainer-sport-types"})
    public Response getTrainerSportTypes(@RequestParam int trainerId)
    {
        return Response.withData(this.trainerService.getTrainerSportTypes(trainerId));
    }

    @ResponseBody
    @RequestMapping({"/delete-trainer"})
    public Response deleteReferee(@RequestParam int itemId)
    {
        this.trainerService.deleteTrainerById(itemId);
        return Response.withData(Boolean.valueOf(true));
    }
}
