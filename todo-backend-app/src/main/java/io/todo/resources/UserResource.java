package io.todo.resources;

import io.quarkus.panache.common.Sort;
import io.todo.dto.UserLoginDto;
import io.todo.dto.UserTokenDto;
import io.todo.model.User;
import io.todo.security.jwt.TokenUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("users")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());


    @GET
    public Response get() {
        return Response.ok(User.listAll(Sort.by("id"))).build();
    }


    @GET
    @Path("{id}")
    public Response getSingle(@PathParam Long id) {
        User entity = User.findById(id);
        if (entity == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        return Response.ok(entity).build();
    }

    @POST
    @Transactional
    public Response create(User user) {
        if (user.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        user.persist();
        return Response.ok(user).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam Long id, User user) {
        if (user.username == null) {
            throw new WebApplicationException("Username was not set on request.", 422);
        }

        User entity = User.findById(id);

        if (entity == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }

        entity.username = user.username;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        User entity = User.findById(id);
        if (entity == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }

    @POST
    @Path("login")
    public Response login(UserLoginDto userLoginDto) throws Exception {
        User entity = User.findByUsernameAndPassword(userLoginDto.getUsername(),userLoginDto.getPassword());
        if (entity == null) {
            throw new WebApplicationException("User not found", 404);
        }
        HashMap<String, Long> timeClaims = new HashMap<>();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("preferred_username", entity.username);
        claims.put("iss", "https://quarkus.io/using-jwt-rbac");
        claims.put("jti", "a-123");
        claims.put("sub", "jdoe-using-jwt-rbac");
        claims.put("aud", "using-jwt-rbac");
        claims.put("groups", Arrays.asList("Echoer","user","Tester","Subscriber","group2"));

        HashMap<String, Object> roleMappings = new HashMap<>();
        roleMappings.put("group1","Group1MappedRole");
        roleMappings.put("group2","Group2MappedRole");

        claims.put("roleMappings", roleMappings);

        String token = TokenUtils.generateTokenString(claims, timeClaims);
        UserTokenDto userTokenDto = new UserTokenDto(1, userLoginDto.getUsername(),userLoginDto.getUsername(),userLoginDto.getUsername(),token);
        return Response.ok(userTokenDto).build();
    }



    @POST
    @Path("register")
    @Transactional
    public Response register(User user) throws Exception {
        LOG.info("User received for registration "+ user);
        if (user.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        user.persist();
        return Response.ok(user).status(201).build();

    }





}