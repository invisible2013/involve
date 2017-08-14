/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.GenderRecord;

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
public class Gender extends TableImpl<GenderRecord> {

	private static final long serialVersionUID = -977741173;

	/**
	 * The reference instance of <code>public.gender</code>
	 */
	public static final Gender GENDER = new Gender();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<GenderRecord> getRecordType() {
		return GenderRecord.class;
	}

	/**
	 * The column <code>public.gender.id</code>.
	 */
	public final TableField<GenderRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.gender.name</code>.
	 */
	public final TableField<GenderRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.gender</code> table reference
	 */
	public Gender() {
		this("gender", null);
	}

	/**
	 * Create an aliased <code>public.gender</code> table reference
	 */
	public Gender(String alias) {
		this(alias, GENDER);
	}

	private Gender(String alias, Table<GenderRecord> aliased) {
		this(alias, aliased, null);
	}

	private Gender(String alias, Table<GenderRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<GenderRecord, Integer> getIdentity() {
		return Keys.IDENTITY_GENDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<GenderRecord> getPrimaryKey() {
		return Keys.GENDER_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<GenderRecord>> getKeys() {
		return Arrays.<UniqueKey<GenderRecord>>asList(Keys.GENDER_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Gender as(String alias) {
		return new Gender(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Gender rename(String name) {
		return new Gender(name, null);
	}
}