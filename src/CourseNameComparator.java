import java.util.Comparator;


public class CourseNameComparator implements Comparator<Course> {
 
	public CourseNameComparator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(Course o1, Course o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
	
}
