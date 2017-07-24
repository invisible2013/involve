/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.ReformFile;

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
public class ReformFileRecord extends UpdatableRecordImpl<ReformFileRecord> implements Record5<Integer, Integer, Integer, String, Date> {

	private static final long serialVersionUID = 1660294557;

	/**
	 * Setter for <code>public.reform_file.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.reform_file.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.reform_file.reform_id</code>.
	 */
	public void setReformId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.reform_file.reform_id</code>.
	 */
	public Integer getReformId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.reform_file.file_type_id</code>.
	 */
	public void setFileTypeId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.reform_file.file_type_id</code>.
	 */
	public Integer getFileTypeId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>public.reform_file.file_name</code>.
	 */
	public void setFileName(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.reform_file.file_name</code>.
	 */
	public String getFileName() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>public.reform_file.create_date</code>.
	 */
	public void setCreateDate(Date value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.reform_file.create_date</code>.
	 */
	public Date getCreateDate() {
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
	public Row5<Integer, Integer, Integer, String, Date> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, Integer, String, Date> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ReformFile.REFORM_FILE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return ReformFile.REFORM_FILE.REFORM_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return ReformFile.REFORM_FILE.FILE_TYPE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return ReformFile.REFORM_FILE.FILE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field5() {
		return ReformFile.REFORM_FILE.CREATE_DATE;
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
		return getReformId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getFileTypeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getFileName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value5() {
		return getCreateDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord value2(Integer value) {
		setReformId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord value3(Integer value) {
		setFileTypeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord value4(String value) {
		setFileName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord value5(Date value) {
		setCreateDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReformFileRecord values(Integer value1, Integer value2, Integer value3, String value4, Date value5) {
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
	 * Create a detached ReformFileRecord
	 */
	public ReformFileRecord() {
		super(ReformFile.REFORM_FILE);
	}

	/**
	 * Create a detached, initialised ReformFileRecord
	 */
	public ReformFileRecord(Integer id, Integer reformId, Integer fileTypeId, String fileName, Date createDate) {
		super(ReformFile.REFORM_FILE);

		setValue(0, id);
		setValue(1, reformId);
		setValue(2, fileTypeId);
		setValue(3, fileName);
		setValue(4, createDate);
	}
}