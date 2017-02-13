package arash.sepasi.streamextensions;

/**
 * Created by sepasa1 on 12/28/2016.
 */
public class MethodsForTesting {

    public static final String RESULT = "RETURNED";

    public static String successfulSupplier() throws Exception  {
        return RESULT;
    }

    public static String successfulFunction(String i) throws Exception {
        return RESULT+i;
    }

    public static String successfulDiFunction(String i1, String i2) throws Exception {
        return RESULT+i1+i2;
    }

    public static String successfulTriFunction(String i1, String i2, String i3) throws Exception {
        return RESULT+i1+i2+i3;
    }

    public static void exceptionThrowingRunnable() throws Exception {
        throw new Exception("StaticRunnable");
    }

    public static <I> void exceptionThrowingConsumer(I i) throws Exception {
        throw new Exception("StaticConsumer");
    }

    public static <I1, I2> void exceptionThrowingDiConsumer(I1 i1, I2 i2) throws Exception {
        throw new Exception("StaticDiConsumer");
    }

    public static <I1, I2, I3> void exceptionThrowingTriConsumer(I1 i1, I2 i2, I3 i3) throws Exception {
        throw new Exception("StaticTriConsumer");
    }

    public static <O> O exceptionThrowingSupplier() throws Exception {
        throw new Exception("StaticSupplier");
    }

    public static <I, O> O exceptionThrowingFunction(I i) throws Exception {
        throw new Exception("StaticFunction");
    }

    public static <I1, I2, O> O exceptionThrowingDiFunction(I1 i1, I2 i2) throws Exception {
        throw new Exception("StaticDiFunction");
    }

    public static <I1, I2, I3, O> O exceptionThrowingTriFunction(I1 i1, I2 i2, I3 i3) throws Exception {
        throw new Exception("StaticTriFunction");
    }

    public static class ExceptionThrowingClass {
        public String successfulSupplier() throws Exception {
            return RESULT;
        }

        public String successfulFunction(String i) throws Exception {
            return RESULT+i;
        }

        public String successfulDiFunction(String i1, String i2) throws Exception {
            return RESULT+i1+i2;
        }

        public String successfulTriFunction(String i1, String i2, String i3) throws Exception {
            return RESULT+i1+i2+i3;
        }

        public void exceptionThrowingRunnable() throws Exception {
            throw new Exception("ClassRunnable");
        }

        public <I> void exceptionThrowingConsumer(I i) throws Exception {
            throw new Exception("ClassConsumer");
        }

        public <I1, I2> void exceptionThrowingDiConsumer(I1 i1, I2 i2) throws Exception {
            throw new Exception("ClassDiConsumer");
        }

        public <I1, I2, I3> void exceptionThrowingTriConsumer(I1 i1, I2 i2, I3 i3) throws Exception {
            throw new Exception("ClassTriConsumer");
        }

        public <O> O exceptionThrowingSupplier() throws Exception {
            throw new Exception("ClassSupplier");
        }

        public <I, O> O exceptionThrowingFunction(I i) throws Exception {
            throw new Exception("ClassFunction");
        }

        public <I1, I2, O> O exceptionThrowingDiFunction(I1 i1, I2 i2) throws Exception {
            throw new Exception("ClassDiFunction");
        }

        public <I1, I2, I3, O> O exceptionThrowingTriFunction(I1 i1, I2 i2, I3 i3) throws Exception {
            throw new Exception("ClassTriFunction");
        }
    }
}
