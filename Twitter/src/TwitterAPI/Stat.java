package TwitterAPI;

public class Stat {
	private String name;
	private String value;
	
	//Stat object with name and value parameters with getters and setters
	public Stat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Stat(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}