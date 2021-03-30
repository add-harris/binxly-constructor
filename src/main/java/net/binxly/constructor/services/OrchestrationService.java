package net.binxly.constructor.services;

import net.binxly.constructor.models.BuildRequest;
import net.binxly.constructor.services.utils.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrchestrationService {

    static Logger log = LoggerFactory.getLogger(OrchestrationService.class);

    @Inject
    ConfigConstructionService configConstructionService;

    @Inject
    DirectoryService directoryService;

    @Inject
    PageConstructionService pageConstructionService;

    public void orchestrate(BuildRequest buildRequest) {

        log.info("begin orchestration");
        try {
            this.directoryService.createBuildDir(buildRequest.getId());
            this.configConstructionService.construct(buildRequest.getId(), buildRequest.getProjectName());
            this.pageConstructionService.construct(buildRequest.getId());
//            this.directoryService.cleanup(buildRequest.getId());
        } catch (Exception e) {
            e.printStackTrace();
//            this.directoryService.cleanup(buildRequest.getId());
        }

    }

}
