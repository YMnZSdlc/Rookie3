package bookstore;

public class StackTraceExample {
    public static void main(String[] args) {

        method1();
//        method2();
//        method3();



    }

    private static void method3() {
        method2();
    }

    private static void method2() {
        method3();
    }

    private static void method1() {
        throw new RuntimeException("Boom ");
    }

}
