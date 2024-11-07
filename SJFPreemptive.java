import java.util.Scanner;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] remainingTime = new int[n];
        boolean[] completed = new boolean[n]; // Tracks if a process is completed
        float avgtat = 0, avgwt = 0;


        for (int i = 0; i < n; i++) {
            System.out.println("enter the process " + (i+1) + " arrival time :");
            arrivalTime[i] = sc.nextInt();
            System.out.println("enter the process " + (i+1) + " burst time :");
            burstTime[i] = sc.nextInt();
            processId[i] = i +1;
            remainingTime[i] = burstTime[i]; // Set remaining time to burst time initially
        }

        int completedProcesses = 0; // Count of completed processes
        int currentTime = 0; // Tracks current time in scheduling

        while (completedProcesses < n) {
            int shortestJob = -1;
            int minimumRemainingTime = Integer.MAX_VALUE;

            // Finding the process with the shortest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (!completed[i] && arrivalTime[i] <= currentTime && remainingTime[i] < minimumRemainingTime) {
                    minimumRemainingTime = remainingTime[i];
                    shortestJob = i;
                }
            }

            // If a suitable process is found, execute it for 1 time unit
            if (shortestJob != -1) {
                remainingTime[shortestJob]--; // Decrease remaining time

                // If the process finishes, update its completion and waiting times
                if (remainingTime[shortestJob] == 0) {
                    completed[shortestJob] = true;
                    completedProcesses++;
                    completionTime[shortestJob] = currentTime + 1;
                    turnaroundTime[shortestJob] = completionTime[shortestJob] - arrivalTime[shortestJob];
                    waitingTime[shortestJob] = turnaroundTime[shortestJob] - burstTime[shortestJob];
                }
                currentTime++; // Move to the next time unit
            } else {
                currentTime++; // Move to the next time unit if no process is ready
            }
        }



        System.out.println("\npid\tarrival\tburst\tcompletion\tturnaround\twaiting");
        for (int i = 0; i < n; i++) {
            avgwt += waitingTime[i];
            avgtat += turnaroundTime[i];
            System.out.println(processId[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }
        
        System.out.println("average turnAround time : " + (avgtat/n));
        System.out.println("average waiting time : " + (avgwt/n));

        sc.close();
    }
}
