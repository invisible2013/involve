package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class PollAnswerDTO {

    private Integer id;
    private  Integer pollId;
    private  String value;


    public static PollAnswerDTO translate(Record record) {
        PollAnswerDTO dto = new PollAnswerDTO();
        dto.setId(record.getValue(Tables.POLL_ANSWER.ID));
        dto.setPollId(record.getValue(Tables.POLL_ANSWER.POLL_ID));
        dto.setValue(record.getValue(Tables.POLL_ANSWER.VALUE));
        return dto;
    }


    public static List<PollAnswerDTO> translateArray(List<Record> records) {
        ArrayList<PollAnswerDTO> list = new ArrayList<PollAnswerDTO>();
        for (Record record : records) {
            list.add(PollAnswerDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
