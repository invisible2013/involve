/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database;


import ge.economy.involve.database.database.tables.AgeRange;
import ge.economy.involve.database.database.tables.FileType;
import ge.economy.involve.database.database.tables.Gender;
import ge.economy.involve.database.database.tables.Initiate;
import ge.economy.involve.database.database.tables.InitiatedIssue;
import ge.economy.involve.database.database.tables.InitiativeVote;
import ge.economy.involve.database.database.tables.PollAnswer;
import ge.economy.involve.database.database.tables.Priority;
import ge.economy.involve.database.database.tables.PriorityAnswer;
import ge.economy.involve.database.database.tables.PriorityItem;
import ge.economy.involve.database.database.tables.PriorityVote;
import ge.economy.involve.database.database.tables.Reform;
import ge.economy.involve.database.database.tables.ReformDetail;
import ge.economy.involve.database.database.tables.ReformFile;
import ge.economy.involve.database.database.tables.ReformType;
import ge.economy.involve.database.database.tables.ReformVote;
import ge.economy.involve.database.database.tables.Session;
import ge.economy.involve.database.database.tables.SessionFile;
import ge.economy.involve.database.database.tables.SessionPoll;
import ge.economy.involve.database.database.tables.SessionPollVote;
import ge.economy.involve.database.database.tables.Sphere;
import ge.economy.involve.database.database.tables.Token;
import ge.economy.involve.database.database.tables.UserApprove;
import ge.economy.involve.database.database.tables.UserGroup;
import ge.economy.involve.database.database.tables.UserRegister;
import ge.economy.involve.database.database.tables.UserStatus;
import ge.economy.involve.database.database.tables.UserType;
import ge.economy.involve.database.database.tables.Users;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table public.age_range
	 */
	public static final AgeRange AGE_RANGE = ge.economy.involve.database.database.tables.AgeRange.AGE_RANGE;

	/**
	 * The table public.file_type
	 */
	public static final FileType FILE_TYPE = ge.economy.involve.database.database.tables.FileType.FILE_TYPE;

	/**
	 * The table public.gender
	 */
	public static final Gender GENDER = ge.economy.involve.database.database.tables.Gender.GENDER;

	/**
	 * The table public.initiate
	 */
	public static final Initiate INITIATE = ge.economy.involve.database.database.tables.Initiate.INITIATE;

	/**
	 * The table public.initiated_issue
	 */
	public static final InitiatedIssue INITIATED_ISSUE = ge.economy.involve.database.database.tables.InitiatedIssue.INITIATED_ISSUE;

	/**
	 * The table public.initiative_vote
	 */
	public static final InitiativeVote INITIATIVE_VOTE = ge.economy.involve.database.database.tables.InitiativeVote.INITIATIVE_VOTE;

	/**
	 * The table public.poll_answer
	 */
	public static final PollAnswer POLL_ANSWER = ge.economy.involve.database.database.tables.PollAnswer.POLL_ANSWER;

	/**
	 * The table public.priority
	 */
	public static final Priority PRIORITY = ge.economy.involve.database.database.tables.Priority.PRIORITY;

	/**
	 * The table public.priority_answer
	 */
	public static final PriorityAnswer PRIORITY_ANSWER = ge.economy.involve.database.database.tables.PriorityAnswer.PRIORITY_ANSWER;

	/**
	 * The table public.priority_item
	 */
	public static final PriorityItem PRIORITY_ITEM = ge.economy.involve.database.database.tables.PriorityItem.PRIORITY_ITEM;

	/**
	 * The table public.priority_vote
	 */
	public static final PriorityVote PRIORITY_VOTE = ge.economy.involve.database.database.tables.PriorityVote.PRIORITY_VOTE;

	/**
	 * The table public.reform
	 */
	public static final Reform REFORM = ge.economy.involve.database.database.tables.Reform.REFORM;

	/**
	 * The table public.reform_detail
	 */
	public static final ReformDetail REFORM_DETAIL = ge.economy.involve.database.database.tables.ReformDetail.REFORM_DETAIL;

	/**
	 * The table public.reform_file
	 */
	public static final ReformFile REFORM_FILE = ge.economy.involve.database.database.tables.ReformFile.REFORM_FILE;

	/**
	 * The table public.reform_type
	 */
	public static final ReformType REFORM_TYPE = ge.economy.involve.database.database.tables.ReformType.REFORM_TYPE;

	/**
	 * The table public.reform_vote
	 */
	public static final ReformVote REFORM_VOTE = ge.economy.involve.database.database.tables.ReformVote.REFORM_VOTE;

	/**
	 * The table public.session
	 */
	public static final Session SESSION = ge.economy.involve.database.database.tables.Session.SESSION;

	/**
	 * The table public.session_file
	 */
	public static final SessionFile SESSION_FILE = ge.economy.involve.database.database.tables.SessionFile.SESSION_FILE;

	/**
	 * The table public.session_poll
	 */
	public static final SessionPoll SESSION_POLL = ge.economy.involve.database.database.tables.SessionPoll.SESSION_POLL;

	/**
	 * The table public.session_poll_vote
	 */
	public static final SessionPollVote SESSION_POLL_VOTE = ge.economy.involve.database.database.tables.SessionPollVote.SESSION_POLL_VOTE;

	/**
	 * The table public.sphere
	 */
	public static final Sphere SPHERE = ge.economy.involve.database.database.tables.Sphere.SPHERE;

	/**
	 * The table public.token
	 */
	public static final Token TOKEN = ge.economy.involve.database.database.tables.Token.TOKEN;

	/**
	 * The table public.user_approve
	 */
	public static final UserApprove USER_APPROVE = ge.economy.involve.database.database.tables.UserApprove.USER_APPROVE;

	/**
	 * The table public.user_group
	 */
	public static final UserGroup USER_GROUP = ge.economy.involve.database.database.tables.UserGroup.USER_GROUP;

	/**
	 * The table public.user_register
	 */
	public static final UserRegister USER_REGISTER = ge.economy.involve.database.database.tables.UserRegister.USER_REGISTER;

	/**
	 * The table public.users
	 */
	public static final Users USERS = ge.economy.involve.database.database.tables.Users.USERS;

	/**
	 * The table public.user_status
	 */
	public static final UserStatus USER_STATUS = ge.economy.involve.database.database.tables.UserStatus.USER_STATUS;

	/**
	 * The table public.user_type
	 */
	public static final UserType USER_TYPE = ge.economy.involve.database.database.tables.UserType.USER_TYPE;
}
