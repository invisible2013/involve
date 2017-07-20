package ge.economy.involve.core;

import ge.economy.involve.utils.email.SendEmailWithAttachment;
import ge.economy.involve.database.lookups.SystemPropertiesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mindia on 11/20/16.
 */
@Service
public class SendEmailWithAttachmentFactory {

    @Autowired
    //private SystemDAO systemDAO;

    public SendEmailWithAttachment getInstance() {

        SendEmailWithAttachment sendEmailWithAttachment = new SendEmailWithAttachment();
     /*   sendEmailWithAttachment.setFrom(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL.getKey()));
        sendEmailWithAttachment.setAuth(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_SMTP_AUTH.getKey()));
        sendEmailWithAttachment.setHost(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_SMTP_HOST.getKey()));
        sendEmailWithAttachment.setPort(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_SMTP_PORT.getKey()));
        sendEmailWithAttachment.setStarttls(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_SMTP_TTLS_ENABLE.getKey()));
        sendEmailWithAttachment.setUsername(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_USERNAME.getKey()));
        sendEmailWithAttachment.setPassword(systemDAO.getValueByKey(SystemPropertiesList.NF_EMAIL_PASSWORD.getKey()));*/

        return sendEmailWithAttachment;
    }
}
