package com.collections.test.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*最近用户报告了一个crash，错误堆栈如下：

[java] view plain copy

    java.lang.IllegalArgumentException: Comparison method violates its general contract!  
        at java.util.TimSort.mergeHi(TimSort.java:864)  
        at java.util.TimSort.mergeAt(TimSort.java:481)  
        at java.util.TimSort.mergeForceCollapse(TimSort.java:422)  
        at java.util.TimSort.sort(TimSort.java:219)  
        at java.util.TimSort.sort(TimSort.java:169)  
        at java.util.Arrays.sort(Arrays.java:2010)  
        at java.util.Collections.sort(Collections.java:1883)  
        at com.xxx.model.c.a(Unknown Source)  
        at com.xxx.view.de.doInBackground(Unknown Source)  
        at android.os.AsyncTask$2.call(AsyncTask.java:288)  
        at java.util.concurrent.FutureTask.run(FutureTask.java:237)  
        at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:231)  
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112)  
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587)  
        at java.lang.Thread.run(Thread.java:841)  


这个app运行了一年多还是第一次碰到这个crash。TimSort又是什么鬼？

简单的讲TimSort是一个优化后的merge sort，比原来的merge sort更稳定、更快。

两大特点：

1. 对已接近排序和倒序的数组排序速度异常快。

2. 最差情况仍是O(n*log(n))

JDK7的排序算法实现改为TimSort。java.util.TimSort的类注释。

[java] view plain copy

    */
/** 
     * A stable, adaptive, iterative mergesort that requires far fewer than 
     * n lg(n) comparisons when running on partially sorted arrays, while 
     * offering performance comparable to a traditional mergesort when run 
     * on random arrays.  Like all proper mergesorts, this sort is stable and 
     * runs O(n log n) time (worst case).  In the worst case, this sort requires 
     * temporary storage space for n/2 object references; in the best case, 
     * it requires only a small constant amount of space. 
     * 
     * This implementation was adapted from Tim Peters's list sort for 
     * Python, which is described in detail here: 
     * 
     *   http://svn.python.org/projects/python/trunk/Objects/listsort.txt 
     * 
     * Tim's C code may be found here: 
     * 
     *   http://svn.python.org/projects/python/trunk/Objects/listobject.c 
     * 
     * The underlying techniques are described in this paper (and may have 
     * even earlier origins): 
     * 
     *  "Optimistic Sorting and Information Theoretic Complexity" 
     *  Peter McIlroy 
     *  SODA (Fourth Annual ACM-SIAM Symposium on Discrete Algorithms), 
     *  pp 467-474, Austin, Texas, 25-27 January 1993. 
     * 
     * While the API to this class consists solely of static methods, it is 
     * (privately) instantiable; a TimSort instance holds the state of an ongoing 
     * sort, assuming the input array is large enough to warrant the full-blown 
     * TimSort. Small arrays are sorted in place, using a binary insertion sort. 
     * 
     * @author Josh Bloch 
     */


/*具体算法原理这里不做细究，有兴趣的可以研究一下上面注释中的链接或者论文。

解决方法为在实现Comparator<T>接口时，必须严格遵循实现约束，否则会导致运行时异常。 

具体约束为：

    sgn(compare(x, y)) == -sgn(compare(y, x)) for all x and y. 
    ((compare(x, y)>0) && (compare(y, z)>0)) implies compare(x, z)>0.
    compare(x, y)==0 implies that sgn(compare(x, z))==sgn(compare(y, z)) for all z.

 

错误示例：（JDK6下运行正常，JDK7下会crash）

public class XXXComparator implements Comparator<SomeModel> {

    // 当lhs==rhs时，compare(lhs,rhs)== -1, compare(rhs, lhs)==-1 违背约束1
    @Override
  public int compare(SomeModel lhs, SomeModel rhs) {
        if (rhs.value < lhs.value) {
            return 1;
        } else {
            return -1;
        }
    }

} */

/**
 * @author puyf
 */
public class SortTest {
	class Dog {
		public int age;
		public String name;

		public Dog(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Dog [age=" + age + ", name=" + name + "]";
		}
	}

	public static void main(String[] args) {
		List<Dog> list = new ArrayList<>();
		list.add(new SortTest().new Dog(5, "DogA"));
		list.add(new SortTest().new Dog(6, "DogB"));
		list.add(new SortTest().new Dog(7, "DogC"));

		Collections.sort(list, new Comparator<Dog>() {
			@Override
			public int compare(Dog o1, Dog o2) {
				return o2.age - o1.age;
			}
		});
		System.out.println("给狗狗按照年龄倒序：" + list);

		Collections.sort(list, new Comparator<Dog>() {
			@Override
			public int compare(Dog o1, Dog o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		System.out.println("给狗狗按名字字母顺序排序：" + list);
	}
}
