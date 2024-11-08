import java.util.*;

public class FCFS {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int tat[] = new int[n];
        int ct[] = new int[n];
        int wt[] = new int[n];

        float avgtat = 0, avgwt = 0;

        for(int i=0;i < n;i++) {
            System.out.println("enter the process " + (1+1) + " arrival time :");
            at[i] = sc.nextInt();
            System.out.println("enter the process " + (1+1) + " burst time :");
            bt[i] = sc.nextInt();
            pid[i] = i+1;
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n - (i+1);j++) {
                if(at[j] > at[j+1]) {
                    int temp = at[j];
                    at[j] = at[j+1];
                    at[j+1] = temp;

                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j+1];
                    pid[j+1] = temp;
                }
            }
        }

        for(int i=0;i<n;i++) {
            if(i==0) {
                ct[i] = at[i] + bt[i];
            } else {
                if(at[i] > ct[i-1]){
                    ct[i] = at[i] + bt[i];
                } else {
                    ct[i] = ct[i-1] + bt[i];
                }
            }
        }

        for(int i=0;i<n;i++) {
            tat[i] = ct[i] - at[i];
        }

        for(int i=0;i<n;i++) {
            wt[i] = tat[i] - bt[i];
            avgtat += tat[i];
            avgwt += wt[i];
        }

        System.out.println("\npid\tarrival\tburst\tcompletion\tturnaround\twaiting");
        for(int i=0;i<n;i++){
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("average turnAround time : " + (avgtat/n));
        System.out.println("average waiting time : " + (avgwt/n));

        sc.close();
    }
}