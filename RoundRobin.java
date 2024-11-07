import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes (up to 10): ");
        int n = sc.nextInt();
        
        
        int[] processID = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] arrivalTime = new int[n];
        int[] completionTime = new int[n];
        float avgtat = 0, avgwt = 0;
       
        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            System.out.println("enter the process " + (i+1) + " arrival time :");
            arrivalTime[i] = sc.nextInt();
            System.out.println("enter the process " + (i+1) + " burst time :");
            burstTime[i] = sc.nextInt();
            processID[i] = i +1;
            remainingTime[i] = burstTime[i]; // Set remaining time to burst time initially
        }
        
        int currentTime = 0; // Current time in the system
        int completedProcesses = 0; // Track completed processes

        // Implement Round Robin scheduling
        while (completedProcesses < n) {
            boolean processFound = false;

            // Iterate through each process
            for (int i = 0; i < n; i++) {
                // Check if the process has arrived and is not yet completed
                if (remainingTime[i] > 0 && arrivalTime[i] <= currentTime) {
                    processFound = true;

                    // Execute the process for the time quantum
                    if (remainingTime[i] > quantum) {
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        // If remaining time is less than or equal to quantum, complete the process
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completedProcesses++;
                        completionTime[i] = currentTime;
                       
                        turnaroundTime[i] = completionTime[i] - arrivalTime[i];
                        waitingTime[i] = turnaroundTime[i] - burstTime[i];
                    }
                }
            }
            
            // If no process is ready, increment time (CPU idle)
            if (!processFound) {
                currentTime++;
            }
        }


        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            avgwt += waitingTime[i];
            avgtat += turnaroundTime[i];
            System.out.println("P" + processID[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" +
                               completionTime[i] + "\t\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        System.out.println("average turnAround time : " + (avgtat/n));
        System.out.println("average waiting time : " + (avgwt/n));
        
        sc.close();
    }
}
