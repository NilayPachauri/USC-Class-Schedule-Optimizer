import java.util.Comparator;
import java.util.List;

public class CourseListSizeComparator implements Comparator<List<Course>> {
	
	
	public CourseListSizeComparator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(List<Course> o1, List<Course> o2) {
		// TODO Auto-generated method stub
		return o1.size() - o2.size();
	}
	
}
