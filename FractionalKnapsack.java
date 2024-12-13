import java.util.Arrays;

// Class to represent an item in the knapsack problem
class Item {
    int itemId;
    double itemProfit;
    double itemWeight;
    double profitWeightRatio;

    Item(int id, double profit, double weight) {
        this.itemId = id;
        this.itemProfit = profit;
        this.itemWeight = weight;
        this.profitWeightRatio = profit / weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemProfit=" + itemProfit +
                ", itemWeight=" + itemWeight +
                ", profitWeightRatio=" + profitWeightRatio +
                '}';
    }
}

public class FractionalKnapsack {
    
    // Function to apply Heap Sort on an array of items based on their profit/weight ratio
    public static void heapSort(Item[] items) {
        int n = items.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(items, n, i);
        }
        // One by one extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Item temp = items[0];
            items[0] = items[i];
            items[i] = temp;
            // call max heapify on the reduced heap
            heapify(items, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in items[]
    static void heapify(Item[] items, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // See if left child exists and is greater than root
        if (left < n && items[left].profitWeightRatio > items[largest].profitWeightRatio) {
            largest = left;
        }

        // See if right child exists and is greater than root
        if (right < n && items[right].profitWeightRatio > items[largest].profitWeightRatio) {
            largest = right;
        }

        // Change root, if needed
        if (largest != i) {
            Item swap = items[i];
            items[i] = items[largest];
            items[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(items, n, largest);
        }
    }

    // Function to find the maximum profit using Fractional Knapsack
    public static double fractionalKnapsack(Item[] items, double capacity) {
        // Sort items by profit/weight ratio in non-increasing order
        heapSort(items);

        double totalProfit = 0.0;
        double remainingCapacity = capacity;

        for (Item item : items) {
            if (remainingCapacity == 0) break;

            // Take the whole item if it fits
            if (item.itemWeight <= remainingCapacity) {
                totalProfit += item.itemProfit;
                remainingCapacity -= item.itemWeight;
            } else {
                // Take fraction of the item
                totalProfit += item.itemProfit * (remainingCapacity / item.itemWeight);
                remainingCapacity = 0; // Knapsack is full
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        // Example items
        Item[] items = {
            new Item(1, 60, 10),
            new Item(2, 100, 20),
            new Item(3, 120, 30)
        };

        // Capacity of knapsack
        double capacity = 50;

        // Compute the maximum profit
        double maxProfit = fractionalKnapsack(items, capacity);

        // Output the result
        System.out.println("Maximum profit: " + maxProfit);
    }
}
