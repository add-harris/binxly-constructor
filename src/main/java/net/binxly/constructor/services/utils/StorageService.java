package net.binxly.constructor.services.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class StorageService {

    static Logger log = LoggerFactory.getLogger(StorageService.class);

    @Inject
    @ConfigProperty(name = "storage.bucket.name")
    String bucketName;

//    @Inject
//    Storage storage;
//
//    @Inject
//    StructureService structureService;

    // taken from these examples
    // https://github.com/quarkiverse/quarkus-google-cloud-services/tree/master/storage
    // https://cloud.google.com/storage/docs/uploading-objects#storage-upload-object-code-sample

    public void pushToStorage(String id) throws IOException {
//        Bucket bucket = storage.get(this.bucketName);
//        Path tarPath = this.structureService.getTarPath(id);
//        bucket.create(id, Files.readAllBytes(tarPath));
    }

}
