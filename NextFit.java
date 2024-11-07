import java.util.Scanner;

public class NextFit {

    static void nextFit(int blockSize[], int m, int processSize[], int n, int remBlockSize[]) {
        int allocation[] = new int[n];
        int j = 0;  // Start from the last allocated block position

        // Initialize all allocations as -1 (indicating unallocated)
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        // Process each process one by one
        for (int i = 0; i < n; i++) {
            int count = 0; // Track to avoid infinite loop

            // Find the next block that fits the current process
            while (count < m) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    remBlockSize[j] = blockSize[j];
                    break;
                }
                j = (j + 1) % m; // Move to the next block
                count++;
            }
        }

        // Display allocation details
        System.out.println("\nProcess No.\tProcess Size\tBlock No.\tRemaining Block Size");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print((allocation[i] + 1) + "\t\t" + remBlockSize[allocation[i]]);
            } else {
                System.out.print("Not Allocated\t-");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input memory block sizes
        System.out.print("Enter the number of memory blocks: ");
        int m = sc.nextInt();
        int blockSize[] = new int[m];
        int remBlockSize[] = new int[m];

        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            remBlockSize[i] = blockSize[i];
        }

        // Input process sizes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int processSize[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        // Call the next fit allocation function
        nextFit(blockSize, m, processSize, n, remBlockSize);

        sc.close();
    }
}
