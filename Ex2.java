package lambda;

@FunctionalInterface
interface MyFunction2 {
    void myMethod();
}

public class Ex2 {
   
    //외부 변수를 참조하는 람다식
    int val = 10; //ex2.this.val
    
    class Inner {
        int val = 20; //this.val

        void method(int i) { //void method(final int i) {
            int val = 30; // final int val = 30;
            // i = 10; //에러 상수값 변경x

            MyFunction2 f = () -> {
                System.out.println("i : " + i);
                System.out.println("val : " + val);
                System.out.println("this.val : " + ++this.val);
                System.out.println("Ex2.this.val : " + ++Ex2.this.val);
            };

            f.myMethod();
        }
    } //inner
} // ex2

class Lambda {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        Ex2.Inner inner = ex2.new Inner();
        inner.method(100);
    }
    //           i : 100
    //         val : 30
    //    this.val : 21
    //Ex2.this.val : 11
}
