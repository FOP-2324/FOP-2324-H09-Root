package h09.function;

import h09.stack.StackOfObjects;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static h09.H09_TestUtils.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class StackOfObjectsOperationsTest {

    BasicTypeLink stackOperationsLink;
    CtClass<StackOfObjectsOperations> ctStackOperations;
    Method filter;
    Method map;

    @BeforeEach
    public void setUp(){
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
    public void testFilter_ReturnType(){
        assertReturnParameter(filter, matchNested(StackOfObjects.class, matchNoBounds(".*")));
    }

    @Test
    public void testFilter_FirstParameter(){
        List<Type> definedType = getDefinedTypes(filter, ".*");
        Type input = getDefinedTypes(filter, ".*").stream()
            .filter(t -> {
                    Pair<List<Type>, List<Type>> bounds = getBounds(t);
                    return (bounds.getLeft() != null && bounds.getLeft().stream().anyMatch(definedType::contains))
                        || bounds.getRight().stream().anyMatch(definedType::contains);
                }
            )
            .findFirst().orElse(null);
        assertNotNull(input, emptyContext(), r -> "Could not determine Type that should be used for this Parameter. Check Type Definition.");
        assertParameters(filter, List.of(
            matchNested(StackOfObjects.class, matchWildcard(true, input).or(match(input))),
            (t) -> true
        ));
    }

    @Test
    public void testFilter_SecondParameter(){
        List<Type> definedType = getDefinedTypes(filter, ".*");
        Type input = getDefinedTypes(filter, ".*").stream()
            .filter(t -> {
                    Pair<List<Type>, List<Type>> bounds = getBounds(t);
                    return (bounds.getLeft() != null && bounds.getLeft().stream().anyMatch(definedType::contains))
                        || bounds.getRight().stream().anyMatch(definedType::contains);
                }
            )
            .findFirst().orElse(null);
        assertNotNull(input, emptyContext(), r -> "Could not determine Type that should be used for this Parameter. Check Type Definition.");
        assertParameters(filter, List.of(
            (t) -> true,
            matchNested(Predicate.class, matchWildcard(false, input))
        ));
    }

    @Test
    public void testFilter_ParameterDefinition(){
        assertDefinedParameters(filter, Set.of(
            matchNoBounds(".*"),
            getDefinedTypes(filter, ".*").stream()
                .map((type) ->
                    matchUpperBounds(".*", type)
                )
                .reduce(Predicate::or)
                .get()
        ));
    }



    @Test
    public void testMap_ParameterDefinition(){
        assertDefinedParameters(map, Set.of(
            matchNoBounds(".*"),
            matchNoBounds(".*")
        ));
    }
}
