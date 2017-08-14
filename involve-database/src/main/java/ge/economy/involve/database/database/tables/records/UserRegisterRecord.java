/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.UserRegister;

import java.util.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class UserRegisterRecord extends UpdatableRecordImpl<UserRegisterRecord> implements Record6<Integer, Integer, String, Boolean, String, Date> {

	private static final long serialVersionUID = 586348992;

	/**
	 * Setter for <code>public.user_register.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.user_register.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.user_register.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.user_register.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.user_register.key</code>.
	 */
	public void setKey(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.user_register.key</code>.
	 */
	public String getKey() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>public.user_register.is_expired</code>.
	 */
	public void setIsExpired(Boolean value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.user_register.is_expired</code>.
	 */
	public Boolean getIsExpired() {
		return (Boolean) getValue(3);
	}

	/**
	 * Setter for <code>public.user_register.email</code>.
	 */
	public void setEmail(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.user_register.email</code>.
	 */
	public String getEmail() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>public.user_register.create_date</code>.
	 */
	public void setCreateDate(Date value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.user_register.create_date</code>.
	 */
	public Date getCreateDate() {
		return (Date) getValue(5);
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
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, String, Boolean, String, Date> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, String, Boolean, String, Date> valuesRow() {
		return (Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return UserRegister.USER_REGISTER.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return UserRegister.USER_REGISTER.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return UserRegister.USER_REGISTER.KEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field4() {
		return UserRegister.USER_REGISTER.IS_EXPIRED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return UserRegister.USER_REGISTER.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field6() {
		return UserRegister.USER_REGISTER.CREATE_DATE;
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
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value4() {
		return getIsExpired();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value6() {
		return getCreateDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value2(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value3(String value) {
		setKey(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value4(Boolean value) {
		setIsExpired(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value5(String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord value6(Date value) {
		setCreateDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegisterRecord values(Integer value1, Integer value2, String value3, Boolean value4, String value5, Date value6) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserRegisterRecord
	 */
	public UserRegisterRecord() {
		super(UserRegister.USER_REGISTER);
	}

	/**
	 * Create a detached, initialised UserRegisterRecord
	 */
	public UserRegisterRecord(Integer id, Integer userId, String key, Boolean isExpired, String email, Date createDate) {
		super(UserRegister.USER_REGISTER);

		setValue(0, id);
		setValue(1, userId);
		setValue(2, key);
		setValue(3, isExpired);
		setValue(4, email);
		setValue(5, createDate);
	}
}
