import java.util.Comparator;

/**
 * 
 * @author Nilay
 *
 */
public class ScheduleStartTimeComparator implements Comparator<Schedule> {
	
	
	public ScheduleStartTimeComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Schedule o1, Schedule o2) {
		// TODO Auto-generated method stub
		return o1.totalTimeTillEarliestClasses() - o2.totalTimeTillEarliestClasses();
	}
	
}
