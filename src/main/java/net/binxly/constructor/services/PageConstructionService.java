package net.binxly.constructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;
import net.binxly.constructor.models.files.IndexVue;
import net.binxly.constructor.services.utils.DirectoryService;
import net.binxly.constructor.services.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class PageConstructionService {

    static Logger log = LoggerFactory.getLogger(PageConstructionService.class);

    @Inject
    FileService fileService;

    @Inject
    DirectoryService directoryService;

    @Inject
    @TemplatePath("index.ftl")
    Template indexTemplate;

    public void construct(String id) throws IOException, TemplateException {
        this.directoryService.createSubDir(id, "pages");
        String path = this.directoryService.createPath(id, "pages");
        IndexVue indexVue = IndexVue.builder().build();
        this.fileService.constructFile(path, indexVue, indexTemplate);
    }

}
