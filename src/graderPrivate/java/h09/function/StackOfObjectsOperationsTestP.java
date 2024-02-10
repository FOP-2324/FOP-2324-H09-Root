package h09.function;

import h09.stack.StackOfObjects;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.declaration.CtClass;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static h09.H09_TestUtilsP.assertDefinedParameters;
import static h09.H09_TestUtilsP.assertGeneric;
import static h09.H09_TestUtilsP.assertParameters;
import static h09.H09_TestUtilsP.assertReturnParameter;
import static h09.H09_TestUtilsP.getBounds;
import static h09.H09_TestUtilsP.getDefinedTypes;
import static h09.H09_TestUtilsP.getInnerTypes;
import static h09.H09_TestUtilsP.getReturnType;
import static h09.H09_TestUtilsP.match;
import static h09.H09_TestUtilsP.matchNested;
import static h09.H09_TestUtilsP.matchNoBounds;
import static h09.H09_TestUtilsP.matchUpperBounds;
import static h09.H09_TestUtilsP.matchWildcard;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class StackOfObjectsOperationsTestP {

    BasicTypeLink stackOperationsLink;
    CtClass<StackOfObjectsOperations> ctStackOperations;
    Method filter;
    Method map;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        stackOperationsLink = BasicTypeLink.of(StackOfObjectsOperations.class);
        ctStackOperations = (CtClass<StackOfObjectsOperations>) stackOperationsLink.getCtElement();

        filter = stackOperationsLink.getMethod(identical("filter")).reflection();
        map = stackOperationsLink.getMethod(identical("map")).reflection();
    }

    @Test
    public void testFilter_isGeneric() {
        assertGeneric(filter);
    }

    @Test
    public void testFilter_ReturnType() {
        List<Type> definedType = getDefinedTypes(filter, ".*");
        Type input = getDefinedTypes(filter, ".*").stream()
            .filter(t -> {
                    Pair<List<Type>, List<Type>> bounds = getBounds(t);
                    return !((bounds.getLeft() != null && bounds.getLeft().stream().anyMatch(definedType::contains))
                        || bounds.getRight().stream().anyMatch(definedType::contains));
                }
            )
            .findFirst().orElse(null);
        assertNotNull(input, emptyContext(),
            r -> "Could not determine Type that should be used for this Parameter. Check Type Definition."
        );
        assertReturnParameter(filter, matchNested(StackOfObjects.class, match(input)));
    }

    @Test
    public void testFilter_FirstParameter() {
        List<Type> definedType = getDefinedTypes(filter, ".*");
        Type input = getDefinedTypes(filter, ".*").stream()
            .filter(t -> {
                    Pair<List<Type>, List<Type>> bounds = getBounds(t);
                    return (bounds.getLeft() != null && bounds.getLeft().stream().anyMatch(definedType::contains))
                        || bounds.getRight().stream().anyMatch(definedType::contains);
                }
            )
            .findFirst().orElse(null);
        assertNotNull(input, emptyContext(),
            r -> "Could not determine Type that should be used for this Parameter. Check Type Definition."
        );
        assertParameters(filter, List.of(
            matchNested(StackOfObjects.class, matchWildcard(true, input).or(match(input))),
            (t) -> true
        ));
    }

    @Test
    public void testFilter_SecondParameter() {
        List<Type> definedType = getDefinedTypes(filter, ".*");
        Type input = getDefinedTypes(filter, ".*").stream()
            .filter(t -> {
                    Pair<List<Type>, List<Type>> bounds = getBounds(t);
                    return (bounds.getLeft() != null && bounds.getLeft().stream().anyMatch(definedType::contains))
                        || bounds.getRight().stream().anyMatch(definedType::contains);
                }
            )
            .findFirst().orElse(null);
        assertNotNull(input, emptyContext(),
            r -> "Could not determine Type that should be used for this Parameter. Check Type Definition."
        );
        assertParameters(filter, List.of(
            (t) -> true,
            matchNested(Predicate.class, matchWildcard(false, input).or(match(input)))
        ));
    }

    @Test
    public void testFilter_ParameterDefinition() {
        assertDefinedParameters(filter, Set.of(
            matchNoBounds(".*"),
            getDefinedTypes(filter, ".*").stream()
                .map((type) ->
                    matchUpperBounds(".*", type)
                )
                .reduce(Predicate::or)
                .orElse(i -> false)
        ));
    }

    @Test
    public void testMap_isGeneric() {
        assertGeneric(map);
    }

    @Test
    public void testMap_ParameterDefinition() {
        assertDefinedParameters(map, Set.of(
            matchNoBounds(".*"),
            matchNoBounds(".*")
        ));
    }

    @Test
    public void testMap_FirstParameter() {

        final Predicate<Type> wildCardMatcher = getDefinedTypes(map, ".*").stream()
            .map(type -> matchWildcard(true, type))
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertParameters(map, List.of(
            matchNested(StackOfObjects.class, wildCardMatcher.or(matchNoBounds(".*"))),
            ignored -> true
        ));
    }

    @Test
    public void testMap_SecondParameter() {
        List<Type> definedTypes = getDefinedTypes(map, ".*");
        final Predicate<Type> firstWildCardMatcher = definedTypes.stream()
            .map(type -> matchWildcard(false, type))
            .reduce(Predicate::or)
            .orElse(i -> false);
        final Predicate<Type> secondWildCardMatcher = definedTypes.stream()
            .map(type -> matchWildcard(true, type))
            .reduce(Predicate::or)
            .orElse(i -> false);

        assertParameters(map, List.of(
            ignored -> true,
            matchNested(
                Function.class,
                firstWildCardMatcher.or(matchNoBounds(".*")),
                secondWildCardMatcher.or(matchNoBounds(".*"))
            )
        ));
    }

    @Test
    public void testMap_TypesMakeSense() {
        List<Type> definedTypes = getDefinedTypes(map, ".*");
        List<Type> returnType = getInnerTypes(getReturnType(map));

        assertEquals(1, returnType.size(), emptyContext(), r -> "Map returns unexpected Type. Cant determine Correct Parameters.");

        final Predicate<Type> firstWildCardMatcher = definedTypes.stream()
            .filter(type -> !type.equals(returnType.get(0)))
            .map(type -> matchWildcard(true, type).or(match(type)))
            .reduce(Predicate::or)
            .orElse(i -> false);
        final Predicate<Type> secondWildCardMatcher = definedTypes.stream()
            .filter(type -> !type.equals(returnType.get(0)))
            .map(type -> matchWildcard(false, type).or(match(type)))
            .reduce(Predicate::or)
            .orElse(i -> false);
        final Predicate<Type> thirdWildCardMatcher = matchWildcard(true, returnType.get(0)).or(match(returnType.get(0)));

        assertParameters(map, List.of(
            matchNested(StackOfObjects.class, firstWildCardMatcher),
            matchNested(Function.class, secondWildCardMatcher, thirdWildCardMatcher)
        ));
    }

    @Test
    public void testMap_ReturnType() {
        assertReturnParameter(map, matchNested(StackOfObjects.class, matchNoBounds(".*")));
    }
}
