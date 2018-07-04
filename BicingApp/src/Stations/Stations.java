package Stations;

import java.util.List;

import Stats.Stat;

public class Stations {
	private static List<Station> stations;
	
	//Constructor, getter and setter, and also some methods for generating stats
	public Stations(List<Station> stations) {
		this.stations = stations;
	}

	public Stations() {
		super();
	}

	public String toString() {
		return stations.get(0).toString();
	}

	public static List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	//Generate stat
	public static Stat getNumberOfSlots() {
		Stat stat = new Stat();
		int i = 0;
		for (Station s : stations)
			i += Integer.parseInt(s.getSlots());
		stat.setName("available slots");
		stat.setValue("The number of available slots is "+ i);
		return stat;
	}
	
	//Generate stat
	public static Stat getNumberOfBikes() {
		Stat stat = new Stat();
		int i = 0;
		for (Station s : stations)
			i += Integer.parseInt(s.getBikes());
		
		stat.setName("available bikes");
		stat.setValue("The number of available bikes is "+ i);
		return stat;
		
	}
	
	//Generate stat
	public static Stat getPercentage() {

		int bikes = 0, slots=0;
		for (Station s : stations){
			bikes += Integer.parseInt(s.getBikes());
			slots += Integer.parseInt(s.getSlots());
		}
		
		double percentage = (double)bikes / (bikes+slots)*100;
		System.out.println(percentage);
		Stat stat = new Stat();
		
		if(percentage < 1){
			stat.setValue("Now, Bicing has "+ bikes + " bikes available of "+(bikes+slots) +" slots");
		}else{
			stat.setValue("Now, Bicing has "+ bikes + " bikes available of "+(bikes+slots) +" slots \nThe percentage of available bikes is " + (int) percentage + "%");	
		}
		return stat;
		
	}
	
	//Generate message for sending Telegram
	public static String TelegramStats(List<Integer> clientSubscription) {
		String cadena = "";
		for (Integer cb : clientSubscription) {
				int slots = Integer.parseInt(stations.get(cb).getSlots());
				cadena += "The station: " + cb + " has "+ slots + " free slots";
		}
		return cadena;

	}
}