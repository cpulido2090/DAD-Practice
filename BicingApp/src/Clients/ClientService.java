package Clients;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import TelegramAPI.TelegramAPI;
import Clients.Clients;

@Path("/clients")
public class ClientService {
	static List<Clients> client = new ArrayList<Clients>();
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Clients c) {
		for(Clients searchClient : client){
			if(searchClient.getPhone_number().equals(c.getPhone_number())){
				//return 409 (Conflict), because the clients exists
				return Response.status(409).entity(c).build();
			}
		}
		client.add(c);
		return Response.status(200).entity(c).build();
	}
	
	@GET
	@Path("/notify/{phone_number}")
	@Produces(MediaType.APPLICATION_JSON)
	//Notify if the phone number is from one client
	public Response get(@PathParam("phone_number") String phone_number) {
		for (Clients c : client) {
			if (c.getPhone_number().equals(phone_number)) {
				TelegramAPI.sendTelegramToClients(c);
				return Response.status(200).entity(phone_number).build();
			}
		}
		//Return 404 (Not Found)
		return Response.status(404).entity(phone_number).build();
		
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	//Get clients
	public List<Clients> getAll() {
		return client;
	}
}