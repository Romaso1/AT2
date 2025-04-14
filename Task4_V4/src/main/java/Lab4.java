import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Lab4 {

    // a) Демонстрація використання wait/notify
    public static void runThreadWaitNotifyDemo() {
        System.out.println("----- Демонстрація wait/notify -----");
        final Object monitor = new Object();

        // Потік, що очікує сигнал (waiting thread)
        Thread waitingThread = new Thread(new Runnable() {
            public void run() {
                synchronized (monitor) {
                    try {
                        System.out.println("Waiting thread: Очікую сигналу...");
                        monitor.wait();  // Потік блокується до отримання сигналу
                        System.out.println("Waiting thread: Отримано сигнал, продовжую роботу.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Потік, що надсилає сигнал (signaling thread)
        Thread signalingThread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Затримка для демонстрації очікування
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (monitor) {
                    System.out.println("Signaling thread: Відправляю сигнал.");
                    monitor.notify();  // Пробудження очікуючого потоку
                }
            }
        });

        waitingThread.start();
        signalingThread.start();

        try {
            waitingThread.join();
            signalingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // b) Демонстрація роботи з рефлексією
    public static void runReflectionDemo() {
        System.out.println("\n----- Демонстрація рефлексії -----");
        // Створення об'єкта CustomData із первинними даними
        CustomData data = new CustomData("Alice", 30);
        System.out.println("До зміни: " + data);

        // За допомогою getDeclaredFields() отримуємо всі поля класу
        Field[] fields = data.getClass().getDeclaredFields();
        System.out.println("Оголошені поля:");
        for (Field field : fields) {
            System.out.println(" - " + field.getName() + " (" + field.getType() + ")");
        }

        // Знаходимо поле "name" і змінюємо його значення
        try {
            Field fieldToModify = data.getClass().getDeclaredField("name");
            fieldToModify.setAccessible(true);  // Дозволяємо доступ до приватного поля
            fieldToModify.set(data, "Bob");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Після зміни: " + data);
    }

    // c) Демонстрація використання генералізованого класу
    public static void runGenericClassDemo() {
        System.out.println("\n----- Демонстрація генералізованого класу -----");

        // Створення об'єкта GenericList для рядкових значень
        GenericList<String> stringList = new GenericList<>();
        stringList.add("Hello");
        stringList.add("World");
        stringList.printElements();

        // Також продемонструємо GenericList для числових значень
        GenericList<Integer> integerList = new GenericList<>();
        integerList.add(100);
        integerList.add(200);
        integerList.printElements();
    }

    public static void main(String[] args) {
        runThreadWaitNotifyDemo();
        runReflectionDemo();
        runGenericClassDemo();
    }
}

// Користувацький клас для демонстрації рефлексії
class CustomData {
    private String name;
    private int age;

    public CustomData(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "CustomData{name='" + name + "', age=" + age + "}";
    }
}

// Генеричний клас для зберігання елементів будь-якого типу
class GenericList<T> {
    private List<T> list;

    public GenericList() {
        list = new ArrayList<>();
    }

    // Метод для додавання елементів
    public void add(T element) {
        list.add(element);
    }

    // Метод для виведення всіх елементів списку
    public void printElements() {
        System.out.println("Елементи GenericList: " + list);
    }
}
