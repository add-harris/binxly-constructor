package net.binxly.constructor.services.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.binxly.constructor.models.files.FileModel;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static net.binxly.constructor.config.Constants.TAR_EXTENSION;

@ApplicationScoped
public class StructureService {

    static Logger log = LoggerFactory.getLogger(StructureService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    public void createBuildDir(String id) throws IOException {
        Path buildDir = Path.of(getPathString(id));
        createDir(buildDir);
    }

    public void createSubDir(String id, String dirName) throws IOException {
        Path subDir = Path.of(getPathString(id, dirName));
        createDir(subDir);
    }

    private void createDir(Path path) throws IOException {
        Files.createDirectory(path);
        log.info("directory created: {}", path.toString());
    }

    public String getPathString(String id) {
        return getPathString(id, null);
    }

    public String getPathString(String id, String subDir) {
        if (subDir != null) {
            return String.format("%s/%s/%s", outputPath, id, subDir);
        } else {
            return String.format("%s/%s", outputPath, id);
        }
    }

    public Path getPath(String id) {
        return Path.of(getPathString(id));
    }

    public Path getPath(String id, String sub) {
        return Path.of(getPathString(id, sub));
    }

    public Path getTarPath(String id) {
        return getPath(id.concat(TAR_EXTENSION));
    }

    public void constructFile(String filePath, FileModel fileModel, Template template) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();

        Path path = Path.of(String.format("%s/%s", filePath, fileModel.getFileName()));

        Files.createFile(path);
        log.info("new file created: {}", fileModel.getFileName());

        template.process(fileModel, stringWriter);

        Files.write(path, stringWriter.toString().getBytes(StandardCharsets.UTF_8));
        log.info("successfully written to file");
    }

    public void cleanup(String id) {

        Path buildDirPath = getPath(id);
        Path tarPath = getPath(id.concat(TAR_EXTENSION));

        log.info("cleanup started");
        try {
            Files.walk(buildDirPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            Files.delete(tarPath);
            log.info("cleanup completed");
        } catch (IOException exception) {
            log.info("cleanup failed");
            exception.printStackTrace();
        }
    }

}
