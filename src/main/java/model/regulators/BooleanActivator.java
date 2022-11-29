package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.RegulatoryGene;

public class BooleanActivator extends BooleanRegulator{


    public BooleanActivator(double threshold, RegulatoryGene regulator) {
        super(threshold, regulator);
    }
    @Override
    public double inputFunction() {
        if (geneIsSignaled() & thresholdIsAttained()){
            return 1.0;
        }
        return 0.;
    }

    public String accept(RegulatorVisitor visitor ){
        return visitor.visit(this);
    }


}
