package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.RegulatoryGene;

public class HillActivator extends HillRegulator{

    public HillActivator(double HillCoef, double activationCoef, RegulatoryGene regulator) {
        super(HillCoef, activationCoef, regulator);
    }

    @Override
    public double inputFunction() {
        double c = getHillRegulator().getProteinConcentration();
        double n = getHillCoef();
        double k = getActivationCoef();
        return  Math.pow(c,n)/( Math.pow(c,n) + Math.pow(k,n));
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return null;
    }
}
