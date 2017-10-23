/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.converter.TimestampConverter;
import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class InitiatedIssue extends TableImpl<InitiatedIssueRecord> {

	private static final long serialVersionUID = -2108756204;

	/**
	 * The reference instance of <code>public.initiated_issue</code>
	 */
	public static final InitiatedIssue INITIATED_ISSUE = new InitiatedIssue();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<InitiatedIssueRecord> getRecordType() {
		return InitiatedIssueRecord.class;
	}

	/**
	 * The column <code>public.initiated_issue.id</code>.
	 */
	public final TableField<InitiatedIssueRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.initiated_issue.description</code>.
	 */
	public final TableField<InitiatedIssueRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiated_issue.create_date</code>.
	 */
	public final TableField<InitiatedIssueRecord, Date> CREATE_DATE = createField("create_date", org.jooq.impl.SQLDataType.TIMESTAMP.defaulted(true), this, "", new TimestampConverter());

	/**
	 * The column <code>public.initiated_issue.creator_id</code>.
	 */
	public final TableField<InitiatedIssueRecord, Integer> CREATOR_ID = createField("creator_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>public.initiated_issue</code> table reference
	 */
	public InitiatedIssue() {
		this("initiated_issue", null);
	}

	/**
	 * Create an aliased <code>public.initiated_issue</code> table reference
	 */
	public InitiatedIssue(String alias) {
		this(alias, INITIATED_ISSUE);
	}

	private InitiatedIssue(String alias, Table<InitiatedIssueRecord> aliased) {
		this(alias, aliased, null);
	}

	private InitiatedIssue(String alias, Table<InitiatedIssueRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<InitiatedIssueRecord, Integer> getIdentity() {
		return Keys.IDENTITY_INITIATED_ISSUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<InitiatedIssueRecord> getPrimaryKey() {
		return Keys.INITIATED_ISSUE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<InitiatedIssueRecord>> getKeys() {
		return Arrays.<UniqueKey<InitiatedIssueRecord>>asList(Keys.INITIATED_ISSUE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssue as(String alias) {
		return new InitiatedIssue(alias, this);
	}

	/**
	 * Rename this table
	 */
	public InitiatedIssue rename(String name) {
		return new InitiatedIssue(name, null);
	}
}
