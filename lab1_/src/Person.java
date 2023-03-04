import java.util.Comparator;
public abstract class Person implements Comparable<Person>{
    protected String firstName;
    protected int age;
    protected String sex;
    public  Person(){
        this.firstName = "No Data";
        this.age = 0;
        this.sex = "No Data";
    }
    public Person(String firstName, int age, String sex) {
        this.firstName = firstName;
        this.age = age;
        this.sex = sex;
    }
    public Person(Person other) {
        this.firstName = other.firstName;
        this.age = other.age;
        this.sex = other.sex;
    }
    public String getFirstName() { return firstName; }
    public int getAge() { return age; }
    public String getSex() { return sex; }

    @Override
    public int compareTo(Person other) {
        return this.getFirstName().compareToIgnoreCase(other.getFirstName());
    }
}