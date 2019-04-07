# stream-practice

## Stream 构建
#### API
<table>
      <tr>
          <td>empty</td>
            <td>Stream<T> empty()</td>
          <td>empty方法返回一个空的顺序Stream，该Stream里面不包含元素项。</td>
      </tr>
    <tr>
        <td>of</td>
          <td>Stream<T> of(T... values),
          Stream<T> of(T t)</td>
        <td>of方法，其生成的Stream是有限长度的，Stream的长度为其内的元素个数</td>
    </tr>
    <tr>
        <td>concat</td>
          <td>Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)</td>
        <td>将两个Stream合并成一个Stream</td>
    </tr>
    <tr>
        <td>generator</td>
          <td>Stream<T> generate(Supplier<T> s)</td>
        <td>返回无限长度的Stream,其元素由Supplier接口的提供</td>
    </tr>

    <tr>
        <td>iterate</td>
          <td> Stream<T> iterate(final T seed, final UnaryOperator<T> f) </td>
        <td>返回一个无限长度的Stream，与generate方法不同的是，它是通过函数f对给指定的元素种子不停迭代而产生无限Stream。
        f(1) = f(seed(0)), f(2) = f(seed(1))</td>
    </tr>

    <tr>
        <td>Collection.stream()</td>
          <td>Stream<E> stream()</td>
        <td>在Collection接口中，定义了一个默认方法stream()，用来生成一个Stream</td>
    </tr>
    <tr>
        <td>Arrays.stream()</td>
          <td>Stream<T> stream(T[] array)</td>
        <td>封装了针对数组的Stream方法，可以将数组转化为Stream</td>
    </tr>

</table>

### 示例
```java
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
```
## Stream 操作符
#### API
<table>
    <tr>
        <td>distinct</td>
        <td>Stream<T> distinct()</td>
        <td>去除掉Stream中重复的元素</td>
    </tr>

    <tr>
        <td>filter</td>
        <td>Stream<T> filter(Predicate<? super T> predicate)</td>
        <td>对原Stream按照指定条件过滤</td>
    </tr>

    <tr>
        <td>map</td>
        <td>Stream<R> map(Function<? super T, ? extends R> mapper)</td>
        <td>对于Stream中包含的元素使用给定的转换函数进行转换操作</td>
    </tr>

    <tr>
        <td>flatMap</td>
        <td>Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)</td>
        <td>与map方法类似，都是将原Stream中的每一个元素通过转换函数转换，不同的是，该换转函数的对象是一个Stream</td>
    </tr>

    <tr>
        <td>peek</td>
        <td>Stream<T> peek(Consumer<? super T> action)</td>
        <td>生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例）</td>
    </tr>

    <tr>
        <td>skip</td>
        <td>Stream<T> skip(long n)</td>
        <td>过滤掉原Stream中的前N个元素</td>
    </tr>
    <tr>
        <td>limit</td>
        <td>Stream<T> limit(long maxSize)</td>
        <td>limit方法将截取原Stream，截取后Stream的最大长度不能超过指定值N</td>
    </tr>
    <tr>
        <td>sorted</td>
        <td>Stream<T> sorted();//对象必须实现Comparable；
        Stream<T> sorted(Comparator<? super T> comparator);</td>
        <td>sorted方法将对原Stream进行排序，返回一个有序列的新Stream</td>
    </tr>

    <tr>
        <td>reduce</td>
        <td>U reduce(U identity,
                 BiFunction<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner)</td>
        <td>sorted方法将对原Stream进行排序，返回一个有序列的新Stream</td>
    </tr>
    <tr>
        <td>min</td>
        <td>Optional<T> min(Comparator<? super T> comparator)</td>
        <td>min方法根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最小的元素</td>
    </tr>
    <tr>
        <td>max</td>
        <td>Optional<T> max(Comparator<? super T> comparator)</td>
        <td>根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最大的元素</td>
    </tr>
    <tr>
        <td>forEachOrdered</td>
        <td>void forEachOrdered(Consumer<? super T> action)</td>
        <td>forEachOrdered方法与forEach类似，都是遍历Stream中的所有元素，不同的是，如果该Stream预先设定了顺序，会按照预先设定的顺序执行（Stream是无序的），默认为元素插入的顺序。</td>
    </tr>
    <tr>
        <td>forEach</td>
        <td>void forEach(Consumer<? super T> action)</td>
        <td>遍历Stream中的所元素，避免了使用for循环</td>
    </tr>

    <tr>
        <td>allMatch</td>
        <td>distinct</td>
        <td>allMatch操作用于判断Stream中的元素是否全部满足指定条件。如果全部满足条件返回true，否则返回false</td>
    </tr>

    <tr>
        <td>anyMatch</td>
        <td> boolean allMatch(Predicate<? super T> predicate)</td>
        <td>anyMatch操作用于判断Stream中的是否有满足指定条件的元素。如果最少有一个满足条件返回true，否则返回false</td>
    </tr>
    <tr>
        <td>findFirst</td>
        <td>Optional<T> findFirst()</td>
        <td>findFirst操作用于获取含有Stream中的第一个元素的Optional，如果Stream为空，则返回一个空的Optional</td>
    </tr>
    <tr>
        <td>findAny</td>
        <td>Optional<T> findAny()</td>
        <td>findAny操作用于获取含有Stream中的某个元素的Optional，如果Stream为空，则返回一个空的Optional</td>
    </tr>

    <tr>
        <td>noneMatch</td>
        <td>boolean noneMatch(Predicate<? super T> predicate)</td>
        <td>noneMatch方法将判断Stream中的所有元素是否满足指定的条件，如果所有元素都不满足条件，返回true；否则，返回false.</td>
    </tr>

</table>

#### 示例
```Java
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
```
