package ge.economy.involve.utils.google.recaptcha;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ge.economy.involve.utils.GsonUtil;

/**
 * Created by nino on 2/20/17.
 */
public class GoogleReCAPTCHACaller {

    public boolean validate(String secretKey, String clientResponse) throws GoogleRECAPTCHAException {

        try {
            HttpResponse<String> jsonNodeHttpResponse = Unirest.post("https://www.google.com/recaptcha/api/siteverify")
                    .field("secret", secretKey)
                    .field("response", clientResponse)
                    .asString();

            GoogleReCAPTCHAResponse googleReCAPTCHAResponse = GsonUtil.fromJson(jsonNodeHttpResponse.getBody(), GoogleReCAPTCHAResponse.class);
            return googleReCAPTCHAResponse.isSuccess();
        } catch (UnirestException e) {
            throw new GoogleRECAPTCHAException(e.getMessage());
        }
    }
}
