package com.neuedu.demo.i_collection;

import java.util.*;

/*
   JavaSE 中的集合框架：
   ■ Iterable ：实现 Iterable 接口的类才能使用 for-Each 遍历（增强for），并且才能使用迭代器（Iterator）。

   ■ Collection ：它继承了 Iterable 接口，单列集合，存放单个元素，每个元素只有一个值，元素之间没有关联，并且长度可变。
   集合的“顺序”，通常指的是元素的存储顺序和迭代顺序。在 List 中，这个顺序与添加顺序相同；而在 Set 中，这个顺序通常是不确定的。
        ● List ：可重复，有索引下标，有序（存储顺序和迭代顺序相同）
           ● ArrayList ：底层是可变数组，默认容量是 10，超出容量后会自动扩容到原来的 1.5 倍。（线程不安全）
             查询效率高，但插入和删除元素效率较低。（因为存在下标，插入或删除元素后所有元素下标都要改动，需要移动大量元素）。
           ● LinkedList ：底层是链表结构，使用头尾的方法操作，可以模拟数据结构 ：队列（FIFO）或者堆栈（FILO）。
             插入和删除元素效率较高，但是随机访问效率低。（双向链表，元素之间通过指针连接。）（线程不安全）

  ■ vector ：在 JDK1.2 的时候才有的集合体系，在 JDK1.0 时期，Vector 的集合结构与 ArrayList 相同，在 JDK1.2 之后成为 List 接口的实现类。（线程安全）

        ● Set ：不可重复，没有索引，无序（存储顺序和迭代顺序不同）
          ● TreeSet ：底层是二叉树（红黑树），可以对存储在其中的数据进行排序。（线程不安全）
          1. 给 TreeSet 集合中存储的元素需要具备比较大小的方法，因此需要去实现 Comparable 接口
          2. 如果存储的元素没有实现 Comparable 接口，也可以在 new TreeSet 对象的时候，给其传递一个 Comparator 的实现类对象
          ● HashSet ：基于哈希表的，它根据元素的哈希码来决定元素在内部数组中的位置，因此迭代顺序通常与添加顺序不同，并且是不确定的，不保证存和取的顺序。
          需要存储的对象复写 Object 类中的 hashCode() 和 equals() 方法。（线程不安全）

  ■ Map：没有继承 Iterable 接口，不能直接使用 forEach 和迭代器对 map 进行遍历，双列集合，存储一对元素，即键值对（key，value）。
         可以使用 entrySet() 方法，返回一个存有 Map 集合所有键值对的 Set 集合，然后再去遍历。

         ● HashMap：允许键和值都为null，默认初始容量为16，当已用容量超过总容量乘以负载因子（默认0.75）时，会进行扩容，扩容规则为当前容量翻倍。（线程不安全）
         HashMap的底层是一个数组，数组中的每个元素都是一个桶（Bucket），也称为位桶或哈希桶。这个数组的类型是Node[]，其中Node是HashMap的一个静态内部类，用于封装键值对。
         每个Node对象都包含了key、value、hash和next四个属性，其中key是键，value是与键相关联的值，hash是键的哈希值（用于计算数组下标），next是指向下一个Node的引用（用于形成链表）。
         JDK 1.8及之后，当链表长度超过阈值（默认为8）并且数组容量大于64时，链表会被转换为红黑树。

          1.哈希冲突
          哈希冲突的根本原因在于哈希函数的有限性和键的无限性。哈希函数将任意长度的输入（键）映射到固定长度的输出（哈希值），
          而哈希表的桶的数量是有限的。因此，当大量的键被映射到有限的桶中时，就必然会出现不同的键映射到同一个桶的情况。
          哈希冲突会导致数据检索效率下降。

          2.解决哈希冲突的方法
          ● 选择合适的哈希函数：选择一个好的哈希函数可以显著减少冲突的发生。一个好的哈希函数应该能够将键均匀地分布到哈希表中。
          ● 定期优化哈希函数：随着密码学的发展和新的攻击方法的出现，应定期优化哈希函数以确保其安全性。
          ● 增加哈希表的容量：通过增加哈希表的桶的数量来降低冲突率。
          ● 使用动态哈希表：动态哈希表可以根据元素的数量自动调整其容量。当元素数量增加时，它会扩展哈希表的容量并重新分配元素；当元素数量减少时，它会缩小哈希表的容量以节省空间。

          3.哈希函数
          定义：
              哈希函数（Hash Function），又称散列函数，是一种将任意长度的数据映射为固定长度值（通常称为哈希值、散列码）的函数。
          特点：
              哈希函数可以接受任意长度的数据作为输入，如文件、文本、数字等。
              无论输入数据的长度如何，哈希函数都会生成一个固定长度的哈希值。
              对于相同的输入数据，哈希函数始终产生相同的哈希值。
          常见哈希函数：
              MD5：输出长度为128位。但由于其安全性问题（如碰撞攻击），现已不再推荐用于密码存储等敏感场景。
              SHA-1：输出长度为160位的哈希函数。但由于其安全性问题也逐渐被更安全的哈希函数所取代。
              SHA-256：属于SHA-2家族的一员，输出长度为256位。SHA-256在安全性上比MD5和SHA-1更强，因此被广泛应用于各种安全场景中。

         ● Hashtable：不允许键或值为null，默认初始容量为11。当已用容量超过总容量乘以负载因子（Hashtable的负载因子也是0.75）时，会进行扩容，扩容规则为当前容量翻倍加1。（线程安全）


  迭代器：
  Iterator ：是 JDK1.2 存在的迭代器主要用于遍历 Collection 下面的所有集合。
  Enumeration ：是 JDK1.0 时期的迭代器，用于遍历 Vector 集合。

  二叉搜索树要么是一颗空树, 要么是具有如下性质的二叉树
  1. 若它的左子树不为空, 则左子树所有节点的值小于根节点的值
  2. 若它的右子树不为空, 则右子树所有节点的值大于根节点的值
  3. 它的左右子树也是二叉搜索树
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

        System.out.println("--------------------------------------------------------");

        Map<String, String> map = new HashMap<>();
        map.put("疾风剑豪", "亚索");
        map.put("不灭狂雷", "狗熊");
        map.put("诡术妖姬", "乐芙兰");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry);
        }

        map.forEach((key, value) -> {
            System.out.println(key + "->" + value);
        });

    }
}
