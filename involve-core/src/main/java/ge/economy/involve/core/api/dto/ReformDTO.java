package ge.economy.involve.core.api.dto;

import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ReformDTO {

    private int id;
    private  String name;


    public static ReformDTO translate(Record record) {
        ReformDTO dto = new ReformDTO();
        return dto;
    }


    public static List<ReformDTO> translateArray(List<Record> records) {
        ArrayList<ReformDTO> list = new ArrayList<ReformDTO>();
        for (Record record : records) {
            list.add(ReformDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
