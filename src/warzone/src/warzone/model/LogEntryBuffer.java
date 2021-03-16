package warzone.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntryBuffer extends Observable{
	private String d_message;
	private String d_time;
	private String d_order;
	private String d_result;
	private String d_phase;
	
	/**
	 * This method will set the time as current moment
	 */
	public void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d_time = df.format(new Date());
	}
	public String getTime() {
		return d_time;
	}
	public String getOrder() {
		return d_order;
	}
	public void setOrder(String d_order) {
		this.d_order = d_order;
	}
	public String getResult() {
		return d_result;
	}
	public void setResult(String d_result) {
		this.d_result = d_result;
	}
	public String getPhase() {
		return d_phase;
	}
	public void setPhase(String d_phase) {
		this.d_phase = d_phase;
	}
	public String getMessage() {
		return d_message;
	}
	public void setMessage(String d_message) {
		this.d_message = d_message;
	}
}
