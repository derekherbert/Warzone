package warzone.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import warzone.state.*;

public class LogEntryBuffer extends Observable{
	private String d_message;
	private String d_time;
	private String d_order;
	private String d_result;
	private String d_phase;
	
	/**
	 * This method will set the time as current moment
	 */
	public LogEntryBuffer() {
		this.d_message = "";
	}
	
	public void clearMessage() {
		this.d_message = "";
	}
	
	public LogEntryBuffer setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d_time = df.format(new Date());
		return this;
	}
	public String getTime() {
		return d_time;
	}
	public String getOrder() {
		return d_order;
	}
	public LogEntryBuffer setOrder(String d_order) {
		this.d_order = d_order;
		return this;
	}
	public String getResult() {
		return d_result;
	}
	public LogEntryBuffer setResult(String d_result) {
		this.d_result = d_result;
		return this;
	}
	public String getPhase() {
		return d_phase;
	}
	public LogEntryBuffer setPhase(Phase p_phase) {
		if (p_phase instanceof MapEditor) {
			this.d_phase = "MapEditor";
		}
		else if (p_phase instanceof Startup) {
			this.d_phase = "Startup";
		}
		else if (p_phase instanceof GamePlay) {
			this.d_phase = "GamePlay";
		}
		else if (p_phase instanceof IssueOrder) {
			this.d_phase = "IssueOrder";
		}
		else if (p_phase instanceof OrderExecution) {
			this.d_phase = "OrderExecution";
		}
		else if (p_phase instanceof Reinforcement) {
			this.d_phase = "Reinforcement";
		}
		return this;
	}
	public String getMessage() {
		return d_message;
	}
	public LogEntryBuffer setMessage(String d_message) {
		this.d_message = d_message;
		return this;
	}
}
