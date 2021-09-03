package lambda.stream;

import java.util.*;
import java.util.stream.*;
import java.util.Random;

public class Stream2 {
    
    public static void main(String[] args) {
        
        //IntStream intStream = new Random().ints();
        //intStream.limit(5).forEach(System.out::println);
        
        //Stream<Integer> evenStream = Stream.iterate(0, n->n+2);
        //System.out.println(evenStream);

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
        long count()                                          : 스트림의 요소의 개수 반환
        Optional<T> max(Comparator<? super T> comparator)     : 스트림의 최대값/최소값을 반환
        Optional<T> min(Comparator<? super T> comparator)     : 스트림의 최대값/최소값을 반환
        Optional<T> findAny() //아무거나 하나                  : 스트림의 요소 하나를 반환
        Optional<T> findFirst() //첫 번째 요소                 : 
        boolean allMatch(Predicate<T> p) //모두 만족하는지     : 주어진 조건을 모든 요소가 만족시키는지, 만족시키지 않는지 확인
        boolean anyMatch(Predicate<T> p) //하나라도 만족
        boolean noneMatch(Predicate<T> p) //모두 만족하지 않는지
        Object[] toArray()                                    : 스트림의 모든 요소를 배열로 반환
        A[]      toArray(IntFunction<A []> generator)         : 
        Optional<T> reduce(BinaryOperator<T> accumulator)     : 스트림의 요소를 하나씩 줄여가면서(리듀싱) 계산한다.
        T reduce(T identity, BinaryOperator<T> accumulator)   :
        U reduce(U identity, BiFunction<U,T,U) accumulator, BinaryOperator<U> combiner
        R collect(Collector<T, A, R> collector)               : 스트림의 요소를 수집한다. 주로 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용된다.
        R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator, BiConsumer<R,R> combiner)

        중간연산은 map과 flatMap 최종연산은 reduce와 collect가 핵심

        */

        /**
         * 문자열 스트림 정렬 방법
         * 출력결과 CCaaabccdd 
         *   strStream.sorted()                              //기본정렬
         *   strStream.sorted(Comparator.naturalOrder())     //기본정렬
         *   strStream.sorted((s1, s2) -> s1.compareTo(s2)); //람다식도 가능
         *   strStream.sorted(String::compareTo);            //위의 문장과 동일
         * 
         * 출력결과 ddccbaaaCC
         *   strStream.sorted(Comparator.reverseOrder()) //기본 정렬의 역순
         *   strStream.sorted(Comparator.<String>naturalOrder().reversed())
         * 
         * 출력결과 aaabCCccdd
         *   strStream.sorted(String.CASE_INSENSITIVE_ORDER)  //대소문자 구분안함
         * 
         * 출력결과 ddCCccbaaa
         *   strStream.sorted(String.CASE_INSENSITIVE_ORDER.reversed()) //오타아님
         * 
         * 출력결과 bddCCccaaa
         *   strStream.sorted(Comparaotr.comparing(String::length))    //길이 순 정렬
         *   strStream.sorted(Comparator.comparingInt(String::length)) //no오토박싱
         * 
         * 출력결과 aaaddCCccb
         *   strStream.sorted(Comparator.comparing(String::length).reversed())
         * 
         */

        /**
         * JDK 1.8부터 추가된 Comparator 인터페이스의 static메서드와 default메서드
         * 이 메서드들을 이용하면 정렬이 쉬워진다.
         * Comparator<T>를 반환해준다.
         * 
         * Comparator의 default메서드
         * reversed()
         * thenComparing(Comparator<T> other)
         * thenComparing(Function<T,U> keyExtractor)
         * thenComparing(Function<T,U> keyExtractor, Comparator<U> keyComp)
         * thenComparingInt(ToIntFunction<T> keyExtractor)
         * thenComparingLong(ToIntFunction<T> keyExtractor)
         * thenComparingDouble(ToIntFunction<T> keyExtractor)
         * 
         * Comparator의 static메서드
         * naturalOrder()
         * reverseOrder()
         * comparing(Function<T,U> keyExtractor)
         * comparing(Function<T,U> keyExtractor. Comparator<U> keyComparator)
         * comparingInt(ToIntFunction<T> keyExtractor)
         * comparingLong(ToLongFunction<T> keyExtractor)
         * comparingDouble(ToDoubleFunction<T> keyExtractor)
         * nullsFirst(Comparator<T> comparator)
         * nullsLast(Comparator<T> comparator)
         * 
         * comparing()이 기본 메소드이다.
         * 비교대상이 기본 자료형이면 comparing대신 comparingInt, comparingLong, comparingDouble을 사용하면
         * 오토박싱과 언박싱 과정이 없어서 더 효율적이다.
         * 
         * 정렬조건을 추가할 때는 thenComparing을 사용한다.
         */
        
        /**
         * 예)
         * 학생 스트림(studentStream)을 반(ban)별, 성적(totalScore)순, 이름(name)순으로 정렬하여
         * 출력하려면
         * studentStream.sorted(Comparator.comparing(Student::getBan)
         *      .thenComparing(Student::getTotalScore)
         *      .thenComparing(Student::getName))
         *      .forEach(System.out::println);
         */

        Stream<Student> studentStream = Stream.of(
            new Student("이자바", 3, 300),
            new Student("김자바", 1, 200),
            new Student("안자바", 2, 100),
            new Student("박자바", 2, 150),
            new Student("소자바", 1, 200),
            new Student("나자바", 3, 290),
            new Student("감자바", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan) //반별
            .thenComparing(Comparator.naturalOrder()))             //기본
            .forEach(System.out::println);
    }

    
}

class Student implements Comparable<Student> {

    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore).toString();
    }

    String getName() { return name; }
    int getBan() { return ban; }
    int getTotalScore() { return totalScore; }

    //총점 내림차순 기본정렬
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}