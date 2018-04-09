

public enum Weekday {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	
	public static Weekday fromString(String s)	{
		switch (s.toUpperCase())	{
			case "MONDAY":		return Weekday.MONDAY;
			case "M":			return Weekday.MONDAY;
			case "TUESDAY":		return Weekday.TUESDAY;
			case "T":			return Weekday.TUESDAY;
			case "WEDNESDAY":	return Weekday.WEDNESDAY;
			case "W":			return Weekday.WEDNESDAY;
			case "THURSDAY":	return Weekday.THURSDAY;
			case "TH":			return Weekday.THURSDAY;
			case "FRIDAY":		return Weekday.FRIDAY;
			case "F":			return Weekday.FRIDAY;
			default: 			throw new IllegalArgumentException("Illegal Weekday: " + s);
		}
	}
	
	public String toShortName()	{
		switch (this)	{
			case MONDAY: 	return "M";
			case TUESDAY: 	return "T";
			case WEDNESDAY: return "W";
			case THURSDAY: 	return "Th";
			case FRIDAY: 	return "F";
		}
		return null;
	}
	
	public String toString()	{
		switch (this)	{
			case MONDAY: 	return "Monday";
			case TUESDAY: 	return "Tuesday";
			case WEDNESDAY: return "Wednesday";
			case THURSDAY: 	return "Thursday";
			case FRIDAY: 	return "Friday";
		}
		return null;
	}
	
	public int value()	{
		switch (this)	{
			case MONDAY:	return 1;
			case TUESDAY:	return 2;
			case WEDNESDAY:	return 3;
			case THURSDAY:	return 4;
			case FRIDAY:	return 5;
			default:		return 0;
		}
	}
}
