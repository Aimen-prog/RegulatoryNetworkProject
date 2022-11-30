package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.RegulatoryGene;

public class HillRepressor extends HillRegulator{

    public HillRepressor(double HillCoef, double activationCoef, RegulatoryGene regulator) {
        super(HillCoef, activationCoef, regulator);
    }

    @Override
    public double inputFunction() {
            double c = getHillRegulator().getProteinConcentration();
            double n = getHillCoef();
            double k = getActivationCoef();
            return  1./(1+( Math.pow(c,n) + Math.pow(c/k,n)));

    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return null;
    }
}
