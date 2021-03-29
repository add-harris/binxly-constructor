package net.binxly.constructor.services;

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
        Path newDir = buildPath(id);
        Files.createDirectory(newDir);
        log.info("directory created: {}", newDir.toString());
    }

    public Path buildPath(String id) {
        return Path.of(String.format("%s/%s", outputPath, id));
    }

    public void cleanup(String id) {

        Path pathToBeDeleted = buildPath(id);

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
