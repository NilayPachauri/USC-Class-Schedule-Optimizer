import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class ClassOptimizer {
	
	
	private List<CourseOption>	courses;
	private List<List<Course>>	sortedCourses;
	private List<Schedule>		schedules;
	
	public ClassOptimizer()	{
		this.courses = new ArrayList<>();
		this.sortedCourses = new ArrayList<>();
		this.schedules = new ArrayList<>();
	}
	
	public ClassOptimizer(String fileName) {
		load(fileName);
		courseOptionsToCourseLists();
		this.schedules = new ArrayList<Schedule>();
		makeSchedules(this.arrayCopy(sortedCourses), new Schedule());
	}
	
	private List<List<Course>> arrayCopy(List<List<Course>> courses) {
		List<List<Course>> courseList = new ArrayList<List<Course>>();
		for (List<Course> cList: courses) {
			courseList.add(new ArrayList<Course>(cList));
		}
		return courseList;
	}
	
	public void reInit(String fileName)	{
		this.clear();
		load(fileName);
		courseOptionsToCourseLists();
		this.schedules = new ArrayList<Schedule>();
		makeSchedules(this.arrayCopy(sortedCourses), new Schedule());
	}
	
	private void clear()	{
		this.courses.clear();
		this.sortedCourses.clear();
		this.schedules.clear();
	}
	
	// A helper to pop up an error message box to show the given error message.
	static void error(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	// Loads the user's courses from COURSES_FILE_NAME and puts the
	// results into the user's current schedule.
	private void load(String fileName) {
		try {
			this.courses = ClassOptimizerIO.load(new FileInputStream(fileName));
		} catch (Exception e) {
			// also try loading from within a JAR
			InputStream stream = ClassOptimizerIO.class.getResourceAsStream("/" + fileName);
			if (stream != null) {
				try {
					this.courses = ClassOptimizerIO.load(stream);
				} catch (Exception e2) {
					System.err.println(e2);
				}
			}
			
			if (this.courses == null) {
				error("An error occurred while loading your course schedule: \n" + e
						+ "\n\nPlease try again");
				this.courses = new ArrayList<CourseOption>();
			}
		}
	}
	
	private void courseOptionsToCourseLists() {
		this.sortedCourses = new ArrayList<List<Course>>();
		
		for (CourseOption c: this.courses) {
			for (Set<Section> courseType: c.getSections()) {
				
				List<Course> courseList = new ArrayList<Course>();
				
				for (Section s: courseType)
					courseList.add(new Course(c.getCatalogName(), c.getCourseName(), s.getSectionNumber(), s.getType(),
							(s.getType() == SectionType.LECTURE) ? c.getCredits() : 0, s.getDays(), s.getStartTime(),
							s.getDuration()));
				
				if (!(courseList.isEmpty())) this.sortedCourses.add(courseList);
				
			}
			
		}
		
		this.sortedCourses.sort(new CourseListSizeComparator());
	}
	
	private void makeSchedules(List<List<Course>> courseLists, Schedule s) {
		if (courseLists.isEmpty()) {
			if (!this.schedules.contains(s)) this.schedules.add(s);
		} else {
			if (courseLists.get(0).isEmpty()) {
				courseLists.remove(0);
			} else {
				while (!courseLists.isEmpty() && !courseLists.get(0).isEmpty()) {
					try {
						Schedule sNew = s.clone();
						sNew.add(courseLists.get(0).remove(0));
						List<List<Course>> next = arrayCopy(courseLists);
						next.remove(0);
						makeSchedules(next, sNew);
					} catch (ScheduleConflictException sce) {
						
					}
				}
			}
		}
		
	}
	
	/**
	 * @return the schedules
	 */
	public List<Schedule> getSchedules() {
		return this.schedules;
	}
	
}
