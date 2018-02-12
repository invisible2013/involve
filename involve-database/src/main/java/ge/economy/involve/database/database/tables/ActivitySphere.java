/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables;


import ge.economy.involve.database.database.Keys;
import ge.economy.involve.database.database.Public;
import ge.economy.involve.database.database.tables.records.ActivitySphereRecord;

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
public class ActivitySphere extends TableImpl<ActivitySphereRecord> {

	private static final long serialVersionUID = -1277869792;

	/**
	 * The reference instance of <code>public.activity_sphere</code>
	 */
	public static final ActivitySphere ACTIVITY_SPHERE = new ActivitySphere();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ActivitySphereRecord> getRecordType() {
		return ActivitySphereRecord.class;
	}

	/**
	 * The column <code>public.activity_sphere.id</code>.
	 */
	public final TableField<ActivitySphereRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.activity_sphere.name</code>.
	 */
	public final TableField<ActivitySphereRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.activity_sphere</code> table reference
	 */
	public ActivitySphere() {
		this("activity_sphere", null);
	}

	/**
	 * Create an aliased <code>public.activity_sphere</code> table reference
	 */
	public ActivitySphere(String alias) {
		this(alias, ACTIVITY_SPHERE);
	}

	private ActivitySphere(String alias, Table<ActivitySphereRecord> aliased) {
		this(alias, aliased, null);
	}

	private ActivitySphere(String alias, Table<ActivitySphereRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ActivitySphereRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ACTIVITY_SPHERE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ActivitySphereRecord> getPrimaryKey() {
		return Keys.ACTIVITY_SPHERE_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ActivitySphereRecord>> getKeys() {
		return Arrays.<UniqueKey<ActivitySphereRecord>>asList(Keys.ACTIVITY_SPHERE_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActivitySphere as(String alias) {
		return new ActivitySphere(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ActivitySphere rename(String name) {
		return new ActivitySphere(name, null);
	}
}