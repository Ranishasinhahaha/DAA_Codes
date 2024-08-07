import java.io.*;
import java.util.Scanner;

public class MergeSortProgram {
    private static int comparisonCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("MAIN MENU (MERGE SORT)");
            System.out.println("1. Ascending Data");
            System.out.println("2. Descending Data");
            System.out.println("3. Random Data");
            System.out.println("4. ERROR (EXIT)");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    sortAndSave("inAsce.dat", "outMergeAsce.dat");
                    break;
                case 2:
                    sortAndSave("inDesc.dat", "outMergeDesc.dat");
                    break;
                case 3:
                    sortAndSave("inRand.dat", "outMergeRand.dat");
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void sortAndSave(String inputFileName, String outputFileName) {
        try {
            int[] data = readDataFromFile(inputFileName);
            System.out.println("Before Sorting: ");
            printArray(data);

            comparisonCount = 0;
            long startTime = System.nanoTime();
            mergeSort(data, 0, data.length - 1);
            long endTime = System.nanoTime();

            System.out.println("After Sorting: ");
            printArray(data);

            writeDataToFile(data, outputFileName);
            System.out.println("Number of Comparisons: " + comparisonCount);
            System.out.println("Execution Time: " + (endTime - startTime) + " nanoseconds\n");

        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    private static int[] readDataFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String[] numbers = reader.readLine().split("\\s+");
        int[] data = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            data[i] = Integer.parseInt(numbers[i]);
        }
        reader.close();
        return data;
    }

    private static void writeDataToFile(int[] data, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int number : data) {
            writer.write(number + " ");
        }
        writer.newLine();
        writer.close();
    }

    private static void printArray(int[] array) {
        for (int number : array) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            comparisonCount++;
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}