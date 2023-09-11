package org.example;

public class SumTh {
    public static void main(String[] args) {
        int[] x = new int[100];

        for (int i = 0; i < 100; i++){
            x[i] = i;
        }

        int[] arr1= new int[50];
        int[] arr2= new int[50];
        System.arraycopy(x, 0, arr1, 0, 50); // 배열을 잘라내기 위한 arraycopy()
        System.arraycopy(x, 50, arr2, 0, 50);

        Thread th1 = new Thread(new SumThread(arr1));
        Thread th2 = new Thread(new SumThread(arr2));

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        int result = th1.getSumResult() + th2.getSumResult();
        System.out.println("최종 결과 : " + result);
    }
}

class SumThread implements Runnable {
    private int[] array;
    private int result;

    public SumThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        for(int num : array) {
            result += num;
        }
    }
    public int getSumResult(){
        return result;
    }
}