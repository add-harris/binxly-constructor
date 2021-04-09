package net.binxly.constructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.binxly.constructor.models.files.IndexVue;
import net.binxly.constructor.services.utils.FreemarkerService;
import net.binxly.constructor.services.utils.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class PageConstructionService {

    static Logger log = LoggerFactory.getLogger(PageConstructionService.class);

    @Inject
    StructureService structureService;

    @Inject
    FreemarkerService freemarkerService;

    @Inject
    @TemplatePath("index.ftl")
    Template indexTemplate;

    public void construct(String id) throws IOException, TemplateException {
        log.info("building page files");
        this.structureService.createSubDir(id, "pages");
        String path = this.structureService.getPathString(id, "pages");
        IndexVue indexVue = IndexVue.builder().build();
        this.freemarkerService.constructFile(path, indexVue, indexTemplate);
    }

}
