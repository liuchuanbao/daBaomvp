package cn.efarm360.com.dabaomvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cn.efarm360.com.dabaomvp.R;

/**
 *  lanbda 语法的使用 demo
 */
public class LambdaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        /**
         * 线程的使用
         */
        new Thread(() -> System.out.println("Run!")).start();
        Runnable r1 = () -> {
            System.out.println("Hello Lambda!");
        };
        Object o = (Runnable) () -> {
            System.out.println("hi");
        }; // correct

//        IClient listener1= (int i,int j)->{return  i+j;};
        IClient listener1= (i,j)->i+j;
        listener1.add(8,9);

        dabao das = (s,w)->s+w;
              das.add(8,9);
               das.runAble();

//Prior Java 8 :
//        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        for (String feature : features) {
//            System.out.println(feature);
//        }
//In Java 8:

        /**
         *   3  list集合的循环
         */
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 用java8的方法引用更好，方法引用由::(双冒号)操作符来完成,看起来像c++中的作用域操作符
//        Java代码  收藏代码
        features.forEach(System.out::println);
        /**
         * 例4 使用Lambda表达式和函数式接口Predicate
         除了提供函数式编程语言级别的支持外，java8同时也新增了一个新的包java.util.function。其中包含了许多类来支持java函数式编程。
         其中之一是Predicate接口，使用这个接口和lamb表达式就可以以更少的代码为API方法添加更多的动态行为。
         以下是Predicate的使用范例，展示了过滤集合数据的许多共性。
         */
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));
        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));
        System.out.println("Print all languages :");
        filter(languages, (str) -> true);
        System.out.println("Print no language : ");
        filter(languages, (str) -> false);
        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);

        /**
         * 6.1 Map
         在这个例子中，我们要将costBeforeTax的每个元素以加上他们的增值税。传递一个Lambda表达式给map方法使之应用于每个元素，之后在用forEach打印结果。
         */
// Without lambda expressions:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }
   // With Lambda expression:
        List<Integer> costBeforeTaxs = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
        /**
         * 6.2 Reduce
         还有另外一个函数reduce可以将所有值转换为一个值。map跟reduce操作是函数式编程的核心，
         reduce也被称作折叠操作。reduce并不是一种新的操作，在SQL中我们用的一些聚集函数比如sum，avg，count等他们实际上也是reduce操作，
         因为他们也是将多个值进行操作然后返回一个值。Stream API定义了reduce函数，可以接受一个Lambda表达式然后组合所有值。
         Stream类中像IntStream都有内置的方法像average(), count(), sum(), mapToLong(), mapToDouble()等转换方法。
         我们可以用内置的方法也可以自定义。
         */
// Old way:
        List costBeforeTaxss = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
// New way:
        List costBeforeTaxsss = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum
                + cost).get();
        System.out.println("Total : " + bill);

       /**
        * 例8 给每个List元素应用函数
        在工作中我们经常会碰到这样的情况：给List中每个元素加以一定的操作例如乘以或者除以某个值等。这些操作用map方法再好不过了，
        我们可以将转换逻辑以Lambda表达式传给map方法来应用于每个元素：
        //将字符串转为大写然后用逗号连起来
        */

        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy","U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
        /**
         * 例9 复制不同值到子列表
         本例演示如何利用Stream类的distinct方法过滤重复值到集合中。
         */
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);
        /**
         *   10   List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
         List<Integer> distinct = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
         System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);
         */
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }


    /**
     *滤集合数据的许多共性。
     * @param names  List集合
     * @param condition      使用系统的Predocate
     */
//    public static void filter(List<String> names, Predicate<String> condition) {
//        for(String name: names) {
//            if(condition.test(name)) {
//                System.out.println(name + " ");
//            }
//        }
//    }
    //更佳的方式  可以看到Stream API的filter方法也接受一个Predicate，意味着可以用内联代码直接替换我们自定义的filter()方法。
    // 这就是Lambda表达式的威力所在。除此之外Predicate接口也可以测试多个条件，将会在下面的例子中加以说明。

    public static void  filter(List<String> names, Predicate<String> condition){
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
        /**
         * 例5: Lambda表达式结合Predicate
         就像上个例子所说，Predicate允许组合两个以上的条件，它提供了类似于逻辑与和或的操作and(),or()和xor()，
         这些方法可以用来组合传递到filter方法中的多个条件。例如为了获取所有以J开头并有四个字符长度的语言，
         可以定义两个单独的Predicate实例覆盖每个条件然后用and方法将他们组合在一起
         */
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        names.stream().filter(startsWithJ.and(fourLetterLong)).forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n));
        /**
         * 例7 用filter创建一个字符串List
         在java开发中对大的集合进行过滤是常用的操作。用Lambda表达式和Stream API会让操作变得简单易懂。
         Stream提供了一个filter()方法，接受一个Predicate对象。这意味着可以传递一个Lambda表达式作为过滤逻辑，看例子:
         */
        //      例7  /创建一个长度大于两个字符的字符串List
        List<String> filtered = names.stream().filter(x -> x.length()>
                2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", names, filtered);


    }

    /**
         * 自定义接口方法
         */
     @FunctionalInterface
     public interface IClient extends Runnable{
            int add(int i,int j);
             @Override
             default public  void run(){};
         }
    @FunctionalInterface
    public interface dabao{
        int add(int i,int j);
        default public  void runAble(){
            String ster = "dadabao";
                throw new RuntimeException("Stub!");
        };
    }
////    最典型的是Function：
//    @FunctionalInterface
//    public interface Function<T, R> {
//        R apply(T t);
//    }
////    这个接口代表一个函数，接受一个T类型的参数，并返回一个R类型的返回值。
////    另一个预定义函数接口叫做Consumer
////    ，跟Function的唯一不同是它没有返回值。
//    @FunctionalInterface
//    public interface Consumer<T> {
//        void accept(T t);
//    }
////    还有一个Predicate，用来判断某项条件是否满足。经常用来进行筛滤操作：
//
//    @FunctionalInterface
//    public interface Predicate<T> {
//        boolean test(T t);
//    }
}
