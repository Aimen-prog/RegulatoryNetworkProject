package model.regulators;

import java.util.List;

public class MinCompositeRegulator extends CompositeRegulator{
    public MinCompositeRegulator(List<Regulator> regulators) {
        super(regulators);
    }


    protected double initialValue() {
        return 1.;
    }

    protected double cumulativeValue(double accumulator,double value) {
        return Math.min(accumulator, value);
    }
}
