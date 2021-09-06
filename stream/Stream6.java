import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream6 {
    
    /**
     * Optional<T>와 Optionallnt
     * 
     * Optional<T>은 제네릭 클래스로 T타입의 객체를 감싸는 래퍼 클래스
     * Optional타입의 객체에는 모든 타입의 참조변수를 담을 수 있다.
     * 
     * 최종 연산의 결과를 Optional객체에 담아서 반환
     * -> Optional에 정의된 메서드를 통해 반환값 null 체크 가능
     * 
     * =======================================================================================
     * 
     * Optional객체 생성
     * of() ofNullable()
     * 
     * String str = "abc";
     * Optional<String> optVal = Optional.of(str);
     * Optional<String> optVal = Optional.of("abc");
     * Optional<String> optVal = Optional.of(new String("abc"));
     * 
     * 만일 참조변수의 값이 null일 가능성이 있으면 of() 대신 ofNullable()을 사용
     * of()는 매개변수의 값이 null이면 NullPointerException이 발생
     * 
     * Optional<String> optVal = Optional.of(null);         //NullPointerException
     * Optional<String> optVal = Optional.ofNullable(null); // OK
     * 
     * Optional<T> 타입의 참조변수를 기본값으로 초기화할 때는 empty()를 사용
     * null로 초기화하는 것이 가능하지만, empty()로 초기화 하는 것이 바람직
     * 
     * Optional<String> optVal = null;                     //null로 초기화
     * Optional<String> optVal = Optional.<String>empty(); // 빈 객체로 초기화
     * 
     * =======================================================================================
     * 
     * Optional 객체의 값 가져오기
     * get()을 사용, 값이 null일 때는 NoSuchElementException이 발생
     * 이때를 대비해서 orElse()로 대체할 값을 지정할 수 있다.
     * 
     * Optional<String> optVal = Optional.of("abc");
     * String str1 = optVal.get();          //optVal에 저장된 값을 반환. null이면 예외발생
     * String str2 = optVal.orElse("")      //optVal에 저장된 값이 null일 때는, ""를 반환
     * 
     * orElse()의 변형으로는 null을 대체할 값을 반환하는 람다식을 지정할 수 있는 orElseGet()과
     * null일때 지정된 예외를 발생시키는 orElseThrow()가 있다.
     * 
     * T orElseGet(Supplier<? extends T> other)
     * T orElseThrow(Supplier<? extends X> exceptionSupplier)
     * 
     * 사용법
     * String str3 = optVal2.orElseGet(String::new);                   //() -> new String()와 동일
     * String str4 = optVal2.orElseThrow(NullPointerException::new)    //널이면 예외발생
     * 
     * Stream처럼 filter, map, flatMap을 사용할 수 있다.
     */

    public static void main(String[] args) {
         
        int result = Optional.of("123")
        .filter(x->x.length() > 0)
        .map(Integer::parseInt).orElse(-1); //result = 123
        System.out.println(result);
        
        result = Optional.of("")
        .filter(x->x.length() > 0)
        .map(Integer::parseInt).orElse(-1); //result = -1
        System.out.println(result);
        
        /**
         * isPresent()
         * 
         * Optional객체의 값이 null이면 false, 아니면 true
         * if(Optional.ofNullable(str).isPresent()) {
         *      System.out.println(str);
         * }
         * 
         * 더 간단히
         * str이 null이 아닐때만 값 출력 null이면 아무 일도 일어나지 않는다.
         * Optional.ofNullable(str).ifPresent(System.out::println);
         * 
         * =======================================================================================
         * 
         * Optional<T>를 반환하는 메서드과 isPresent()는 잘 어울린다.
         * Optional<T> findAny()
         * Optional<T> findFirst()
         * Optional<T> max(Comparator<? super T> comparator)
         * Optional<T> min(Comparator<? super T> comparator)
         * Optional<T> reduce(BinaryOperator<T> accumulator)
         * 
         * =======================================================================================
         * 
         * OptionalInt, OptionalLong, OptionalDouble
         * 
         * OptionalInt findAny()
         * OptionalInt findFirst()
         * OptionalInt reduce(IntBinaryOperator op)
         * OptionalInt max()
         * OptionalInt min()
         * OptionalDouble average()
         * 
         * Optional에 저장된 값을 꺼낼 때 사용하는 메서드 이름이 조금씩 다르다
         * Optional<T> T get()
         * OptionalInt int getAsInt()
         * OptionalLong long getAsLong()
         * OptionalDouble double getAsDouble()
         * 
         */
        
        Optional<String> optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr = " + optStr.get());
        System.out.println("optInt = " + optInt.get());

        //=======================================================================================

        int result1 = Optional.of("123")
                                .filter(x->x.length() > 0)
                                .map(Integer::parseInt).get();
        
        int result2 = Optional.of("")
                                .filter(x->x.length() > 0)
                                .map(Integer::parseInt).orElse(-1);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        
        //=======================================================================================
        
        Optional.of("456").map(Integer::parseInt)
        .ifPresent(x->System.out.printf("result3 = %d%n", x));
        
        //=======================================================================================
        
        OptionalInt optInt1 = OptionalInt.of(0);   //0을 저장
        OptionalInt optInt2 = OptionalInt.empty(); //빈 객체를 생성
        System.out.println(optInt1.isPresent());   //true
        System.out.println(optInt2.isPresent());   //false

        System.out.println(optInt1.getAsInt());    //0
    //  System.out.println(optInt2.getAsInt());    //NoSuchElementException
        System.out.println("optInt1 = " + optInt1);
        System.out.println("optInt2 = " + optInt2);
        //=======================================================================================


    }
}
