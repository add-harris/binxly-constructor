package net.binxly.constructor.controllers;

import net.binxly.constructor.dto.ResponseDTO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class ConstructController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO test() {
        return new ResponseDTO("Hello Binxley");
    }

}
