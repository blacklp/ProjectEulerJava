package project_euler_java;

import java.util.Calendar;
import java.util.Date;

public class Problem19 {
	private static final Date knownDate;
	static {
		Calendar c = Calendar.getInstance();
		c.set(1900, Calendar.JANUARY, 1, 0, 0, 0); // MONDAY
		knownDate = c.getTime();
	}
	
	public long countingSundays(Calendar start, Calendar end) {
		long result = 0;
		long milliDifference = start.getTime().getTime() - knownDate.getTime();
		int dayDifferenceAddition = (int)(milliDifference % 86400000);
		int weekDay = (Calendar.MONDAY + dayDifferenceAddition) % 7;
		if (weekDay == Calendar.SUNDAY) {
			result++;
		} else {
			int daysToReachSunday = Calendar.SUNDAY - weekDay;
			if (daysToReachSunday < 0) {
				daysToReachSunday += 7;
			}
			start.add(Calendar.DATE, daysToReachSunday);
		}
		while (start.before(end)) {
			if (start.get(Calendar.DAY_OF_MONTH) == 1) {
				result++;
			}
			start.add(Calendar.DATE, 7);
		}
		return result;
	}
	
	public static void main(String[] args) {
		Problem19 o = new Problem19();
		Calendar start = Calendar.getInstance();
		start.set(1901, Calendar.JANUARY, 1, 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.set(2000, Calendar.DECEMBER, 31, 0, 0, 0);
		
		long result = o.countingSundays(start, end);
		System.out.println("result: " + result);
	}
}
