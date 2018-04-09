

public class Time implements Cloneable, Comparable<Time>{
	
	private int hour, minute;
	private boolean PM;
	
	public Time (int hour, int minute, boolean PM)	{
		this.hour = hour;
		this.minute = minute;
		this.PM = PM;
	}
	
	public static Time fromString(String str)	{
		int hour = Integer.parseInt(str.substring(0, str.indexOf(':')));
		int minute = Integer.parseInt(str.substring(str.indexOf(':') + 1, str.indexOf(' ')));
		boolean PM = str.substring(str.indexOf(' ') + 1).equals("PM");
		
		return new Time(hour, minute, PM);
	}
	
	public Time clone()	{
		return new Time(this.hour, this.minute, this.PM);
	}
	
	public int compareTo(Time time)	{
		int hour = (this.hour % 12) - (time.hour % 12);
		int minute = this.minute - time.minute;
		
		if (this.PM != time.PM)	{
			if (!this.PM && time.PM)
				hour -= 12;
			else
				hour += 12;
		}
		
		minute += hour * 60;
		
		return minute;
	}
	
	public boolean equals(Time t)	{
		return (this.hour == t.hour) && (this.minute == t.minute) && (this.PM == t.PM);
	}
	
	public int getHour()	{
		return this.hour;
	}
	
	public int getMinute()	{
		return this.minute;
	}
	
	public boolean isPM()	{
		return this.PM;
	}
	
	public boolean shift(int minutes)	{
		if (minutes < 0)
			throw new IllegalArgumentException("Illegal Number of Minutes: " + minutes);
		
		int hours = minutes / 60;
		minutes %= 60;
		
		this.minute += minutes;
		
		hours += this.minute / 60;
		this.minute %= 60;
		
		this.hour += hours;
		
		if ((this.hour - hours < 12) && (this.hour >= 12))
			for (int i = this.hour / 12; i > 0; i--)
				this.PM = !this.PM;
		
		this.hour %= 12;
		
		if (this.hour == 0)
			this.hour += 12;
		
		return true;	
	}
	
	public String toString()	{
		return String.format("%1$02d:%02$02d %3$s", this.hour, this.minute, (this.PM) ? "PM" : "AM");
	}
	
}
