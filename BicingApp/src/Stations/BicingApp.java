package Stations;

import java.util.ArrayList;
import MainApp.MainApp;
import java.util.Date;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Stats.Stat;

public class BicingApp implements Job {

	public static Client client = ClientBuilder.newClient();

	public void execute(JobExecutionContext context)
			throws JobExecutionException, BadRequestException, NotAllowedException {

		System.out.println("Executing ScheduledTestJob at " + new Date());

		try {

			// Get stations from Bicing API and save on list of stations
			WebTarget targetGet = client.target("http://wservice.viabicing.cat/v2").path("/stations");
			Stations getStations = targetGet.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Stations>() {
			});
			MainApp.setStations(Stations.getStations());

			//Add stats to the list of stats
			List<Stat> tempStat = new ArrayList<Stat>();
			tempStat.add(Stations.getNumberOfBikes());
			tempStat.add(Stations.getNumberOfSlots());
			tempStat.add(Stations.getPercentage());
			MainApp.setStat(tempStat);

		} catch (BadRequestException bre) {
			bre.printStackTrace();
		}
	}

}
