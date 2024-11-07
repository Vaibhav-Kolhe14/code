import java.util.Scanner;

public class WorstFit {

    static void worstFit(int blockSize[], int m, int processSize[], int n, int remainingBlockSize[]) {
        int allocation[] = new int[n];
        
        // Initialize all allocations as -1 (indicating unallocated)
        for (int i = 0; i < n; i++) {
            allocation[i] = -1;
        }
        
        // Allocate memory to processes
        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            // Find the worst block for the current process
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[j] > blockSize[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            
            // If we found a block, allocate it
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
                remainingBlockSize[i] = blockSize[worstIdx];
            }
        }

        // Display allocation details
        System.out.println("\nProcess No.\tProcess Size\tBlock No.\tRemaining Block Size");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print((allocation[i] + 1) + "\t\t" + remainingBlockSize[i]);
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
        int remainingBlockSize[] = new int[m];

        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            remainingBlockSize[i] = blockSize[i];
        }

        // Input process sizes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int processSize[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
        }

        // Call the worst fit allocation function
        worstFit(blockSize, m, processSize, n, remainingBlockSize);

        sc.close();
    }
}
