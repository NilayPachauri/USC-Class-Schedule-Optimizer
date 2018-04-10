import java.util.Comparator;


public class CourseCreditComparator implements Comparator<Course> {
	
	
	public CourseCreditComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Course o1, Course o2) {
		// TODO Auto-generated method stub
		if (o1.getCredits() - o2.getCredits() == 0)
			return o1.getName().compareTo(o2.getName());
		return o1.getCredits() - o2.getCredits();
	}
	
}
