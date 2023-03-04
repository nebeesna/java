import java.util.*;
//Варіант № 3
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //task1();
        //task2();
        task3();
    }
    static  void task1(){
//1. Дано прямокутну матрицю розмірності  .
// Утворити вектор, кожен елемент якого дорівнює мінімальному із елементів,
// які є числами Фібоначі, відповідного стовпця матриці.
        System.out.println("Enter matrix size");
        System.out.print("Enter m: ");
        int m = input.nextInt();
        System.out.print("Enter n: ");
        int n = input.nextInt();

        Matrix matrix = new Matrix(m,n);
        System.out.print(matrix);

        List<Integer> vector = new ArrayList<>();
        List<Integer> fibonacci_nums = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        int currentNum = 0;
        for(int i=0; i<matrix.getN(); i++) {
            for (int j = 0; j < matrix.getM(); j++) {
                currentNum = matrix.getArr()[j][i];
                tempList = get_fibonacci(currentNum);
                if(currentNum==tempList.get(tempList.size()-1)){
                    fibonacci_nums.add(currentNum);
                }
                tempList.clear();
            }
            if(fibonacci_nums.isEmpty()){
                vector.add(-1);
            } else {
                vector.add(Collections.min(fibonacci_nums));
            }
            fibonacci_nums.clear();
            tempList.clear();
        }
        System.out.print(vector);

    }
    static void task2(){
//2. Дано послідовність слів, розділених комами.
// Видрукувати слова, попередньо перетворивши кожне із них за правилом:
// замінити кожну літеру 'g' послідовність літер 'th'.
        System.out.println("Enter words:");
        String words = input.nextLine();
        System.out.println("Result:");
        String[] res = stringChanger(words, "g", "th");
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
    static void task3(){
//3. Створити базовий клас людина (ім’я, вік, стать) та
// похідні класи викладач (посада, стаж) і студент (факультет, середня успішність).
//Дано масив посилань на об’єкти людей.
//+	Посортувати його за іменем.
//+	Знайти викладача з мінімальним стажем.
//+	Вивести студентів з максимальною успішністю.
        Person[] people = {
                new Student("Vovk Mykhajlo", 18, "Male", "History", 92.3),
                new Student("Datsiuk Olelsij", 17, "Male", "Math", 88),
                new Student("Bobyliak Ihor", 20, "Male", "Economy", 75.9),
                new Student("Vengryn Sergij", 23, "Male", "Math", 99.9),
                new Student("Vovchuk Maryna", 18, "Female", "History", 92.3),
                new Student("Belia Viktoria", 17, "Female", "Economy", 90.4),
                new Teacher("Priadko Ohla", 39, "Female", "Assistant", 12),
                new Teacher("Tarasiuk Sviatoslav", 60, "Male", "Lector", 32),
                new Teacher("Kozij Iryna", 37, "Female", "Assistant", 8),
                new Teacher("Horlatch Vitalij", 45, "Male", "Lector", 20),
        };
        Arrays.sort(people);
        System.out.println("\nSorted by name:");
        for(var person:people){
            System.out.println(person);
        }
        int countOfTeachers = 0;
        for(int i=0; i<people.length; i++){
            if (people[i].getClass().getName() == "Teacher"){
                countOfTeachers++;
            }
        }

        Teacher[] teachers = new Teacher[countOfTeachers];
        Student[] students = new Student[people.length-countOfTeachers];
        int teacher = 0, student = 0;
        for(var person:people){
            if (person.getClass().getName() == "Teacher"){
                teachers[teacher] = (Teacher) person;
                teacher++;
            } else if (person.getClass().getName() == "Student"){
                students[student] = (Student) person;
                student++;
            }
            else System.out.println("error");
        }
        System.out.println("\nTeachers:");
        for(var person:teachers){
            System.out.println(person);
        }
        int minExperience = Integer.MAX_VALUE, index = 0;
        for(int i=0; i<teachers.length; i++){
            if(teachers[i].getExperience()<minExperience){
                minExperience = teachers[i].getExperience();
                index = i;
            }
        }
        System.out.println("\nTeacher with min experience:");
        System.out.println(teachers[index]);

        System.out.println("\nStudents:");
        for(var person:students){
            System.out.println(person);
        }
        System.out.println("\nStudents with highest rate:");
        for(int i=0; i<students.length; i++){
            if(students[i].getRate()>90){
                System.out.println(students[i]);
            }
        }
    }
    static List<Integer> get_fibonacci(int num){
        List<Integer> fibonacci_list = new ArrayList<>();
        int firstNum=0, second_num=0;
        if(num==1){
            fibonacci_list.add(0);
            return fibonacci_list;
        } else{
            fibonacci_list.add(0);
            fibonacci_list.add(1);
            while((firstNum+second_num)+firstNum<=num){
                firstNum = fibonacci_list.get(fibonacci_list.size() - 1);
                second_num = fibonacci_list.get(fibonacci_list.size() - 2);
                fibonacci_list.add(firstNum+second_num);
            }
            return fibonacci_list;
        }
    }
    static String[] stringChanger(String givenStr,String first, String second){
        String[] words = givenStr.split(",");
        String[] res = new String[words.length];
        for(int i = 0; i < words.length; i++){
            res[i] = words[i].strip().replace(first, second);
        }
        return res;
    }
}