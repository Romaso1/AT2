public class Lab2 {
    public Lab2() {
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        System.out.println("Елементи масиву:");

        for(int i = 0; i < numbers.length; ++i) {
            System.out.println(numbers[i]);
        }

        int sum = 0;

        for(int i = 0; i < numbers.length; ++i) {
            sum += numbers[i];
        }

        System.out.println("Сума елементів масиву: " + sum);
    }
}