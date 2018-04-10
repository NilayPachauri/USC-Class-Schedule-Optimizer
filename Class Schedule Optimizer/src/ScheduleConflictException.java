
public class ScheduleConflictException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScheduleConflictException(Course c1, Course c2) {
		super("Schedule Conflict: " + c1.getName() + " conflicts with " + c2.getName());
	}
	
}
