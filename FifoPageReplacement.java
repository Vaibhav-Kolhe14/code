import java.util.*;

public class FifoPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Step 1: Get user input for number of frames and pages
        System.out.print("Enter the number of frames: ");
        int numberOfFrames = sc.nextInt();
        
        System.out.print("Enter the number of pages: ");
        int numberOfPages = sc.nextInt();
        
        System.out.print("Enter the page reference string (space-separated): ");
        int[] pageReferenceString = new int[numberOfPages];
        for (int i = 0; i < numberOfPages; i++) {
            pageReferenceString[i] = sc.nextInt();
        }

        // Step 2: Initialize frames array and variables
        int[] frames = new int[numberOfFrames];
        Arrays.fill(frames, -1); // Fill frames with -1 (indicating empty slots)
        int pageFaults = 0;
        int pageHits = 0;
        int currentIndex = 0;

        // Step 3: Iterate through the page reference string
        for (int page : pageReferenceString) {
            boolean pageHit = false;

            // Check if the page is already in one of the frames
            for (int frame : frames) {
                if (frame == page) {
                    pageHit = true;
                    pageHits++; // Increment page hit count
                    break;
                }
            }

            // If it's a page fault (page not found in frames)
            if (!pageHit) {
                frames[currentIndex] = page; // Replace the oldest page in FIFO order
                currentIndex = (currentIndex + 1) % numberOfFrames; // Move to next frame index in circular manner
                pageFaults++; // Increment page fault count
            }

            // Display the current state of frames
            System.out.print("Frames: ");
            for (int frame : frames) {
                if (frame == -1) {
                    System.out.print("Empty ");
                } else {
                    System.out.print(frame + " ");
                }
            }
            System.out.println();
        }

        // Step 4: Output total page faults, page hits, and page fault ratio
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);

        sc.close();
    }
}
