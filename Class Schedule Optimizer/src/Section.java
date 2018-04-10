import java.util.Set;

public class Section {
	
	private int sectionNumber;
	private SectionType type;
	private Set <Weekday> days;
	private Time startTime;
	private int duration;
	
	public Section(int sectionNumber, SectionType type, Set<Weekday> days, Time startTime, int duration) {
		this.sectionNumber = sectionNumber;
		this.type = type;
		this.days = days;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public boolean conflictsWith(Section section)	{
		
		boolean dayConflict = false;
		
		for (Weekday w: section.days)
			if (this.days.contains(w))
				dayConflict = true;
		
		if (dayConflict)	{
			if (this.startTime.compareTo(section.startTime) < 0)	{
				Time t = this.startTime.clone();
				t.shift(this.duration);
				if (t.compareTo(section.startTime) >= 0)
					return true;
			} else	{
				Time t = section.startTime.clone();
				t.shift(section.duration);
				if (this.startTime.compareTo(t) < 0)
					return true;
			}
		}
		
		return false;
	}
	
	
	public boolean contains(Weekday day, Time time)	{
		
		if (!this.days.contains(day))
			return false;
		
		Time endTime = this.startTime.clone();
		endTime.shift(this.duration);
		
		if (!((this.startTime.compareTo(time) <= 0) && (endTime.compareTo(time) > 0)))
			return false;
		
		return true;
	}
	
	
	public boolean equals(Section s)	{
		return this.sectionNumber == s.sectionNumber;
	}

	
	/**
	 * @return the days
	 */
	public Set<Weekday> getDays() {
		return this.days;
	}

	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return this.duration;
	}
	
	
	public Time getEndTime()	{
		Time t = this.startTime.clone();
		t.shift(this.duration);
		return t;
	}
	
	
	
	/**
	 * @return the sectionNumber
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}
	
	
	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return this.startTime;
	}

	
	/**
	 * @return the type
	 */
	public SectionType getType() {
		return type;
	}

	public String toString()	{
		String days = "";
		for (Weekday w: this.days)
			days += w.toShortName();
		
		return String.format("%1$d,%2$s,%3$d,%4$s,%5$s,%6$d", this.sectionNumber, this.type.toString(), days, this.startTime.toString(), this.duration);
	}
	
}
