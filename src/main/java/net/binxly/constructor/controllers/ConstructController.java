package net.binxly.constructor.controllers;

import net.binxly.constructor.dto.BuildRequestDTO;
import net.binxly.constructor.services.ConstructionService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/build")
public class ConstructController {

    @Inject
    ConstructionService constructionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response build(BuildRequestDTO buildRequestDTO) {
        this.constructionService.doNothing();
        // for now echo the input back to user to see it is working
        return Response.ok().entity(buildRequestDTO).build();
    }

}
