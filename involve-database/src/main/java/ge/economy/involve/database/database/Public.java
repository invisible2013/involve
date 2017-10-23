/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database;


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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

	private static final long serialVersionUID = -2015227249;

	/**
	 * The reference instance of <code>public</code>
	 */
	public static final Public PUBLIC = new Public();

	/**
	 * No further instances allowed
	 */
	private Public() {
		super("public");
	}

	@Override
	public final List<Sequence<?>> getSequences() {
		List result = new ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final List<Sequence<?>> getSequences0() {
		return Arrays.<Sequence<?>>asList(
			Sequences.FILE_TYPE_ID_SEQ,
			Sequences.GENDER_ID_SEQ,
			Sequences.INITIATED_ISSUE_ID_SEQ,
			Sequences.INITIATE_ID_SEQ,
			Sequences.INITIATIVE_VOTE_ID_SEQ,
			Sequences.POLL_ANSWER_ID_SEQ,
			Sequences.PRIORITY_ANSWER_ID_SEQ,
			Sequences.PRIORITY_ID_SEQ,
			Sequences.PRIORITY_ITEM_ID_SEQ,
			Sequences.PRIORITY_VOTE_ID_SEQ,
			Sequences.REFORM_DETAIL_ID_SEQ,
			Sequences.REFORM_FILE_ID_SEQ,
			Sequences.REFORM_ID_SEQ,
			Sequences.REFORM_TYPE_ID_SEQ,
			Sequences.SESSION_FILE_ID_SEQ,
			Sequences.SESSION_ID_SEQ,
			Sequences.SESSION_POLL_ID_SEQ,
			Sequences.SESSION_POLL_VOTE_ID_SEQ,
			Sequences.SESSION_VOTE_ID_SEQ,
			Sequences.SPHERE_ID_SEQ,
			Sequences.TOKEN_ID_SEQ,
			Sequences.USER_APPROVE_ID_SEQ,
			Sequences.USER_GROUP_ID_SEQ,
			Sequences.USER_REGISTER_ID_SEQ,
			Sequences.USERS_ID_SEQ,
			Sequences.USER_STATUS_ID_SEQ,
			Sequences.USER_TYPE_ID_SEQ);
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			FileType.FILE_TYPE,
			Gender.GENDER,
			Initiate.INITIATE,
			InitiatedIssue.INITIATED_ISSUE,
			InitiativeVote.INITIATIVE_VOTE,
			PollAnswer.POLL_ANSWER,
			Priority.PRIORITY,
			PriorityAnswer.PRIORITY_ANSWER,
			PriorityItem.PRIORITY_ITEM,
			PriorityVote.PRIORITY_VOTE,
			Reform.REFORM,
			ReformDetail.REFORM_DETAIL,
			ReformFile.REFORM_FILE,
			ReformType.REFORM_TYPE,
			ReformVote.REFORM_VOTE,
			Session.SESSION,
			SessionFile.SESSION_FILE,
			SessionPoll.SESSION_POLL,
			SessionPollVote.SESSION_POLL_VOTE,
			Sphere.SPHERE,
			Token.TOKEN,
			UserApprove.USER_APPROVE,
			UserGroup.USER_GROUP,
			UserRegister.USER_REGISTER,
			Users.USERS,
			UserStatus.USER_STATUS,
			UserType.USER_TYPE);
	}
}
