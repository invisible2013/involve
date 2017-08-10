package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.UsersRecord;
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
                from(Tables.USERS).
                join(Tables.USER_GROUP).
                on(Tables.USERS.USER_GROUP_ID.eq(Tables.USER_GROUP.ID)).
                fetch();
    }

    public List<Record> getUserGroups() {
        return dslContext.
                select().
                from(Tables.USER_GROUP).
                fetch();
    }

    public List<Record> getUserTypes() {
        return dslContext.
                select().
                from(Tables.USER_TYPE).
                fetch();
    }

    public List<UsersRecord> search(String userName) {

        SelectConditionStep<Record> selectConditionStep =
                dslContext.
                        select().
                        from(Tables.USERS).where(Tables.USERS.ID.eq(Tables.USERS.ID));

        if (userName != null) {
            selectConditionStep.and(Tables.USERS.EMAIL.eq(userName));
        }
        return selectConditionStep.fetch().into(UsersRecord.class);
    }

    public UsersRecord getUser(String username, String password) {

        List<Record> records = dslContext
                .select()
                .from(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(username))
                .and(Tables.USERS.PASSWORD.eq(password))
                .fetch();

        return records.size() > 0 ? records.get(0).into(UsersRecord.class) : null;
    }
}
