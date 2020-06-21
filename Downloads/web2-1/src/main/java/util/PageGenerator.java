package util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static volatile PageGenerator instance;

    private PageGenerator() {

        cfg = new Configuration();
    }
    public static PageGenerator getInstance(){
        if (instance == null)
            synchronized (PageGenerator.class){
            if (instance == null)
                instance = new PageGenerator();
            }
        return instance;
    }

    private static final String HTML_DIR = "templates";

    private final Configuration cfg;
    private Object data;


    public String getPage(String filename) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
