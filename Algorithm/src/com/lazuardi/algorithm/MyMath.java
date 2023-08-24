package com.lazuardi.algorithm;

import java.util.Arrays;

public class MyMath {
	
	public static int[][] combination(int[] p_iaSetArray, int l_iR){
		
		// check boundaries
		int l_iN = p_iaSetArray.length;
		if (l_iN < l_iR)
			throw new RuntimeException("n must be greater than r");
		
		// calculate combination result
		int l_iCombinationLen = factorial(l_iN) / (factorial(l_iN - l_iR) * factorial(l_iR));
		int[][] l_iaCombinationList = new int[l_iCombinationLen][l_iR];
		
		// offset buffer
		int[] l_iaSrcOffs = new int[l_iR];
		
		int[] l_iaArray = p_iaSetArray.clone();
		for(int i=0; i<l_iCombinationLen; i++) {
			
			// initialize offset
			if(i == 0) {
				for(int j=0; j<l_iR; j++) {
					l_iaSrcOffs[j] = j;
				}
			}
			// set next offset
			else {
				nextOffsetCombination(l_iaSrcOffs, l_iN, l_iR);
			}
			
			// save combination based on offset
			for(int j=0; j<l_iR; j++) {
				l_iaCombinationList[i][j] = l_iaArray[l_iaSrcOffs[j]];
			}
		}
		
		return l_iaCombinationList;
	}

	private static void nextOffsetCombination(int[] p_iaSrcOffs, int p_iN, int p_iR) {
		
		int l_iMaxOffs = p_iN-1;
		int l_iOffs = p_iR-1;
		
		// increment value
		p_iaSrcOffs[l_iOffs]++;
		
		// normalize array value
		if(p_iR < p_iaSrcOffs.length) {
			for(int i=l_iOffs; i<p_iaSrcOffs.length-1; i++) {
				p_iaSrcOffs[i+1] = p_iaSrcOffs[i] + 1;
			}
		}
		
		// if everything ok, then get out
		if(p_iaSrcOffs[l_iOffs] <= l_iMaxOffs) {
			return;
		}
		
		// recursive method
		nextOffsetCombination(p_iaSrcOffs, --p_iN, --p_iR);
	}

	public static int[][] permutation(int[] p_iaSetArray, int l_iR){
		
		// get combination list
		int[][] l_iaCombinationList = combination(p_iaSetArray, l_iR);
		
		// check boundaries
		int l_iN = p_iaSetArray.length;
		
		// calculate permutation result
		int l_iPermutationLen = factorial(l_iN) / (factorial(l_iN - l_iR));
		int[][] l_iaPermutationList = new int[l_iPermutationLen][l_iR];
		int l_iOffs = 0;
		
		// extends the order based on combination list
		for(int i=0; i<l_iaCombinationList.length; i++) {
			int[][] l_iaPermutationListTemp = permutation(l_iaCombinationList[i]).clone();
			
			for(int j=0; j<l_iaPermutationListTemp.length; j++) {
				l_iaPermutationList[l_iOffs++] = l_iaPermutationListTemp[j];
			}
		}
		
		return l_iaPermutationList;
	}

	public static int[][] permutation(int[] p_iaSetArray) {
		
		int l_iN = p_iaSetArray.length;
		int l_iPermutationLen = factorial(l_iN);
		int[][] l_iaPermutationList = new int[l_iPermutationLen][l_iN];
		
		int l_iStart = 0;
		int l_iEnd = 1;
		
		for(int i=0; i<l_iPermutationLen; i++) {
			
			// save permutation
			l_iaPermutationList[i] = p_iaSetArray.clone();
			
			// right hand swap
			MyUtil.swapArray(p_iaSetArray, l_iStart, l_iEnd);
			
			// define offset
			l_iStart++;
			if(l_iStart == l_iN)
				l_iStart = 0;
			l_iEnd++;
			if(l_iEnd == l_iN)
				l_iEnd = 0;
		}
		
		return l_iaPermutationList;
		
	}

	public static int factorial(int p_iNum) {
		
		if(p_iNum > 12)
			throw new RuntimeException("parameter too big");
		
		int l_iResult = 1;
		
		if(p_iNum > 1) {
			for(int i = 2; i <= p_iNum; i++) {
				l_iResult *= i;
			}
		}
		
		return l_iResult;
	}

	public static int[] factorialBig(int p_iNum) {
		
		int[] l_iResult = {1};
		
		if(p_iNum > 1) {
			for(int i = 2; i <= p_iNum; i++) {
				l_iResult = multipleBig(l_iResult, MyUtil.intToArray(i));
			}
		}
		
		return l_iResult;
	}
	
	public static int[] multipleBig(int[] p_iaA, int[] p_iaB) {
		
		int[] l_iaResult = new int[p_iaA.length + p_iaB.length];
		int l_iOffs = l_iaResult.length - 1;
		
		for(int i=p_iaA.length-1; i>=0; i--) {
			int l_iA = p_iaA[i];
			
			for(int j=p_iaB.length-1; j>=0; j--) {
				int l_iB = p_iaB[j];
				int l_iResult = l_iA * l_iB;
				
				l_iaResult[l_iOffs] += l_iResult;
				if(l_iaResult[l_iOffs] > 9) {
					int l_iTemp = l_iaResult[l_iOffs];
					l_iaResult[l_iOffs] = l_iTemp % 10;
					l_iaResult[l_iOffs - 1] += l_iTemp / 10;
				}
				l_iOffs--;
			}
			
			l_iOffs += p_iaB.length-1;
		}
		
		
		return (l_iaResult[0] != 0) ? l_iaResult : Arrays.copyOfRange(l_iaResult, 1, l_iaResult.length);
	}
	
	public static int highestCommonFactor(int... p_iaNumbers) {
		
		int[] l_iaHighestCommonFactor = commonFactor(p_iaNumbers);
		
		// return the highest value
		return l_iaHighestCommonFactor[l_iaHighestCommonFactor.length - 1];
	}
	
	public static int[] commonFactor(int... p_iaNumbers) {
		
		// get the smallest number
		MyUtil.quickSort(p_iaNumbers, 0, p_iaNumbers.length-1);
		int l_iSmallestNumber = p_iaNumbers[0];
		
		// initialize HCF value
		int[] l_iHighestCommonFactor = new int[l_iSmallestNumber];
		int l_iOffs = 0;
		
		for(int i = 1; i <= l_iSmallestNumber; i++) {
			
			// initialize HCF flag
			boolean l_zFlag = true;
			
			// check all parameter number
			for(int j = 0; j < p_iaNumbers.length; j++) {
				l_zFlag = l_zFlag & ((p_iaNumbers[j] % i == 0) ? true:false);
			}
			
			// check HCF value
			if (l_zFlag) {
				l_iHighestCommonFactor[l_iOffs++] = i;
			}
		}
		
		// return all values
		return Arrays.copyOf(l_iHighestCommonFactor, l_iOffs);
	}
	
	public static int lowestCommonMultiple(int... p_iaNumbers) {
		
		// return the lowest value
		return commonMultiple(p_iaNumbers)[0];
	}
	
	public static int[] commonMultiple(int... p_iaNumbers) {
		
		// get the highest number
		MyUtil.quickSort(p_iaNumbers, 0, p_iaNumbers.length-1, true);
		int l_iHighestNumber = p_iaNumbers[0];
		
		// multiple all values as maximum iteration to check LCM
		int l_iMaxIteration = 1;
		for(int i = 0; i < p_iaNumbers.length; i++) {
			l_iMaxIteration *= p_iaNumbers[i];
		}
		
		// initialize LCM value
		int[] l_iLowestCommonMultiple = new int[l_iMaxIteration - l_iHighestNumber];
		int l_iOffs = 0;
		
		for(int i = l_iHighestNumber; i <= l_iMaxIteration; i++) {
			
			// initialize LCM flag
			boolean l_zFlag = true;
			
			// check all parameter number
			for(int j = 0; j < p_iaNumbers.length; j++) {
				l_zFlag = l_zFlag & ((i % p_iaNumbers[j] == 0) ? true:false);
			}
			
			// check LCM value
			if (l_zFlag) {
				l_iLowestCommonMultiple[l_iOffs++] = i;
			}
			
		}
		
		// return all values
		return Arrays.copyOf(l_iLowestCommonMultiple, l_iOffs);
	}

}
