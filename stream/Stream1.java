package lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Stream1 {

    public static void main(String[] args) {
        
        String[] strArr = { "aaa", "ddd", "ccc"};
        List<String> strList = Arrays.asList(strArr);
    
        System.out.println("strArr : " + strArr);
        System.out.println("strList : " + strList);
        
        Stream<String> strStream1 = strList.stream();
        Stream<String> strStream2 = Arrays.stream(strArr);
        
        System.out.println("strStream1 : " + strStream1);
        System.out.println("strStream2 : " + strStream2);

        //strStream1.sorted().forEach(System.out::println);
        //strStream2.sorted().forEach(System.out::println);

        List<String> sortedList = strStream2.sorted().collect(Collectors.toList());

        System.out.println("sortedList : " + sortedList);

        //스트림은 1회용이라 사용하고 나면 다시 열어야 한다.
        //중간 연산 - 연산 결과가 스트림인 연산, 스트림에 연속해서 중간 연산할 수 있음
        //최종 연산 - 연산 결과가 스트림이 아닌 연산, 스트림의 요소를 소모하므로 단 한번만 가능
        //stream.distinct().limit(5).sorted().forEach(System.out::println)
        //          중간      중간      중간      최종

        //최종연산이 수행되기 전까지는 중간연산이 수행되지 않고 단지 어떤 작업을 해야하는지 지정하는 것 뿐이다.
        //병렬 처리가 쉽다.
    }


    
}
