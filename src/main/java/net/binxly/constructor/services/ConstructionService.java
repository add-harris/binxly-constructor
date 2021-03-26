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
    @TemplatePath("package_json.ftl")
    Template template;

    public void construct(BuildRequestDTO buildRequest) {

        StringWriter stringWriter = new StringWriter();

        try {

            // set output file
            Path newDir = Path.of(outputPath);
            Path filePath = Path.of(String.format("%s/package.json", outputPath));

            if (!Files.exists(newDir)) {
                Files.createDirectory(newDir);
                log.info("directory created: {}", filePath);
            }

            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.createFile(filePath);
            log.info("new file created: package.json");

            template.process(Map.of("projectName", "some-project"), stringWriter);

            Files.write(filePath, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
            log.info("successfully written to file");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
