import java.util.Comparator;


public class CourseTimeComparator implements Comparator<Course> {
	
	
	public CourseTimeComparator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(Course o1, Course o2) {
		// TODO Auto-generated method stub
		if (o1.getStartTime().compareTo(o2.getStartTime()) == 0)	{
			if (o1.getEndTime().compareTo(o2.getEndTime()) == 0)	{
				if (o1.getName().compareTo(o2.getName()) == 0)	{
					return 0;
				}
				return (o1.getName().compareTo(o2.getName()) > 0) ? 1 : -1;
			}
			return (o1.getEndTime().compareTo(o2.getEndTime()) > 0) ? 1 : -1;
		}
		return (o1.getStartTime().compareTo(o2.getStartTime()) > 0) ? 1 : -1;
	}
	
}
