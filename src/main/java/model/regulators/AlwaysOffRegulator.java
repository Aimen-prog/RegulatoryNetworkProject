package model.regulators;

import model.file.writer.GeneVisitor;
import model.file.writer.RegulatorVisitor;

public class AlwaysOffRegulator implements Regulator{

    @Override
    public double inputFunction() {
        return 0.;
    }

    @Override
    public String getInfo() {
        return null;
    }

    public String accept(RegulatorVisitor visitor ){
        return visitor.visit(this);
    }
}
