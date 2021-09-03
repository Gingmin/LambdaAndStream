package lambda;

import java.util.Arrays;

public class hello {
    
    public static void main(String[] args) {

        //메서드의 이름과 반환값이 없어지므로 람다를 익명함수라고도 한다.
        //이름과 반환타입이 없다.
        //선언부와 몸체{} 사이에 '->'를 추가한다.
        //int[] arr = new int[5];
        //Arrays.setAll(arr, (i) -> (int)(Math.random() * 5) + 1);
        // for(int a : arr) {
        //     System.out.print(a + " ");
        // }

        //max 함수
        int a = 2;
        int b = 3;
        System.out.println(maxNum(a,b));
        
        //반환값이 있는 메서드의 경우 return문 대신 식(expression)으로 대신 할 수 있다. 식이므로 ;를 붙이지 않는다.
        //타입이 추론 가능하면 생략 가능하다.
        //매개변수가 하나면 ()가 생략 가능하나 타입이 있으면 불가능하다
        //{} 안의 문장이 하나일 때는 {}를 생략가능하다. ;를 붙이지 않아야 한다. return문은 불가능하다
        
        MyFunction f = new MyFunction() {
            public int max(int a, int b) {
                return a > b ? a : b;
            }
        };
        int big = f.max(5, 3); //익명 객체의 메서드를 호출
        System.out.println("big : " + big);
        
        MyFunction f2 = (int c, int d) -> c > d ? c : d; //람다식
        int big2 = f2.max(5, 3);
        System.out.println("big2 : " + big2);

        //람다식은 익명 클래스의 객체와 동일
        //참조변수가 있어야 객체의 메서드를 호출 할 수 있다.
        //참조변수 f의 타입은 클래스 또는 인터페이스가 가능하다. 또한 람다식과 동등한 메서드가 정의되어 있어야 한다.

        //MyFuction인터페이스를 구현한 익명 객체의 max메서드와 람다식의 매개변수 타입 개수 반환값이 일치하기 때문에 대체가 가능하다.
        //람다는 하나의 메서드가 선언된 인터페이스를 정의해서 다루게 되었는데
        //이 방식이 기본의 자바의 규칙을 어기지 않기 때문이다.
        //람다식을 다루는 인터페이스를 '함수형 인터페이스(functional interface)'라고 부른다.
        //이 함수형 인터페이스에는 람다식과 인터페이스가 1:1로 연결되어야하므로 오직 하나의 추상 메서드만 정의되어 있어야 한다.
        //@FunctionalInterface는 함수형 인터페이스를 올바르게 정의하였는지 확인해준다.

        







    }

    //max 함수
    public static int maxNum(int a, int b) {
        return a > b ? a : b;
    }
    
    interface MyFunction {
        public abstract int max(int a, int b);
    }
}
