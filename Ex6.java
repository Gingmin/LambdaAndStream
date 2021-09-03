package lambda;

import java.util.function.*;

public class Ex6 {
    
    //메서드 참조

    /**
     * 람다식을 더 간결하게 표현할 수 있는 방법
     * 
     * 람다식이 하나의 메서드만 호출하는 경우에 메서드 참조 가능
     * 클래스이름::메서드이름 or 참조변수::메서드이름
     */

    //Function<String, Integer> f = (String s) -> Integer.parseInt(s);
    Function<String, Integer> f = Integer::parseInt;

    //이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 이름 대신 그 객체의 참조변수를 적어야 한다.
    /**
     * MyClass obj = new MyClass();
     * Function<String, Boolean> f = (x) -> obj.equals(x);
     * Function<String, Boolean> f2 = obj::equals;
     * 
     * 종류                          //   람다                     // 메서드 참조
     * static 메서드 참조            // (x) -> ClassName.method(x) // ClassName::method
     * 인스턴스메서드 참조            // (obj, x) -> obj.method(x)   // ClassName::method
     * 특정 객체 인스턴스 메서드 참조 // (x) -> obj.method(x)        // obj::method
     */
}
