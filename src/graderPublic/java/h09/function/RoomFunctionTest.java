package h09.function;

import h09.room.Room;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicFieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static h09.H09_TestUtils.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class RoomFunctionTest {

    @Test
    public void testNULL_PREDICATE() {
        Field nullPredicate = BasicTypeLink.of(RoomFunctions.class).getField(identical("IS_NULL_PREDICATE")).reflection();

        assertType(nullPredicate, matchNested(Predicate.class, match(Object.class)));
    }
}
