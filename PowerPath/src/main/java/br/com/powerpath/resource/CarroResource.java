package br.com.powerpath.resource;

import br.com.powerpath.Exception.TipoCarroInvalidoException;
import br.com.powerpath.bo.CarroBO;
import br.com.powerpath.to.CarroTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/carro")
public class CarroResource {
    private CarroBO carroBO = new CarroBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<CarroTO> resultado = carroBO.listarTodos();
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
    public Response inserir(CarroTO carro) throws SQLException, TipoCarroInvalidoException {
        CarroTO resultado = carroBO.inserir(carro);
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
    @Path("/{idCarro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(CarroTO carroTO, @PathParam("idCarro") int idCarro) throws TipoCarroInvalidoException {
        carroTO.setIdCarro(idCarro);
        CarroTO resultado = carroBO.alterar(carroTO);
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
    @Path("/{idCarro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idCarro") int idCarro) {
        Response.ResponseBuilder response;
        if (carroBO.excluir(idCarro)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @GET
    @Path("/{idCarro}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vizualizarPeloCodigo(@PathParam("idCarro") int idCarro) {
        CarroTO resultado = carroBO.visualizarPeloCodigo(idCarro);
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
    @Path("/{idCarro}/recarga")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recarregarCarro(@PathParam("idCarro") int idCarro, CarroTO carroTO) {
        carroTO.setIdCarro(idCarro);
        CarroTO resultado = carroBO.recarregarCarro(carroTO);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao recarregar o carro").build();
        }
    }
}
