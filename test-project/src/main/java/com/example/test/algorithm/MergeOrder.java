package com.example.test.algorithm;

/**
 * 归并排序实现
 * @Author
 * @Date ${date}
 */
public class MergeOrder {

	public void sortBegin(int[] target, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			sortBegin(target, left, middle);
			sortBegin(target, middle + 1, right);
			sort(target, left, right);
		}

	}

	private void sort(int[] target, int left, int right) {
		if (left == right)
			return;
		// 可以优化的点：把temp数组传入减少临时数组创建次数
		int[] temp = new int[right - left + 1];
		int middle = (left + right) / 2;
		int l = left, r = middle + 1, t = 0;
		while (l <= middle && r <= right) {
			if (target[l] <= target[r]) {
				temp[t++] = target[l++];
			} else {
				temp[t++] = target[r++];
			}

		}
		while (l <= middle) {
			temp[t++] = target[l++];
		}
		while (r <= right) {
			temp[t++] = target[r++];
		}
		int c = 0;
		while (left <= right) {
			target[left++] = temp[c++];
		}

	}

	public static void main(String[] args) {
		MergeOrder mergeOrder = new MergeOrder();
		int[] target = { 1, 6, 2, 10, 3, 44, 22, 10 };
		mergeOrder.sortBegin(target, 0, target.length - 1);
		int temp = 0;
		while (temp < target.length) {
			System.out.println(target[temp++]);
		}
	}
}
