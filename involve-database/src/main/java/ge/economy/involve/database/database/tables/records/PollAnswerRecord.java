/**
 * This class is generated by jOOQ
 */
package ge.economy.involve.database.database.tables.records;


import ge.economy.involve.database.database.tables.PollAnswer;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class PollAnswerRecord extends UpdatableRecordImpl<PollAnswerRecord> implements Record3<Integer, Integer, String> {

	private static final long serialVersionUID = 1030585377;

	/**
	 * Setter for <code>public.poll_answer.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.poll_answer.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.poll_answer.poll_id</code>.
	 */
	public void setPollId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.poll_answer.poll_id</code>.
	 */
	public Integer getPollId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.poll_answer.value</code>.
	 */
	public void setValue(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.poll_answer.value</code>.
	 */
	public String getValue() {
		return (String) getValue(2);
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
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, Integer, String> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, Integer, String> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return PollAnswer.POLL_ANSWER.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return PollAnswer.POLL_ANSWER.POLL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return PollAnswer.POLL_ANSWER.VALUE;
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
		return getPollId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PollAnswerRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PollAnswerRecord value2(Integer value) {
		setPollId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PollAnswerRecord value3(String value) {
		setValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PollAnswerRecord values(Integer value1, Integer value2, String value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PollAnswerRecord
	 */
	public PollAnswerRecord() {
		super(PollAnswer.POLL_ANSWER);
	}

	/**
	 * Create a detached, initialised PollAnswerRecord
	 */
	public PollAnswerRecord(Integer id, Integer pollId, String value) {
		super(PollAnswer.POLL_ANSWER);

		setValue(0, id);
		setValue(1, pollId);
		setValue(2, value);
	}
}
