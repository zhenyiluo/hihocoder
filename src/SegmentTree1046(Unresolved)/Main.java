import java.util.*;
import java.io.*;
public class Main{
    public static PriorityQueue<Integer> pq;
    public static int k;
    public static int[] a;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while(sc.hasNext()){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();
    }
    
    private static void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        k = sc.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        pq = new PriorityQueue<Integer>(10, new Comparator<Integer>(){ 
            @Override
            public int compare(Integer i1, Integer i2){
                return i1 - i2;
            }
        });
        SegmentTree st = new SegmentTree(0, n-1);
        
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int sum = st.query(i, j);
                if(pq.size() < k){
                    pq.add(sum);
                }else{
                   if(sum > pq.peek()){
                        pq.poll();
                        pq.add(sum);
                    }
                }
            }
        }
        pw.println(pq.peek());
    }
    
    static class SegmentTree{
        SegmentTree lc;
        SegmentTree rc;
        int left;
        int right;
        int sum;
        public SegmentTree(int left, int right){
            this.left = left;
            this.right = right;
            if(left == right){
                sum = a[left];
            }else{
                int mid = left + ((right - left) >> 1);
                lc = new SegmentTree(left, mid);
                rc = new SegmentTree(mid + 1, right);
                sum = lc.sum + rc.sum;
            }
            
        }
        
        public int query(int left, int right){
            if(left <= this.left && right >= this.right){
                return sum;
            }
            int mid = this.left + ((this.right - this.left) >> 1);
            if(left > mid){
                return rc.query(left, right);
            }else if(right <= mid){
                return lc.query(left, right);
            }else{
                return lc.query(left, mid) + rc.query(mid+1, right);
            }
        }
    }
}