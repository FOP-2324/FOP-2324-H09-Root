package h09.stack;

import h09.function.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static h09.H09_TestUtils.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class StackOfObjectsTest {

    BasicTypeLink stackLink;
    Class<?> ctClassStack;
    Method get;
    Method pop;
    Method push;
    Method of;


    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        stackLink = BasicTypeLink.of(StackOfObjects.class);
        ctClassStack = stackLink.reflection();
        get = stackLink.getMethod(identical("get")).reflection();
        pop = stackLink.getMethod(identical("pop")).reflection();
        push = stackLink.getMethod(identical("push")).reflection();
        of = stackLink.getMethod(identical("of")).reflection();
    }

    @Test
    public void testClassParameter() {
        assertDefinedParameters(ctClassStack, Set.of(matchNoBounds("T")));
    }

    @Test
    public void testGetParameter() {
        assertReturnParameter(get, matchNoBounds("T"));
    }

    @Test
    public void testGetCast() {
        List<CtExpression<?>> expressions = stackLink.getCtElement()
            .filterChildren(
                    (CtMethod<?> m) -> m.getSimpleName().equals("get")
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
                t.equals("T")
            );

        assertTrue(hasCast, emptyContext(), r -> "get() does not contain cast to type T.");
    }

    @Test
    public void testPushParameter(){
        assertParameters(push, List.of(matchNoBounds("T")));
    }

    @Test
    public void testPopParameter() {
        assertReturnParameter(pop, matchNoBounds("T"));
    }

    @Test
    public void testOfParameter() {
        assertDefinedParameters(of, Set.of(matchNoBounds(".*")));

        List<Type> types = getTypeParameters(of, ".*");

        assertReturnParameter(of, match(types.get(0)));

        assertParameters(of, List.of(match(types.get(0))));
    }
}
