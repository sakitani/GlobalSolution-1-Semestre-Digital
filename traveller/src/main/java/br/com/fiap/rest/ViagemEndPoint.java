package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.ViagemDao;
import br.com.fiap.model.Viagem;

@Path("/viagem")
@Produces(MediaType.APPLICATION_JSON)
public class ViagemEndPoint {
	
	private ViagemDao dao = new ViagemDao();
	
	@GET
	public Response index() {
		try {
			List<Viagem> list =  dao.getAll();
			return Response.status(Response.Status.OK).entity(list).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Viagem viagem) {
		if (viagem == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(viagem);
			return Response.status(Response.Status.CREATED).entity(viagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Viagem viagem = dao.findById(id);
		if (viagem == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(viagem).build();
	}
	
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Viagem viagem) {
		if(id==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(viagem==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(dao.findById(id)==null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		viagem.setId(id);
		try {
			dao.update(viagem);
			return Response.status(Response.Status.OK).entity(viagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Viagem viagem = dao.findById(id);
		if (viagem == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			dao.delete(id);
			return Response.status(Response.Status.OK).entity(viagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
