import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class L1ques3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many numbers you want to read from file: ");
        int n = scanner.nextInt();

        try {
            File file = new File("data.txt");
            Scanner fileScanner = new Scanner(file);

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = fileScanner.nextInt();
            }

            fileScanner.close();

            System.out.print("The content of the array: ");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();

            int[] result = countDuplicates(arr);
            System.out.println("Total number of duplicate values = " + result[0]);
            System.out.println("The most repeating element in the array = " + result[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
        }
        scanner.close();
    }

    public static int[] countDuplicates(int[] arr) {
        int[] count = new int[100];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int totalDuplicates = 0;
        int mostRepeating = arr[0];
        int maxCount = 0;
        for (int i = 0; i < 100; i++) {
            if (count[i] > 1) {
                totalDuplicates++;
            }
            if (count[i] > maxCount) {
                maxCount = count[i];
                mostRepeating = i;
            }
        }

        return new int[] {totalDuplicates, mostRepeating};
        
    }
}