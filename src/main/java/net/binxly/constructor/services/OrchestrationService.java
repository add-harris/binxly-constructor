package net.binxly.constructor.services;

import net.binxly.constructor.models.BuildRequest;
import net.binxly.constructor.services.utils.StorageService;
import net.binxly.constructor.services.utils.StructureService;
import net.binxly.constructor.services.utils.TarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrchestrationService {

    static Logger log = LoggerFactory.getLogger(OrchestrationService.class);

    @Inject
    StructureService structureService;

    @Inject
    TarService tarService;

    @Inject
    ConfigConstructionService configConstructionService;

    @Inject
    PageConstructionService pageConstructionService;

    @Inject
    StorageService storageService;

    public void orchestrate(BuildRequest buildRequest) {

        log.info("begin orchestration");
        try {
            this.structureService.createBuildDir(buildRequest.getId());
            this.configConstructionService.construct(buildRequest.getId(), buildRequest.getProjectName());
            this.pageConstructionService.construct(buildRequest.getId());
            this.tarService.tarDirectory(buildRequest.getId());
//            this.storageService.pushToStorage(buildRequest.getId());
            this.structureService.cleanup(buildRequest.getId());
        } catch (Exception e) {
            e.printStackTrace();
            this.structureService.cleanup(buildRequest.getId());
        }

    }

}
