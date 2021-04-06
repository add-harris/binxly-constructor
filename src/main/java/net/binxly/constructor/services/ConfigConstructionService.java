package net.binxly.constructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.binxly.constructor.models.files.FileModel;
import net.binxly.constructor.models.files.NuxtConfig;
import net.binxly.constructor.models.files.PackageJson;
import net.binxly.constructor.services.utils.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class ConfigConstructionService {

    static Logger log = LoggerFactory.getLogger(ConfigConstructionService.class);

    @Inject
    StructureService structureService;

    @Inject
    @TemplatePath("package_json.ftl")
    Template packageJsonTemplate;

    @Inject
    @TemplatePath("nuxt_config.ftl")
    Template nuxtConfigTemplate;

    public void construct(String id, String projectName) throws IOException, TemplateException {
        PackageJson packageJson = PackageJson.builder().projectName(projectName).build();
        NuxtConfig nuxtConfig = NuxtConfig.builder().projectName(projectName).build();
        constructConfigFile(id, packageJson, packageJsonTemplate);
        constructConfigFile(id, nuxtConfig, nuxtConfigTemplate);
    }

    private void constructConfigFile(String id, FileModel fileModel, Template template) throws IOException, TemplateException {
        log.info("building config files");
        this.structureService.constructFile(this.structureService.createPath(id), fileModel, template);
    }

}
