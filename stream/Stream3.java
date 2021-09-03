

import java.util.*;
import java.util.stream.*;
import java.util.Random;

public class Stream3 {
    
    /**
     * (변환) map
     * 
     * 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때가 있는데
     * 이 때 사용하는 것이 map
     * 
     * Stream<R> map(Function<? super  T, ? extends R> mapper)
     * 
     * 매개변수로 T타입을 R타입으로 변환해서 반환하는 함수를 지정해야한다.
     * 
     * 예) File의 스트림에서 파일의 이름만 뽑아서 출력하고 싶을 때 map을 이용
     * Stream<File> fileStream = Stream.of(new File("Ex1.java"), new File("Ex1"), new File("Ex1.bak"), new File("Ex2.java"),
     * new File("Ex1.txt"));
     * //map으로 Stream<File>을 Stream<String>으로 변환
     * Stream<String> filenameStream = fileStream.map(File::getName);
     * filenameStream.forEach(System.out::println);
     * //스트림의 모든 파일 이름 출력
     * 
     * map은 중간연산이므로 결과는 String을 요소로 하는 스트림
     * map으로 Stream<File>을 Stream<String>으로 변환했다고 볼 수 있다.
     * map에도 filter처럼 하나의 스트림에 여러 번 적용할 수 있다.
     * 
     */

    /* 확장자만 뽑고 중복 제거 
    
    fileStream.map(File::getName)                 //Stream<File> -> Stream<String>
        .filter(s -> s.indexOf('.') != -1)        //확장자가 없는 것은 제외
        .map(s -> s.substring(s.indexOf('.') +1)) //Stream<String> -> Stream<String>
        .map(String::toUpperCase)                 //모두 대문자로 변환
        .distinct()                               //중복 제거
        .forEach(System.out::print);              //JAVABAKTXT
    
    */

    /** (조회) peek()
     * 연산과 연산이 올바르게 처리 됐는지 확인하고 싶다면 peek 사용
     * forEach와 달리 스트림의 요소를 소모하지 않으므로 연산 사이에 여러 번 끼워 넣어도 문제가 되지 않는다.
     * 
     *  fileStream.map(File::getName)             //Stream<File> -> Stream<String>
        .filter(s -> s.indexOf('.') != -1)        //확장자가 없는 것은 제외
        .peek(s -> System.out.printf("filename = %s%n", s))  //파일 명 출력
        .map(s -> s.substring(s.indexOf('.') +1)) //확장자만 추출
        .peek(s -> System.out.printf("extension" = %s%n", s))  //확장자를 출력
        .forEach(System.out::print);             
     */

    File[] fileArr = { new File("Ex1.java"), 
                       new File("Ex1"), 
                       new File("Ex1.bak"), 
                       new File("Ex2.java"),
                       new File("Ex1.txt") 
                    };
    
    Stream<File> fileStream = Stream.of(fileArr);

    //map으로 Stream<File>을 Stream<String>으로 변환

    Stream<String> filenameStream = fileStream.map(File::getName);
       // .peek(s -> System.out.printf("filename=%s%n", s));

    filenameStream.forEach(System.out::println); //모든 파일 이름 출력
}
