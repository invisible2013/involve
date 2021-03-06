/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.UserStatusRecord;

import java.util.Arrays;
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
public class UserStatus extends TableImpl<UserStatusRecord> {

	private static final long serialVersionUID = -699147454;

	/**
	 * The reference instance of <code>public.user_status</code>
	 */
	public static final UserStatus USER_STATUS = new UserStatus();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UserStatusRecord> getRecordType() {
		return UserStatusRecord.class;
	}

	/**
	 * The column <code>public.user_status.id</code>.
	 */
	public final TableField<UserStatusRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.user_status.name</code>.
	 */
	public final TableField<UserStatusRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.user_status</code> table reference
	 */
	public UserStatus() {
		this("user_status", null);
	}

	/**
	 * Create an aliased <code>public.user_status</code> table reference
	 */
	public UserStatus(String alias) {
		this(alias, USER_STATUS);
	}

	private UserStatus(String alias, Table<UserStatusRecord> aliased) {
		this(alias, aliased, null);
	}

	private UserStatus(String alias, Table<UserStatusRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<UserStatusRecord, Integer> getIdentity() {
		return Keys.IDENTITY_USER_STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<UserStatusRecord> getPrimaryKey() {
		return Keys.STATUS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<UserStatusRecord>> getKeys() {
		return Arrays.<UniqueKey<UserStatusRecord>>asList(Keys.STATUS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserStatus as(String alias) {
		return new UserStatus(alias, this);
	}

	/**
	 * Rename this table
	 */
	public UserStatus rename(String name) {
		return new UserStatus(name, null);
	}
}
