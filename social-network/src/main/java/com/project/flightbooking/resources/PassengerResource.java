package com.project.flightbooking;

import com.project.dao.UserDAO;
import com.project.nodes.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/v1/passengers")
@Api("/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    @POST
    @Path("/add")
    @ApiOperation("Save User")
    @ApiResponses(value = {
        @ApiResponse(code = 202, message = "User saved successfully"),
        @ApiResponse(code = 400, message = "Could not create new user")
    })
    public Response findUser(User u) {
        long id = userDAO.createUser(u);
        if (id == 0) {
            Response.serverError().status(Response.Status.BAD_REQUEST);
        }
        return Response.ok(id).status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).build();
    }







    
    @GET
    @Path("/all")
    @ApiOperation("find all user")
     @ApiResponses(value = {
        @ApiResponse(code = 202, message = "User list fetched"),
        @ApiResponse(code = 400, message = "Could not find any user")
    })
    public Response findUser() {
        List<User> userlist = userDAO.findAll();
        return Response.ok(userlist).status(Response.Status.ACCEPTED).type(MediaType.APPLICATION_JSON).build();
    }

}