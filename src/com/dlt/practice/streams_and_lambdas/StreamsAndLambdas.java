package com.dlt.practice.streams_and_lambdas;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class StreamsAndLambdas {

    // Return the highest grade for the students with a specific major
    static int getHighestGradeForMajor(List<Student> students, String major) {

        // int answer = students.stream()
        //         .filter(student -> major.equals(student.getMajor()))
        //         .flatMap(student -> student.getGrades().values().stream())
        //         .max(Integer::compareTo)
        //         .orElseGet(() -> 0);

        Comparator<Integer> c = (o1, o2) -> o1 - o2;
        Predicate<Student> hasMajor = student -> student.getMajor().equals(major);
        Function<Student, Integer> getMaxStudentGrade = s -> s.getGrades().values().stream().max(c).get();
        int answer = students.stream().filter(hasMajor)
                .map(getMaxStudentGrade).max(c).get();
        return answer;
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Amine", "Ousmane", "Computer Science",
                        Map.of("Algorithms", 90, "Data Structures", 80, "Calculus", 85)),
                new Student("Lily-Ann", "Smith", "Mathematics",
                        Map.of("Algorithms", 80, "Data Structures", 75, "Calculus", 88)),
                new Student("Li", "Wei", "Computer Science",
                        Map.of("Algorithms", 92, "Data Structures", 89, "Calculus", 88)),
                new Student("Jessica", "Rodriguez", "Mathematics",
                        Map.of("Algorithms", 85, "Data Structures", 80, "Calculus", 89)));
        String major = "Computer Science";
        int result = StreamsAndLambdas.getHighestGradeForMajor(students, major);
        System.out.println(result); // Should be 92
    }

}

class Student {
    private String firstname;
    private String lastname;
    private String major;
    private Map<String, Integer> grades;

    public Student(String firstname, String lastname, String major, Map<String, Integer> grades) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.major = major;
        this.grades = grades;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMajor() {
        return major;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}
