public class Main {
    /*
    * Створити застосування з графічним інтерфейсом для демонстрації роботи декількох потоків. Створити три потоки.
1.	Перший з потоків виконує анімацію графічного зображення (самостійно створити складне зображення або повільно відмальовувіти графік функції з другого завдання);
2.	Другий потік виконує складні обчислення і виводить покроково результати;
3.	Третій потік відображає плаваючий текст (біжучий рядок).
Використати блокувальники та об’єкти умов для демонстрації взаємодії двох потоків.
Передбачити засоби для зміни параметрів потоків (наприклад зміна пріоритету, задання часу блокування потоку), а також для запуску, призупинення кожного потоку і т.п.
*/
    public static void main(String[] args) {
          Tasks.Task1 task1 = new Tasks.Task1();
          Tasks.Task2 task2 = new Tasks.Task2();
          Tasks.Task3 task3 = new Tasks.Task3();
          Thread first = new Thread(task1);
          Thread second = new Thread(task2);
          Thread third = new Thread(task3);
          first.start();
          second.start();
          third.start();
    }
}