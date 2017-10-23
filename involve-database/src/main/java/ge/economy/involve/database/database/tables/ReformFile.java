/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.converter.TimestampConverter;
import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.ReformFileRecord;

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
public class ReformFile extends TableImpl<ReformFileRecord> {

	private static final long serialVersionUID = 176960123;

	/**
	 * The reference instance of <code>public.reform_file</code>
	 */
	public static final ReformFile REFORM_FILE = new ReformFile();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ReformFileRecord> getRecordType() {
		return ReformFileRecord.class;
	}

	/**
	 * The column <code>public.reform_file.id</code>.
	 */
	public final TableField<ReformFileRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.reform_file.reform_id</code>.
	 */
	public final TableField<ReformFileRecord, Integer> REFORM_ID = createField("reform_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.reform_file.file_type_id</code>.
	 */
	public final TableField<ReformFileRecord, Integer> FILE_TYPE_ID = createField("file_type_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.reform_file.file_name</code>.
	 */
	public final TableField<ReformFileRecord, String> FILE_NAME = createField("file_name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.reform_file.create_date</code>.
	 */
	public final TableField<ReformFileRecord, Date> CREATE_DATE = createField("create_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new TimestampConverter());

	/**
	 * Create a <code>public.reform_file</code> table reference
	 */
	public ReformFile() {
		this("reform_file", null);
	}

	/**
	 * Create an aliased <code>public.reform_file</code> table reference
	 */
	public ReformFile(String alias) {
		this(alias, REFORM_FILE);
	}

	private ReformFile(String alias, Table<ReformFileRecord> aliased) {
		this(alias, aliased, null);
	}

	private ReformFile(String alias, Table<ReformFileRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ReformFileRecord, Integer> getIdentity() {
		return Keys.IDENTITY_REFORM_FILE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ReformFileRecord> getPrimaryKey() {
		return Keys.REFORM_FILE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ReformFileRecord>> getKeys() {
		return Arrays.<UniqueKey<ReformFileRecord>>asList(Keys.REFORM_FILE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFile as(String alias) {
		return new ReformFile(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ReformFile rename(String name) {
		return new ReformFile(name, null);
	}
}
