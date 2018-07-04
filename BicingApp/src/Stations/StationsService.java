package Stations;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import MainApp.MainApp;
import Stats.Stat;
import Stations.Station;

@Path("/stations")
public class StationsService {

	//Get stations
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> getAll() {
		return MainApp.getStations();
	}
	
	//Get stats
	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Stat> getAllStat() {
		return MainApp.getStats();
	}

}
