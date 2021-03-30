package net.binxly.constructor.services.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.binxly.constructor.models.files.FileModel;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


@ApplicationScoped
public class FileService {

    static Logger log = LoggerFactory.getLogger(FileService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    DirectoryService directoryService;

    public void constructFile(String id, String fileName, FileModel fileModel, Template template) throws IOException, TemplateException {

        StringWriter stringWriter = new StringWriter();

        Path filePath = this.directoryService.createPath(id, fileName);

        Files.createFile(filePath);
        log.info("new file created: {}", fileName);

        template.process(fileModel, stringWriter);

        Files.write(filePath, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");

    }

}
