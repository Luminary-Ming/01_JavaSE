package com.neuedu.demo.i_collection;

import java.util.*;

/**
 * JavaSE 中的集合框架：
 * Iterable ：实现 Iterable 接口的类才能使用 for-Each 遍历（增强for），并且才能使用迭代器（Iterator）。
 * <p>
 * Collection ：它继承了 Iterable 接口，单列集合，存放单个元素，每个元素只有一个值，元素之间没有关联，并且长度可变。
 * List ： 可重复，有索引下标，有序。
 * ArrayList ：底层是可变数组，默认容量是 10，超出容量后会自动扩容到原来的 1.5 倍。多线程不安全。
 * 查询效率高，但插入和删除元素效率较低。（因为存在下标，插入或删除元素后所有元素下标都要改动，需要移动大量元素）。
 * LinkedList ：底层是链表结构，使用头尾的方法操作，多线程操作不安全，可以模拟数据结构 ：队列（FIFO）或者堆栈（FILO）。
 * 插入和删除元素效率较高，但是随机访问效率低。（双向链表，元素之间通过指针连接。）
 * vector ：在 JDK1.2 的时候才有的集合体系，在 JDK1.0 时期，Vector的集合结构与 ArrayList 相同，在 JDK1.2 之后成为 List 接口的实现类。
 * <p>
 * Set ：不可重复，无序，没有索引。
 * TreeSet ：底层是二叉树（红黑树），可以对存储在其中的数据进行排序。
 * 1. 给TreeSet集合中存储的元素需要具备比较大小的方法，因此需要去实现 Comparable 接口
 * *       2. 如果存储的元素没有实现 Comparable 接口，也可以在 new TreeSet 对象的时候，给其传递一个 Comparator 的实现类对象
 * HashSet ：底层是哈希表，不保证存和取的顺序。
 * 需要存储的对象复写 Object 类中的 hashCode() 和 equals() 方法。
 * <p>
 * Map：没有继承 Iterable 接口，不能直接使用 forEach 和迭代器对 map 进行遍历，双列集合，存储一对元素，即键值对（key，value）。
 * <p>
 * 迭代器：
 * Iterator ：是 JDK1.2 存在的迭代器主要用于遍历 Collection 下面的所有集合。
 * Enumeration ：是 JDK1.0 时期的迭代器，用于遍历 Vector 集合。
 * <p>
 * 二叉搜索树要么是一颗空树, 要么是具有如下性质的二叉树
 * 1. 若它的左子树不为空, 则左子树所有节点的值小于根节点的值
 * 2. 若它的右子树不为空, 则右子树所有节点的值大于根节点的值
 * 3. 它的左右子树也是二叉搜索树
 */
public class CollectionFramework {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        // 迭代器
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("--------------------------------------------------------");

        Vector<String> vector = new Vector<>();
        vector.addElement("fff");
        vector.addElement("ggg");
        vector.addElement("hhh");
        vector.addElement("jjj");
        vector.addElement("kkk");
        // 枚举迭代器
        Enumeration<String> en = vector.elements();
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }

        // Person 类实现 Comparable 接口
        TreeSet<Person> set = new TreeSet<>();
        set.add(new Person());
        // 传递一个 Comparator 的实现类对象
        TreeSet<User> set2 = new TreeSet<>((o1, o2) -> {
            return 0;
        });
        set2.add(new User());
    }
}
