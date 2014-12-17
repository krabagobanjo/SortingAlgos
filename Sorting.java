import java.util.Random;
import java.util.LinkedList;

/**
  * Sorting implementation
  * CS 1332 : Fall 2014
  * @author Kyle Rabago-Banjo
  * @version 1.0
  */
public class Sorting implements SortingInterface {

    // Do not add any instance variables.

    @Override
    public <T extends Comparable<? super T>> void bubblesort(T[] arr) {
        T temp;
        for (int i = 0; i < (arr.length); i++) {
            for (int j = (i + 1); j < (arr.length); j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Override
    public <T extends Comparable<? super T>> void insertionsort(T[] arr) {
        T temp = null;
        int j;
        for (int i = 0; i < (arr.length); i++) {
            j = i;
            temp = arr[j];
            while (j > 0 && arr[j - 1].compareTo(temp) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    @Override
    public <T extends Comparable<? super T>> void selectionsort(T[] arr) {
        T temp;
        for (int i = 0; i < (arr.length); i++) {
            int minDex = findIndexOfMinimum(i, (arr.length), arr);
            if (minDex != i) {
                temp = arr[minDex];
                arr[minDex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * Finds index of min val
     * @param initial index
     * @param length
     * @return index of minimum val
     */
    private <T extends Comparable<? super T>> int findIndexOfMinimum(int index, int length, T[] arr) {
        int minDex = index;
        for (int i = index; i < length; i++) {
            if (arr[i].compareTo(arr[minDex]) < 0) {
                minDex = i;
            }
        }
        return minDex;
    }

    @Override
    public <T extends Comparable<? super T>> void quicksort(T[] arr, Random r) {
        qSort(arr, 0, arr.length - 1, r);
    }

    private <T extends Comparable<? super T>> void qSort(T[] arr, int l, int r, Random rand) {
        if (l < r) {
            int p = partition(arr, l, r, rand);
            if (l < p - 1) {
                qSort(arr, l, p - 1, rand);
            }
            if (p < r) {
                qSort(arr, p, r, rand);
            }
        }
    }

    private <T extends Comparable<? super T>> int partition(T[] arr, int l, int r, Random rand) {
        int pInd = rand.nextInt(arr.length);
        T pVal = arr[pInd];
        T temp = pVal;
        int i = l;
        int j = r;
        while (i <= j) {
            while (arr[i].compareTo(pVal) < 0) {
                i++;
            }
            while (arr[j].compareTo(pVal) > 0) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    @Override
    public <T extends Comparable<? super T>> void mergesort(T[] arr) {
        T[] temp = (T[]) new Comparable[arr.length];
        mSort(arr, temp, 0, arr.length - 1 );
    }

    private <T extends Comparable<? super T>> void mSort(T[] a, T[] temp,
            int l, int r) {
        if(l < r) {
            int mid = (l + r) / 2;
            mSort(a, temp, l, mid);
            mSort(a, temp, mid + 1, r);
            merge(a, temp, l, mid + 1, r);
        }
    }

    private <T extends Comparable<? super T>> void merge(T[] a, T[] temp,
        int l, int r, int rEnd ) {
        int lEnd = r - 1;
        int tempPos = l;
        int tot = rEnd - l + 1;
        while(l <= lEnd && r <= rEnd )
            if(a[l].compareTo(a[r]) <= 0 )
                temp[tempPos++] = a[l++];
            else
                temp[tempPos++] = a[r++];
        while(l <= lEnd) {
            temp[tempPos++] = a[l++];
        }
        while(r <= rEnd) {
            temp[tempPos++] = a[r++];
        }
        for(int i = 0; i < tot; i++, rEnd-- )
            a[rEnd] = temp[rEnd];
    }

    @Override
    public int[] radixsort(int[] arr) {
        LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList<?>[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int largest = arr[0];
        int place = 1;
        int mod = 10;
        int bucket = 0;
        int j = 0;
        int work = 0;
        while (largest >= place) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > largest) {
                    largest = arr[i];
                }
                if (arr[i] < 0) {
                    work = arr[i] * -1;
                } else {
                    work = arr[i];
                }
                bucket = work % mod / place;
                buckets[bucket].add(arr[i]);
            }
            j = 0;
            for (int i = 0; i < buckets.length; i++) {
                while (buckets[i].peek() != null) {
                    arr[j++] = buckets[i].remove();
                }
            }
            mod *= 10;
            place *= 10;
        }
        return arr;
    }

}
