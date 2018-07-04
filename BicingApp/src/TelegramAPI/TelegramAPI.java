package TelegramAPI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.NotFoundException;


import Stations.Stations;
import Clients.Clients;

public class TelegramAPI {
	public static Client client = ClientBuilder.newClient();

	public static void sendTelegramToClients(Clients clients){

		// Bot: cpulido2090_bot
		// Token: 295822338:AAGyWjXoYQp8QJCbxyLNEfpF_9ng-Qw69o8
		try {

			//Chat id
			long chat_id = 75944206;
			
			//Message for telegram bot
			Message message = new Message(chat_id, Stations.TelegramStats(clients.getSubscribedStations()));			
			
			//Client creation for sending an HTTP POST request
			WebTarget targetSendMessage = client.target("https://api.telegram.org")
					.path("/bot" + clients.getTelegramToken() + "/sendMessage");
			String response = targetSendMessage.request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE),
					String.class);
			
		} catch (NotFoundException nfe) {
			nfe.printStackTrace();
		}
	}
}
