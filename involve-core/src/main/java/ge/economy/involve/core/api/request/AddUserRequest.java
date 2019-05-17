package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;

/**
 * Created by nino on 7/10/16.
 */
public class AddUserRequest {

    private Integer id;
    private String firstName;
    private String lastName;
    private String orgName;
    private String idNumber;
    private String email;
    private String phone;
    private String password;
    private int typeId;
    private int statusId;
    private int groupId;
    private Integer genderId;
    private Integer ageRangeId;
    private Integer sphereId;
    private Integer activitySphereId;
    private Integer enterpriseSizeId;
    private Integer educationLevelId;
    private Integer economyActivityStatusId;
    private String otherSphereName;
    private String profession;
    private boolean isApproved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getAgeRangeId() {
        return ageRangeId;
    }

    public void setAgeRangeId(Integer ageRangeId) {
        this.ageRangeId = ageRangeId;
    }

    public Integer getSphereId() {
        return sphereId;
    }

    public void setSphereId(Integer sphereId) {
        this.sphereId = sphereId;
    }

    public Integer getActivitySphereId() {
        return activitySphereId;
    }

    public void setActivitySphereId(Integer activitySphereId) {
        this.activitySphereId = activitySphereId;
    }

    public Integer getEnterpriseSizeId() {
        return enterpriseSizeId;
    }

    public void setEnterpriseSizeId(Integer enterpriseSizeId) {
        this.enterpriseSizeId = enterpriseSizeId;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public Integer getEconomyActivityStatusId() {
        return economyActivityStatusId;
    }

    public void setEconomyActivityStatusId(Integer economyActivityStatusId) {
        this.economyActivityStatusId = economyActivityStatusId;
    }

    public String getOtherSphereName() {
        return otherSphereName;
    }

    public void setOtherSphereName(String otherSphereName) {
        this.otherSphereName = otherSphereName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
