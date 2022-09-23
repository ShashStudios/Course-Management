package Homework1;

/**
 * The <code>Planner</code> class implements an array of <code>Course</code>
 * objects.
 *
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/
public class Planner implements Cloneable{
    public static final int MAX_COURSES = 50;  //Total number of courses the array can hold

    private int arraySize;  //Number of course objects currently in the array
    private Course[] courses = null; //An array holding course objects

    public Planner(){
        courses = new Course[MAX_COURSES];
        arraySize = 0;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    /** Returns the current number of items in the array
     *
     * @return
     *      Returns the number of items in the array currently
     * <dt>Precondition:
     *    <dd>Planner has been instantiated
     */
    public int size(){
        return arraySize;
    }

    /** Adds the input course into the courses array of the planner object
     *  at the given position
     *
     * @param newCourse
     *      The object that is to be added tp the courses array
     * @param position
     *      The position in the courses array where the course
     *      object is to be added
     * <dt>Precondition:
     *     <dd>This Course object has been instantiated and 1 ≤ <code>position</code> ≤ items_currently_in_list + 1.
     *     The number of Course objects in this Planner is less than MAX_COURSES.
     * <dt>Postcondition:
     *     <dd>The new Course is now listed in the correct preference on the list.
     *     All Courses that were originally greater than or equal to position are moved back one position.
     * @throws IllegalArgumentException
     *      Indicates that input position is not within a valid range in the array
     * @throws FullPlannerException
     *      Indicates that the array is full and no more elements can be added
     */
    public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException {

        if(arraySize == MAX_COURSES){
            throw new FullPlannerException();
        }
        if(position < 1 || position>arraySize+1){
            throw new IllegalArgumentException();
        }

        int indexToBeInserted = position-1;
        for(int index = arraySize; index>indexToBeInserted; index--){
            courses[index]= courses[index-1];
            courses[index-1] = null;
        }
        courses[indexToBeInserted] = newCourse;
        arraySize++;
        double sectionRepresentation = ((double)newCourse.getSection()/100.0)+(double)newCourse.getCode();
        System.out.println(newCourse.getDepartment()+" "+sectionRepresentation+" successfully added to planner");
    }

    /** Adds a course object to the end of the courses array
     *
     * @param newCourse
     *      The object that is to be added to the courses array
     */
    public void addCourse(Course newCourse){
        addCourse(newCourse, size()+1);
    }

    /** Removes a course object from the courses array at the
     *  given position
     *
     * @param position
     *      The position in the courses array from where the object
     *      is to be removed
     * <dt>Precondition:
     *      <dd>This Planner has been instantiated and 1 ≤ position ≤ items_currently_in_list.
     * <dt>Postcondition:
     *      <dd>The Course at the desired position has been removed.
     *      All Courses that were originally greater than or equal to
     *      position are moved backward one position.
     * @throws IllegalArgumentException
     *      Indicates that input position is not within a valid range in the array
     */
    public void removeCourse(int position) throws IllegalArgumentException{

        if(position > arraySize || position < 1){
            throw new IllegalArgumentException();
        }
        int indexToBeRemoved = position-1;
        Course removedCourse = courses[indexToBeRemoved];
        courses[indexToBeRemoved] = null;
        for(int index = indexToBeRemoved; index<arraySize; index++){
            courses[index] = courses[index+1];
            courses[index+1] = null;
        }
        arraySize--;

        double sectionRepresentation = ((double)removedCourse.getSection()/100.0)+(double)removedCourse.getCode();

        System.out.println(removedCourse.getDepartment()+" "+sectionRepresentation+ " successfully removed from planner");

    }

    /** Returns and prints in a string format, the course object given its position in the courses array
     *
     * @param position
     *      The position in the courses array from where the object is
     *      to be removed
     * <dt>Precondition:
     *      <dd>This Planner has been instantiated and 1 ≤ position ≤ items_currently_in_list.
     * @return
     *      Returns a course object through its position in the array.
     * @throws IllegalArgumentException
     *      Indicates that input position is not within a valid range in the array
     */
    public Course getCourse(int position) throws IllegalArgumentException{

        if(position< 1 || position > arraySize){
            throw new IllegalArgumentException();
        }
        System.out.println(String.format("%-4s%-26s%-12s%-12s%-15s%-24s", "No.", "Course Name", "Department", "Code",
                "Section", "Instructor"));
        System.out.println("=====================================================================================");
        Course c= courses[position-1];
        System.out.println((position)+"   "+c.toString());
        return c;
    }

    /**  Filters the courses array by department,
     *   and prints in a string format, each course objects in that department
     *
     * @param planner
     *      The planner object which has a courses array being filtered
     * @param department
     *      The department used to filter the courses array
     * <dt>Precondition:
     *      <dd>Planner has been instantiated
     * <dt>Postcondition:
     *      <dd>Displays a neatly formatted table of each course
     *      filtered from the Planner.Keep the preference numbers the same.
     *
     */
    public static void filter(Planner planner, String department){
        System.out.println(String.format("%-4s%-26s%-12s%-12s%-15s%-24s", "No.", "Course Name", "Department", "Code",
                "Section", "Instructor"));
        System.out.println("=====================================================================================");
        for(int i = 0; i<planner.arraySize; i++){
            Course temp = planner.courses[i];
            if(temp.getDepartment().equals(department)){
                System.out.println((i+1)+"   "+temp.toString());
            }

        }
    }

    /** Returns a boolean indicating if the passed Course object exists in the array
     *
     * @param course
     * <dt>Precondition:
     *     <dd>This Planner and Course has both been instantiated.
     * @return
     *      Returns a boolean indicating if a Course object exists in the array.
     *      If true s returned then course object exists and if false is returned
     *      the course object does not exist in the array.
     */
    public boolean exists(Course course){
        boolean found= false;
        int position = 0;
        Course c= null;
        for(int i = 0; i<arraySize; i++){
            if(courses[i].equals(course)){
                c = courses[i];
                found = true;
                position = i+1;
            }
        }
        if(found){
            double sectionRepresentation = ((double)c.getSection()/100.0)+(double)c.getCode();
            System.out.println(c.getDepartment()+" "+sectionRepresentation+" is found at position "+position);
        } else{
            System.out.println("Course not found in the planner");
        }
        return found;
    }

    /** Returns an exact copy of the Planner object
     * <dt>Precondition:
     *      <dd>This Planner object has been instantiated.
     * @return
     *      Returns an exact copy of the Planner object
     * @throws CloneNotSupportedException
     *      Indicates that class that has called the clone method not implemented Cloneable interface
     */
    public Object clone() throws CloneNotSupportedException {
        Planner plannerClone = new Planner();
        plannerClone.arraySize = this.arraySize;
        Course[] coursesClone = plannerClone.courses;
        for(int i = 0; i < arraySize; i++){
            coursesClone[i] = (Course)(courses[i]).clone();
        }
        plannerClone.courses = coursesClone;
        return plannerClone;
    }

    /** Prints all the courses in the courses array in a proper format
     *
     * <dt>Precondition:
     *      <dd>This Planner object has been instantiated.
     * <dt>Postcondition:
     *      <dd>Displats a neatly formatted table of each course from the Planner.
     */
    public void printAllCourses(){
        toString();
    }


    /** Returns a string and prints all the courses in the array in an ordered format
     *
     * @return
     *      Returns a string and prints all the courses in the array in an ordered format
     */
    public String toString(){

       System.out.println(String.format("%-4s%-26s%-12s%-12s%-15s%-24s", "No.", "Course Name", "Department", "Code",
               "Section", "Instructor"));
       System.out.println("=====================================================================================");
       String table = null;
       Course eachCourse = null;
       for(int i = 0; i<arraySize; i++){
            eachCourse = courses[i];
            System.out.println((i+1)+"   "+eachCourse.toString());
       }
       return table;
    }






}
