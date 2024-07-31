import java.util.Scanner;

public class L1ques4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of elements to be rotated: ");
        int p2 = scanner.nextInt();

        System.out.print("Before ROTATE: ");
        printArray(arr);

        rotateRight(arr, p2);

        System.out.print("After ROTATE: ");
        printArray(arr);
        scanner.close();
    }

    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void rotateRight(int[] arr, int p2) {
        int last = arr[p2 - 1];
        for (int i = p2 - 1; i > 0; i--) {
            exchange(arr, i, i - 1);
        }
        arr[0] = last;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}