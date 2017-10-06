package ge.economy.involve.core.api.dto;

/**
 * Created by NINO on 10/4/2017.
 */
public class QuestionAnswer {

    private int questionId;
    private int answerId;
    private String answerNote;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerNote() {
        return answerNote;
    }

    public void setAnswerNote(String answerNote) {
        this.answerNote = answerNote;
    }
}
