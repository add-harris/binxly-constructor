package net.binxly.constructor.services.utils;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StorageService {

    static Logger log = LoggerFactory.getLogger(StorageService.class);
    
    @Inject
    Storage storage;

    @Inject
    StructureService structureService;

    // taken from these examples
    // https://github.com/quarkiverse/quarkus-google-cloud-services/tree/master/storage
    // https://cloud.google.com/storage/docs/uploading-objects#storage-upload-object-code-sample

    public void pushToStorage(String id) throws IOException {
        String bukcetName = "my-storage-bucket";
        Bucket bucket = storage.get(bukcetName);
        Path buildPath = Path.of(this.structureService.getPathString(id));
        // blobInfo is built internally by 'create' method
        bucket.create(id, Files.readAllBytes(buildPath));
    }

}
