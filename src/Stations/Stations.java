package Stations;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Stations {
	private List<Station> stations;
	
	public static void main(String [] args){
		
		Client client = ClientBuilder.newClient();
		Station station= (Station) client.target("http://wservice.viabicing.cat/v2").path("/stations");
		System.out.println(station.toString());
	}

}
