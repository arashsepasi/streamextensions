package arash.sepasi.streamextensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static arash.sepasi.streamextensions.FunctionExtensions.*;
import static arash.sepasi.streamextensions.StreamExtensions.FunctionExtensions.returnOptional;

/**
 * A collection of utility methods to allow the usage of exception-throwing methods in Streams.<br/>
 * The {@code handleException(...)} methods allow the user to supply a custom consumer of the thrown Exception.<br/>
 * The {@code throwRuntimeException(...)} methods rethrow a thrown Exception as a RuntimeException.<br/>
 * The {@code returnOptional(...)} methods return an {@link Optional} instead, which will be {@link Optional#empty()} in case of an Exception.<br/>
 * The {@link #removeExceptionsFromStream(Stream, ExceptionThrowingFunction)} method will map the provided Stream using the provided mapping Function, removing any inputs which caused Exceptions from the returned output Stream.<br/>
 * The {@link #splitExceptionsFromStream(Stream, ExceptionThrowingFunction)} method is similar, but it also returns a Stream of the inputs which caused Exceptions.<br/>
 * <p>
 * Created by Arash Sepasi on 12/28/2016.
 */
public class StreamExtensions {

    private static final Logger LOG = LoggerFactory.getLogger(StreamExtensions.class);

    public static class RunnableExtensions {
        public static void handleException(ExceptionThrowingRunnable r, Consumer<Exception> ec) {
            try {
                r.run();
            } catch(Exception e) {
                ec.accept(e);
            }
        }
        public static void throwRuntimeException(ExceptionThrowingRunnable r) {
            handleException(r, e -> {
                throw new RuntimeException(runtimeExceptionText(e, r));
            });
        }
    }

    public static class ConsumerExtensions {
        public static <I> void handleException(ExceptionThrowingConsumer<I> c, I input,  DiConsumer<Exception, I> ec) {
            try {
                c.accept(input);
            } catch(Exception e) {
                ec.accept(e, input);
            }
        }
        public static <I1, I2> void handleException(ExceptionThrowingBiConsumer<I1, I2> c, I1 i1, I2 i2, TriConsumer<Exception, I1, I2> ec) {
            try {
                c.accept(i1, i2);
            } catch(Exception e) {
                ec.accept(e, i1, i2);
            }
        }
        public static <I1, I2> void handleException(ExceptionThrowingDiConsumer<I1, I2> c, I1 i1, I2 i2, TriConsumer<Exception, I1, I2> ec) {
            handleException((ExceptionThrowingBiConsumer<I1, I2>)c, i1, i2, ec);
        }
        public static <I1, I2, I3> void handleException(ExceptionThrowingTriConsumer<I1, I2, I3> c, I1 i1, I2 i2, I3 i3,
                                                        PolyConsumer<Object> ec) {
            try {
                c.accept(i1, i2, i3);
            } catch(Exception e) {
                ec.accept(e, i1, i2, i3);
            }
        }
        public static <I> void throwRuntimeException(ExceptionThrowingConsumer<I> c, I i) {
            handleException(c, i, (e, in) -> {
                throw new RuntimeException(runtimeExceptionText(e, c, i));
            });
        }
        public static <I1, I2> void throwRuntimeException(ExceptionThrowingBiConsumer<I1, I2> c, I1 i1, I2 i2) {
            handleException(c, i1, i2, (e, in1, in2) -> {
                throw new RuntimeException(runtimeExceptionText(e, c, i1, i2));
            });
        }
        public static <I1, I2> void throwRuntimeException(ExceptionThrowingDiConsumer<I1, I2> c, I1 i1, I2 i2) {
            throwRuntimeException((ExceptionThrowingBiConsumer<I1, I2>)c, i1, i2);
        }
        public static <I1, I2, I3> void throwRuntimeException(ExceptionThrowingTriConsumer<I1, I2, I3> c, I1 i1, I2 i2, I3 i3) {
            handleException(c, i1, i2, i3, (PolyConsumer<Object>) ((Object[] inputs) -> {
                throw new RuntimeException(runtimeExceptionText((Exception)inputs[0], c, i1, i2, i3));
            }));
        }
    }

    public static class SupplierExtensions {
        public static <O> O handleException(ExceptionThrowingSupplier<O> s, Function<Exception, O> ec) {
            try {
                return s.get();
            } catch(Exception e) {
                return ec.apply(e);
            }
        }
        public static <O> O throwRuntimeException(ExceptionThrowingSupplier<O> s) {
            return handleException(s, e -> {
                throw new RuntimeException(runtimeExceptionText(e, s));
            });
        }
        public static <O> Optional<O> returnOptional(ExceptionThrowingSupplier<O> s) {
            return Optional.ofNullable(handleException(s, e -> {
                LOG.warn("{}", runtimeExceptionText(e, s));
                return null;
            }));
        }
    }

    public static class FunctionExtensions {
        public static <I, O> O handleException(ExceptionThrowingFunction<I, O> f, I i, DiFunction<Exception, I, O> ef) {
            try {
                return f.apply(i);
            } catch(Exception e) {
                return ef.apply(e, i);
            }
        }

        public static <I1, I2, O> O handleException(ExceptionThrowingBiFunction<I1, I2, O> f, I1 i1, I2 i2, TriFunction<Exception, I1, I2, O> ef) {
            try {
                return f.apply(i1, i2);
            } catch(Exception e) {
                return ef.apply(e, i1, i2);
            }
        }

        public static <I1, I2, O> O handleException(ExceptionThrowingDiFunction<I1, I2, O> f, I1 i1, I2 i2, TriFunction<Exception, I1, I2, O> ef) {
            return handleException((ExceptionThrowingBiFunction<I1, I2, O>) f, i1, i2, ef);
        }

        public static <I1, I2, I3, O> O handleException(ExceptionThrowingTriFunction<I1, I2, I3, O> f, I1 i1, I2 i2, I3 i3,
                                                        PolyFunction<Object, O> ef) {
            try {
                return f.apply(i1, i2, i3);
            } catch(Exception e) {
                return ef.apply(e, i1, i2, i3);
            }
        }

        public static <I, O> O throwRuntimeException(ExceptionThrowingFunction<I, O> f, I i) {
            return handleException(f, i, (e, in) -> {
                throw new RuntimeException(runtimeExceptionText(e, f, i));
            });
        }

        public static <I1, I2, O> O throwRuntimeException(ExceptionThrowingBiFunction<I1, I2, O> f, I1 i1, I2 i2) {
            return handleException(f, i1, i2, (e, in1, in2) -> {
                throw new RuntimeException(runtimeExceptionText(e, f, i1, i2));
            });
        }

        public static <I1, I2, O> O throwRuntimeException(ExceptionThrowingDiFunction<I1, I2, O> f, I1 i1, I2 i2) {
            return throwRuntimeException((ExceptionThrowingBiFunction<I1, I2, O>) f, i1, i2);
        }

        public static <I1, I2, I3, O> O throwRuntimeException(ExceptionThrowingTriFunction<I1, I2, I3, O> f, I1 i1, I2 i2, I3 i3) {
            return handleException(f, i1, i2, i3, (PolyFunction<Object, O>) ((Object[] inputs) -> {
                throw new RuntimeException(runtimeExceptionText((Exception)inputs[0], f, i1, i2, i3));
            }));
        }

        public static <I, O> Optional<O> returnOptional(ExceptionThrowingFunction<I, O> f, I i) {
            return Optional.ofNullable(handleException(f, i, (e, in) -> {
                LOG.warn("{}", runtimeExceptionText(e, f, i));
                return null;
            }));
        }

        public static <I1, I2, O> Optional<O> returnOptional(ExceptionThrowingBiFunction<I1, I2, O> f, I1 i1, I2 i2) {
            return Optional.ofNullable(handleException(f, i1, i2, (e, in1, in2) -> {
                LOG.warn("{}", runtimeExceptionText(e, f, i1, i2));
                return null;
            }));
        }

        public static <I1, I2, O> Optional<O> returnOptional(ExceptionThrowingDiFunction<I1, I2, O> f, I1 i1, I2 i2) {
            return returnOptional((ExceptionThrowingBiFunction<I1, I2, O>) f, i1, i2);
        }

        public static <I1, I2, I3, O> Optional<O> returnOptional(ExceptionThrowingTriFunction<I1, I2, I3, O> f, I1 i1, I2 i2, I3 i3) {
            return Optional.ofNullable(handleException(f, i1, i2, i3, (PolyFunction<Object, O>) ((Object[] inputs) -> {
                LOG.warn("{}", runtimeExceptionText((Exception)inputs[0], f, i1, i2, i3));
                return null;
            })));
        }
    }

    // ==== Stream Mappers ===== //

    public static <I, O> Stream<O> removeExceptionsFromStream(Stream<I> stream, ExceptionThrowingFunction<I, O> mapper) {
        return stream
                .map(i -> {
                    final Optional<O> o = returnOptional(mapper, i);
                    if(!o.isPresent())
                        LOG.warn("Element {} will be removed from the stream as it caused an exception in {}!", i, mapper);
                    return o;
                })
                .filter(o -> o.isPresent())
                .map(o -> o.get());
    }

    public static <I, O> Tuple<Stream<O>, Stream<I>> splitExceptionsFromStream(Stream<I> stream, ExceptionThrowingFunction<I, O> mapper) {
        final ConcurrentLinkedQueue<I> badInputs = new ConcurrentLinkedQueue<>();
        return new Tuple<Stream<O>, Stream<I>>(
                stream
                    .map(i -> {
                        final Optional<O> o = returnOptional(mapper, i);
                        if(!o.isPresent()) {
                            LOG.warn("Element {} will be removed from the stream as it caused an exception in {}!", i, mapper);
                            badInputs.add(i);
                        }
                        return o;
                    })
                    .filter(o -> o.isPresent())
                    .map(o -> o.get()),
                badInputs.stream()
        );
    }

    public static class Tuple<First, Second> {
        final private First first;
        final private Second second;
        public Tuple(First first, Second second) {
            this.first = first;
            this.second = second;
        }
        public First getFirst() {
            return first;
        }
        public Second getSecond() {
            return second;
        }
    }

    protected static String runtimeExceptionText(Exception e, Object funcInt, Object... inputs) {
        final StringBuilder sb = new StringBuilder();
        sb
                .append("Caught an exception when evaluating ")
                .append(funcInt);
        if(inputs != null && inputs.length > 0) {
            sb.append(" with inputs (");
            Stream.of(inputs)
                    .forEachOrdered(i -> sb.append(i).append(", "));
            sb
                    .delete(sb.length() - 2, Integer.MAX_VALUE)
                    .append(")");
        }
        sb
                .append(": ")
                .append(e.getMessage());
        return sb.toString();
    }

}
