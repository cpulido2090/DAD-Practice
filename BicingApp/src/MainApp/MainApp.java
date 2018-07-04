package MainApp;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.sun.net.httpserver.HttpServer;

import Clients.ClientService;
import Clients.Clients;
import Stations.BicingApp;
import Stations.Station;
import Stations.StationsService;
import Stats.Stat;

public class MainApp {
	public static Client client = ClientBuilder.newClient();
	
	//List for clients, stations and stats
	public static List<Clients> clients = new ArrayList<Clients>();
	public static List<Station> stations = new ArrayList<Station>();
	public static List<Stat> stats = new ArrayList<Stat>();
	
	public static void main(String[] args) throws SchedulerException {
	
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(15000).build();
		ResourceConfig config = new ResourceConfig(StationsService.class, ClientService.class);
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.out.println("Server started...");

		try {

			// Job for execute BicingApp class every 60 seconds
			JobDetail bicingApp = JobBuilder.newJob(BicingApp.class).withIdentity("BicingApp").build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
					.build();

			//Schedule the job
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.start();
			sched.scheduleJob(bicingApp, trigger);

			
		} catch (SchedulerException se) {
			se.printStackTrace();
		}	
	}

	//Set of station
	public static void setStations(List<Station> station){
		stations = station;
	}
	
	//Get of station
	public static List<Station> getStations(){
		return stations;
	}
	
	//Set of stat
	public static void setStat(List <Stat> stat){
		stats =  stat;
	}
	
	//Get of stat
	public static List<Stat> getStats(){
		return stats;
	}
	
	//Add a stat
	public static void addStat(Stat stat){
		stats.add(stat);
		
	}
}
