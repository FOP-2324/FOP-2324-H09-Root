package h09;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicFieldLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

public class H09_TestUtils {

    public static class GenericPredicate implements Predicate<Type> {

        private String description;
        private Predicate<Type> predicate;

        GenericPredicate(Predicate<Type> predicate, String description) {
            this.predicate = predicate;
            this.description = description;
        }

        @Override
        public boolean test(Type type) {
            return predicate.test(type);
        }

        @NotNull
        @Override
        public Predicate<Type> and(@NotNull Predicate<? super Type> other) {
            return new GenericPredicate(predicate.and(other), "(" + this.description + " and " + other + ")");
        }

        @NotNull
        @Override
        public Predicate<Type> or(@NotNull Predicate<? super Type> other) {
            return new GenericPredicate(predicate.or(other), "(" + this.description + " or " + other + ")");
        }

        @NotNull
        @Override
        public Predicate<Type> negate() {
            return new GenericPredicate(predicate.negate(), "(not " + description + ")");
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public static Predicate<Type> matchNoBounds(String name){
        return new GenericPredicate(
            (type) -> {
                Pair<List<Type>, List<Type>> bounds = getBounds(type);
                if (!type.getTypeName().matches(name)) {
                    return false;
                }
                if (bounds.getLeft() != null) {
                    return false;
                }
                return bounds.getRight().size() == 1 && bounds.getRight().get(0) == Object.class;
            },
            String.format("Name: %s; Bounds: No Bounds", name)
        );
    }

    public static Predicate<Type> matchLowerBounds(String name, Type... expectedBounds){
        return new GenericPredicate(
            (type) -> {
                Pair<List<Type>, List<Type>> bounds = getBounds(type);
                if (!type.getTypeName().matches(name)) {
                    return false;
                }
                return bounds.getLeft().size() == expectedBounds.length && new HashSet<>(bounds.getLeft()).containsAll(List.of(expectedBounds));
            },
            String.format("Name: %s; Bounds: %s", name, Arrays.stream(expectedBounds).map(Type::getTypeName).collect(Collectors.joining(", ")))
        );
    }

    public static Predicate<Type> matchUpperBounds(String name, Type... expectedBounds){
        return new GenericPredicate(
            (type) -> {
                Pair<List<Type>, List<Type>> bounds = getBounds(type);
                if (!type.getTypeName().matches(name)) {
                    return false;
                }
                return bounds.getRight().size() == expectedBounds.length && new HashSet<>(bounds.getRight()).containsAll(List.of(expectedBounds));
            },
            String.format("Name: %s; Bounds: %s", name, Arrays.stream(expectedBounds).map(Type::getTypeName).collect(Collectors.joining(", ")))
        );
    }

    public static Predicate<Type> matchWildcard(boolean isUpperBound, Type... expectedBounds){
        return new GenericPredicate(
            (type) -> {
                Pair<List<Type>, List<Type>> bounds = getBounds(type);
                if (!(type instanceof WildcardType wildcardType)){
                    return false;
                }
                if (isUpperBound){
                    return bounds.getRight().size() == expectedBounds.length && new HashSet<>(bounds.getRight()).containsAll(List.of(expectedBounds));
                }
                return bounds.getLeft().size() == expectedBounds.length && new HashSet<>(bounds.getLeft()).containsAll(List.of(expectedBounds));
            },
            String.format("Wildcard: ? %s %s", isUpperBound ? "extends" : "super", Arrays.stream(expectedBounds).map(Type::getTypeName).collect(Collectors.joining(" & ")))
        );
    }

    public static Predicate<Type> match(Type expected){
        return new GenericPredicate(
            (type) -> type.equals(expected),
            String.format("Type: %s", expected.getTypeName())
        );
    }

    @SafeVarargs
    public static Predicate<Type> matchNested(Type outerType, Predicate<Type>... expectedNested){
        return new Predicate<>() {
            @Override
            public boolean test(Type type) {
                if (!(type instanceof ParameterizedType parameterizedType)){
                    return false;
                }
                if (!parameterizedType.getRawType().equals(outerType)){
                    return false;
                }
                Type[] actualType = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < expectedNested.length; i++){
                    if (!expectedNested[i].test(actualType[i])){
                        return false;
                    }
                }
                return true;
            }

            @Override
            public String toString() {
                return String.format("Type: %s; Inner Type: %s", outerType.getTypeName(), List.of(expectedNested));
            }
        };
    }

    public static Pair<List<Type>, List<Type>> getBounds(Type type){
        if (type instanceof WildcardType wildcardType){
            return ImmutablePair.of(Arrays.asList(wildcardType.getLowerBounds()), Arrays.asList(wildcardType.getUpperBounds()));
        }
        if (type instanceof ParameterizedType parameterizedType){
            return ImmutablePair.of(null, Arrays.asList(parameterizedType.getActualTypeArguments()));
        }
        if (type instanceof TypeVariable<?> typeVariable){
            return ImmutablePair.of(null, Arrays.asList(typeVariable.getBounds()));
        }
        return null;
    }

    public static Type getReturnType(Method method){
        return method.getGenericReturnType();
    }

    public static List<Type> getTypeParameters(Method method, String regex){
        return Arrays.stream(method.getGenericParameterTypes()).filter(t -> t.getTypeName().matches(regex)).toList();
    }

    public static List<Type> getDefinedTypes(Method method, String regex){
        return Arrays.stream(method.getTypeParameters()).filter(t -> t.getTypeName().matches(regex)).map(t -> (Type) t).toList();
    }

    public static void assertDefinedParameters(Class<?> clazz, Set<Predicate<Type>> expected) {

        List<TypeVariable<?>> typeVariable = Arrays.asList(clazz.getTypeParameters());
        CtClass<?> ctClass = (CtClass<?>) BasicTypeLink.of(clazz).getCtElement();
        var actualNames = ctClass.getFormalCtTypeParameters().stream().map(CtType::toStringDebug).toList();
        Context context = contextBuilder()
            .add("expected", expected)
            .add("actual", actualNames)
            .build();

        assertTrue(!typeVariable.isEmpty(), emptyContext(), r -> clazz.getSimpleName() + " does not have any generic parameters.");

        assertEquals(expected.size(), typeVariable.size(), context, r -> clazz.getSimpleName() + " does not have the expected number of generic parameters.");
        typeVariable.forEach(a ->
            assertTrue(expected.stream().anyMatch(e -> e.test(a)), context, r -> String.format("The type parameter %s of %s do not match any expected types.", a, clazz.getSimpleName()))
        );
    }

    public static void assertDefinedParameters(Method method, Set<Predicate<Type>> expected) {

        List<TypeVariable<?>> typeVariable = Arrays.asList(method.getTypeParameters());
        CtMethod<?> ctMethod = BasicMethodLink.of(method).getCtElement();
        var actualNames = ctMethod.getFormalCtTypeParameters().stream().map(CtTypeParameter::toStringDebug).toList();
        Context context = contextBuilder()
            .add("expected", expected)
            .add("actual", actualNames)
            .build();

        assertTrue(!typeVariable.isEmpty(), emptyContext(), r -> method.getName() + " does not have any generic parameters.");

        assertEquals(expected.size(), typeVariable.size(), context, r -> method.getName() + " does not have the expected number of generic parameters.");
        typeVariable.forEach(a ->
            assertTrue(expected.stream().anyMatch(e -> e.test(a)), context, r -> String.format("The type parameter %s of %s do not match any expected types.", a, method.getName()))
        );
    }

    public static void assertReturnParameter(Method method, Predicate<Type> expected) {
        Type type = method.getGenericReturnType();

        CtMethod<?> ctMethod = BasicMethodLink.of(method).getCtElement();
        var actualNames = ctMethod.getType().toStringDebug();
        Context context = contextBuilder()
            .add("actual type", actualNames)
            .add("expected", expected)
            .build();

        assertTrue(expected.test(type), context, r -> String.format("%s has a wrong return type.", method.getName()));
    }

    public static void assertParameters(Method method, List<Predicate<Type>> expected) {
        Type[] type = method.getGenericParameterTypes();

        assertEquals(expected.size(), type.length, emptyContext(), r -> String.format("The method %s() does not have the correct amount of parameters", method.getName()));

        CtMethod<?> ctMethod = BasicMethodLink.of(method).getCtElement();
        var actualNames = ctMethod.getParameters().stream().map(CtParameter::getType).map(CtTypeReference::toStringDebug).toList();

        for (int i = 0; i< type.length; i++){
            int finalI = i;

            Context context = contextBuilder()
                .add("actual type", actualNames.get(i))
                .add("expected", expected.get(i))
                .build();

            assertTrue(expected.get(i).test(type[i]), context, r -> String.format("%s has a wrong parameter at index %d.", method.getName(), finalI));
        }

    }

    public static void assertType(Field field, Predicate<Type> expected) {
        Type type = field.getGenericType();

        CtField<?> ctField = BasicTypeLink.of(field.getDeclaringClass()).getCtElement().filterChildren(new TypeFilter<>(CtField.class){
            @Override
            public boolean matches(CtField element) {
                return super.matches(element) && element.getSimpleName().equals(field.getName());
            }
        }).first();
        var actualNames = ctField.getType().toStringDebug();

        Context context = contextBuilder()
            .add("actual type", actualNames)
            .add("expected", expected)
            .build();

        assertTrue(expected.test(type), context, r -> String.format("%s has a wrong type.", field.getName()));

    }

    public static void assertGeneric(Method toTest){

        Predicate<Method> isGeneric = (method) -> !getTypeParameters(method, ".*").isEmpty();
        isGeneric = isGeneric.or((method) -> getBounds(getReturnType(method)) != null);
//        isGeneric = isGeneric.or((method) -> );

        assertTrue(isGeneric.test(toTest), emptyContext(), r -> String.format("The method %s() is not Generic.", toTest.getName()));
    }
}
