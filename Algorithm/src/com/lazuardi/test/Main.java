package com.lazuardi.test;

import java.util.Arrays;

import com.lazuardi.algorithm.MyMath;
import com.lazuardi.algorithm.MyUtil;

public class Main {

	public static void main(String[] args) {

//		bubbleSortTest();
//		quickSortTest();
//		rotateLeftTest();
//		rotateRightTest();
//		multipleBigTest();
		factorialTest();
//		combinationTest();
//		permutationTest();
	}
	
	static void bubbleSortTest() {
		
		System.out.println("Bubblesort Algorithm");
		
		int[] l_iaArray = {18,964,3,156,45,87,149,6,1348,9,4,32,13,3,48,9713,79,4,6,54,67,9,46,31,684,131};
		MyUtil.bubbleSort(l_iaArray, 0, l_iaArray.length-1);
		System.out.println(Arrays.toString(l_iaArray));
	}
	
	static void quickSortTest() {
		
		System.out.println("Quicksort Algorithm");

		int[] l_iaArray = {18,964,3,156,45,87,149,6,1348,9,4,32,13,3,48,9713,79,4,6,54,67,9,46,31,684,131};
		MyUtil.quickSort(l_iaArray, 0, l_iaArray.length-1);
		System.out.println(Arrays.toString(l_iaArray));
	}
	
	static void rotateLeftTest() {
		
		System.out.println("Rotate Left Algorithm");
		
		int[] l_iaArray = {0,1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.toString(l_iaArray));
		MyUtil.rotateLeft(l_iaArray, 2, l_iaArray.length-2, 23);
		System.out.println(Arrays.toString(l_iaArray));
	}
	
	static void rotateRightTest() {
		
		System.out.println("Rotate Right Algorithm");
		
		int[] l_iaArray = {0,1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.toString(l_iaArray));
		MyUtil.rotateRight(l_iaArray, 2, l_iaArray.length-2, 23);
		System.out.println(Arrays.toString(l_iaArray));
	}
	
	static void multipleBigTest() {
		
		System.out.println("Multiple Big Value Algorithm");
		
		// 0
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(000), MyUtil.intToArray(00000000))));
		// 5,963
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(67), MyUtil.intToArray(89))));
		// 9,801
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(99), MyUtil.intToArray(99))));
		// 12,177
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(123), MyUtil.intToArray(99))));
		// 19,363,907
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(44311), MyUtil.intToArray(437))));
		// 100,000,000,000
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(1000), MyUtil.intToArray(100000000))));
		// 12,345,666,666,666,666,666,666,654,321
		System.out.println(Arrays.toString(MyMath.multipleBig(MyUtil.intToArray(111111), new int[] {1,1,1, 1,1,1, 1,1,1, 1,1,1, 1,1,1, 1,1,1, 1,1,1, 1,1,1})));
		// 975,461,057,802,164,304,112,482,853,211,126,352,690
		System.out.println(Arrays.toString(MyMath.multipleBig(new int[] {9,8,7,6,5,4,3,2,1,0}, new int[] {9,8,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9})));
	}
	
	static void factorialTest() {
		
		System.out.println("factorial (Big) Algorithm");
		
		for(int i=0; i<=12; i++) {
			System.out.println(MyMath.factorial(i));
		}
		
		for(int i=13; i<=50; i++) {
			System.out.println(Arrays.toString(MyMath.factorialBig(i)));
		}
	}
	
	static void combinationTest() {
		
		System.out.println("Combination List Algorithm");
		
		int[] l_iaArray = {1,2,3,4,5};
		int[][] l_iRetData = MyMath.combination(l_iaArray,3);
		System.out.println(Arrays.deepToString(l_iRetData));
	}
	
	static void permutationTest() {
		
		System.out.println("Permutation List Algorithm");
		
		int[] l_iaArray = {1,2,3,4,5};
		int[][] l_iRetData = MyMath.permutation(l_iaArray,3);
		System.out.println(Arrays.deepToString(l_iRetData));
	}
}
