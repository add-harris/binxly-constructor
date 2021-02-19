package net.binxly.constructor.controllers;

import net.binxly.constructor.dto.BuildRequestDTO;
import net.binxly.constructor.dto.TestResponseDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/build")
public class ConstructController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response build(BuildRequestDTO buildRequestDTO) {
        return Response.ok()
                .entity(new TestResponseDTO(
                    buildRequestDTO.getTitle()
                )).build();
    }

}
