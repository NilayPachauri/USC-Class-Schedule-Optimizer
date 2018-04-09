import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class ClassOptimizerIO {
	
	
	public static List<CourseOption> load(InputStream in) {
		Scanner input = new Scanner(in);
		List<CourseOption> courses = new ArrayList<CourseOption>();
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			
			String[] cs = line.split(",");
			
			String catalogName = cs[0];
			String courseName = cs[1];
			int credits = Integer.parseInt(cs[2]);
			
			CourseOption c = new CourseOption(catalogName, courseName, credits);
			
			while (!(line = input.nextLine()).equals("-")) {
				String[] ss = line.split(",");
				
				int sectionNumber = Integer.parseInt(ss[0]);
				SectionType type = SectionType.fromString(ss[1]);
				Set<Weekday> days = weekdaysFromShortNames(ss[2]);
				Time startTime = Time.fromString(ss[3]);
				int duration = Integer.parseInt(ss[4]);
				
				c.add(new Section(sectionNumber, type, days, startTime, duration));
			}
			
			courses.add(c);
		}
		
		return courses;
	}
	
	public static boolean save(String folder, List<Schedule> scheduleList, Comparator<Schedule> intensityComparator,
			Comparator<Schedule> startTimeComparator, Comparator<Schedule> endTimeComparator) {
		scheduleList.sort(intensityComparator);
		scheduleList.sort(startTimeComparator);
		scheduleList.sort(endTimeComparator);
		
		File parent = new File(folder,
				"Schedule " + new SimpleDateFormat("yy_MM_dd HH_mm_ss").format(Calendar.getInstance().getTime()));
		parent.mkdir();
		
		int x = 0;
		for (Schedule schedule: scheduleList) {
			try {
				schedule.save(new PrintStream(new File(parent, "courses" + ++x + ".txt")), new CourseTimeComparator());
			} catch (Exception ioe) {
				JOptionPane.showMessageDialog(null, "An error occurred while saving your course schedule: \n" + ioe,
						"Input/output error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Converts a string of short day names into a set of Weekday values. For
	 * example, turns "MWF" into the set [MONDAY, WEDNESDAY, FRIDAY].
	 * 
	 * @param shortNames
	 *            string of condensed weekday names, such as "MWF".
	 */
	public static Set<Weekday> weekdaysFromShortNames(String shortNames) {
		try {
			Set<Weekday> days = EnumSet.noneOf(Weekday.class);
			int amount;
			for (int i = 0; i < shortNames.length(); i++) {
				amount = 1;
				if ((i < shortNames.length() - 1) && (shortNames.substring(i, i + 2).equalsIgnoreCase("Th"))) {
					amount = 2;
					i++;
				}
				
				Weekday day = Weekday.fromString(shortNames.substring(i - amount / 2, i + 1));
				days.add(day);
			}
			return days;
		} catch (IllegalArgumentException e) {
			throw new InputMismatchException(shortNames);
		}
	}
	
}
