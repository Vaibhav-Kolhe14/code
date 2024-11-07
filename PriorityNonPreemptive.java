import java.util.Scanner;

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] processId = new int[n];          
        int[] arrivalTime = new int[n];         
        int[] burstTime = new int[n];          
        int[] priority = new int[n];            
        int[] waitingTime = new int[n];        
        int[] turnaroundTime = new int[n];      
        int[] completionTime = new int[n];      
        boolean[] completed = new boolean[n];  
        float avgtat = 0, avgwt = 0;

        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + ": ");
            priority[i] = sc.nextInt();
            processId[i] = i + 1; 
        }

        int currentTime = 0;          // Tracks total elapsed time
        int completedProcesses = 0;   // Tracks number of completed processes

        // Process scheduling
        while (completedProcesses < n) {
            int highestPriorityProcess = -1; // Index of the process to execute
            int minimumPriority = Integer.MAX_VALUE;

            // Select the highest-priority process among those that have arrived
            for (int i = 0; i < n; i++) {
                if (!completed[i] && arrivalTime[i] <= currentTime) {
                    // Choose process with the highest priority (smallest priority number)
                    // In case of tie in priority, choose the one that arrived first
                    if(priority[i] < minimumPriority|| (priority[i] == minimumPriority&&arrivalTime[i] < arrivalTime[highestPriorityProcess])) {
                        minimumPriority = priority[i];
                        highestPriorityProcess = i;
                    }
                }
            }

            if (highestPriorityProcess == -1) {
                // If no process has arrived yet, increment the total time and continue
                currentTime++;
            } else {
                // Execute the selected process
                waitingTime[highestPriorityProcess] = currentTime - arrivalTime[highestPriorityProcess];
                currentTime += burstTime[highestPriorityProcess];
                completionTime[highestPriorityProcess] = currentTime;
                turnaroundTime[highestPriorityProcess] = completionTime[highestPriorityProcess] - arrivalTime[highestPriorityProcess];

                // Update totals and mark the process as completed
                avgwt += waitingTime[highestPriorityProcess];
                avgtat += turnaroundTime[highestPriorityProcess];
                completed[highestPriorityProcess] = true;
                completedProcesses++;
            }
        }

       
        System.out.println("\nProcess\tArrival\tBurst\tPriority\tWaiting\tTurnaround\tCompletion");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + processId[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + priority[i] +
                               "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + completionTime[i]);
        }

        System.out.println("average turnAround time : " + (avgtat/n));
        System.out.println("average waiting time : " + (avgwt/n));

        sc.close();
    }
}
