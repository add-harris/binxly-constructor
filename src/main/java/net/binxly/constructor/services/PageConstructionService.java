package net.binxly.constructor.services;

import net.binxly.constructor.models.files.IndexVue;
import net.binxly.constructor.services.utils.QuteService;
import net.binxly.constructor.services.utils.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

import static net.binxly.constructor.config.Templates.indexTemplate;

@ApplicationScoped
public class PageConstructionService {

    static Logger log = LoggerFactory.getLogger(PageConstructionService.class);

    @Inject
    StructureService structureService;

    @Inject
    QuteService quteService;

    public void construct(String id) throws IOException {
        log.info("building page files");
        this.structureService.createSubDir(id, "pages");
        String path = this.structureService.getPathString(id, "pages");
        IndexVue indexVue = IndexVue.builder().build();
        this.quteService.constructFile(path, indexVue, indexTemplate());
    }

}
