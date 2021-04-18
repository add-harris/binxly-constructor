package net.binxly.constructor.services;

import net.binxly.constructor.models.files.NuxtConfig;
import net.binxly.constructor.models.files.PackageJson;
import net.binxly.constructor.services.utils.QuteService;
import net.binxly.constructor.services.utils.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

import static net.binxly.constructor.config.Templates.packageJsonTemplate;
import static net.binxly.constructor.config.Templates.nuxtConfigTemplate;

@ApplicationScoped
public class ConfigConstructionService {

    static Logger log = LoggerFactory.getLogger(ConfigConstructionService.class);

    @Inject
    StructureService structureService;

    @Inject
    QuteService quteService;

    public void construct(String id, String projectName) throws IOException {
        log.info("building config files, id: {}, projectName: {}", id, projectName);
        PackageJson packageJson = PackageJson.builder().projectName(projectName).build();
        NuxtConfig nuxtConfig = NuxtConfig.builder().projectName(projectName).build();
        String pathString = this.structureService.getPathString(id);
        this.quteService.constructFile(pathString, packageJson, packageJsonTemplate());
        this.quteService.constructFile(pathString, nuxtConfig, nuxtConfigTemplate());
    }

}
