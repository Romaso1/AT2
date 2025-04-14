import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Отримання першого масиву від користувача
        System.out.print("Введіть розмір першого масиву: ");
        int size1 = scanner.nextInt();
        int[] array1 = new int[size1];
        System.out.println("Введіть елементи першого масиву:");
        for (int i = 0; i < size1; i++) {
            System.out.print("Елемент " + (i + 1) + ": ");
            array1[i] = scanner.nextInt();
        }

        // Отримання другого масиву від користувача
        System.out.print("Введіть розмір другого масиву: ");
        int size2 = scanner.nextInt();
        int[] array2 = new int[size2];
        System.out.println("Введіть елементи другого масиву:");
        for (int i = 0; i < size2; i++) {
            System.out.print("Елемент " + (i + 1) + ": ");
            array2[i] = scanner.nextInt();
        }

        // Знаходження перетину двох масивів
        // Поміщаємо перший масив у HashSet для зручного пошуку
        Set<Integer> set1 = new HashSet<>();
        for (int num : array1) {
            set1.add(num);
        }

        // Проходимо по другому масиву та шукаємо спільні елементи
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : array2) {
            if (set1.contains(num)) {
                intersectionSet.add(num);
            }
        }

        if (intersectionSet.isEmpty()) {
            System.out.println("Немає спільних елементів між масивами.");
        } else {
            System.out.println("\nСпільні елементи:");
            for (int num : intersectionSet) {
                System.out.println(num);
            }
        }

        // Створення HashMap з результатів перетину
        // Кожен ключ – це спільний елемент, а значення – приклад (елемент * 10)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : intersectionSet) {
            map.put(num, num * 10);
        }

        // a) Додавання нової пари ключ-значення до мапи
        System.out.println("\nОперація: Додавання нової пари ключ-значення");
        System.out.print("Введіть новий ключ (ціле число): ");
        int newKey = scanner.nextInt();
        System.out.print("Введіть значення для ключа " + newKey + ": ");
        int newValue = scanner.nextInt();
        map.put(newKey, newValue);
        System.out.println("HashMap після додавання: " + map);

        // b) Видалення пари ключ-значення з мапи
        System.out.println("\nОперація: Видалення пари ключ-значення");
        System.out.print("Введіть ключ для видалення: ");
        int keyToRemove = scanner.nextInt();
        if (map.containsKey(keyToRemove)) {
            map.remove(keyToRemove);
            System.out.println("Ключ " + keyToRemove + " видалено.");
        } else {
            System.out.println("Ключ " + keyToRemove + " не знайдено у мапі.");
        }
        System.out.println("HashMap після видалення: " + map);

        // c) Перевірка наявності ключа в мапі
        System.out.println("\nОперація: Перевірка наявності ключа в мапі");
        System.out.print("Введіть ключ для перевірки: ");
        int keyToCheck = scanner.nextInt();
        if (map.containsKey(keyToCheck)) {
            System.out.println("Ключ " + keyToCheck + " присутній у мапі.");
        } else {
            System.out.println("Ключ " + keyToCheck + " відсутній у мапі.");
        }

        // d) Знаходження значення за заданим ключем
        System.out.println("\nОперація: Знаходження значення за заданим ключем");
        System.out.print("Введіть ключ для пошуку значення: ");
        int keyToFind = scanner.nextInt();
        if (map.containsKey(keyToFind)) {
            int value = map.get(keyToFind);
            System.out.println("Значення для ключа " + keyToFind + " = " + value);
        } else {
            System.out.println("Ключ " + keyToFind + " не знайдено у мапі.");
        }

        // e) Виведення всіх ключів та значень мапи
        System.out.println("\nОперація: Виведення всіх ключів та значень мапи");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Ключ: " + entry.getKey() + ", Значення: " + entry.getValue());
        }

        // Симуляція ситуації для NumberFormatException
        System.out.println("\nСимуляція NumberFormatException");
        System.out.print("Введіть рядок, який потрібно перетворити на число: ");
        String input = scanner.next();

        try {
            // Намірено намагаємося перетворити рядок на число
            int parsedNumber = Integer.parseInt(input);
            System.out.println("Перетворене число: " + parsedNumber);
        } catch (NumberFormatException e) {
            // Перехоплення виключення та виведення пояснення
            System.out.println("Помилка: Не вдалося перетворити введений рядок '" + input + "' на число.");
            System.out.println("Будь ласка, переконайтеся, що ви вводите дійсне ціле число.");
        }

        scanner.close();
    }
}
