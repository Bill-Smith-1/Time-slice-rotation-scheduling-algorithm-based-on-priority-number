import java.util.*;
class Process {
    String processName;
    Process next;
    int arrivalTime;
    int requiredTime;
    int executedTime; // 已运行时间
    int priority;
    String status;

    public Process(String processName, int arrivalTime, int requiredTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.requiredTime = requiredTime;
        this.priority = priority;
        this.status="就绪";
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Process head = new Process("头结点", 0, 0, 100000); // 创建头结点
        head.next = head;
        int all_num=12;
        String[] processNames = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M"};
        int[] arrivalTimes = {0, 1, 2, 3, 6, 8, 12, 12, 12, 18, 25, 25};
        int[] requiredTimes = {6, 4, 10, 5, 1, 2, 5, 10, 4, 3, 15, 8};

        Process lastProcess = null;
        for (int i = 0; i < processNames.length; i++) {
            Process process = new Process(processNames[i], arrivalTimes[i], requiredTimes[i], random.nextInt(91) + 10);
            if (lastProcess == null) {
                head.next = process;
            } else {
                lastProcess.next = process;
            }
            lastProcess = process;
        }
        lastProcess.next = head;
        System.out.println("系统运行时间：");
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        for(int i=0;i<num;i++){
            Process process = head;
            for (int u=0;u<all_num;u++){
                process = process.next;
                System.out.print("进程"+process.processName+"优先数: " + process.priority+"  ");
            }
            System.out.println();
            process = head;
            int min=1000;
            int index=0;
            for (int u=0;u<all_num;u++){
                process=process.next;
                if(process.priority<min) {
                    min=process.priority;
                    //System.out.println("最小值"+min);
                    index=u;
                }
            }
            index++;
            //System.out.println("索引"+index);
            process = head;
            for (int u=0;u<index;u++){
                process=process.next;
            }
            process.priority+=3;
            process.requiredTime-=1;
            process.executedTime+=1;
            System.out.println("当前执行进程是："+process.processName);
            System.out.println("要求运行时间: " + process.requiredTime);
            System.out.println("已运行时间: " + process.executedTime);
            System.out.println("优先数: " + process.priority);
            System.out.println("进程状态: 执行" );
            if(process.requiredTime==0){
                process = head;
                for (int u=0;u<index-1;u++){
                    process=process.next;
                }
                process.next=process.next.next;
                all_num--;
            }
        }

    }


}