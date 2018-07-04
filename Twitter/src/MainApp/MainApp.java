package MainApp;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import TwitterAPI.*;

public class MainApp {

	public static Client client = ClientBuilder.newClient();
	
	public static void main(String[] args) throws SchedulerException {

		try {
			// Job for execute TwitterAPI class every 60 seconds
			JobDetail twitterAPI = JobBuilder.newJob(TwitterAPI.class).withIdentity("TwitterAPI").build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
					.build();

			//Schedule the job
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.start();
			sched.scheduleJob(twitterAPI, trigger);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}
		