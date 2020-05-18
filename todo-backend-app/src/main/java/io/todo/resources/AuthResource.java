package io.todo.resources;

import io.todo.dto.UserLoginDto;
import io.todo.dto.UserTokenDto;
import io.todo.model.User;
import io.todo.security.jwt.TokenUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("auth")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AuthResource {



}
