import java.util.Comparator;

/**
 * 
 * @author Nilay
 *
 */
public class ScheduleIntensityComparator implements Comparator<Schedule> {
	
	
	public ScheduleIntensityComparator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(Schedule o1, Schedule o2) {
		// TODO Auto-generated method stub
		return o1.totalTimeBetweenClasses() - o2.totalTimeBetweenClasses();
	}
	
}
