//4
class ArraySort {
	private int temp[] = null;

	public ArraySort(int temp[]) {
		this.temp = temp;
	}

	public void exchange(int A[], int i, int j) // 交换A[i]和A[j]
	{
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	/* 插入排序 */
	public void insertionSort() {
		/*
		 * 插入排序 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2) 最优时间复杂度 ---
		 * 最好情况为输入序列是升序排列的,此时时间复杂度O(n) 平均时间复杂度 ---- O(n^2) 所需辅助空间 ------ O(1)
		 * 11到已排序的元素小于或者等于新元素的位置 5.将新元素插入到该位置后 6.重复步骤2~5
		 */

		int get, j;
		for (int i = 1; i < temp.length; i++) { // 类似抓扑克牌排序
			get = temp[i]; // 右手抓到一张扑克牌
			j = i - 1; // 拿在左手上的牌总是排序好的
			while (j >= 0 && temp[j] > get) // 将抓到的牌与手牌从右向左进行比较
			{
				temp[j + 1] = temp[j]; // 如果该手牌比抓到的牌大,就将其右移
				j--;
			}
			temp[j + 1] = get;// 直到该手牌比抓到的牌小(或二者相等),将抓到的牌插入到该手牌右边(相等元素的相对次序未变,所以插入排序是稳定的)

		}
	}

	/* 插入排序的改进：二分插入排序 */
	public void insertionSort_Perfection1() {
		/*
		 * 　插入排序的改进：二分插入排序 最差时间复杂度 ---- O(n^2) 最优时间复杂度 ---- O(nlogn)
		 * 平均时间复杂度 ---- O(n^2) 所需辅助空间 ------ O(1) 稳定性 ------------ 稳定
		 * n较大时，二分插入排序的比较次数比直接插入排序的最差情况好得多，但比直接插入排序的最好情况要差，所当以元素初始序列已经接近升序时，直接插入排序比二分插入排
		 * 序比较次数少。二分插入排序元素移动次数与直接插入排序相同，依赖于元素初始序列。*/
			 
		int j, get, left, right, middle;
		for (int i = 1; i < temp.length; i++) // 类似抓扑克牌排序
		{
			get = temp[i]; // 右手抓到一张扑克牌
			left = 0; // 拿在左手上的牌总是排序好的,所以可以用二分法
			right = i - 1; // 手牌左右边界进行初始化
			while (left <= right) // 采用二分法定位新牌的位置
			{
				middle = (left + right) / 2;
				if (temp[middle] > get)
					right = middle - 1;
				else
					left = middle + 1;
			}
			for (j = i - 1; j >= left; j--) // 将欲插入新牌位置右边的牌整体向右移动一个单位
			{
				temp[j + 1] = temp[j];
			}
			temp[left] = get; // 将抓到的牌插入手牌
		}
	}

	/*
	 * 希尔算法
	 * 
	 */
	public void shellSort() {
		/*
		 * 希尔算法 1.选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1； 2.按增量序列个数k，对序列进行k 趟排序；
		 * 3.每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进 直接插入排序。仅增量因子为1
		 * 时，整个序列作为一个表来处理，表长度即为整个序列的长 。
		 * 
		 */
		int j, get;
		int h = 0;
		while (h <= temp.length) // 生成初始增量
		{
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < temp.length; i++) {
				j = i - h;
				get = temp[i];
				while ((j >= 0) && (temp[j] > get)) {
					temp[j + h] = temp[j];
					j = j - h;
				}
				temp[j + h] = get;
			}
			h = (h - 1) / 3; // 递减增量
		}
	}

	/* 选择排序 */
	public void selctionSort() {
		/*
		 * 选择排序 最差时间复杂度 ---- O(n^2) 最优时间复杂度 ---- O(n^2) 平均时间复杂度 ---- O(n 2) 所需辅助空间
		 * ------ O(1) 稳定性 ------------ 不稳定 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置；
		 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。 以此类推，直到所有元素均排序完毕
		 */

		for (int i = 0; i < temp.length - 1; i++) // 已排序序列的末尾
		{
			int min = i;
			for (int j = i + 1; j < temp.length; j++) // 未排序序列
			{
				if (temp[j] < temp[min])// 依次找出未排序序列中的最小值,存放到已排序序列的末尾
				{
					min = j;
				}
			}
			if (min != i) {
				exchange(temp, min, i); // 该操作很有可能把稳定性打乱,所以选择排序是不稳定的排序算法
			}
		}
	}

	public void simpleSelctionSort_Perfection1() {
	}

	public void heapSort() {
	}

	/* 冒泡算法 */
	public void bubbleSort() {
		/*
		 * 冒泡算法 最差时间复杂度 ---- O(n^2) 最优时间复杂度 ---- 如果能
		 * 内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n) 平均时间复杂度 ---- O(n^2) 所需辅助空间
		 * ------ O(1) 稳定性 ------------ 稳定
		 */

		for (int i = 0; i < temp.length - 1; i++)
			for (int j = 0; j < temp.length - i - 1; j++) {
				if (temp[j] > temp[j + 1]) {
					exchange(temp, j + 1, j);
				}
			}
	}

	/*
	 * 冒泡算法的改进1 假如标志性变量，由于标志某一趟排序过程中是否有数据交换 如果进行某一趟排序时并没有进行数据交换，则说明数据已经按照要求排序好
	 * 可立即结束排序
	 */

	public void bubbleSort_Perfection1() {
		int i = temp.length - 1; // 初始时,最后位置保持不变
		while (i > 0) {
			int pos = 0; // 每趟开始时,无记录交换
			for (int j = 0; j < i; j++)
				if (temp[j] > temp[j + 1]) {
					pos = j; // 交换的位置
					exchange(temp, j, j + 1);
				}
			i = pos; // 为下一趟排序作准备
		}
	}

	/* 冒泡算法的改进2 */
	public void bubbleSort_Perfection2() {
		/*
		 * 冒泡算法的改进2 数据结构 ---------- 数组 最差时间复杂度 ---- O(n^2) 最优时间复杂度 ---- 如果序
		 * 在一开始已经大部分排序过的话,会接近O(n) 平均时间复杂度 ---- O(n^2) 所需辅助空间 --- -- O(1) 稳定性
		 * ------------ 稳定 鸡尾酒排序 也叫定向冒泡排序，是冒泡排序的一种改进。 此算法与冒泡排序的不同处在于从低到高然后从高到低，
		 * 而冒泡排序则仅从低到高去比较序列里的每个元素。 他可以得到比冒泡排序稍微好一点的效能。
		 */

		int left = 0; // 初始化边界
		int right = temp.length - 1;
		while (left < right) {
			for (int i = left; i < right; i++) // 将最大元素放到后面
				if (temp[i] > temp[i + 1]) {
					exchange(temp, i, i + 1);
				}
			right--;
			for (int i = right; i > left; i--) // 将最小元素放到前面
				if (temp[i - 1] > temp[i]) {
					exchange(temp, i - 1, i);
				}
			left++;
		}
	}

	public void quickSort() {

	}

	public void quickSort_perfection() {

	}

	public void radixSort() {

	}

	public void print() {
		for (int i = 0; i < temp.length; i++)
			System.out.print(temp[i] + "\t");
	}
}

public class Sort {
	public static void main(String args[]) {
		int temp[] = { 51, 43, 48, 36, 82, 15, 62, 45, 84, 36 };
		ArraySort A = new ArraySort(temp);
		A.print();
		A.bubbleSort();
		// A.selctionSort();
		// A.insertionSort();
		// A.insertionSort_Perfection1();
		System.out.println("\n");
		// A.bubbleSort_Perfection1();
		// A.bubbleSort_Perfection2();
		A.print();
	}

}