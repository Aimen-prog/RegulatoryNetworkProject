package model.regulators;

import model.file.writer.RegulatorVisitor;

import java.util.List;

public class MaxCompositeRegulator extends CompositeRegulator{
    public MaxCompositeRegulator(List<Regulator> regulators) {
        super(regulators);
    }


    protected double initialValue() {
        return 0.;
    }

    protected double cumulativeValue(double accumulator,double value) {
        return Math.max(accumulator, value);

    }

    public String accept(RegulatorVisitor visitor ){
        return visitor.visit(this);
    }

}
