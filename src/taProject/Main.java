package taProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		MysqlConnect mysqlConnect = new MysqlConnect();

		ArrayList<String[]> rankings = mysqlConnect.getDataFromTableRankings();
		// ArrayList<String[]> teachers = mysqlConnect.getDataFromTableTeachers();
		ArrayList<String[]> students = mysqlConnect.getDataFromTableStudents();
		// ArrayList<String[]> courses = mysqlConnect.getDataFromTableGivenCourses();
		ArrayList<String[]> askTas = mysqlConnect.getDataFromTableAskTa();

		HashMap<String, String> matchings = new HashMap<String, String>();
		HashMap<String, Integer> missingCourses = new HashMap<String, Integer>();
		for (String[] ranking : rankings) {
			String searchedStudentId = ranking[1];
			boolean contains = false;
			String[] res = null;

			for (String[] student : students) {
				if (student[0].equals(searchedStudentId)) {
					contains = true;
					res = student;
					break;
				}
			}

			if (contains) {
				matchings.put(ranking[1], ranking[2]);
				students.remove(res);
			} else {
				if (missingCourses.containsKey(ranking[2])) {
					missingCourses.put(ranking[2], missingCourses.get(ranking[2]) + 1);
				} else {
					missingCourses.put(ranking[2], 1);
				}
			}

		}

		for (String[] askTa : askTas) {
			if (!matchings.containsKey(askTa[0])) {
				if (missingCourses.containsKey(askTa[1]) && missingCourses.get(askTa[1]) > 0) {
					matchings.put(askTa[0], askTa[1]);
					missingCourses.put(askTa[1], missingCourses.get(askTa[1]) - 1);
					if (missingCourses.get(askTa[1]) == 0) {
						missingCourses.remove(askTa[1]);
					}

					String[] removed = null;
					for (String[] student : students) {
						if (student[0].equals(askTa[0])) {
							removed = student;
							break;
						}
					}
					students.remove(removed);
				}
			}
		}

		for (String[] student : students) {
			String course = (String) missingCourses.keySet().toArray()[0];
			matchings.put(student[0], course);
			missingCourses.put(course, missingCourses.get(course) - 1);
			if (missingCourses.get(course) == 0) {
				missingCourses.remove(course);
			}
		}

		for (String key : matchings.keySet()) {
			System.out.println("Student whose ID is " + key + " has been assigned to course " + matchings.get(key));
		}

	}

}
