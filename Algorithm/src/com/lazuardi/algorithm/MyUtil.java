package com.lazuardi.algorithm;

public class MyUtil {
	
	public static void bubbleSort(int[] p_iaArray, int p_iStart, int p_iEnd) {
		
		bubbleSort(p_iaArray, p_iStart, p_iEnd, false);
	}
	
	public static void bubbleSort(int[] p_iaArray, int p_iStart, int p_iEnd, boolean p_zIsDescending) {
		
		for(int i = p_iStart; i <= p_iEnd; i++){
			for(int j = i+1; j <= p_iEnd; j++) { 
				
				// high to low
				if (p_zIsDescending && (p_iaArray[i] < p_iaArray[j])) {
					swapArray(p_iaArray, i, j);
				} 
				// low to high
				else if (!p_zIsDescending && (p_iaArray[i] > p_iaArray[j])) {
					swapArray(p_iaArray, i, j);
				}
			}
		}
	}
	
	public static void quickSort(int[] p_iaArray, int p_iStart, int p_iEnd) {
		if(p_iStart < p_iEnd) {
			
			int l_iPi = partition(p_iaArray, p_iStart, p_iEnd, false);
			
			quickSort(p_iaArray, p_iStart, l_iPi - 1);
			quickSort(p_iaArray, l_iPi + 1, p_iEnd);
		}
	}
	
	public static void quickSort(int[] p_iaArray, int p_iStart, int p_iEnd, boolean p_zIsDescending) {
		if(p_iStart < p_iEnd) {
			
			int l_iPi = partition(p_iaArray, p_iStart, p_iEnd, p_zIsDescending);
			
			quickSort(p_iaArray, p_iStart, l_iPi - 1, p_zIsDescending);
			quickSort(p_iaArray, l_iPi + 1, p_iEnd, p_zIsDescending);
		}
	}
	
	private static int partition(int[] p_iaArray, int p_iStart, int p_iEnd, boolean p_zIsDescending) {
		
		int l_iPivot = p_iaArray[p_iEnd];
		int i = p_iStart - 1;
		
		for (int j = p_iStart; j < p_iEnd; j++) {
			
			// high to low
			if(p_zIsDescending && p_iaArray[j] > l_iPivot) {
				i++;
				swapArray(p_iaArray, i, j);
			} 
			// low to high
			else if (!p_zIsDescending && (p_iaArray[j] <= l_iPivot)) {
				i++;
				swapArray(p_iaArray, i, j);
			}
		}
		
		swapArray(p_iaArray, i+1, p_iEnd);
		
		return i+1;
		
	}
	
	static void swapArray(int[] p_iaArray, int p_iFirstIndex, int p_iSecondIndex) {
		int l_iTemp = p_iaArray[p_iFirstIndex];
		p_iaArray[p_iFirstIndex] = p_iaArray[p_iSecondIndex];
		p_iaArray[p_iSecondIndex] = l_iTemp;
	}
	
	public static void rotateLeft(int[] p_iaArray, int p_iStart, int p_iEnd) {
		
		int l_iTemp = p_iaArray[p_iStart];
		
		for(int i = p_iStart; i < p_iEnd; i++) {
			p_iaArray[i] = p_iaArray[i+1];
		}
		
		p_iaArray[p_iEnd] = l_iTemp;
	}
	
	public static void rotateLeft(int[] p_iaArray, int p_iStart, int p_iEnd, int p_iRotation) {
		
		// check limitation
		if(p_iStart > p_iEnd ||
				p_iStart < 0 ||
				p_iEnd > p_iaArray.length - 1)
			throw new RuntimeException("index offset out of bound");
		
		// p_iRotation minus means rotate otherwise
		if(p_iRotation < 0) {
			rotateRight(p_iaArray, p_iStart, p_iEnd, Math.abs(p_iRotation));
			return;
		}
		
		// set boundaries
		int l_iBoundaries = p_iEnd - p_iStart + 1;
		
		// normalize rotation
		while(p_iRotation > l_iBoundaries) {
			p_iRotation -= l_iBoundaries;
		}
		
		// initialize array temporary
		int[] l_iArrTemp = new int[p_iRotation];
		for(int i = 0; i < p_iRotation; i++) {
			l_iArrTemp[i] = p_iaArray[i + p_iStart];
		}
		
		// rotate all values
		for(int i = p_iStart; i <= p_iEnd - p_iRotation; i++) {
			p_iaArray[i] = p_iaArray[i+p_iRotation];
		}
		for(int i = 0; i < p_iRotation; i++) {
			p_iaArray[i + p_iEnd - p_iRotation + 1] = l_iArrTemp[i];
		}
	}
	
	public static void rotateRight(int[] p_iaArray, int p_iStart, int p_iEnd, int p_iRotation) {
		
		// check limitation
		if(p_iStart > p_iEnd ||
				p_iStart < 0 ||
				p_iEnd > p_iaArray.length - 1)
			throw new RuntimeException("index offset out of bound");
		
		// p_iRotation minus means rotate otherwise
		if(p_iRotation < 0) {
			rotateLeft(p_iaArray, p_iStart, p_iEnd, Math.abs(p_iRotation));
			return;
		}
		
		// set boundaries
		int l_iBoundaries = p_iEnd - p_iStart + 1;
		
		// normalize rotation
		while(p_iRotation > l_iBoundaries) {
			p_iRotation -= l_iBoundaries;
		}
		
		// initialize array temporary
		int[] l_iArrTemp = new int[p_iRotation];
		for(int i = 0; i < p_iRotation; i++) {
			l_iArrTemp[i] = p_iaArray[i + p_iStart + l_iBoundaries - p_iRotation];
		}
		
		// rotate all values
		for(int i = p_iEnd; i >= p_iStart + p_iRotation; i--) {
			p_iaArray[i] = p_iaArray[i-p_iRotation];
		}
		for(int i = 0; i < p_iRotation; i++) {
			p_iaArray[i + p_iStart] = l_iArrTemp[i];
		}
	}
	
	public static int[] intToArray(int p_iA) {
		
		char[] l_oDigit = String.valueOf(p_iA).toCharArray();
		int[] l_iaA = new int[l_oDigit.length];
		
		for(int i=0; i < l_oDigit.length; i++) {
			
			switch (l_oDigit[i]) {
			case '9':
				l_iaA[i]++;
			case '8':
				l_iaA[i]++;
			case '7':
				l_iaA[i]++;
			case '6':
				l_iaA[i]++;
			case '5':
				l_iaA[i]++;
			case '4':
				l_iaA[i]++;
			case '3':
				l_iaA[i]++;
			case '2':
				l_iaA[i]++;
			case '1':
				l_iaA[i]++;
			}
		}
		
		return l_iaA;
	}
}
