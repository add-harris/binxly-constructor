package net.binxly.constructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.binxly.constructor.models.files.FileModel;
import net.binxly.constructor.models.files.NuxtConfig;
import net.binxly.constructor.models.files.PackageJson;
import net.binxly.constructor.services.utils.FileService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class ConfigConstructionService {

    static Logger log = LoggerFactory.getLogger(ConfigConstructionService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    FileService fileService;

    @Inject
    @TemplatePath("package_json.ftl")
    Template packageJsonTemplate;

    @Inject
    @TemplatePath("nuxt_config.ftl")
    Template nuxtConfigTemplate;

    public void construct(String id, String projectName) throws IOException, TemplateException {
        PackageJson packageJson = new PackageJson(projectName);
        NuxtConfig nuxtConfig = new NuxtConfig(projectName);
        constructConfigFile(id, "package.json", packageJson, packageJsonTemplate);
        constructConfigFile(id, "nuxt.config.js", nuxtConfig, nuxtConfigTemplate);
    }

    private void constructConfigFile(String id, String fileName, FileModel fileModel, Template template) throws IOException, TemplateException {
        this.fileService.constructFile(id, fileName, fileModel, template);
    }

}
