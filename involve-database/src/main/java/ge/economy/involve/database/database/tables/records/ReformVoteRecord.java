/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.ReformVote;

import java.util.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
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
public class ReformVoteRecord extends UpdatableRecordImpl<ReformVoteRecord> implements Record11<Integer, Integer, Integer, String, Boolean, Integer, String, String, Date, String, String> {

	private static final long serialVersionUID = -817308795;

	/**
	 * Setter for <code>public.reform_vote.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.reform_vote.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.reform_vote.reform_id</code>.
	 */
	public void setReformId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.reform_vote.reform_id</code>.
	 */
	public Integer getReformId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.reform_vote.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.reform_vote.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>public.reform_vote.mail</code>.
	 */
	public void setMail(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.reform_vote.mail</code>.
	 */
	public String getMail() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>public.reform_vote.agreed</code>.
	 */
	public void setAgreed(Boolean value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.reform_vote.agreed</code>.
	 */
	public Boolean getAgreed() {
		return (Boolean) getValue(4);
	}

	/**
	 * Setter for <code>public.reform_vote.range_id</code>.
	 */
	public void setRangeId(Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.reform_vote.range_id</code>.
	 */
	public Integer getRangeId() {
		return (Integer) getValue(5);
	}

	/**
	 * Setter for <code>public.reform_vote.profession</code>.
	 */
	public void setProfession(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.reform_vote.profession</code>.
	 */
	public String getProfession() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>public.reform_vote.field_of_activity</code>.
	 */
	public void setFieldOfActivity(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.reform_vote.field_of_activity</code>.
	 */
	public String getFieldOfActivity() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>public.reform_vote.create_date</code>.
	 */
	public void setCreateDate(Date value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.reform_vote.create_date</code>.
	 */
	public Date getCreateDate() {
		return (Date) getValue(8);
	}

	/**
	 * Setter for <code>public.reform_vote.client_uid</code>.
	 */
	public void setClientUid(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.reform_vote.client_uid</code>.
	 */
	public String getClientUid() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>public.reform_vote.ip_address</code>.
	 */
	public void setIpAddress(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>public.reform_vote.ip_address</code>.
	 */
	public String getIpAddress() {
		return (String) getValue(10);
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
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row11<Integer, Integer, Integer, String, Boolean, Integer, String, String, Date, String, String> fieldsRow() {
		return (Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row11<Integer, Integer, Integer, String, Boolean, Integer, String, String, Date, String, String> valuesRow() {
		return (Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ReformVote.REFORM_VOTE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return ReformVote.REFORM_VOTE.REFORM_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return ReformVote.REFORM_VOTE.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return ReformVote.REFORM_VOTE.MAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field5() {
		return ReformVote.REFORM_VOTE.AGREED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return ReformVote.REFORM_VOTE.RANGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return ReformVote.REFORM_VOTE.PROFESSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return ReformVote.REFORM_VOTE.FIELD_OF_ACTIVITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field9() {
		return ReformVote.REFORM_VOTE.CREATE_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field10() {
		return ReformVote.REFORM_VOTE.CLIENT_UID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field11() {
		return ReformVote.REFORM_VOTE.IP_ADDRESS;
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
	public Integer value2() {
		return getReformId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getMail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value5() {
		return getAgreed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getRangeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getProfession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getFieldOfActivity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value9() {
		return getCreateDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value10() {
		return getClientUid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value11() {
		return getIpAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value2(Integer value) {
		setReformId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value3(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value4(String value) {
		setMail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value5(Boolean value) {
		setAgreed(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value6(Integer value) {
		setRangeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value7(String value) {
		setProfession(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value8(String value) {
		setFieldOfActivity(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value9(Date value) {
		setCreateDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value10(String value) {
		setClientUid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord value11(String value) {
		setIpAddress(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformVoteRecord values(Integer value1, Integer value2, Integer value3, String value4, Boolean value5, Integer value6, String value7, String value8, Date value9, String value10, String value11) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ReformVoteRecord
	 */
	public ReformVoteRecord() {
		super(ReformVote.REFORM_VOTE);
	}

	/**
	 * Create a detached, initialised ReformVoteRecord
	 */
	public ReformVoteRecord(Integer id, Integer reformId, Integer userId, String mail, Boolean agreed, Integer rangeId, String profession, String fieldOfActivity, Date createDate, String clientUid, String ipAddress) {
		super(ReformVote.REFORM_VOTE);

		setValue(0, id);
		setValue(1, reformId);
		setValue(2, userId);
		setValue(3, mail);
		setValue(4, agreed);
		setValue(5, rangeId);
		setValue(6, profession);
		setValue(7, fieldOfActivity);
		setValue(8, createDate);
		setValue(9, clientUid);
		setValue(10, ipAddress);
	}
}
