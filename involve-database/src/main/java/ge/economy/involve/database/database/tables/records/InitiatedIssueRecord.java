/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.InitiatedIssue;

import java.util.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class InitiatedIssueRecord extends UpdatableRecordImpl<InitiatedIssueRecord> implements Record5<Integer, String, Date, Integer, String> {

	private static final long serialVersionUID = -761637530;

	/**
	 * Setter for <code>public.initiated_issue.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.initiated_issue.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.initiated_issue.description</code>.
	 */
	public void setDescription(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.initiated_issue.description</code>.
	 */
	public String getDescription() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.initiated_issue.create_date</code>.
	 */
	public void setCreateDate(Date value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.initiated_issue.create_date</code>.
	 */
	public Date getCreateDate() {
		return (Date) getValue(2);
	}

	/**
	 * Setter for <code>public.initiated_issue.creator_id</code>.
	 */
	public void setCreatorId(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.initiated_issue.creator_id</code>.
	 */
	public Integer getCreatorId() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>public.initiated_issue.name</code>.
	 */
	public void setName(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.initiated_issue.name</code>.
	 */
	public String getName() {
		return (String) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, Date, Integer, String> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, Date, Integer, String> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return InitiatedIssue.INITIATED_ISSUE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return InitiatedIssue.INITIATED_ISSUE.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field3() {
		return InitiatedIssue.INITIATED_ISSUE.CREATE_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return InitiatedIssue.INITIATED_ISSUE.CREATOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return InitiatedIssue.INITIATED_ISSUE.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value3() {
		return getCreateDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getCreatorId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord value2(String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord value3(Date value) {
		setCreateDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord value4(Integer value) {
		setCreatorId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord value5(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatedIssueRecord values(Integer value1, String value2, Date value3, Integer value4, String value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached InitiatedIssueRecord
	 */
	public InitiatedIssueRecord() {
		super(InitiatedIssue.INITIATED_ISSUE);
	}

	/**
	 * Create a detached, initialised InitiatedIssueRecord
	 */
	public InitiatedIssueRecord(Integer id, String description, Date createDate, Integer creatorId, String name) {
		super(InitiatedIssue.INITIATED_ISSUE);

		setValue(0, id);
		setValue(1, description);
		setValue(2, createDate);
		setValue(3, creatorId);
		setValue(4, name);
	}
}
