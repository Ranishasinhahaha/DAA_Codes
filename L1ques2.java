import java.util.Scanner;

public class L1ques2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        System.out.print("Prefix sum array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(prefixSum[i] + " ");
        }
        System.out.println();
        scanner.close();
    }
}