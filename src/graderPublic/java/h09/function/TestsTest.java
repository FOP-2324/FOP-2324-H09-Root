package h09.function;

import h09.TUDa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mockStatic;

@TestForSubmission
public class TestsTest {

    @Test
    public void testTestFilter() {
        try (
            MockedStatic<Assertions> assertionsMock = mockStatic(Assertions.class, CALLS_REAL_METHODS);
            MockedStatic<StackOfObjectsOperations> stackOperationsMock = mockStatic(StackOfObjectsOperations.class, CALLS_REAL_METHODS)
        ) {
            new Tests().testFilter();

            stackOperationsMock.verify(() -> StackOfObjectsOperations.filter(any(), any()), atLeastOnce());
            assertionsMock.verify(() -> Assertions.assertEquals(anyInt(), anyInt()), atLeastOnce());
            assertionsMock.verify(() -> Assertions.assertEquals(any(String.class), any(String.class)), atLeast(5));
        }
    }

    @Test
    public void testTestMap() {
        try (
            MockedStatic<Assertions> assertionsMock = mockStatic(Assertions.class, CALLS_REAL_METHODS);
            MockedStatic<StackOfObjectsOperations> stackOperationsMock = mockStatic(StackOfObjectsOperations.class, CALLS_REAL_METHODS)
        ) {
            new Tests().testMap();

            stackOperationsMock.verify(() -> StackOfObjectsOperations.map(any(), any()), atLeastOnce());
            assertionsMock.verify(() -> Assertions.assertEquals(anyInt(), anyInt()), atLeastOnce());
            try {
                assertionsMock.verify(() -> Assertions.assertEquals(anyInt(), any(Integer.class)), atLeast(TUDa.stackOfSeminarRooms().numberOfObjects()));
            } catch (AssertionError e) {
                try {
                    assertionsMock.verify(() -> Assertions.assertEquals(any(Integer.class), anyInt()), atLeast(TUDa.stackOfSeminarRooms().numberOfObjects()));
                } catch (AssertionError e2) {
                    try {
                        assertionsMock.verify(() -> Assertions.assertEquals(anyInt(), anyInt()), atLeast(TUDa.stackOfSeminarRooms().numberOfObjects()));
                    } catch (AssertionError e3) {
                        assertionsMock.verify(() -> Assertions.assertEquals(any(Integer.class), any(Integer.class)), atLeast(TUDa.stackOfSeminarRooms().numberOfObjects()));
                    }
                }
            }
        }
    }
}
