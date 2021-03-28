package net.binxly.constructor.controllers;

import net.binxly.constructor.models.BuildRequest;
import net.binxly.constructor.services.OrchestrationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/build")
public class ConstructionController {

    @Inject
    OrchestrationService orchestrationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response build(BuildRequest buildRequest) {

        this.orchestrationService.orchestrate(buildRequest);
        // for now echo the input back to user to see it is working
        return Response.ok().entity(buildRequest).build();
    }

}
