/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.Token;

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
public class TokenRecord extends UpdatableRecordImpl<TokenRecord> implements Record5<Integer, Integer, String, Date, Date> {

	private static final long serialVersionUID = -934953327;

	/**
	 * Setter for <code>public.token.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.token.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.token.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.token.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.token.key</code>.
	 */
	public void setKey(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.token.key</code>.
	 */
	public String getKey() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>public.token.create_date</code>.
	 */
	public void setCreateDate(Date value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.token.create_date</code>.
	 */
	public Date getCreateDate() {
		return (Date) getValue(3);
	}

	/**
	 * Setter for <code>public.token.valid_date</code>.
	 */
	public void setValidDate(Date value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.token.valid_date</code>.
	 */
	public Date getValidDate() {
		return (Date) getValue(4);
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
	public Row5<Integer, Integer, String, Date, Date> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, String, Date, Date> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Token.TOKEN.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Token.TOKEN.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Token.TOKEN.KEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field4() {
		return Token.TOKEN.CREATE_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field5() {
		return Token.TOKEN.VALID_DATE;
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
	public Date value4() {
		return getCreateDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value5() {
		return getValidDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord value2(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord value3(String value) {
		setKey(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord value4(Date value) {
		setCreateDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord value5(Date value) {
		setValidDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenRecord values(Integer value1, Integer value2, String value3, Date value4, Date value5) {
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
	 * Create a detached TokenRecord
	 */
	public TokenRecord() {
		super(Token.TOKEN);
	}

	/**
	 * Create a detached, initialised TokenRecord
	 */
	public TokenRecord(Integer id, Integer userId, String key, Date createDate, Date validDate) {
		super(Token.TOKEN);

		setValue(0, id);
		setValue(1, userId);
		setValue(2, key);
		setValue(3, createDate);
		setValue(4, validDate);
	}
}
