package lambda;

import java.util.*;
import java.util.function.*;

public class Ex4 {

    public static void main(String[] args) {
        
        /*
        인터페이스      /  메서드          / 설명
        Supplier<T>    / T get()          / 매개변수는 없고, 반환값만 있음
        Consumer<T>    / void accept(T t) / Supplier와 반대로 매개변수만 있고 반환값이 없음
        Function<T, R> / R apply(T t)     / 일반적인 함수 하나의 매개변수를 받아 결과를 반환
        Predicate<T>   / boolean test(T t)/ 조건식을 표현하는데 사용, 매개변수는 하나, 반환 타입은 boolean

        매개변수가 2개면 앞에 Bi-
        */

        Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1;
        Consumer<Integer> c = i -> System.out.print(i + ", ");
        Predicate<Integer> p = i -> i % 2 == 0;
        Function<Integer, Integer> f = i -> i / 10 * 10; //i의 일의 자리를 없앤다.

        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);
        printEvenNum(p, c, list);
        List<Integer> newList = doSomething(f, list);
        System.out.println(newList);

        System.out.println();

        //기본 자료형 함수형 인터페이스
        /**
         * 인터페이스            /  메서드                   / 설명
         * DoubleToIntFunction  / int applyAsInt(double d)  / AToBFunction은 입력이 A타입 출력이 B타입
         * ToIntFunction<T>     / int applyAsInt(T value)   / ToBFunction은 출력이 B타입이다. 입력은 제네릭 타입
         * IntFunction<R>       / R apply(int value)        / AFunction은 입력이 A타입이고 출력은 제네릭 타입
         * ObjIntConsumer       / void accept(T t, int i)   / ObjFuntion은 입력이 T, A타입이고 출력은 없다
         */
        IntSupplier is = () -> (int) (Math.random() * 100) + 1;
        IntConsumer ic = i -> System.out.print(i + ", ");
        IntPredicate ip = i -> i % 2 == 0;
        IntUnaryOperator op = i -> i / 10 * 10;

        int[] arr = new int[10];

        makeRandomList2(is, arr);
        System.out.println(Arrays.toString(arr));
        printEvenNum2(ip, ic, arr);
        int[] newArr = doSomething2(op, arr);
        System.out.println(Arrays.toString(newArr));
    }

    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());
        
        for(T i : list) {
            newList.add(f.apply(i));
        }
        return newList;
    }

    static int[] doSomething2(IntUnaryOperator op, int[] arr) {
        int[] newArr = new int[arr.length];
        
        for(int i = 0; i < newArr.length; i++) {
            newArr[i] = op.applyAsInt(arr[i]);
        }
        return newArr;
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.print("[");
        for(T i : list) {
            if(p.test(i))
                c.accept(i);
        }
        System.out.println("]");
    }

    static void printEvenNum2(IntPredicate p, IntConsumer c, int[] arr) {
        System.out.print("[");
        for(int i : arr) {
            if(p.test(i))
                c.accept(i);
        }
        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i = 0; i < 10; i++) {
            list.add(s.get());
        }
    }

    static void makeRandomList2(IntSupplier s, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = s.getAsInt(); //get()이 아니라 getAsInt();
        }
    }

}