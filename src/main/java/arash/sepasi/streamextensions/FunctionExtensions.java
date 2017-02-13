package arash.sepasi.streamextensions;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * An extension of the {@link java.util.function} functional interfaces, with support for methods which may throw Exceptions.
 * Created by sepasa1 on 12/28/2016.
 */
public class FunctionExtensions {
    // ===== Function Extensions ===== //
    @FunctionalInterface
    public interface DiConsumer<I1, I2> extends BiConsumer<I1, I2> {
        void accept(I1 i1, I2 i2);
    }

    @FunctionalInterface
    public interface TriConsumer<I1, I2, I3> {
        void accept(I1 i1, I2 i2, I3 i3);
    }

    @FunctionalInterface
    public interface PolyConsumer<I> {
        void accept(I... inputs);
    }

    @FunctionalInterface
    public interface DiFunction<I1, I2, O> extends BiFunction<I1, I2, O> {
        O apply(I1 i1, I2 i2);
    }

    @FunctionalInterface
    public interface TriFunction<I1, I2, I3, O> {
        O apply(I1 i1, I2 i2, I3 i3);
    }

    @FunctionalInterface
    public interface PolyFunction<I, O> {
        O apply(I... inputs);
    }

    // ===== Exception Throwing Functions ===== //
    @FunctionalInterface
    public interface ExceptionThrowingRunnable {
        void run() throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingConsumer<I> {
        void accept(I i) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingBiConsumer<I1, I2> {
        void accept(I1 i1, I2 i2) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingDiConsumer<I1, I2> extends ExceptionThrowingBiConsumer<I1, I2> {
    }

    @FunctionalInterface
    public interface ExceptionThrowingTriConsumer<I1, I2, I3> {
        void accept(I1 i1, I2 i2, I3 i3) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingPolyConsumer<I> {
        void accept(I... inputs) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingSupplier<O> {
        O get() throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingFunction<I, O> {
        O apply(I i) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingBiFunction<I1, I2, O> {
        O apply(I1 i1, I2 i2) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingDiFunction<I1, I2, O> extends ExceptionThrowingBiFunction<I1, I2, O> {
    }

    @FunctionalInterface
    public interface ExceptionThrowingTriFunction<I1, I2, I3, O> {
        O apply(I1 i1, I2 i2, I3 i3) throws Exception;
    }

    @FunctionalInterface
    public interface ExceptionThrowingPolyFunction<I, O> {
        O apply(I... inputs) throws Exception;
    }
}
