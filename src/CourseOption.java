import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseOption {
	
	
	private String			catalogName;
	private String			courseName;
	private int				credits;
	private Set<Section>	lectureSections;
	private Set<Section>	labSections;
	private Set<Section>	discussionSections;
	private Set<Section>	quizSections;
	
	/**
	 * @param catalogName
	 * @param courseName
	 * @param credits
	 * @param sections
	 */
	public CourseOption(String catalogName, String courseName, int credits) {
		super();
		this.catalogName = catalogName;
		this.courseName = courseName;
		this.credits = credits;
		this.lectureSections = new HashSet<Section>();
		this.labSections = new HashSet<Section>();
		this.discussionSections = new HashSet<Section>();
		this.quizSections = new HashSet<Section>();
	}
	
	public boolean add(Section s) {
		switch(s.getType())	{
			case LECTURE: 		return this.lectureSections.add(s);
			case LAB:			return this.labSections.add(s);
			case DISCUSSION:	return this.discussionSections.add(s);
			case QUIZ:			return this.quizSections.add(s);
			default:			return false;
		}
		
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
	 * @return the credits
	 */
	public int getCredits()	{
		return credits;
	}
	
	
	
	/**
	 * @return the lectureSections
	 */
	public Set<Section> getLectureSections() {
		return lectureSections;
	}

	
	/**
	 * @return the labSections
	 */
	public Set<Section> getLabSections() {
		return labSections;
	}

	
	/**
	 * @return the discussionSections
	 */
	public Set<Section> getDiscussionSections() {
		return discussionSections;
	}

	
	/**
	 * @return the quizSections
	 */
	public Set<Section> getQuizSections() {
		return quizSections;
	}
	
	/**
	 * @return the sections
	 */
	public List<Set<Section>> getSections()	{
		List<Set<Section>> l = new ArrayList<Set<Section>>();
		l.add(this.lectureSections);
		l.add(this.labSections);
		l.add(this.discussionSections);
		l.add(this.quizSections);
		return l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return catalogName + "," + courseName + "," + credits;
	}
	
}
