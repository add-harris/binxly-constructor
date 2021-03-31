package net.binxly.constructor.services.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@ApplicationScoped
public class DirectoryService {

    static Logger log = LoggerFactory.getLogger(DirectoryService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    public void createBuildDir(String id) throws IOException {
        Path buildDir = Path.of(createPath(id));
        createDir(buildDir);
    }

    public void createSubDir(String id, String dirName) throws IOException {
        Path subDir = Path.of(createPath(id, dirName));
        createDir(subDir);
    }

    private void createDir(Path path) throws IOException {
        Files.createDirectory(path);
        log.info("directory created: {}", path.toString());
    }

    public String createPath(String id) {
        return createPath(id, null);
    }

    public String createPath(String id, String subDir) {
        if (subDir != null) {
            return String.format("%s/%s/%s", outputPath, id, subDir);
        } else {
            return String.format("%s/%s", outputPath, id);
        }
    }

    public void cleanup(String id) {

        Path pathToBeDeleted = Path.of(createPath(id));

        if (Files.exists(pathToBeDeleted)) {
            log.info("cleanup started");
            try {
                Files.walk(pathToBeDeleted)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                log.info("cleanup completed");
            } catch (IOException exception) {
                log.info("cleanup failed");
                exception.printStackTrace();
            }

        }
    }

}
