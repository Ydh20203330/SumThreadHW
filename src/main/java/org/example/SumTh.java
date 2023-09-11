package org.example;

public class SumTh {
    public static void main(String[] args) {
        int[] x = new int[100]; // 배열 생성

        for (int i = 0; i < 100; i++){
            x[i] = i; // 배열에 0~99 저장
        }

        int[] arr1= new int[50]; // 배열 1
        int[] arr2= new int[50]; // 배열 2
        System.arraycopy(x, 0, arr1, 0, 50); // 배열을 잘라내기 위한 arraycopy()
        System.arraycopy(x, 50, arr2, 0, 50); // 두 부분 0~49 50~99로 나눔

        SumThread th1 = new SumThread(arr1); // 스레드 1
        SumThread th2 = new SumThread(arr2); // 스레드 2

        th1.start(); // 스레드 1 시작
        th2.start(); // 스레드 2 시작

        try { // join이 제대로 수행됐는지 확인하기 위한 try-catch문
            th1.join(); // 스레드 1
            th2.join(); // 스레드 2
        } catch (InterruptedException e){ // 인터럽트 에러 catch
            e.printStackTrace(); 
        }

        int result = th1.getResult() + th2.getResult(); // 두 스레드의 계산값을 더함
        System.out.println("최종 결과 : " + result); // 결과 출력
    }
}

class SumThread extends Thread {
    private int[] array;
    private int result;

    public SumThread(int[] array) {
        this.array = array;
    } // array를 받아 저장

    @Override
    public void run() {
        for(int num : array) {
            result += num;
        }
    } // 스레드는 배열을 받아 값들을 더해 result에 저장
    public int getResult(){
        return result;
    } // 결과를 얻기 위한 getResult()
}