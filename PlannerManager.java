package Homework1;

import java.util.Scanner;

/**
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/
public class PlannerManager {
    static Planner planner;
    static Planner plannerCopy;

    static{
        planner = new Planner();
    }

    public static void main(String[] args){
        //System.out.printf("%-15s%-20s%17s%06d", "No.", "Course Name", "Department", "Code", "Section", "Instructor");
        boolean isDone = false;
        System.out.println("Key:");
        System.out.println("(A) Add Course\n" +
                "(G) Get Course\n" +
                "(R) Remove Course\n" +
                "(P) Print Courses in Planner\n" +
                "(F) Filter by Department Code\n" +
                "(L) Look For Course\n" +
                "(S) Size\n" +
                "(B) Backup\n" +
                "(PB) Print Courses in Backup\n" +
                "(RB) Revert to Backup\n" +
                "(Q) Quit");


        while(!isDone){
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter a selection:");
            String selection = (sc.next()).toUpperCase();
            switch(selection){
                case("A"):
                    addCourse();
                    break;
                case("G"):
                    getCourse();
                    break;
                case("R"):
                    removeCourse();
                    break;
                case("P"):
                    System.out.println("Planner:");
                    planner.printAllCourses();
                    break;
                case("F"):
                    System.out.print("Enter department:");
                    String department = sc.next();
                    Planner.filter(planner, department);
                    break;
                case("L"):
                    existsCourse();
                    break;
                case("S"):
                    System.out.println("There are "+planner.size()+" courses in the planner");
                    break;
                case("B"):
                    clonePlanner();
                    break;
                case("PB"):
                    System.out.println("Planner(Backup):");
                    plannerCopy.printAllCourses();
                    break;
                case("RB"):
                    planner = plannerCopy;
                    System.out.println("Planner successfully reverted to the backup copy.");
                    break;
                case("Q"):
                    isDone = true;
                    System.out.println("Program terminated successfully...");
                    break;
                default:
                    System.out.println("Not a valid selection");
                    break;
            }
        }

    }


    private static void clonePlanner(){

        try {
            plannerCopy = (Planner) planner.clone();
            System.out.println("Created a backup of the current planner");
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getCourse() {
        Scanner sc = new Scanner(System.in);
        int position = 0;
        try {
            System.out.print("Enter position:");
            position = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input\nInput Format:\nName:ABC\nDepartment:ABC\nCode:123\nSection:123\nInstructor:ABC");
        }
        Course course = null;
        try {
            course = planner.getCourse(position);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("invalid Input");
        }

    }

    private static void removeCourse() {
        Scanner sc = new Scanner(System.in);
        int position = 0;
        try {
            System.out.print("Enter position:");
            position = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input\nInput Format:\nName:ABC\nDepartment:ABC\nCode:123\nSection:123\nInstructor:ABC");
        }
        try {
            planner.removeCourse(position);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Input");
        }
    }

    private static void addCourse(){
        Course course = null;
        int position = 0;
        try {
            Scanner sc = new Scanner(System.in);
            course = new Course();
            System.out.print("Enter course name:");
            course.setName(sc.nextLine());

            System.out.print("Enter department:");
            String department = sc.nextLine();
            course.setDepartment(department);


            System.out.print("Enter course code:");
            int courseCode = Integer.parseInt(sc.nextLine());
            course.setCode(courseCode);

            System.out.print("Enter course section:");
            Byte courseSection = Byte.parseByte(sc.nextLine());
            course.setSection(courseSection);

            System.out.print("Enter instructor:");
            course.setInstructor(sc.nextLine());

            System.out.print("Enter position:");
            position = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input\nInput Format:\nName:ABC\nDepartment:ABC\nCode:123\nSection:123\nInstructor:ABC");
            return;
        }
        try {
            planner.addCourse(course, position);
        } catch(Exception e){
            System.out.println("Invalid Input");
        }
    }
    private static void existsCourse(){
        Course course = null;
        try {
            Scanner sc = new Scanner(System.in);
            course = new Course();
            System.out.print("Enter course name:");
            course.setName(sc.nextLine());

            System.out.print("Enter department:");
            String department = sc.nextLine();
            course.setDepartment(department);


            System.out.print("Enter course code:");
            int courseCode = Integer.parseInt(sc.nextLine());
            course.setCode(courseCode);

            System.out.print("Enter course section:");
            Byte courseSection = Byte.parseByte(sc.nextLine());
            course.setSection(courseSection);

            System.out.print("Enter instructor:");
            course.setInstructor(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input\nInput Format:\nName:ABC\nDepartment:ABC\nCode:123\nSection:123\nInstructor:ABC");
            return;
        }
        try {
            planner.exists(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
