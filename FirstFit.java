import java.util.Scanner;

public class FirstFit {

    // Function to allocate memory to processes using the first fit strategy
    static void firstFit(int blockSize[], int m, int processSize[], int n, int remBlockSize[]) {
        int allocation[] = new int[n]; // To store block allocation for each process

        // Initialize all allocations as -1 (indicating unallocated)
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }

        // Process each process one by one
        for (int i = 0; i < n; i++) {
            // Find the first block that fits the current process
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    // Assign block j to process i
                    allocation[i] = j;
                    // Reduce available memory in this block
                    blockSize[j] -= processSize[i];
                    // Update remaining block size after allocation
                    remBlockSize[j] = blockSize[j];
                    break;
                }
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

        // Input number of memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int m = sc.nextInt();
        int blockSize[] = new int[m];
        int remBlockSize[] = new int[m]; // Track remaining sizes of blocks
        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            remBlockSize[i] = blockSize[i]; // Initialize remBlockSize with original sizes
        }

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int processSize[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        // Call the first fit allocation function
        firstFit(blockSize, m, processSize, n, remBlockSize);

        sc.close();
    }
}
