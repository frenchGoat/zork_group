import java.util.ArrayList;
import java.util.regex.Pattern;

public class Dialogue {

	private String message;
	private String messageKey;
	private ArrayList<String> actions;


	public Dialogue(String message, String messageKey) {
		super();
		init();
		this.message = message.substring(2);
		this.messageKey = messageKey;
	}
	
	public Dialogue(String messageLine) {
		init();
		this.messageKey = Character.toString(messageLine.charAt(0));
		if (messageLine.contains(Pattern.quote("["))) {
			String[] parts = message.substring(2).split(Pattern.quote(","));
			this.message = parts[1].substring(2);
			actions.add(parts[0].substring(0, parts[0].length()));
		}else{
			this.message = messageLine.substring(2);
		}
	}
	
	public void init(){
		actions = new ArrayList<String>();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

	public void setActions(ArrayList<String> actions) {
		this.actions = actions;
	}
}
