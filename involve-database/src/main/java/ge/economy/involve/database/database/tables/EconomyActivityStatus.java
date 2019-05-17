/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.EconomyActivityStatusRecord;

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
public class EconomyActivityStatus extends TableImpl<EconomyActivityStatusRecord> {

	private static final long serialVersionUID = 554237537;

	/**
	 * The reference instance of <code>public.economy_activity_status</code>
	 */
	public static final EconomyActivityStatus ECONOMY_ACTIVITY_STATUS = new EconomyActivityStatus();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<EconomyActivityStatusRecord> getRecordType() {
		return EconomyActivityStatusRecord.class;
	}

	/**
	 * The column <code>public.economy_activity_status.id</code>.
	 */
	public final TableField<EconomyActivityStatusRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.economy_activity_status.name</code>.
	 */
	public final TableField<EconomyActivityStatusRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.economy_activity_status</code> table reference
	 */
	public EconomyActivityStatus() {
		this("economy_activity_status", null);
	}

	/**
	 * Create an aliased <code>public.economy_activity_status</code> table reference
	 */
	public EconomyActivityStatus(String alias) {
		this(alias, ECONOMY_ACTIVITY_STATUS);
	}

	private EconomyActivityStatus(String alias, Table<EconomyActivityStatusRecord> aliased) {
		this(alias, aliased, null);
	}

	private EconomyActivityStatus(String alias, Table<EconomyActivityStatusRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<EconomyActivityStatusRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ECONOMY_ACTIVITY_STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<EconomyActivityStatusRecord> getPrimaryKey() {
		return Keys.ECONOMY_ACTIVITY_STATUS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<EconomyActivityStatusRecord>> getKeys() {
		return Arrays.<UniqueKey<EconomyActivityStatusRecord>>asList(Keys.ECONOMY_ACTIVITY_STATUS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EconomyActivityStatus as(String alias) {
		return new EconomyActivityStatus(alias, this);
	}

	/**
	 * Rename this table
	 */
	public EconomyActivityStatus rename(String name) {
		return new EconomyActivityStatus(name, null);
	}
}