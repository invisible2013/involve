/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.converter.TimestampConverter;
import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.UserRegisterRecord;

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
public class UserRegister extends TableImpl<UserRegisterRecord> {

	private static final long serialVersionUID = 1374306820;

	/**
	 * The reference instance of <code>public.user_register</code>
	 */
	public static final UserRegister USER_REGISTER = new UserRegister();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UserRegisterRecord> getRecordType() {
		return UserRegisterRecord.class;
	}

	/**
	 * The column <code>public.user_register.id</code>.
	 */
	public final TableField<UserRegisterRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.user_register.user_id</code>.
	 */
	public final TableField<UserRegisterRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.user_register.key</code>.
	 */
	public final TableField<UserRegisterRecord, String> KEY = createField("key", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.user_register.is_expired</code>.
	 */
	public final TableField<UserRegisterRecord, Boolean> IS_EXPIRED = createField("is_expired", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * The column <code>public.user_register.email</code>.
	 */
	public final TableField<UserRegisterRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.user_register.create_date</code>.
	 */
	public final TableField<UserRegisterRecord, Date> CREATE_DATE = createField("create_date", org.jooq.impl.SQLDataType.TIMESTAMP.defaulted(true), this, "", new TimestampConverter());

	/**
	 * Create a <code>public.user_register</code> table reference
	 */
	public UserRegister() {
		this("user_register", null);
	}

	/**
	 * Create an aliased <code>public.user_register</code> table reference
	 */
	public UserRegister(String alias) {
		this(alias, USER_REGISTER);
	}

	private UserRegister(String alias, Table<UserRegisterRecord> aliased) {
		this(alias, aliased, null);
	}

	private UserRegister(String alias, Table<UserRegisterRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<UserRegisterRecord, Integer> getIdentity() {
		return Keys.IDENTITY_USER_REGISTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<UserRegisterRecord> getPrimaryKey() {
		return Keys.USER_REGISTER_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<UserRegisterRecord>> getKeys() {
		return Arrays.<UniqueKey<UserRegisterRecord>>asList(Keys.USER_REGISTER_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserRegister as(String alias) {
		return new UserRegister(alias, this);
	}

	/**
	 * Rename this table
	 */
	public UserRegister rename(String name) {
		return new UserRegister(name, null);
	}
}
