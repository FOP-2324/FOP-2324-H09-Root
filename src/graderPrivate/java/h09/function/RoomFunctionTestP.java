package h09.function;

import h09.H09_TestUtilsP;
import h09.WithSeats;
import h09.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static h09.H09_TestUtilsP.assertDefinedParameters;
import static h09.H09_TestUtilsP.assertGeneric;
import static h09.H09_TestUtilsP.assertParameters;
import static h09.H09_TestUtilsP.assertReturnParameter;
import static h09.H09_TestUtilsP.assertType;
import static h09.H09_TestUtilsP.getDefinedTypes;
import static h09.H09_TestUtilsP.match;
import static h09.H09_TestUtilsP.matchNested;
import static h09.H09_TestUtilsP.matchUpperBounds;
import static h09.H09_TestUtilsP.matchWildcardNoBounds;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class RoomFunctionTestP {

    BasicTypeLink roomFunctionsLink;
    CtClass<RoomFunctions> ctRoomFunctions;
    Method isInArea;
    Method isInAreaAndHasMinimumNumberOfSeats;
    MethodLink isInAreaAndHasMinimumNumberOfSeatsLink;
    Method toRoomTypeOrNull;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        roomFunctionsLink = BasicTypeLink.of(RoomFunctions.class);
        ctRoomFunctions = (CtClass<RoomFunctions>) roomFunctionsLink.getCtElement();

        isInArea = roomFunctionsLink.getMethod(identical("isInArea")).reflection();
        isInAreaAndHasMinimumNumberOfSeatsLink = roomFunctionsLink.getMethod(identical("isInAreaAndHasMinimumNumberOfSeats"));
        isInAreaAndHasMinimumNumberOfSeats = isInAreaAndHasMinimumNumberOfSeatsLink.reflection();
        toRoomTypeOrNull = roomFunctionsLink.getMethod(identical("toRoomTypeOrNull")).reflection();
    }

    @Test
    public void testNULL_PREDICATE() {
        Field nullPredicate =
            BasicTypeLink.of(RoomFunctions.class).getField(identical("IS_NULL_PREDICATE")).reflection();

        assertType(nullPredicate, matchNested(Predicate.class, match(Object.class).or(matchWildcardNoBounds())));
    }

    @Test
    public void testInArea_ParameterDefinition() {
        assertGeneric(isInArea);
        assertDefinedParameters(isInArea, Set.of(
                matchUpperBounds(".*", Room.class)
            )
        );
    }

    @Test
    public void testInArea_ReturnType() {
        Predicate<Type> typeMatcher = getDefinedTypes(isInArea, ".*").stream()
            .map(H09_TestUtilsP::match)
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertReturnParameter(isInArea, matchNested(Predicate.class, typeMatcher));
    }

    @Test
    public void testInAreaMinSeats_ParameterDefinition() {
        assertGeneric(isInAreaAndHasMinimumNumberOfSeats);
        assertDefinedParameters(isInAreaAndHasMinimumNumberOfSeats, Set.of(
                matchUpperBounds(".*", Room.class, WithSeats.class)
            )
        );
    }

    @Test
    public void testInAreaMinSeats_ReturnType() {
        Predicate<Type> typeMatcher = getDefinedTypes(isInAreaAndHasMinimumNumberOfSeats, ".*").stream()
            .map(H09_TestUtilsP::match)
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertReturnParameter(isInAreaAndHasMinimumNumberOfSeats, matchNested(Predicate.class, typeMatcher));
    }

    @Test
    public void testInAreaMinSeats_inMethod() {
        CtMethod<?> ctMethod = ((BasicMethodLink)isInAreaAndHasMinimumNumberOfSeatsLink).getCtElement();
        String[] lines = ctMethod.getBody().toString().split("\n");

        int matches = 0;
        for (String line: lines) {
            if (line.matches(".*Predicate<.*>.*")) {
                matches++;
            }
        }

        assertEquals(2, matches, emptyContext(), r -> "An incorrect number of generic Variables was found in the method.");
    }

    @Test
    public void testRoomTypeOrNull_ParameterDefinition(){
        assertGeneric(toRoomTypeOrNull);
        assertDefinedParameters(toRoomTypeOrNull, Set.of(
            matchUpperBounds(".*", Room.class)
        ));
    }

    @Test
    public void testRoomTypeOrNull_ReturnType(){
        Predicate<Type> typeMatcher = getDefinedTypes(toRoomTypeOrNull, ".*").stream()
            .map(H09_TestUtilsP::match)
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertReturnParameter(toRoomTypeOrNull, matchNested(Function.class, match(Room.class), typeMatcher));
    }

    @Test
    public void testRoomTypeOrNull_Parameter(){
        Predicate<Type> typeMatcher = getDefinedTypes(toRoomTypeOrNull, ".*").stream()
            .map(H09_TestUtilsP::match)
            .map(pred -> matchNested(Class.class, pred))
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertParameters(toRoomTypeOrNull, List.of(
            typeMatcher
        ));
    }

    @Test
    public void testRoomTypeOrNull_Cast(){

        List<Type> definedTypes = getDefinedTypes(toRoomTypeOrNull, ".*");

        List<CtExpression<?>> expressions = roomFunctionsLink.getCtElement()
            .filterChildren(
                (CtMethod<?> m) -> m.getSimpleName().equals("toRoomTypeOrNull")
            )
            .<CtMethod>first()
            .getBody()
            .filterChildren(new TypeFilter<>(CtExpression.class))
            .list();
        Stream<CtTypeReference<?>> casts = expressions.stream()
            .flatMap(e -> e.getTypeCasts().stream());
        boolean hasCast = casts
            .map(CtTypeReference::getSimpleName)
            .anyMatch(t ->
                definedTypes.stream().anyMatch(defined -> t.equals(defined.getTypeName()))
            );

        assertTrue(hasCast, emptyContext(), r -> "get() does not contain correct cast.");
    }
}
