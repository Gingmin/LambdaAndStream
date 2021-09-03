package lambda;

@FunctionalInterface
interface MyFunction1 {
    void run();
}

public class Ex1 {
    
    static void execute(MyFunction1 f) { //매개변수의 타입이 MyFunction인 메서드
        f.run(); 
    }

    static MyFunction1 getMyFunction() { //반환 타입이 MyFunction인 메서드
        MyFunction1 f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) { //람다식으로 MyFunction의 run()을 구현
        MyFunction1 f1 = () -> System.out.println("f1.run()");

        MyFunction1 f2 = new MyFunction1() { //익명 클래스로 run()을 구현
            public void run() { //public을 반드시 붙여야 함
                System.out.println("f2.run()");
            }
        };

        MyFunction1 f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute( () -> System.out.println("run()") );
    }

    /*
    f1.run()
    f2.run()
    f3.run()
    f1.run()
    run()
    */

    //람다식은 익명 객체이고 익명 객체는 타입이 없다.
    //정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없는 것이다.
    //그래서 대입 연산자의 양변의 타입을 일치시키기 위해 아래와 같이 형변환이 필요하다.

    //MyFunction : interface MyFunction { void method(); }
    //MyFunction f = (MyFunction) ( ()->{} );
    //양변의 타입이 다르므로 형변환이 필요
    //생략가능
}
