
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream4 {
    
    public static void main(String[] args) {
        
        /**
         * mapToInt(), mapToLong(), mapToDouble
         * map은 연산의 결과로 Stream<T> 타입의 스트림을 반환
         * 스트림의 요소를 숫자로 반환하는 경우 IntStream과 같은 기본 스트림으로 변환하는 것이 더 유용할 수 있다.
         * 
         * Stream<T>타입의 스트림을 기본형 스트림으로 변환할 때 사용하는 메서드
         * 
         * DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
         * IntStream    mapToInt(ToIntFunction<? super T> mapper)
         * LongStream   mapToLong(ToLongFunction<? super T> mapper)
         * 
         * 전의 예, Stream<Integer> studentScoreStream = studentStream.map(Student::getTotalScore);
         * 스트림에 포함된 모든 학생의 성적 합산
         * ->
         * mapToInt가 효율적 성적을 더할 때 Integer를 int로 변환할 필요가 없다.
         * 
         * IntStream studentScoreStream = studentStream.mapToInt(Student::getTotalScore);
         * int allTotalScore = studentScoreStream.sum(); //int sum();
         * 
         * count만 지원하는 Stream<T>와 달리 숫자를 다루는 편리한 메서드 지원
         * int            sum()     스트림의 모든 요소의 총합
         * OptionalDouble average() sum() / (double)count()
         * OptionalInt    max()     스트림의 요소 중 제일 큰 값
         * OptionalInt    min()     스트림의 요소 중 제일 작은 값
         * 
         * 최종연산이므로 연속 호출이 안 된다.
         * 
         * sum()과 average()를 모두 호출해야하는 경우를 위해 summaryStatistics()메서드가 있다.
         * IntSummartyStatisitcs stat = scoreStream.summaryStatistics();
         * long totalCount = stat.getCount();
         * long totalScore = stat.getSum();
         * double avgScore = stat.getAverage();
         * int minScore    = stat.getMin();
         * int maxScore    = stat.getMax();
         * 
         * IntStream을 Stream<T>로 변환할 때는 mapToObj()를, Stream<Integer>로 변환할 때는 boxed()
         */

        IntStream intStream = new Random().ints(1, 46); //46은 미포함
        Stream<String> lottoStream = intStream.distinct().limit(6).sorted().mapToObj(i -> i + ","); //정수를 문자열로
        lottoStream.forEach(System.out::print);

        Student[] stuArr = {
            new Student("이자바", 3, 300),
            new Student("김자바", 1, 200),
            new Student("안자바", 2, 100),
            new Student("박자바", 2, 150),
            new Student("소자바", 1, 200),
            new Student("나자바", 3, 290),
            new Student("감자바", 3, 180)
        };

        Stream<Student> stuStream = Stream.of(stuArr);

        stuStream.sorted(Comparator.comparing(Student::getBan)
            .thenComparing(Comparator.naturalOrder()))
            .forEach(System.out::println);
        
        stuStream = Stream.of(stuArr);
        IntStream stuScoreStream = stuStream.mapToInt(Student::getTotalScore);

        IntSummaryStatistics stat = stuScoreStream.summaryStatistics();

        System.out.println("count = " + stat.getCount());
        System.out.println("sum = " + stat.getSum());
        System.out.printf("average = %.2f%n", stat.getAverage());
        System.out.println("min=" + stat.getMin());
        System.out.println("max=" + stat.getMax());
    }
    
}


