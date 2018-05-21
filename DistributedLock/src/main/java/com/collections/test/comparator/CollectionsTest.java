package com.collections.test.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * Comparable与Comparator的区别

Comparable和Comparator都是用来实现集合中元素的比较、排序的。
Comparable是在集合内部定义的方法实现的排序，位于java.util下。
Comparator是在集合外部实现的排序，位于java.lang下。

Comparable是一个对象本身就已经支持自比较所需要实现的接口，如String、Integer自己就实现了Comparable接口，可完成比较大小操作。自定义类要在加入list容器中后能够排序，也可以实现Comparable接口，在用Collections类的sort方法排序时若不指定Comparator，那就以自然顺序排序。所谓自然顺序就是实现Comparable接口设定的排序方式。


Comparator是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足要求时，可写一个比较器来完成两个对象之间大小的比较。Comparator体现了一种策略模式(strategy design pattern)，就是不改变对象自身，而用一个策略对象(strategy object)来改变它的行为。

总而言之Comparable是自已完成比较，Comparator是外部程序实现比较。


分析二：

再接下来我用小结描述下二者的不同：

1、Comparator在集合（即你要实现比较的类）外进行定义的实现，而Comparable接口则是在你要比较的类内进行方法的实现。这样看来Comparator更像是一个专用的比较器。

2、Comparator实现了算法和数据的分离，从代码也可以看出，其实这和第一点是相辅相成的，因为Comparable依赖于某一个需要比较的类来实现。

3、Comparable支持自比较，自比较是指比如String等类里面本身就有CompareTo()方法，直接就可以进行String类对象的比较，这也可以从较之Comparator,Comparable中Arrays.sort()方法中只带数组参数的形式与书上例子更相似这点看出。 

4、从第3点延伸，我们可以看到当不满足于自比较函数，如String类时，我们试图改写规则要怎么办——通过Comparator因为它支持外比较，它是分离的。

5、当一个又一个类设计完成后，或许我们最初没有设想到类的比较问题，而没使用Comparable接口，那我们之后可以通过Comparator来完成，而同时无需改变之前完成的类的构建。

6、运用Arrays.sort()方法时，注意二者的参数不同，Comparator多了一个参数，这第二个参数是使用Comparator接口的那个被视为专用比较器的类的对象，如汪同学例子中的new ByWeightComparator()。

其实大部分情况下我们并不需要刻意去对二者做选择，哪个用得顺手就用哪个，但当你的习惯遭遇某种问题时，这样的区别分析可以让你不妨换个方向思考，不至于走入死胡同。

以上分析多是个人理解，如有纰漏，还望加以修正。
 * 
 * 
*/

public class CollectionsTest {
	static int aa[] = { 10, 23, 40, 55, 34 };

	public static void main(String[] args) {
		test();
		setTest();
	}

	static void test() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}

		// String[] array= (String[]) list.toArray();
		Collections.shuffle(list);

		System.out.println(list);
	}

	static void setTest() {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			set.add("Set" + i);// object里有重复的数据，采用set去除重复的数据
		}

		String[] devOnlyIds = new String[set.size()];
		set.toArray(devOnlyIds);

		System.out.println(set);
	}
}
