package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class OrganisationDTO {

    private Integer id;
    private String name;
    private String address;
    private String mobilePhone;
    private String email;
    private String web;
    private String logoUrl;
    private Integer regionId;
    private String regionName;



    public static OrganisationDTO translate(Record record) {
        OrganisationDTO dto = new OrganisationDTO();
        if (record != null) {
            dto.setId(record.getValue(Tables.ORGANISATION.ID));
            dto.setName(record.getValue(Tables.ORGANISATION.NAME));
            dto.setMobilePhone(record.getValue(Tables.ORGANISATION.MOBILE_PHONE));
            dto.setWeb(record.getValue(Tables.ORGANISATION.WEB));
            dto.setEmail(record.getValue(Tables.ORGANISATION.MAIL));
            dto.setLogoUrl(record.getValue(Tables.ORGANISATION.LOGO_URL));
            dto.setAddress(record.getValue(Tables.ORGANISATION.ADDRESS));
            dto.setRegionId(record.getValue(Tables.ORGANISATION.REGION_ID));
            dto.setRegionName(record.getValue(Tables.REGION.NAME));
        }
        return dto;
    }


    public static List<OrganisationDTO> translateArray(List<Record> records) {
        ArrayList<OrganisationDTO> list = new ArrayList<OrganisationDTO>();
        for (Record record : records) {
            list.add(OrganisationDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
