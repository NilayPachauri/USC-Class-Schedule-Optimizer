import java.util.List;
import java.util.Set;

public class Restriction {
	
	private Set<Weekday> days;
	private Time startTime, endTime;
	
	public Restriction(Set<Weekday> set, Time startTime, Time endTime) {
		this.days = set;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	
	/**
	 * @return the days
	 */
	public Set<Weekday> getDays() {
		return this.days;
	}

	
	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return this.startTime;
	}

	
	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return this.endTime;
	}
	
}
