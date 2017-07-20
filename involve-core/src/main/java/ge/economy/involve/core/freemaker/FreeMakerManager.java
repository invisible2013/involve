package ge.economy.involve.core.freemaker;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by mindia on 3/4/17.
 */
public class FreeMakerManager {

    public static final String TEMPLATE_DIR = "/opt/sportstat/email/templates";

    private Logger logger = Logger.getLogger(FreeMakerManager.class);


    public String getText(String templateName, Map<String, Object> data) {
        //Freemarker configuration object
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);

        try {
            cfg.setTemplateLoader(new FileTemplateLoader(new File(FreeMakerManager.TEMPLATE_DIR)));

            //Load template from source folder
            Template template = cfg.getTemplate(templateName);

            // Console output
            StringWriter out = new StringWriter();
            template.process(data, out);
            out.flush();

            return out.toString();
        } catch (Exception e) {
            logger.error("Unable to send render email template", e);
        }
        return null;
    }
}
