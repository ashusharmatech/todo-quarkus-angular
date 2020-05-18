package io.todo.resources;

import io.quarkus.panache.common.Sort;
import io.todo.model.Todo;
import io.todo.security.jwt.TokenUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

@Path("token")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TokenResource {

    @GET
    public Response getJwtToke() throws Exception {
        HashMap<String, Long> timeClaims = new HashMap<>();

        HashMap<String, Object> claimms = new HashMap<>();
        claimms.put("iss","https://quarkus.io/using-jwt-rbac");
        claimms.put("preferred_username","ashu");
        claimms.put("roleMappings","test");


        String token = TokenUtils.generateTokenString(claimms, timeClaims);
        return Response.ok(token).build();
    }

}