package h09;

import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

public class H09_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09")
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
