package net.binxly.constructor.services;

import net.binxly.constructor.models.components.NavBar;
import net.binxly.constructor.models.files.ErrorLayoutVue;
import net.binxly.constructor.models.files.LayoutVue;
import net.binxly.constructor.services.utils.QuteService;
import net.binxly.constructor.services.utils.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

import static net.binxly.constructor.config.Templates.layoutTemplate;
import static net.binxly.constructor.config.Templates.errorLayoutTemplate;

@ApplicationScoped
public class LayoutConstructionService {

    static Logger log = LoggerFactory.getLogger(LayoutConstructionService.class);

    @Inject
    StructureService structureService;

    @Inject
    QuteService quteService;

    public void construct(String id, NavBar navBar) throws IOException {
        log.info("building layout files");
        this.structureService.createSubDir(id, "layouts");
        String path = this.structureService.getPathString(id, "layouts");

        buildDefaultLayout(path, navBar);
        buildErrorLayout(path);
    }

    void buildDefaultLayout(String path, NavBar navBar) throws IOException {
        LayoutVue layoutVue = LayoutVue.builder().navBar(navBar).build();
        this.quteService.constructFile(path, layoutVue, layoutTemplate());
    }

    void buildErrorLayout(String path) throws IOException {
        ErrorLayoutVue errorLayoutVue = ErrorLayoutVue.builder().build();
        this.quteService.constructFile(path, errorLayoutVue, errorLayoutTemplate());
    }

}
