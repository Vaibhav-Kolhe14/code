import java.util.Arrays;
import java.util.Scanner;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of frames and pages
        System.out.print("Enter the number of frames: ");
        int numberOfFrames = sc.nextInt();

        System.out.print("Enter the number of pages: ");
        int numberOfPages = sc.nextInt();

        // Input page reference string
        int[] pages = new int[numberOfPages];
        System.out.print("Enter the page reference string: ");
        for (int i = 0; i < numberOfPages; i++) {
            pages[i] = sc.nextInt();
        }

        // Initialize frames, fault and hit counters
        int[] frames = new int[numberOfFrames];
        Arrays.fill(frames, -1);  // Fill frames with -1 to represent empty slots
        int pageFaults = 0, pageHits = 0;

        // Loop through each page request
        for (int i = 0; i < numberOfPages; i++) {
            int currentPage = pages[i];

            // Check if current page is already in frames
            if (isInFrames(frames, currentPage)) {
                pageHits++;
                System.out.print("Hit: ");
            } else {
                pageFaults++;
                int replaceIndex = findOptimalIndex(frames, pages, i + 1);
                frames[replaceIndex] = currentPage;  // Replace page using optimal replacement
                System.out.print("Fault: ");
            }

            // Display current frame state
            displayFrames(frames);
        }

        // Output total page fault and hit information
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);

        sc.close();
    }

    // Helper method to check if a page is already in frames
    private static boolean isInFrames(int[] frames, int page) {
        for (int frame : frames) {
            if (frame == page) {
                return true;
            }
        }
        return false;
    }

    // Helper method to find the optimal index for page replacement
    private static int findOptimalIndex(int[] frames, int[] pages, int start) {
        int farthest = start;
        int indexToReplace = -1;

        for (int i = 0; i < frames.length; i++) {
            int frame = frames[i];
            int j;
            for (j = start; j < pages.length; j++) {
                if (pages[j] == frame) {
                    if (j > farthest) {  // Find the frame that won’t be used for the longest time
                        farthest = j;
                        indexToReplace = i;
                    }
                    break;
                }
            }
            if (j == pages.length) {  // If frame page isn’t found in future, replace it
                return i;
            }
        }
        
        if (indexToReplace != -1) {
            return indexToReplace;
        } else {
            return 0;
        }
    }

    // Helper method to display the current state of frames
    private static void displayFrames(int[] frames) {
        for (int frame : frames) {
            if (frame == -1) {
                System.out.print("[ ] ");
            } else {
                System.out.print("[" + frame + "] ");
            }
        }
        System.out.println();
    }
}
