package sortcomparison;

import static java.lang.Math.*;
import static sbcc.Core.*;

import java.lang.reflect.*;
import java.util.*;

import org.apache.commons.lang3.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(String[] data, int fi, int n) {
		int propIndex, sortedIndex = fi;
		for (var i = fi + 1; i < fi + n; i++) {
			String temp;
			// Search for smaller value. This will be temp.
			if (data[i].compareTo(data[sortedIndex]) < 0) {
				temp = data[i];
				// Find propIndex;
				propIndex = sortedIndex;
				while (propIndex > fi && temp.compareTo(data[propIndex - 1]) < 0) {
					propIndex--;
				}
				System.arraycopy(data, propIndex, data, propIndex + 1, sortedIndex - propIndex + 1);
				// Move elements from propInex to sorted by 1.
				/*
				 * for (var j = sortedIndex; j >= propIndex; j--) { data[j + 1] = data[j]; }
				 */
				// Insert temp at correct location
				data[propIndex] = temp;
			}
			// Update indexes
			sortedIndex++;
		}

	}


	@Override
	public void quickSort(String[] data, int fi, int n) {
		// If arraysize is greater than 1 and less than 15, use insertionSort
		if (n > 1) {
			if (n <= 15) {
				insertionSort(data, fi, n);
			} else {// Partition array
				int pivIndex = partition(data, fi, n);
				// Compute left and right size
				int leftSize = pivIndex - fi;
				int rightSize = (n - leftSize) - 1;
				quickSort(data, fi, leftSize);
				quickSort(data, pivIndex + 1, rightSize);
			}
		}
	}


	@Override
	public int partition(String[] data, int fi, int n) {
		// Find the pivot using the median-of-three method.
		// Select three random indexes and find the median of their values.
		int max = fi + n, min = fi;
		String[] randArr = new String[3];
		int[] randIndexes = new int[3];
		for (var i = 0; i < 3; i++) {
			int randIndex = (int) (random() * (max - min) + min);
			randIndexes[i] = randIndex;
			randArr[i] = data[randIndex];
		}
		insertionSort(randArr, 0, 3);

		// Find the index of the middle value in array.
		String pivotVal = randArr[1];
		int pivotIndex = 0;
		for (var i = 0; i < 3; i++) {
			if (data[randIndexes[i]].compareTo(pivotVal) == 0)
				pivotIndex = randIndexes[i];
		}
		// Swap median value with value at firstIndex.
		String temp1 = pivotVal;
		data[pivotIndex] = data[fi];
		data[fi] = temp1;
		pivotIndex = fi;

		int left = fi + 1, right = fi + n - 1;
		// Throw rocks over fence.
		while (left < right) {
			while (left < right && data[left].compareTo(pivotVal) <= 0) {
				left++;
			}
			while (right > pivotIndex && data[right].compareTo(pivotVal) > 0) {
				right--;
			}
			if (left < right) {
				// Swap left and right values.
				String temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			}
		}
		if (data[right].compareTo(pivotVal) <= 0) {
			String temp2 = data[right];
			data[right] = data[pivotIndex];
			data[pivotIndex] = temp2;
			return right;
		} else
			return pivotIndex;

	}


	@Override
	public void mergeSort(String[] data, int fi, int n) {
		if (n > 1) {
			if (n <= 15) {
				insertionSort(data, fi, n);
			} else {
				// Compute size of two halves.
				// If size is odd, right half gets extra element.
				int leftSize = n / 2;
				int rightSize;
				if (n % 2 == 1)
					rightSize = leftSize + 1;
				else
					rightSize = leftSize;
				mergeSort(data, fi, leftSize);
				mergeSort(data, fi + leftSize, rightSize);
				// Call merge
				merge(data, fi, leftSize, rightSize);
			}
		}
	}


	@Override
	public void merge(String[] data, int fi, int nl, int nr) {
		// Create a new string array with the size of both segments.
		int size = nl + nr;
		String[] tempArr = new String[size];
		int leftIndex = fi, rightIndex = fi + nl;
		int i = 0;
		while (tempArr[size - 1] == null) {
			// Left side is less than or equal to right.
			if (leftIndex < fi + nl && data[leftIndex].compareTo(data[rightIndex]) <= 0) {
				tempArr[i] = data[leftIndex];
				leftIndex++;
			} else if (rightIndex <= fi + size - 1) {
				tempArr[i] = data[rightIndex];
				rightIndex++;
			}
			i++;
		}
		// Copy elements back into original array.
		for (var j = 0; j < size - 1; j++) {
			data[fi + j] = tempArr[j];
		}

	}


	@Override
	public void heapSort(String[] data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(String[] data) {
		// TODO Auto-generated method stub

	}

}
