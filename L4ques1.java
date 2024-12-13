import java.io.*;
import java.util.*;

class Person {
    int id;
    String name;
    int age;
    int height;
    int weight;

    Person(int id, String name, int age, int height, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}

public class L4ques1 {
    private static List<Person> persons = new ArrayList<>();
    private static PriorityQueue<Person> minHeap;
    private static PriorityQueue<Person> maxHeap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("/nMAIN MENU (HEAP)");
            System.out.println("1. Read Data");
            System.out.println("2. Create a Min-heap based on the age");
            System.out.println("3. Create a Max-heap based on the weight");
            System.out.println("4. Display weight of the youngest person");
            System.out.println("5. Insert a new person into the Min-heap");
            System.out.println("6. Delete the oldest person");
            System.out.println("7. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    readData();
                    break;
                case 2:
                    createMinHeap();
                    break;
                case 3:
                    createMaxHeap();
                    break;
                case 4:
                    displayWeightOfYoungest();
                    break;
                case 5:
                    insertNewPerson(scanner);
                    break;
                case 6:
                    deleteOldestPerson();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data1.txt"))) {
            persons.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1] + " " + parts[2];
                int age = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                int weight = Integer.parseInt(parts[5]);
                persons.add(new Person(id, name, age, height, weight));
            }
            System.out.println("Data read successfully.");
            displayPersons();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void displayPersons() {
        System.out.println("Id\tName\t\t\tAge\tHeight\tWeight");
        for (Person p : persons) {
            System.out.printf("%d\t%s\t\t%d\t%d\t%d%n", p.id, p.name, p.age, p.height, p.weight);
        }
    }

    private static void createMinHeap() {
        minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.age));
        minHeap.addAll(persons);
        System.out.println("Min-heap created based on age.");
        displayHeap(minHeap);
    }

    private static void createMaxHeap() {
        maxHeap = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.weight, p1.weight));
        maxHeap.addAll(persons);
        System.out.println("Max-heap created based on weight.");
        displayHeap(maxHeap);
    }

    private static void displayHeap(PriorityQueue<Person> heap) {
        PriorityQueue<Person> copy = new PriorityQueue<>(heap);
        while (!copy.isEmpty()) {
            Person p = copy.poll();
            System.out.printf("Id: %d, Name: %s, Age: %d, Height: %d, Weight: %d%n", p.id, p.name, p.age, p.height, p.weight);
        }
    }

    private static void displayWeightOfYoungest() {
        if (minHeap != null && !minHeap.isEmpty()) {
            Person youngest = minHeap.peek();
            System.out.printf("Weight of the youngest person (%s): %.2f kg%n", youngest.name, youngest.weight * 0.453592);
        } else {
            System.out.println("Min-heap is not created or empty.");
        }
    }

    private static void insertNewPerson(Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter height: ");
        int height = scanner.nextInt();
        System.out.print("Enter weight: ");
        int weight = scanner.nextInt();

        Person newPerson = new Person(id, name, age, height, weight);
        persons.add(newPerson);
        if (minHeap != null) {
            minHeap.offer(newPerson);
            System.out.println("New person added to Min-heap.");
            displayHeap(minHeap);
        } else {
            System.out.println("Min-heap is not created.");
        }
    }

    private static void deleteOldestPerson() {
        if (minHeap != null && !minHeap.isEmpty()) {
            Person oldest = null;
            for (Person p : minHeap) {
                if (oldest == null || p.age > oldest.age) {
                    oldest = p;
                }
            }
            if (oldest != null) {
                minHeap.remove(oldest);
                persons.remove(oldest);
                System.out.printf("Oldest person (%s) removed from Min-heap.%n", oldest.name);
                displayHeap(minHeap);
            }
        } else {
            System.out.println("Min-heap is not created or empty.");
        }
    }
}