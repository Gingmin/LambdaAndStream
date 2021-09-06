
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Stream5 {
    
    public static void main(String[] args) {
        
        /*
        flatMap() - Stream<T[]>를 Stream<T>로 변환
        스트림의 요소가 배열이거나 map()의 연산결과가 배열인 경우
        즉 스트림의 타입이 Stream<T[]>인 경우 Stream<T>로 다루는 것이 더 편할 때가 있다.

        Stream<String[]> strArrStrm = Stream.of(
            new String[]{"abc", "def", "ghi"},
            new String[]{"ABC", "GHI", "JKLMN"}
        )

        Stream<String>으로 만들려면?
        Stream<Stream<String>> strStrStrm = strArrStrm.map(Arrays::stream);

        이는 스트림의 스트림의 결과가 나온다. Stream<Stream<String>>
        flatMap을 써서 해결
        ->
        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);

         */

        Stream<String[]> strArrStrm = Stream.of(
            new String[]{"abc", "def", "jkl"},
            new String[]{"ABC", "GHI", "JKL"}
        );

        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);

        strStrm.map(String::toLowerCase)
            .distinct()
            .sorted()
            .forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
            "Believe or not It is true",
            "Do or do not There is no try",
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
            .map(String::toLowerCase)
            .distinct()
            .sorted()
            .forEach(System.out::println);
        System.out.println();

        Stream<String> strStrm1 = Stream.of("AAA", "ABC", "bBb", "Dd");
        Stream<String> strStrm2 = Stream.of("bbb", "aaa", "ccc", "dd");

        Stream<Stream<String>> strStrmStrm = Stream.of(strStrm1, strStrm2);
        Stream<String> strStream = strStrmStrm
            .map(s -> s.toArray(String[]::new))
            .flatMap(Arrays::stream);
        strStream.map(String::toLowerCase)
            .distinct()
            .forEach(System.out::println);
    }
}
