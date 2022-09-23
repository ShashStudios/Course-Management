package Homework1;

/**
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class Course implements Cloneable{
    private String name;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    public Course(){
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }


    public int getCode() {
        return code;
    }

    public byte getSection() {
        return section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCode(int code) throws IllegalArgumentException{
        if(code < 0){
            throw new IllegalArgumentException();
        }
        this.code = code;
    }

    public void setSection(byte section) throws IllegalArgumentException{
        if(section < 0 || section>127){
            throw new IllegalArgumentException();
        }
        this.section = section;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /** Returns an exact copy of the course object
     *
     * @return
     *      Returns an exact copy of the course object
     * @throws CloneNotSupportedException
     *      Indicates that class that has called the clone method not implemented Cloneable interface
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Course)super.clone();
        /*Course c = new Course();
        c.setName(this.getName());
        c.setDepartment(this.getDepartment());
        c.setCode(this.getCode());
        c.setSection(this.getSection());
        c.setInstructor(this.getInstructor());
        return c;*/
    }

    /** Returns a boolean value indicating if two course objects have a same values
     *
     * @param obj
     *      The course object that is to be compared to the calling object for
     *      same values
     * @return
     *      Returns a boolean value indicating if two course objects have a same values.
     *      Returns true if all values match and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Course c;
        if(obj instanceof Course){
            c = (Course)obj;
        } else {
            return false;
        }
        return (name.equals(c.name) && department.equals(c.department) && code == c.code && section == this.section && instructor.equals(c.instructor));
    }

    /** Returns a properly formatted string representation of a course object
     *
     * @return
     *      Returns a properly formatted string representation of a course object
     */
    public String toString(){
        String s = String.format("%-26s%-12s%-12s%-15s%-24s", this.getName(), this.getDepartment(), this.getCode(),
                this.getSection(), this.getInstructor());
        return s;
    }



}
