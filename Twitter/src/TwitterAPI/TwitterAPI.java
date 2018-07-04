package TwitterAPI;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.Date;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TwitterAPI  implements Job{
	public static Client client = ClientBuilder.newClient();
	
	public void execute(JobExecutionContext context) throws JobExecutionException, BadRequestException, NotAllowedException {
		
		System.out.println("Executing ScheduledTestJob at " + new Date());
		
		try{
							
			//Get stats from stats api and save on a list of stats
			WebTarget targetGet = client.target("http://localhost:15000/stations/stats");	
			List<Stat> getStats = targetGet.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Stat>>() {});
			
			//Loop the list of stats for generating the message to upload on twitter=> it only sends one(percentage stat)
			String message = "";
			for(Stat s : getStats){
				if(s.getName().contains("percentage"))
				message+= s.getValue() + "\n";
			}	
			
			//publish tweet with the message generated
			sendTweets(message);
			
		}catch(BadRequestException bre){
			bre.printStackTrace();
		}
	}
	
	public static void sendTweets(String tweetStats) {
		
		//Keys obtained from TwitterAPI
		String CONSUMER_KEY = "roP9u656lt9uT1frukmbutida";
		String CONSUMER_SECRET = "SpiwBlJtEWxDN1jVMwnZVH2pAuUqeSARaeBkFjRsbpiJ8g5fGx";
		String ACCESS_TOKEN= "798913413057540098-Ild1WOzLU7S7aOud94zghxSl5sEiYMJ";
		String ACCESS_TOKEN_SECRET = "LyHax1OA5PU8kV1PLYuwNrrWHnT43BMPyPwKZ0VOZlBlg";

		// Retrieving tweet to a channel
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));
		
		try {
			//Check that tweet is post succesfully
			Status status = twitter.updateStatus(tweetStats);
		
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
