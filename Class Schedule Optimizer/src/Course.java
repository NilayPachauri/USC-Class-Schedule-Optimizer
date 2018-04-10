import java.util.Set;

public class Course {
	
	
	private String			catalogName, courseName;
	private int				sectionNumber;
	private SectionType		type;
	private int				credits;
	private Set<Weekday>	days;
	private Time			startTime;
	private int				duration;
	
	public Course(String catalogName, String courseName, int sectionNumber, SectionType type, int credits,
			Set<Weekday> days, Time startTime, int duration) {
		this.catalogName = catalogName;
		this.courseName = courseName;
		this.sectionNumber = sectionNumber;
		this.type = type;
		this.credits = credits;
		this.days = days;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public boolean conflictsWith(Course course) {
		
		boolean dayConflict = false;
		
		for (Weekday day: course.days)
			if (this.days.contains(day)) dayConflict = true;
		
		if (dayConflict) {
			if (this.startTime.compareTo(course.startTime) < 0) {
				if (this.getEndTime().compareTo(course.startTime) >= 0) return true;
			} else {
				if (this.startTime.compareTo(course.getEndTime()) < 0) return true;
			}
		}
		
		return false;
	}
	
	public boolean conflictsWith(Restriction restriction)	{
		
		boolean dayConflict = false;
		
		for (Weekday day: restriction.getDays())
			if (this.days.contains(day)) dayConflict = true;
		
		if (dayConflict)	{
			if (this.startTime.compareTo(restriction.getStartTime()) < 0)	{
				if (this.getEndTime().compareTo(restriction.getStartTime()) >= 0) return true;
			} else	{
				if (this.startTime.compareTo(restriction.getEndTime()) < 0) return true;
			}
		}
		
		return false;
		
	}
	
	public boolean contains(Weekday day, Time time) {
		
		if (!this.days.contains(day)) return false;
		
		Time endTime = this.startTime.clone();
		endTime.shift(this.duration);
		
		if (!((this.startTime.compareTo(time) <= 0) && (endTime.compareTo(time) > 0))) return false;
		
		return true;
	}
	
	public boolean equals(Course c) {
		return (this.catalogName.equals(c.catalogName)) && (this.credits == c.credits) && (this.days.equals(c.days))
				&& (this.startTime.equals(c.startTime)) && (this.duration == c.duration);
	}
	
	/**
	 * @return the catalogName
	 */
	public String getCatalogName() {
		return catalogName;
	}
	
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * @return the sectionNumber
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}
	
	/**
	 * @return the type
	 */
	public SectionType getType() {
		return type;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.catalogName;
	}
	
	/**
	 * @return the credits
	 */
	public int getCredits() {
		return this.credits;
	}
	
	/**
	 * @return the days
	 */
	public Set<Weekday> getDays() {
		return this.days;
	}
	
	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return this.startTime;
	}
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return this.duration;
	}
	
	public Time getEndTime() {
		Time t = this.startTime.clone();
		t.shift(this.duration);
		return t;
	}
	
	public String toString() {
		String days = "";
		for (Weekday w: this.days)
			days += w.toShortName();
		
		return String.format("%1$s,%2$s,%3$d,%4$s,%5$d,%6$s,%7$s,%8$d", this.catalogName, this.courseName,
				this.sectionNumber, this.type.toString(), this.credits, days, this.startTime.toString(), this.duration);
	}
	
}
