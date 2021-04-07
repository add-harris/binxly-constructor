package net.binxly.constructor.services.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


@ApplicationScoped
public class TarService {

    static Logger log = LoggerFactory.getLogger(TarService.class);

    @Inject
    @ConfigProperty(name = "build.filepath.output")
    String outputPath;

    @Inject
    StructureService structureService;

    public void tarDirectory(String id) throws IOException {

        Path sourcePath = this.structureService.getPath(id);
        Path tarPath = this.structureService.getTarPath(id);

        try (OutputStream fOut = Files.newOutputStream(tarPath);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(new BufferedOutputStream(fOut));
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

            log.info("tar archive created");

            Files.walkFileTree(sourcePath, getFileVisitor(tOut));

        }
    }

    private FileVisitor<Path> getFileVisitor(TarArchiveOutputStream tOut) {

        class CustomFileVisitor implements FileVisitor<Path> {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                Path relativePath = Path.of(outputPath).relativize(file);
                TarArchiveEntry tarEntry = new TarArchiveEntry(file.toFile(), relativePath.toString());

                tOut.putArchiveEntry(tarEntry);
                Files.copy(file, tOut);
                tOut.closeArchiveEntry();

                log.info("file added to tar: {}", file.toString());

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                log.error("tar archive aborted, failed to add file: {}", file.toString());
                return FileVisitResult.TERMINATE ;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        }

        return new CustomFileVisitor();

    }



}
