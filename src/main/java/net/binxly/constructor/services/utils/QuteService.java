package net.binxly.constructor.services.utils;

import io.quarkus.qute.TemplateInstance;
import net.binxly.constructor.models.files.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class QuteService {

    static Logger log = LoggerFactory.getLogger(QuteService.class);


    public void constructFile(String filePath, FileModel fileModel, TemplateInstance template) throws IOException {

        String renderedTemplate = template.data("model", fileModel).render();

        Path path = Path.of(String.format("%s/%s", filePath, fileModel.getFileName()));

        Files.createFile(path);
        log.info("new file created: {}", fileModel.getFileName());

        log.info("printing contents: \n{}", renderedTemplate);

        Files.write(path, renderedTemplate.getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");

    }

}
