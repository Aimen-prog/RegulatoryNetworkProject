package model.regulators;

import java.util.List;

public class MaxCompositeRegulator extends CompositeRegulator{
    public MaxCompositeRegulator(List<Regulator> regulators) {
        super(regulators);
    }

    protected double initialValue() {
        return 0.;

    }

    protected double cumulativeValue(double accumulator,double value) {
        return 0.;

    }



}
