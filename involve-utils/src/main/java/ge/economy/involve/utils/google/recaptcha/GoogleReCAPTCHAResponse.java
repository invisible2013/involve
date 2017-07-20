package ge.economy.involve.utils.google.recaptcha;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by nino on 2/20/17.
 */
public class GoogleReCAPTCHAResponse {

    private boolean success;
    @SerializedName("challenge_ts")
    private Date challengeTS;
    private String hostname;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getChallengeTS() {
        return challengeTS;
    }

    public void setChallengeTS(Date challengeTS) {
        this.challengeTS = challengeTS;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
