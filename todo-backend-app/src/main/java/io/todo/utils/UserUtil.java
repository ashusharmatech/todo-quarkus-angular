package io.todo.utils;

import io.todo.model.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class UserUtil {
    public static User getUserFromContext(SecurityContext ctx){
        Principal caller =  ctx.getUserPrincipal();
        String name = caller == null ? null : caller.getName();
        if (name == null) {
            throw new WebApplicationException("Unauthorized Access.", 422);
        }
        User user = User.findByUsername(name);
        if(user==null)
        {
            throw new WebApplicationException("No user found.", 422);
        }
        return user;
    }
}
