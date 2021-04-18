package net.binxly.constructor.services.utils;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class StorageService {

    static Logger log = LoggerFactory.getLogger(StorageService.class);

    @Inject
    @ConfigProperty(name = "storage.bucket.name")
    String bucketName;

    @Inject
    Storage storage;

    @Inject
    StructureService structureService;

    // taken from these examples
    // https://github.com/quarkiverse/quarkus-google-cloud-services/tree/master/storage
    // https://cloud.google.com/storage/docs/uploading-objects#storage-upload-object-code-sample

    public void pushToStorage(String id) throws IOException {
        log.info("pushing to storage, build: {}", id);
        Bucket bucket = storage.get(this.bucketName);
        log.info("bucket got");
        Path tarPath = this.structureService.getTarPath(id);
        bucket.create(id, Files.readAllBytes(tarPath));
        log.info("successfully pushed to storage");
    }

}
