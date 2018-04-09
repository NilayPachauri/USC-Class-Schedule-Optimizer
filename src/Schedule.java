import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Schedule implements Cloneable {
	
	
	private List<Course> courses;
	
	public Schedule() {
		this.courses = new ArrayList<Course>();
	}
	
	public boolean add(Course course) {
		
		for (Course c: this.courses)
			if (c.conflictsWith(course))
				throw new ScheduleConflictException(c, course);
		
		return this.courses.add(course);
	}
	
	public Schedule clone() {
		Schedule s = new Schedule();
		
		for (Course c: this.courses)
			s.add(c);
		
		return s;
	}
	
	public boolean conflictsWith(Set<Weekday> set, Time startTime, Time endTime)	{
		for (Course c: this.courses)
			if (c.conflictsWith(new Restriction(set, startTime, endTime)))
				return true;
		return false;
	}
	
	public Course getCourse(Weekday day, Time time) {
		for (Course c: this.courses)
			if (c.contains(day, time)) return c;
		return null;
	}
	
	public boolean remove(Weekday day, Time time) {
		return this.courses.remove(this.getCourse(day, time));
	}
	
	public boolean save(PrintStream ps, Comparator<Course> comp)	{
		Collections.sort(this.courses, comp);
		
		for (Course c: this.courses)
			ps.println(c.toString());
		
		if (ps.checkError())
			return false;
		
		ps.close();
		return true;
	}
	
	public int totalCredits()	{
		int credits = 0;
		
		for (Course c: this.courses)
			credits += c.getCredits();
		
		return credits;
	}
	
	public int totalTimeBetweenClasses()	{
		int time = 0;
		
		this.courses.sort(new CourseTimeComparator());
		
		Course[] days = new Course[5];
		
		for (Course c: this.courses)	{
			for (Weekday w: c.getDays())	{
				if (days[w.value() - 1] == null)	{
					days[w.value() - 1] = c;
				} else	{
					time += c.getStartTime().compareTo(days[w.value() - 1].getEndTime());
					days[w.value() - 1] = c;
				}
			}
			
		}
		
		return time;
	}
	
	public int totalTimeTillEarliestClasses()	{
		int time = 0;
		
		this.courses.sort(new CourseTimeComparator());
		
		Course[] days = new Course[5];
		
		for (Course c: this.courses)	{
			for (Weekday w: c.getDays())	{
				if (days[w.value() - 1] == null)	{
					days[w.value() - 1] = c;
					time += c.getStartTime().compareTo(new Time(7, 30, false));
				} 
			}
			
		}
		
		return time;
	}
	
	public int totalTimeTillLatestClasses()	{
		int time = 0;
		
		this.courses.sort(Collections.reverseOrder(new CourseTimeComparator()));
		
		Course[] days = new Course[5];
		
		for (Course c: this.courses)	{
			for (Weekday w: c.getDays())	{
				if (days[w.value() - 1] == null)	{
					days[w.value() - 1] = c;
					time += (new Time(11, 0, true)).compareTo(c.getEndTime());
				} 
			}
			
		}
		
		return time;
	}
}
