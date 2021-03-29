package net.binxly.constructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
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
import java.util.Map;

@ApplicationScoped
public class ConfigConstructionService {

    static Logger log = LoggerFactory.getLogger(ConfigConstructionService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    @TemplatePath("package_json.ftl")
    Template packageJsonTemplate;

    @Inject
    @TemplatePath("nuxt_config.ftl")
    Template nuxtConfigTemplate;

    public void construct(String id, String projectName) throws IOException, TemplateException {
        constructConfigFile(packageJsonTemplate, id, projectName, "package.json");
        constructConfigFile(nuxtConfigTemplate, id, projectName, "nuxt.config.js");
    }

    private void constructConfigFile(Template template, String id, String projectName, String fileName) throws IOException, TemplateException {

        StringWriter stringWriter = new StringWriter();

        Path filePath = Path.of(String.format("%s/%s/%s", outputPath, id, fileName));

        Files.createFile(filePath);
        log.info("new file created: {}", fileName);

        template.process(Map.of("projectName", projectName), stringWriter);

        Files.write(filePath, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");

    }

}
