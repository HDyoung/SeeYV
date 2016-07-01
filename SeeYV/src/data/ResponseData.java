package data;

public class ResponseData {
	public String sendPerson;
	public String data;
	public String time;
	
	@Override
	public String toString() {
		return "sendPerson :"+sendPerson+" Data: "+data+" Time: "+time;
	}
}
