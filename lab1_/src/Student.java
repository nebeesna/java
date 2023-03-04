public class Student extends Person{
    private String faculty;
    private double avgRate;
    public Student(String name, int age, String sex, String faculty, double avgRate){
        super(name, age, sex);
        this.faculty = faculty;
        this.avgRate = avgRate;
    }
    public  String getFaculty(){
        return  this.faculty;
    }
    public double getRate(){
        return  this.avgRate;
    }
    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s", getClass().getName(), firstName,age, sex, faculty, avgRate);
    }
}
