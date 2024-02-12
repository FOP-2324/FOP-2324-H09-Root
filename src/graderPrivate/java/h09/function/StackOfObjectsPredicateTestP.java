package h09.function;

import h09.H09_TestUtilsP;
import h09.stack.StackOfObjects;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Predicate;

import static h09.H09_TestUtilsP.assertGeneric;
import static h09.H09_TestUtilsP.getDefinedTypes;
import static h09.H09_TestUtilsP.getGenericSuperTypes;
import static h09.H09_TestUtilsP.match;
import static h09.H09_TestUtilsP.matchNested;
import static h09.H09_TestUtilsP.matchWildcard;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class StackOfObjectsPredicateTestP {

    @Test
    public void testGenericDefinition() {
        assertGeneric(StackOfObjectsPredicate.class);
        assertEquals(
            1,
            getDefinedTypes(StackOfObjects.class, ".*").size(),
            emptyContext(),
            r -> "StackOfObjectsPredicate defines an incorrect number of generic Parameters"
        );
    }

    @Test
    public void testExtends() {

        List<Type> definedTypes = getDefinedTypes(StackOfObjectsPredicate.class, ".*");

        Predicate<Type> genericMatcher = definedTypes.stream()
            .map(type -> matchNested(Predicate.class, matchNested(StackOfObjects.class, matchWildcard(false, type).or(match(type)))))
            .reduce(Predicate::or)
            .orElse(new H09_TestUtilsP.GenericPredicate(i -> false, "Expected type is not defined"));

        List<Type> superTypes = getGenericSuperTypes(StackOfObjectsPredicate.class);

        assertEquals(1, superTypes.size(), emptyContext(), r -> "StackOfObjectsPredicate has an unexpected amount of SuperTypes");

        Context context = contextBuilder()
            .add("actual type", superTypes.get(0))
            .add("expected", genericMatcher)
            .build();

        assertTrue(genericMatcher.test(superTypes.get(0)), context, r -> "StackOfObjectsPredicate has a wrong supertype.");
    }
}
