package com.beetexting.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeTwoSortedArrays {
	
	public static void main(String[] args) {
		
		int[] arr1 = {1,3,5,7};
		int[] arr2 = {2,4,6,8};
		
		MergeTwoSortedArrays mergeTwoSortedArrays = new MergeTwoSortedArrays();
		int[] response = mergeTwoSortedArrays.mergeTwoSortedArrays(arr1, arr2);
		System.out.println(response.length);
	}
	
	public int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
		
		int[] response = new int[arr1.length + arr2.length];
		
		int[] arr = new int[arr1.length + arr2.length];
		int count = 0;
		for(int i: arr1) {
			arr[count] = i;
			count++;
		}
		for(int i: arr2) {
			arr[count] = i;
			count++;
		}
		
		List<Integer> sortedIntegers = Arrays.stream(arr).sorted().boxed().collect(Collectors.toList());
		
		count = 0;
		for(Integer i: sortedIntegers) {
			response[count] = i;
			count++;
		}
		
		return response;
	}

}
