package io.todo;

import io.quarkus.panache.common.Sort;
import io.todo.model.Todo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("todos")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TodoResource {
    @GET
    public Response get() {
        return Response.ok(Todo.listAll(Sort.by("title"))).build();
    }


    @GET
    @Path("{id}")
    public Response getSingle(@PathParam Long id) {
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", 404);
        }
        return Response.ok(entity).build();
    }

    @POST
    @Transactional
    public Response create(Todo todo) {
        if (todo.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        todo.persist();
        return Response.ok(todo).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam Long id, Todo todo) {
        if (todo.title == null) {
            throw new WebApplicationException("Todo Title was not set on request.", 422);
        }

        Todo entity = Todo.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", 404);
        }

        entity.title = todo.title;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Todo entity = Todo.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", 404);
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


}