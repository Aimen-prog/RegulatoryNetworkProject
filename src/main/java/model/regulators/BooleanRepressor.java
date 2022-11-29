package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.RegulatoryGene;

public class BooleanRepressor extends BooleanRegulator{
    public BooleanRepressor(double threshold, RegulatoryGene regulator) {
        super(threshold, regulator);
    }

    @Override
    public double inputFunction() {
        if (geneIsSignaled() & thresholdIsAttained()){
            return 0.0;
        }
        return 1.;
    }

    public String accept(RegulatorVisitor visitor ){
        return visitor.visit(this);
    }

}
