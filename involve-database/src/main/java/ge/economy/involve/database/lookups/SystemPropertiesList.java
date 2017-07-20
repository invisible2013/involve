package ge.economy.involve.database.lookups;

/**
 * Created by nino on 7/20/16.
 */
public enum SystemPropertiesList {

    NF_EMAIL("System Notification Email", "sys_notification_email"),
    NF_EMAIL_SMTP_AUTH("System Notification Email SMTP  Auth", "sys_notification_email_smtp_auth"),
    NF_EMAIL_SMTP_HOST("System Notification Email SMTP Host", "sys_notification_email_smtp_host"),
    NF_EMAIL_SMTP_PORT("System Notification Email SMTP Port", "sys_notification_email_smtp_port"),
    NF_EMAIL_SMTP_TTLS_ENABLE("System Notification Email SMTP TTLS Enable", "sys_notification_email_smtp_ttls_enable"),
    NF_EMAIL_USERNAME("System Notification Email Username", "sys_notification_email_username"),
    NF_EMAIL_PASSWORD("System Notification Email Password", "sys_notification_email_password");


    private String description;
    private String key;

    SystemPropertiesList(String description, String key) {
        this.description = description;
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


// INSERT INTO system_properties(id, description, key, value, group_id) VALUES (20, 'Ignores the expiration date of an SSL certificate for LDAP server(Note: disable need to restart server)', 'ignore_expired_ldap_ssl_certificate', '0',1);