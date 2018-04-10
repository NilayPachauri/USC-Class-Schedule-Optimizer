

public enum SectionType {
	LECTURE, LAB, DISCUSSION, QUIZ;
	
	public static SectionType fromString(String s)	{
		switch (s.toUpperCase())	{
			case "LECTURE":		return SectionType.LECTURE;
			case "LAB":			return SectionType.LAB;
			case "DISCUSSION":	return SectionType.DISCUSSION;
			case "QUIZ":		return SectionType.QUIZ;
			default:			throw new IllegalArgumentException("Invalid Section Type: " + s);
		}
	}
	
	public String toString()	{
		switch (this)	{
			case LECTURE:		return "Lecture";
			case LAB:			return "Lab";
			case DISCUSSION:	return "Discussion";
			case QUIZ:			return "Quiz";
		}
		return null;
	}
}
