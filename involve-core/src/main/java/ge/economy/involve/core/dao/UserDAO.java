package ge.economy.involve.core.dao;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.TokenRecord;
import ge.economy.involve.database.database.tables.records.UserRegisterRecord;
import ge.economy.involve.database.database.tables.records.UserResetPasswordRecord;
import ge.economy.involve.database.database.tables.records.UsersRecord;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
                join(Tables.USER_GROUP).on(Tables.USERS.USER_GROUP_ID.eq(Tables.USER_GROUP.ID)).
                join(Tables.USER_TYPE).on(Tables.USERS.USER_TYPE_ID.eq(Tables.USER_TYPE.ID)).
                join(Tables.USER_STATUS).on(Tables.USERS.STATUS_ID.eq(Tables.USER_STATUS.ID)).
                leftJoin(Tables.GENDER).on(Tables.USERS.GENDER_ID.eq(Tables.GENDER.ID)).
                orderBy(Tables.USERS.ID.desc()).
                fetch();
    }

    public UsersRecord getUserObjectById(int id) {
        return dslContext.fetchOne(Tables.USERS, Tables.USERS.ID.eq(id));
    }

    public Record getUserById(int id) {
        return dslContext.select().
                from(Tables.USERS).
                join(Tables.USER_GROUP).on(Tables.USERS.USER_GROUP_ID.eq(Tables.USER_GROUP.ID)).
                join(Tables.USER_TYPE).on(Tables.USERS.USER_TYPE_ID.eq(Tables.USER_TYPE.ID)).
                join(Tables.USER_STATUS).on(Tables.USERS.STATUS_ID.eq(Tables.USER_STATUS.ID)).
                leftJoin(Tables.GENDER).on(Tables.USERS.GENDER_ID.eq(Tables.GENDER.ID)).
                leftJoin(Tables.AGE_RANGE).on(Tables.USERS.AGE_RANGE_ID.eq(Tables.AGE_RANGE.ID)).
                leftJoin(Tables.EDUCATION_LEVEL).on(Tables.USERS.EDUCATION_LEVEL_ID.eq(Tables.EDUCATION_LEVEL.ID)).
                leftJoin(Tables.ENTERPRISE_SIZE).on(Tables.USERS.ENTERPRISE_SIZE_ID.eq(Tables.ENTERPRISE_SIZE.ID)).
                leftJoin(Tables.ACTIVITY_SPHERE).on(Tables.USERS.ACTIVITY_SPHERE_ID.eq(Tables.ACTIVITY_SPHERE.ID)).
                leftJoin(Tables.SPHERE).on(Tables.USERS.SPHERE_ID.eq(Tables.SPHERE.ID)).
                leftJoin(Tables.ECONOMY_ACTIVITY_STATUS).on(Tables.USERS.ECONOMY_ACTIVITY_STATUS_ID.eq(Tables.ECONOMY_ACTIVITY_STATUS.ID)).
                where(Tables.USERS.ID.eq(id)).fetchAny();
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

    public List<Record> getUserStatus() {
        return dslContext.
                select().
                from(Tables.USER_STATUS).
                fetch();
    }

    public List<Record> getGenders() {
        return dslContext.
                select().
                from(Tables.GENDER).
                fetch();
    }

    public List<Record> getAgeRanges() {
        return dslContext.
                select().
                from(Tables.AGE_RANGE).
                orderBy(Tables.AGE_RANGE.ID).
                fetch();
    }

    public List<Record> getEducationLevels() {
        return dslContext.
                select().
                from(Tables.EDUCATION_LEVEL).
                orderBy(Tables.EDUCATION_LEVEL.ID).
                fetch();
    }

    public List<Record> getEnterpriseSizes() {
        return dslContext.
                select().
                from(Tables.ENTERPRISE_SIZE).
                orderBy(Tables.ENTERPRISE_SIZE.ID).
                fetch();
    }

    public List<Record> getEconomyActivityStatus() {
        return dslContext.
                select().
                from(Tables.ECONOMY_ACTIVITY_STATUS).
                orderBy(Tables.ECONOMY_ACTIVITY_STATUS.ID).
                fetch();
    }

    public List<Record> getActivitySpheres() {
        return dslContext.
                select().
                from(Tables.ACTIVITY_SPHERE).
                orderBy(Tables.ACTIVITY_SPHERE.ID).
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

    public Record getUser(String username, String password) {
        return dslContext
                .select()
                .from(Tables.USERS)
                .join(Tables.USER_GROUP).on(Tables.USERS.USER_GROUP_ID.eq(Tables.USER_GROUP.ID))
                .join(Tables.USER_TYPE).on(Tables.USERS.USER_TYPE_ID.eq(Tables.USER_TYPE.ID))
                .join(Tables.USER_STATUS).on(Tables.USERS.STATUS_ID.eq(Tables.USER_STATUS.ID))
                .leftJoin(Tables.GENDER).on(Tables.USERS.GENDER_ID.eq(Tables.GENDER.ID))
                .where(Tables.USERS.EMAIL.eq(username))
                .and(Tables.USERS.PASSWORD.eq(password))
                .and(Tables.USERS.STATUS_ID.eq(UserDTO.USER_STATUS_ACTIVE))
                .and(Tables.USERS.IS_APPROVED.eq(true))
                .fetchOne();

    }


    public void deleteUser(int itemId) {
        dslContext.deleteFrom(Tables.USERS).where(Tables.USERS.ID.eq(itemId)).execute();
    }

    public UserRegisterRecord getUserRegistrationByKey(String key) {
        Record record = dslContext.
                select().
                from(Tables.USER_REGISTER).
                where(Tables.USER_REGISTER.KEY.eq(key)).and(Tables.USER_REGISTER.IS_EXPIRED.eq(false)).
                fetchAny();
        return record == null ? null : record.into(UserRegisterRecord.class);
    }

    public UserResetPasswordRecord getUserResetPasswordByKey(String key) {
        Record record = dslContext.
                select().
                from(Tables.USER_RESET_PASSWORD).
                where(Tables.USER_RESET_PASSWORD.KEY.eq(key)).and(Tables.USER_RESET_PASSWORD.IS_EXPIRED.eq(false)).
                fetchAny();
        return record == null ? null : record.into(UserResetPasswordRecord.class);
    }

    public UsersRecord getUserByMail(String mail) {
        Record record = dslContext.
                select().
                from(Tables.USERS).
                where(Tables.USERS.EMAIL.eq(mail)).
                fetchAny();
        return record == null ? null : record.into(UsersRecord.class);
    }

    public UsersRecord getUserRecordById(int id) {
        Record record = dslContext.
                select().
                from(Tables.USERS).
                where(Tables.USERS.ID.eq(id)).
                fetchAny();
        return record == null ? null : record.into(UsersRecord.class);
    }

    public void updateUserRegistration(int id) {
        dslContext.update(Tables.USER_REGISTER).set(Tables.USER_REGISTER.IS_EXPIRED, true).where(Tables.USER_REGISTER.ID.eq(id)).execute();
    }

    public void updateUserActivation(int userId) {
        dslContext.update(Tables.USERS).set(Tables.USERS.IS_APPROVED, true).where(Tables.USERS.ID.eq(userId)).execute();
    }

    public void updateUserPassword(String password, int userId) {
        dslContext.update(Tables.USERS).set(Tables.USERS.PASSWORD, password).where(Tables.USERS.ID.eq(userId)).execute();
    }

    public TokenRecord getToken(int userId) {
        Record record = dslContext.
                select().
                from(Tables.TOKEN).
                where(Tables.TOKEN.USER_ID.eq(userId)).
                fetchAny();
        return record == null ? null : record.into(TokenRecord.class);
    }

    public void deleteToken(int itemId) {
        dslContext.deleteFrom(Tables.TOKEN).where(Tables.TOKEN.ID.eq(itemId)).execute();
    }

    public TokenRecord generateToken(int userId, String token, Date startDate, Date validDate) {
        TokenRecord record = dslContext.newRecord(Tables.TOKEN);
        record.setUserId(userId);
        record.setKey(token);
        record.setCreateDate(startDate);
        record.setValidDate(validDate);
        record.store();
        return record;
    }

}
