/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.UserType;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class UserTypeRecord extends UpdatableRecordImpl<UserTypeRecord> implements Record2<Integer, String> {

	private static final long serialVersionUID = 1515297005;

	/**
	 * Setter for <code>public.user_type.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.user_type.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.user_type.name</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.user_type.name</code>.
	 */
	public String getName() {
		return (String) getValue(1);
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
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return UserType.USER_TYPE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return UserType.USER_TYPE.NAME;
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
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTypeRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTypeRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserTypeRecord values(Integer value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UserTypeRecord
	 */
	public UserTypeRecord() {
		super(UserType.USER_TYPE);
	}

	/**
	 * Create a detached, initialised UserTypeRecord
	 */
	public UserTypeRecord(Integer id, String name) {
		super(UserType.USER_TYPE);

		setValue(0, id);
		setValue(1, name);
	}
}
