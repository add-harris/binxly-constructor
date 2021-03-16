package net.binxly.constructor.services;

import freemarker.template.Template;
import io.quarkiverse.freemarker.TemplatePath;
import net.binxly.constructor.dto.BuildRequestDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@ApplicationScoped
public class ConstructionService {

    static Logger log = LoggerFactory.getLogger(ConstructionService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    @TemplatePath("template.ftl")
    Template template;

    public void construct(BuildRequestDTO buildRequest) {

        String text = buildRequest.getNavBar().getTitle();
        StringWriter stringWriter = new StringWriter();

        try {

//            set output file
            Path newDir = Path.of(outputPath);
            Path filePath = Path.of(String.format("%s/newfile.html", outputPath));

            Files.createDirectory(newDir);
            log.info("directory created: {}", filePath);
            Files.createFile(filePath);
            log.info("new file created: newFile.html");

//            do freemarker
            template.process(Map.of("text", text), stringWriter);

            Files.write(filePath, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
            log.info("successfully written to file");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
