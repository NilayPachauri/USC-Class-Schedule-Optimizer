import java.util.Comparator;


/**
 * 
 */

/**
 * @author Nilay
 *
 */
public class SchedulesEndTimeComparator implements Comparator<Schedule> {
	
	
	/**
	 * 
	 */
	public SchedulesEndTimeComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Schedule o1, Schedule o2) {
		// TODO Auto-generated method stub
		return o1.totalTimeTillLatestClasses() - o2.totalTimeTillLatestClasses();
	}
	
	
}
