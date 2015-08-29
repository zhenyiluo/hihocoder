import java.util.*;
import java.io.*;
public class Main {
    public static final int INF = (int)(1e9 + 10);
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while(sc.hasNextInt()){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();
    }
    static int[] a;
    private static void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        SegmentTree minST = new SegmentTree(0, n-1);
        
        int k = sc.nextInt();
        for(int i = 0; i < k; i++){
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            if(b == 0){
                pw.println(minST.query(c-1, d-1));
            }else{
                minST.update(c-1, d);
            }
        }
    }
    static class SegmentTree{
        public SegmentTree left, right;
        public int start;
        public int end;
        public int min;
        
        public SegmentTree(int start, int end){
            this.start = start;
            this.end = end;
            if(start == end){
                this.min = a[start];
            }else{
                int mid = start + ((end - start)>>1);
                left = new SegmentTree(start, mid);
                right = new SegmentTree(mid+1, end);
                this.min = Math.min(left.min, right.min);
            }
        }
        
        public void update(int p, int v){
            if(start == p && end == p){
                this.min = v;
                return;
            }
            int mid = start + ((end - start) >> 1);
            if(mid >= p){
                left.update(p, v);
            }else{
                right.update(p, v);
            }
            this.min = Math.min(left.min, right.min);
        }
        
        public int query(int l, int r){
            if(l <= start && r >= end){
                return min;
            }
            int mid = start + ((end - start) >> 1);
            if(mid >= r){
                return left.query(l, r);
            }else if(mid < l){
                return right.query(l, r);
            }else{
                return Math.min(left.query(l, mid), right.query(mid+1, r));
            }
        }
    }
}


