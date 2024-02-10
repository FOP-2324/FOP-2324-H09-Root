package h09;

import h09.function.RoomFunctionTestP;
import h09.function.StackOfObjectsOperationsTestP;
import h09.function.StackOfObjectsPredicateTestP;
import h09.function.TestsTestP;
import h09.stack.StackOfObjectsTestP;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.graderPrivateOnly;

public class H09_RubricProviderPrivate implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H1: Stacks of Objects")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H1.1: Klasse StackOfObjects")
                        .addChildCriteria(
                            criterion(
                                "H1.1: Der Klassenkopf von StackOfObjects ist korrekt definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsTestP.class.getDeclaredMethod("testClassParameter"))
                            ),
                            criterion(
                                "H1.1: Der Methodenkopf von push() ist korrekt definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsTestP.class.getDeclaredMethod("testPushParameter"))
                            ),
                            criterion(
                                "H1.1: Der Methodenkopf von get() ist korrekt definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsTestP.class.getDeclaredMethod("testGetParameter"))
                            ),
                            criterion(
                                "H1.1: get() verwendet einen korrekten Cast.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsTestP.class.getDeclaredMethod("testGetCast"))
                            ),
                            criterion(
                                "H1.1: Der Methodenkopf von pop() ist korrekt definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsTestP.class.getDeclaredMethod("testPopParameter"))
                            ),
                            criterion(
                                "H1.1: Der Methodenkopf von of() ist korrekt definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsTestP.class.getDeclaredMethod("testOfParameter")),
                                2
                            )
                        )
                        .build()
                )
                .build(),
            Criterion.builder()
                .shortDescription("H2: Stack Operations")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H2.1: Methode filter")
                        .addChildCriteria(
                            criterion(
                                "H2.1: filter() besitzt generische Typen.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testFilter_isGeneric"))
                            ),
                            criterion(
                                "H2.1: Der erste Parameter wurde passend gewählt.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                    "testFilter_FirstParameter"))
                            ),
                            criterion(
                                "H2.1: Der zweite Parameter wurde passend gewählt.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                    "testFilter_SecondParameter"))
                            ),
                            criterion(
                                "H2.1: Der Rückgabetyp wurde passend gewählt.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testFilter_ReturnType"))
                            ),
                            criterion(
                                "H2.1: Die Typen der Methode wurden passend definiert.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                    "testFilter_ParameterDefinition"))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.2: Methode map")
                        .addChildCriteria(
                            criterion(
                                "H2.2: map() besitzt generische Typen.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testMap_isGeneric"))
                            ),
                            criterion(
                                "H2.2: Der erste Parameter wurde passend gewählt.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testMap_FirstParameter"))
                            ),
                            criterion(
                                "H2.2: Der zweiter Parameter wurde passend gewählt.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testMap_SecondParameter")),
                                2
                            ),
                            criterion(
                                "H2.2: Der erste Parameter wurde passend gewählt.",
                                JUnitTestRef.ofMethod(
                                    () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testMap_ReturnType"))
                            ),
                            criterion(
                                "H2.2: Die Parameter und der Rückgabetyp wurden sinnvoll verknüpft.",
                                JUnitTestRef.or(
                                    JUnitTestRef.not(
                                        JUnitTestRef.or(
                                            JUnitTestRef.ofMethod(
                                                () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                                    "testMap_FirstParameter")),
                                            JUnitTestRef.ofMethod(
                                                () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                                    "testMap_SecondParameter"))
                                        )
                                    ),
                                    JUnitTestRef.ofMethod(
                                        () -> StackOfObjectsOperationsTestP.class.getDeclaredMethod("testMap_TypesMakeSense"))
                                ),
                                -1
                            ),
                            criterion(
                                "H2.2: Die Typen der Methode wurden passend definiert.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsOperationsTestP.class.getDeclaredMethod(
                                    "testMap_ParameterDefinition"))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.3: Interface StackPredicate")
                        .addChildCriteria(
                            criterion(
                                "H2.3: StackPredicate wurde korrekt in ein generisches Interface überführt.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsPredicateTestP.class.getDeclaredMethod(
                                    "testGenericDefinition"))
                            ),
                            criterion(
                                "H2.3: Die Typen von Predicate wurden Korrekt instanziiert.",
                                JUnitTestRef.ofMethod(() -> StackOfObjectsPredicateTestP.class.getDeclaredMethod(
                                    "testExtends"))
                            )
                        )
                        .build()
                )
                .build(),
            Criterion.builder()
                .shortDescription("H3: Room Functions")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H3.1: Predicate IS_NULL_PREDICATE")
                        .addChildCriteria(
                            criterion(
                                "H3.1: IS_NULL_PREDICATE ist vollständig korrekt.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testNULL_PREDICATE"))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.2: Methode isInArea()")
                        .addChildCriteria(
                            criterion(
                                "H3.2: Die Methode ist korrekt generisch definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInArea_ParameterDefinition"))
                            ),
                            criterion(
                                "H3.2: Der Rückgabetyp der Methode ist korrekt instanziiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInArea_ReturnType"))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.3: Funktion isInAreaAndHasMinimumNumberOfSeats()")
                        .addChildCriteria(
                            criterion(
                                "H3.3: Die Methode ist korrekt generisch definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInAreaMinSeats_ParameterDefinition"))
                            ),
                            criterion(
                                "H3.3: Der Rückgabetyp der Methode ist korrekt instanziiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInAreaMinSeats_ReturnType"))
                            ),
                            criterion(
                                "H3.3: Die Typen der Methode sind korrekt generisch definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInAreaMinSeats_inMethod"))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.4: toRoomTypeOrNull")
                        .addChildCriteria(
                            criterion(
                                "H3.4: Der Rückgabetyp der Methode ist korrekt instanziiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testInAreaMinSeats_ReturnType"))
                            ),
                            criterion(
                                "H3.4: Die Typen der Methode sind korrekt generisch definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testRoomTypeOrNull_ParameterDefinition"))
                            ),
                            criterion(
                                "H3.4: Die Parameter der Methode sind korrekt generisch definiert.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testRoomTypeOrNull_Parameter"))
                            ),
                            criterion(
                                "H3.4: toRoomTypeOrNull() verwendet einen korrekten Cast.",
                                JUnitTestRef.ofMethod(
                                    () -> RoomFunctionTestP.class.getDeclaredMethod("testRoomTypeOrNull_Cast"))
                            )
                        )
                        .build()
                )
                .build(),
            Criterion.builder()
                .shortDescription("H4: Testen mittels JUnit")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H4.1: Die großen 5, definiert von Stadtmitte und Lichtwiese.")
                        .addChildCriteria(
                            criterion(
                                "H4.1: Alle tests sind vollständig korrekt implementiert.",
                                JUnitTestRef.ofMethod(() -> TestsTestP.class.getDeclaredMethod("testTestFilter"))
                            )

                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H4.2: Test von map()")
                        .addChildCriteria(
                            criterion(
                                "H4.2: Alle tests sind vollständig korrekt implementiert.",
                                JUnitTestRef.ofMethod(() -> TestsTestP.class.getDeclaredMethod("testTestMap"))
                            )
                        )
                        .build()
                )
                .build()
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
