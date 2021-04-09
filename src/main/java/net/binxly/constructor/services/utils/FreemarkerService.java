package net.binxly.constructor.services.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.binxly.constructor.models.files.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@ApplicationScoped
public class FreemarkerService {

    static Logger log = LoggerFactory.getLogger(FreemarkerService.class);

    public void constructFile(String filePath, FileModel fileModel, Template template) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();

        Path path = Path.of(String.format("%s/%s", filePath, fileModel.getFileName()));

        Files.createFile(path);
        log.info("new file created: {}", fileModel.getFileName());

        Map<String, FileModel> root = Map.of("model", fileModel);
        template.process(root, stringWriter);

        Files.write(path, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");
    }

}
