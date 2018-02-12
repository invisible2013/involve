/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.EnterpriseSizeRecord;

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
public class EnterpriseSize extends TableImpl<EnterpriseSizeRecord> {

	private static final long serialVersionUID = -1562562344;

	/**
	 * The reference instance of <code>public.enterprise_size</code>
	 */
	public static final EnterpriseSize ENTERPRISE_SIZE = new EnterpriseSize();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<EnterpriseSizeRecord> getRecordType() {
		return EnterpriseSizeRecord.class;
	}

	/**
	 * The column <code>public.enterprise_size.id</code>.
	 */
	public final TableField<EnterpriseSizeRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.enterprise_size.name</code>.
	 */
	public final TableField<EnterpriseSizeRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.enterprise_size</code> table reference
	 */
	public EnterpriseSize() {
		this("enterprise_size", null);
	}

	/**
	 * Create an aliased <code>public.enterprise_size</code> table reference
	 */
	public EnterpriseSize(String alias) {
		this(alias, ENTERPRISE_SIZE);
	}

	private EnterpriseSize(String alias, Table<EnterpriseSizeRecord> aliased) {
		this(alias, aliased, null);
	}

	private EnterpriseSize(String alias, Table<EnterpriseSizeRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<EnterpriseSizeRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ENTERPRISE_SIZE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<EnterpriseSizeRecord> getPrimaryKey() {
		return Keys.ENTERPRISE_SIZE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<EnterpriseSizeRecord>> getKeys() {
		return Arrays.<UniqueKey<EnterpriseSizeRecord>>asList(Keys.ENTERPRISE_SIZE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnterpriseSize as(String alias) {
		return new EnterpriseSize(alias, this);
	}

	/**
	 * Rename this table
	 */
	public EnterpriseSize rename(String name) {
		return new EnterpriseSize(name, null);
	}
}