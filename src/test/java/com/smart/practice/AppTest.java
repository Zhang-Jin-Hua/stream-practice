package com.smart.practice;


import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void initStream() {
        //构建空的Stream
        Stream<Object> empty = Stream.empty();

        //构建非定长的Stream
        Stream<Object> objectStream = Stream.of(new Object(), new Object());

        //构建单个元素的Stream
        Stream<Object> ofStream = Stream.of(new Object());

        //将两个Stream合并成一个
        Stream<Object> concat = Stream.concat(objectStream, Stream.of());

        //根据传入的Supplier生成元素
        Stream<Object> generate = Stream.generate(Object::new);

        //返回一个无限长度的Stream，与generate方法不同的是，它是通过函数f对给指定的元素种子不停迭代而产生无限Stream
        Stream<Object> iterate = Stream.iterate(new Object(), Object::getClass);

        //集合类可直接转化为Stream
        Collection collection = new ArrayList();
        Stream collectionStream = collection.stream();

        //将数组转化为Stream
        IntStream stream = Arrays.stream(new int[0]);
    }

    @Test
    public void operator() {
        //根据传入的Supplier生成元素
        Stream<Object> generate = Stream.generate(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException e) {
                //
            }
            return new Object();
        });

        {
            List<Long> collect = generate
                    .map(object -> System.currentTimeMillis())
                    .peek(mills -> System.out.println("before filter:" + mills))
                    .filter(mills -> mills % 100 > 50)
                    .peek(mills -> System.out.println("after filter:" + mills))
                    .skip(2)
                    .limit(3)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            System.out.println(collect);
        }

        {
            Optional<Long> min = generate.limit(10)
                    .map(object -> System.currentTimeMillis())
                    .min(Comparator.naturalOrder());
        }

        {
            Optional<Object> min = generate.limit(10)
                    .min(Comparator.comparing(Object::hashCode));
        }

        {
            Optional<Object> max = generate.limit(10)
                    .max(Comparator.comparing(Object::hashCode));
        }

        {
            List<Object> collect = generate.limit(100)
                    .distinct()
                    .map(object -> {
                        ArrayList<Object> arrayList = new ArrayList<>();
                        arrayList.add(object);
                        return arrayList;
                    })
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

        {
            Optional<Object> first = generate.limit(100)
                    .distinct()
                    .filter(object -> object.hashCode() % 10 > 5)
                    .findFirst();

            Optional<Object> any = generate.limit(100)
                    .distinct()
                    .filter(object -> object.hashCode() % 10 > 5)
                    .findAny();
        }
    }
}