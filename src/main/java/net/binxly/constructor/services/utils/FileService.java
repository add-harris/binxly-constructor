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


@ApplicationScoped
public class FileService {

    static Logger log = LoggerFactory.getLogger(FileService.class);

    public void constructFile(String filePath, FileModel fileModel, Template template) throws IOException, TemplateException {
        log.info("constructing {}", filePath.toString());
        StringWriter stringWriter = new StringWriter();

        Path path = Path.of(String.format("%s/%s", filePath, fileModel.getFileName()));

        Files.createFile(path);
        log.info("new file created: {}", fileModel.getFileName());

        template.process(fileModel, stringWriter);

        Files.write(path, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");
    }

}
