package Clients;

import java.util.List;

public class Clients {

	//Parameters of the object Clients with getters and setters
	private String phone_number;
	private List<Integer> subscribedStations;
	private String telegramToken;

	public Clients(String phone_number, List<Integer> subscribedStations, String telegramToken) {
		super();
		this.phone_number = phone_number;
		this.subscribedStations = subscribedStations;
		this.telegramToken = telegramToken;
	}
	public Clients() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public List<Integer> getSubscribedStations() {
		return subscribedStations;
	}
	
	public void setSubscribedStations(List<Integer> subscribedStations) {
		this.subscribedStations = subscribedStations;
	}

	public String getTelegramToken() {
		return telegramToken;
	}

	public void setTelegramToken(String telegramToken) {
		this.telegramToken = telegramToken;
	}

}