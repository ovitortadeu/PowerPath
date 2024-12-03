package br.com.powerpath.resource;

import br.com.powerpath.bo.UsuarioBO;
import br.com.powerpath.to.UsuarioTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<UsuarioTO> resultado = usuarioBO.listarTodos();
        Response.ResponseBuilder response;
        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(UsuarioTO usuario) throws Exception {
        UsuarioTO resultado = usuarioBO.inserir(usuario);
        Response.ResponseBuilder response;
        if (resultado != null) {
            response = Response.status(201); // 201 CREATED
        } else {
            response = Response.status(400); // 400 BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(UsuarioTO usuarioTO, @PathParam("idUsuario") int idUsuario) {
        usuarioTO.setIdUsuario(idUsuario);
        UsuarioTO resultado = usuarioBO.alterar(usuarioTO);
        Response.ResponseBuilder response;
        if (resultado != null) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(400); // 400 BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idUsuario}")
    public Response excluir(@PathParam("idUsuario") int idUsuario) {
        Response.ResponseBuilder response;
        if (usuarioBO.excluir(idUsuario)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response visualizarPeloCodigo(@PathParam("idUsuario") int idUsuario) {
        UsuarioTO resultado = usuarioBO.visualizarPeloCodigo(idUsuario);
        Response.ResponseBuilder response;
        if (resultado != null) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }


    @POST
    @Path("/{idUsuario}/pontos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPontos(@PathParam("idUsuario") int idUsuario) {
        Response.ResponseBuilder response;
        if (usuarioBO.atualizarPontos(idUsuario)) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(400); // 400 BAD REQUEST
        }
        return response.build();
    }
}
