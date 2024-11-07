import java.util.*;

public class LruPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of frames
        System.out.print("Enter the number of frames: ");
        int numberOfFrames = sc.nextInt();

        // Input: Number of pages in the reference string
        System.out.print("Enter the number of pages: ");
        int numberOfPages = sc.nextInt();

        // Input: Page reference string
        System.out.print("Enter the page reference string (space-separated): ");
        int[] pageReferenceString = new int[numberOfPages];
        for (int i = 0; i < numberOfPages; i++) {
            pageReferenceString[i] = sc.nextInt();
        }

        // Initialize frames using LinkedList to maintain LRU order
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0; // Count of page faults
        int pageHits = 0;   // Count of page hits

        // Process each page in the reference string
        for (int page : pageReferenceString) {
            // Check if the page is already in frames (Page Hit)
            if (frames.contains(page)) {
                frames.remove((Integer) page); // Remove the page from its current position
                frames.addLast(page);          // Move the page to the end (most recently used)
                pageHits++;                    // Increment page hit count
            } else {
                // Page Fault: Page is not in frames
                if (frames.size() == numberOfFrames) {
                    frames.removeFirst();       // Remove the least recently used page
                }
                frames.addLast(page);           // Add new page at the end (most recently used)
                pageFaults++;                   // Increment page fault count
            }

            // Display the current state of frames after each page reference
            System.out.print("Frames: ");
            for (int frame : frames) {
                System.out.print(frame + " ");
            }
            System.out.println();
        }

        // Display the results
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);

        sc.close();
    }
}
