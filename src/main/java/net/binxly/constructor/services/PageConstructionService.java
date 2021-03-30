package net.binxly.constructor.services;

import freemarker.template.TemplateException;
import net.binxly.constructor.services.utils.DirectoryService;
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
public class PageConstructionService {

    static Logger log = LoggerFactory.getLogger(PageConstructionService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    DirectoryService directoryService;

    public void construct(String id) throws IOException, TemplateException {
        this.directoryService.createSubDir(id, "pages");
        constructIndexPage(id);
    }

    private void constructIndexPage(String id) {


    }


}
