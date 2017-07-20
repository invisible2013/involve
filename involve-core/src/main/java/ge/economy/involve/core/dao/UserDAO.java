package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.UserRecord;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nino on 7/10/16.
 */

@Repository
public class UserDAO extends AbstractDAO {

    public List<Record> getUsers() {
        return dslContext.
                select().
                from(Tables.USER).
                join(Tables.USER_STATUS).
                on(Tables.USER.STATUS_ID.eq(Tables.USER_STATUS.ID)).
                fetch();
    }

    public List<UserRecord> search(String userName) {

        SelectConditionStep<Record> selectConditionStep =
                dslContext.
                        select().
                        from(Tables.USER).where(Tables.USER.ID.eq(Tables.USER.ID));

        if (userName != null) {
            selectConditionStep.and(Tables.USER.USER_NAME.eq(userName));
        }
        return selectConditionStep.fetch().into(UserRecord.class);
    }

    public UserRecord getUser(String username, String password) {

        List<Record> records = dslContext
                .select()
                .from(Tables.USER)
                .where(Tables.USER.EMAIL.eq(username))
                .and(Tables.USER.PASSWORD.eq(password))
                .fetch();

        return records.size() > 0 ? records.get(0).into(UserRecord.class) : null;
    }
}
