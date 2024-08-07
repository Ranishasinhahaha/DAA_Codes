import java.io.*;
import java.util.Scanner;

public class L3ques2 {
    private static int comparisonCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("MAIN MENU (QUICK SORT)");
            System.out.println("1. Ascending Data");
            System.out.println("2. Descending Data");
            System.out.println("3. Random Data");
            System.out.println("4. ERROR (EXIT)");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    sortAndSave("inAsce.dat", "outQuickAsce.dat");
                    break;
                case 2:
                    sortAndSave("inDesc.dat", "outQuickDesc.dat");
                    break;
                case 3:
                    sortAndSave("inRand.dat", "outQuickRand.dat");
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
            quickSort(data, 0, data.length - 1);

            System.out.println("After Sorting: ");
            printArray(data);

            writeDataToFile(data, outputFileName);
            System.out.println("Number of Comparisons: " + comparisonCount);

            String scenario = (comparisonCount == data.length * (data.length - 1) / 2) ? "Worst-case" : "Best-case";
            System.out.println("Scenario: " + scenario + "\n");

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

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisonCount++;
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}