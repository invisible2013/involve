package ge.economy.involve.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.economy.involve.core.api.dto.QuestionAnswer;
import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.api.request.*;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.execptions.*;
import ge.economy.involve.core.response.Response;
import ge.economy.involve.core.services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import ge.economy.involve.utils.email.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/app"})
public class ApplicationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReformService reformService;
    @Autowired
    private InitiateService initiateService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping({"/get-version"})
    public Response getVersion() {
        return Response.withData("v0.0.1");
    }

    @ResponseBody
    @RequestMapping({"/sign-in"})
    public Response userSignIn(@RequestParam String email, @RequestParam String password) {
        try {
            return Response.withData(userService.signIn(email, password));
        } catch (IncorectUserCredentialsException e) {
            return Response.withError(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/save-initiate"})
    public Response saveInitiate(@RequestParam int sphereId, @RequestParam String name, @RequestParam String description, @RequestParam String necessity, @RequestParam String advantages,
                                 @RequestParam String ipAddress, @RequestParam(required = false, defaultValue = "") String clientUID, @RequestParam(required = false, defaultValue = "0") int userId,
                                 @RequestParam(required = false, defaultValue = "") String otherSphereName) {
        AddInitiateRequest request = new AddInitiateRequest();
        request.setUserId(userId);
        request.setSphereId(sphereId);
        request.setName(name);
        request.setDescription(description);
        request.setNecessity(necessity);
        request.setAdvantages(advantages);
        request.setIpAddress(ipAddress);
        request.setClientUID(clientUID);
        return Response.withData(initiateService.saveInitiate(request));
    }

    @ResponseBody
    @RequestMapping({"/save-priority"})
    public Response savePriority(@RequestParam int priorityId, @RequestParam int answerId, @RequestParam String ipAddress,
                                 @RequestParam String clientUID, @RequestParam(required = false, defaultValue = "0") int userId) {
        AddPriorityVoteRequest request = new AddPriorityVoteRequest();
        request.setUserId(userId);
        request.setPriorityId(priorityId);
        request.setAnswerId(answerId);
        request.setIpAddress(ipAddress);
        request.setClientUID(clientUID);
        return Response.withData(initiateService.savePriorityVote(request));
    }

    @ResponseBody
    @RequestMapping({"/save-initiate-issue"})
    public Response saveInitiateIssue(@RequestParam int initiateIssueId, @RequestParam boolean agreed, @RequestParam String ipAddress,
                                      @RequestParam String clientUID, @RequestParam(required = false, defaultValue = "0") int userId) {
        AddInitiativeVoteRequest request = new AddInitiativeVoteRequest();
        request.setUserId(userId);
        request.setInitiatedIssueId(initiateIssueId);
        request.setAgreed(agreed);
        request.setIpAddress(ipAddress);
        request.setClientUID(clientUID);
        return Response.withData(initiateService.saveInitiativeIssueVote(request));
    }


    @ResponseBody
    @RequestMapping({"/get-initiates"})
    public Response getInitiates(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam int limit) {
        return Response.withData(initiateService.getInitiates(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-initiate-issues"})
    public Response getInitiateIssues(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam int limit) {
        return Response.withData(initiateService.getIssues(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-priorities"})
    public Response getPriorities(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam int limit) {
        return Response.withData(priorityService.getPriorities(start, limit));
    }


    @RequestMapping({"/get-file"})
    @ResponseBody
    public void getFile(HttpServletResponse response, @RequestParam String identifier)
            throws IOException {
        response.getOutputStream().write(fileService.readFile(identifier.split("\\.")[0]));
    }

    @ResponseBody
    @RequestMapping({"/registration"})
    public Response registration(@RequestParam Integer userTypeId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer genderId,
                                 @RequestParam Integer ageRangeId, @RequestParam Integer sphereId, @RequestParam String orgName,
                                 @RequestParam String idNumber, @RequestParam String phone, @RequestParam String email, @RequestParam String password,
                                 @RequestParam(required = false, defaultValue = "") Integer enterpriseSizeId,
                                 @RequestParam(required = false, defaultValue = "") String otherSphereName, @RequestParam(required = false, defaultValue = "") Integer educationLevelId) {
        AddUserRequest request = new AddUserRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setTypeId(userTypeId);
        request.setGenderId(genderId);
        request.setAgeRangeId(ageRangeId);
        request.setSphereId(sphereId);
        request.setOrgName(orgName);
        request.setIdNumber(idNumber);
        request.setPhone(phone);
        request.setEmail(email);
        request.setPassword(password);
        request.setEnterpriseSizeId(enterpriseSizeId);
        request.setEducationLevelId(educationLevelId);
        request.setOtherSphereName(otherSphereName);
        try {
            userService.registrationUser(request);
        } catch (EmailNotSentException ex) {
            return Response.withError("ემაილის გაგზავნა მითითებულ მაილზე არ სრულდება, გთხოვთ სწორად შეიყვანოთ მაილი");
        } catch (MailAlreadyUsedException e) {
            return Response.withError(e.getMessage());
        } catch (MissingParameterException e) {
            return Response.withError(e.getMessage());
        }
        return Response.ok();
    }

    @ResponseBody
    @RequestMapping({"/activate-user"})
    public Response activateUser(@RequestParam String key) {
        try {
            userService.activateUser(key);
            return Response.ok();
        } catch (UserNotFoundWithKeyException e) {
            return Response.withError(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/reset-password-request"})
    public Response resetPasswordRequest(@RequestParam String mail) {
        try {
            userService.resetPasswordRequest(mail);
            return Response.ok();
        } catch (UserNotFoundWithKeyException e) {
            return Response.withError(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/reset-user-password"})
    public Response resetUserPassword(@RequestParam String key, @RequestParam String newPassword) {
        try {
            userService.resetUserPassword(key, newPassword);
            return Response.ok();
        } catch (UserNotFoundWithKeyException e) {
            return Response.withError(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/get-active-sessions"})
    public Response getAllSessions(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam int limit, @RequestParam(required = false, defaultValue = "desc") String orderBy) {
        return Response.withData(reformService.getActiveSessions(start, limit, orderBy));
    }

    @ResponseBody
    @RequestMapping({"/get-current-reforms"})
    public Response getCurrentReform(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam int limit) {
        return Response.withData(reformService.getCurrentReforms(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-reform-by-id"})
    public Response getReformById(@RequestParam int reformId) {
        return Response.withData(reformService.getReform(reformId));
    }

    @ResponseBody
    @RequestMapping({"/get-session-by-id"})
    public Response getSessionById(@RequestParam int sessionId) {
        return Response.withData(reformService.getSession(sessionId));
    }

    @ResponseBody
    @RequestMapping({"/get-close-sessions"})
    public Response getCloseSessions(@RequestParam int start, @RequestParam int limit) {
        return Response.withData(reformService.getCloseSessions(start, limit));
    }

    @ResponseBody
    @RequestMapping({"/get-session-details"})
    public Response getSessionDetails(@RequestParam int sessionId) {
        return Response.withData(reformService.getSession(sessionId));
    }

    @ResponseBody
    @RequestMapping({"/save-reform-vote"})
    public Response saveReformVote(@RequestParam int reformId, @RequestParam boolean agreed, @RequestParam(required = false, defaultValue = "0") int userId,
                                   @RequestParam(required = false) String clientUID) {
        try {
            AddReformVoteRequest request = new AddReformVoteRequest();
            request.setReformId(reformId);
            request.setUserId(userId);
            request.setAgreed(agreed);
            request.setClientUID(clientUID);
            return Response.withData(voteService.saveReformVote(request));
        } catch (ReformHasVoteAlreadyException ex) {
            return Response.withError(ex.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/get-reform-vote-by-client"})
    public Response saveReformVote(@RequestParam int reformId, @RequestParam(required = false, defaultValue = "0") int userId, @RequestParam String clientUID) {
        return Response.withData(voteService.getReformVoteByClientGuid(reformId, clientUID));
    }

    @ResponseBody
    @RequestMapping({"/save-poll-vote"})
    public Response savePollVote(@RequestParam int reformId, @RequestParam int sessionId, @RequestParam String questionAnswerList, @RequestParam(required = false) String answerNote,
                                 @RequestParam(required = false, defaultValue = "0") int sessionVoteId, @RequestParam(required = false, defaultValue = "0") int userId,
                                 @RequestParam(required = false) String ipAddress, @RequestParam(required = false) String clientUID) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            QuestionAnswer[] questionAnswers = mapper.readValue(questionAnswerList, QuestionAnswer[].class);
            if (questionAnswers.length > 0) {
                AddVoteRequest request = new AddVoteRequest();
                request.setReformId(reformId);
                request.setSessionId(sessionId);
                request.setQuestionAnswerList(Arrays.asList(questionAnswers));
                request.setAnswerNote(answerNote);
                request.setSessionVoteId(sessionVoteId);
                request.setUserId(userId);
                request.setIpAddress(ipAddress);
                request.setClientUID(clientUID);
                return Response.withData(voteService.saveSessionPollVote(request));
            } else return Response.withError("polls is empty");
        } catch (SessionAlreadyHasVoteException ex) {
            return Response.withError(ex.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping({"/get-spheres"})
    public Response getSpheres() {
        return Response.withData(initiateService.getSpheres());
    }

    @ResponseBody
    @RequestMapping({"/get-activity-spheres"})
    public Response getActivitySpheres() {
        return Response.withData(userService.getActivitySpheres());
    }

    @ResponseBody
    @RequestMapping({"/get-age-ranges"})
    public Response getAgeRanges() {
        return Response.withData(userService.getAgeRanges());
    }

    @ResponseBody
    @RequestMapping({"/get-education-levels"})
    public Response getEducationLevels() {
        return Response.withData(userService.getEducationLevels());
    }

    @ResponseBody
    @RequestMapping({"/get-enterprise-sizes"})
    public Response getEnterpriseSizes() {
        return Response.withData(userService.getEnterpriseSizes());
    }
}
