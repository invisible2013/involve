/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.converter.TimestampConverter;
import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.InitiateRecord;

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
public class Initiate extends TableImpl<InitiateRecord> {

	private static final long serialVersionUID = -1392798189;

	/**
	 * The reference instance of <code>public.initiate</code>
	 */
	public static final Initiate INITIATE = new Initiate();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<InitiateRecord> getRecordType() {
		return InitiateRecord.class;
	}

	/**
	 * The column <code>public.initiate.id</code>.
	 */
	public final TableField<InitiateRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.initiate.sphere_id</code>.
	 */
	public final TableField<InitiateRecord, Integer> SPHERE_ID = createField("sphere_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.initiate.user_id</code>.
	 */
	public final TableField<InitiateRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.initiate.description</code>.
	 */
	public final TableField<InitiateRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiate.group_id</code>.
	 */
	public final TableField<InitiateRecord, Integer> GROUP_ID = createField("group_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.initiate.create_date</code>.
	 */
	public final TableField<InitiateRecord, Date> CREATE_DATE = createField("create_date", org.jooq.impl.SQLDataType.TIMESTAMP.defaulted(true), this, "", new TimestampConverter());

	/**
	 * The column <code>public.initiate.client_uid</code>.
	 */
	public final TableField<InitiateRecord, String> CLIENT_UID = createField("client_uid", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiate.ip_address</code>.
	 */
	public final TableField<InitiateRecord, String> IP_ADDRESS = createField("ip_address", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiate.name</code>.
	 */
	public final TableField<InitiateRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiate.necessity</code>.
	 */
	public final TableField<InitiateRecord, String> NECESSITY = createField("necessity", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.initiate.advantages</code>.
	 */
	public final TableField<InitiateRecord, String> ADVANTAGES = createField("advantages", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.initiate</code> table reference
	 */
	public Initiate() {
		this("initiate", null);
	}

	/**
	 * Create an aliased <code>public.initiate</code> table reference
	 */
	public Initiate(String alias) {
		this(alias, INITIATE);
	}

	private Initiate(String alias, Table<InitiateRecord> aliased) {
		this(alias, aliased, null);
	}

	private Initiate(String alias, Table<InitiateRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<InitiateRecord, Integer> getIdentity() {
		return Keys.IDENTITY_INITIATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<InitiateRecord> getPrimaryKey() {
		return Keys.INITIATE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<InitiateRecord>> getKeys() {
		return Arrays.<UniqueKey<InitiateRecord>>asList(Keys.INITIATE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Initiate as(String alias) {
		return new Initiate(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Initiate rename(String name) {
		return new Initiate(name, null);
	}
}
