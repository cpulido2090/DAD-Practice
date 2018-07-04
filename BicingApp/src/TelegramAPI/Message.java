package TelegramAPI;

public class Message {
	private long chat_id;
	private String text;
	
	//Message constructor with chat id and text for sending telegram
	public Message(long chat_id, String text) {
		super();
		this.chat_id = chat_id;
		this.text = text;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Message [chat_id=" + chat_id + ", text=" + text + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public long getChat_id() {
		return chat_id;
	}
	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	
}