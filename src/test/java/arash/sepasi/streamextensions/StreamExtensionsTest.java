package arash.sepasi.streamextensions;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by sepasa1 on 12/28/2016.
 */
public class StreamExtensionsTest {
    private final static Logger LOG = LoggerFactory.getLogger(StreamExtensionsTest.class);

    private final static String INPUT = "INPUT";
    private final static MethodsForTesting.ExceptionThrowingClass claxx = new MethodsForTesting.ExceptionThrowingClass();

    // ===== throwRuntimeException Tests ===== //

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Runnable_static() throws Exception {
        StreamExtensions.RunnableExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingRunnable);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Runnable_class() throws Exception {
        StreamExtensions.RunnableExtensions.throwRuntimeException(claxx::exceptionThrowingRunnable);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Consumer_static() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingConsumer, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Consumer_class() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(claxx::exceptionThrowingConsumer, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_DiConsumer_static() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingDiConsumer, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_DiConsumer_class() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(claxx::exceptionThrowingDiConsumer, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_TriConsumer_static() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingTriConsumer, INPUT, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_TriConsumer_class() throws Exception {
        StreamExtensions.ConsumerExtensions.throwRuntimeException(claxx::exceptionThrowingTriConsumer, INPUT, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Supplier_static() throws Exception {
        StreamExtensions.SupplierExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingSupplier);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Supplier_class() throws Exception {
        StreamExtensions.SupplierExtensions.throwRuntimeException(claxx::exceptionThrowingSupplier);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Function_static() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingFunction, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_Function_class() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(claxx::exceptionThrowingFunction, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_DiFunction_static() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingDiFunction, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_DiFunction_class() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(claxx::exceptionThrowingDiFunction, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_TriFunction_static() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(MethodsForTesting::exceptionThrowingTriFunction, INPUT, INPUT, INPUT);
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeException_TriFunction_class() throws Exception {
        StreamExtensions.FunctionExtensions.throwRuntimeException(claxx::exceptionThrowingTriFunction, INPUT, INPUT, INPUT);
    }

    // ===== returnOptional Tests ===== //

    @Test
    public void returnOptional_staticSuccessfulSupplier_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.SupplierExtensions.returnOptional(MethodsForTesting::successfulSupplier);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT, res.get());
    }

    @Test
    public void returnOptional_classSuccessfulSupplier_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.SupplierExtensions.returnOptional(claxx::successfulSupplier);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT, res.get());
    }

    @Test
    public void returnOptional_staticExceptionThrowingSupplier_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.SupplierExtensions.returnOptional(MethodsForTesting::exceptionThrowingSupplier);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_classExceptionThrowingSupplier_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.SupplierExtensions.returnOptional(claxx::exceptionThrowingSupplier);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_staticSuccessfulFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::successfulFunction, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT, res.get());
    }

    @Test
    public void returnOptional_classSuccessfulFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::successfulFunction, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT, res.get());
    }

    @Test
    public void returnOptional_staticExceptionThrowingFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::exceptionThrowingFunction, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_classExceptionThrowingFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::exceptionThrowingFunction, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_staticSuccessfulDiFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::successfulDiFunction, INPUT, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT+INPUT, res.get());
    }

    @Test
    public void returnOptional_classSuccessfulDiFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::successfulDiFunction, INPUT, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT+INPUT, res.get());
    }

    @Test
    public void returnOptional_staticExceptionThrowingDiFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::exceptionThrowingDiFunction, INPUT, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_classExceptionThrowingDiFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::exceptionThrowingDiFunction, INPUT, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_staticSuccessfulTriFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::successfulTriFunction, INPUT, INPUT, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT+INPUT+INPUT, res.get());
    }

    @Test
    public void returnOptional_classSuccessfulTriFunction_ExpectValidResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::successfulTriFunction, INPUT, INPUT, INPUT);
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(MethodsForTesting.RESULT+INPUT+INPUT+INPUT, res.get());
    }

    @Test
    public void returnOptional_staticExceptionThrowingTriFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(MethodsForTesting::exceptionThrowingTriFunction, INPUT, INPUT, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void returnOptional_classExceptionThrowingTriFunction_ExpectEmptyResult() {
        final Optional<String> res = StreamExtensions.FunctionExtensions.returnOptional(claxx::exceptionThrowingTriFunction, INPUT, INPUT, INPUT);
        Assert.assertFalse(res.isPresent());
    }

    @Test
    public void removeExceptionsFromStream_IntegerStream_AlwaysThrowException_ExpectEmptyResults() {
        final FunctionExtensions.ExceptionThrowingFunction<Integer, String> mapper = i -> {
            throw new Exception("Throwing Exception for " + i);
        };
        List<String> actualResults = StreamExtensions.removeExceptionsFromStream(IntStream.range(0,10).boxed(), mapper).collect(Collectors.toList());
        Assert.assertTrue(actualResults.isEmpty());
    }

    @Test
    public void removeExceptionsFromStream_IntegerStream_ThrowExceptionOnEvens_ExpectOnlyOdds() {
        final FunctionExtensions.ExceptionThrowingFunction<Integer, String> mapper = i -> {
            if(i%2 ==0)
                throw new Exception("Mapper caught even number " + i);
            else
                return i.toString();
        };
        List<String> expectedResults = IntStream.range(0, 10)
                .filter(i -> i%2!=0)
                .mapToObj(i -> String.valueOf(i))
                .collect(Collectors.toList());
        List<String> actualResults = StreamExtensions.removeExceptionsFromStream(IntStream.range(0,10).boxed(), mapper).collect(Collectors.toList());
        Assert.assertEquals(expectedResults, actualResults);
    }

    @Test
    public void splitExceptionsFromStream_IntegerStream_AlwaysThrowException_ExpectEmptyGoodStreamAndAllInBadStream() {
        final FunctionExtensions.ExceptionThrowingFunction<Integer, String> mapper = i -> {
            throw new Exception("Mapper caught even number " + i);
        };

        List<Integer> input = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        StreamExtensions.Tuple<Stream<String>, Stream<Integer>> results = StreamExtensions.splitExceptionsFromStream(input.stream(), mapper);
        List<String> actualGoodResults = results.getFirst().collect(Collectors.toList());
        List<Integer> actualBadResults = results.getSecond().collect(Collectors.toList());
        Assert.assertTrue(actualGoodResults.isEmpty());
        Assert.assertEquals(input, actualBadResults);
    }

    @Test
    public void splitExceptionsFromStream_IntegerStream_ThrowExceptionOnEvens_ExpectOnlyOddsInGoodStreamAndOnlyEvensInBadStream() {
        final FunctionExtensions.ExceptionThrowingFunction<Integer, String> mapper = i -> {
            if(i%2 ==0)
                throw new Exception("Mapper caught even number " + i);
            else
                return i.toString();
        };
        List<String> expectedGoodResults = IntStream.range(0, 10)
                .filter(i -> i%2!=0)
                .mapToObj(i -> String.valueOf(i))
                .collect(Collectors.toList());
        List<Integer> expectedBadResults = IntStream.range(0, 10)
                .filter(i -> i%2==0)
                .boxed()
                .collect(Collectors.toList());
        StreamExtensions.Tuple<Stream<String>, Stream<Integer>> results = StreamExtensions.splitExceptionsFromStream(IntStream.range(0,10).boxed(), mapper);
        List<String> actualGoodResults = results.getFirst().collect(Collectors.toList());
        List<Integer> actualBadResults = results.getSecond().collect(Collectors.toList());
        Assert.assertEquals(expectedGoodResults, actualGoodResults);
        Assert.assertEquals(expectedBadResults, actualBadResults);
    }
}
