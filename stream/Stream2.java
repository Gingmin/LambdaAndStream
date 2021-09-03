package lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Random;

public class Stream2 {
    
    public static void main(String[] args) {
        
        //IntStream intStream = new Random().ints();
        //intStream.limit(5).forEach(System.out::println);
        
        //중간 연산 
        /*
        Stream<T> distinct()                      :  중복을 제거
        Stream<T> filter(Predicate<T> predicate)  :  조건에 안 맞는 요소 제외
        Stream<T> limit(long maxSize)             :  스트림의 일부를 잘라낸다.
        Stream<T> skip(long n)                    :  스트림의 일부를 건너뛴다.
        Stream<T> peek(Consumer<T> action)        :  스트림의 요소에 작업수행.
        Stream<T> sorted()                        :  스트림의 요소를 정렬한다.
        Stream<T> sorted(Comparator<T> comparator):  

        Stream<R> map(Function<T,R> mapper)       :  스트림의 요소를 반환한다(밑에 내용도 같음).
        DoubleStream mapToDouble(ToDoubleFunction<T> mapper)
        IntStream mapToInt(ToIntFunction<T> mapper)
        LongStream mapToLong(ToLongFunction<T> mapper)

        Stream<R> flatMap(Function<T,Stream<R>> mapper)
        DoubleStream flatMapToDouble<Function<T,DoubleStream> m)
        IntStream flatMapToInt(Function<T,IntStream> m)
        LongStream flatMapToLong(Function<T,LongStream> m)

        최종연산
        void forEach(Counsumer<? super T> action)             : 각 요소에 지정된 작업 수행
        Void forEachOrdered(Consumer<? super T> action)
        */

        Stream<Integer> evenStream = Stream.iterate(0, n->n+2);
        System.out.println(evenStream);


    }
}
