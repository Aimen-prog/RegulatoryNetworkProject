package model.file.writer;

import model.file.serializers.regulator.AlwaysOffRegulatorSerializer;
import model.file.serializers.regulator.AlwaysOnRegulatorSerializer;
import model.regulators.*;

public class ConcreteRegulatorVisitor implements RegulatorVisitor{
    private RegulatoryNetworkWriter writer;

    ConcreteRegulatorVisitor(RegulatoryNetworkWriter writer){
            this.writer= writer;
    }

    @Override
    public String visit(AlwaysOffRegulator regulator) {
        return AlwaysOffRegulatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(AlwaysOnRegulator regulator) {
        return AlwaysOnRegulatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(BooleanActivator regulator) {
        return null;
    }

    @Override
    public String visit(BooleanRepressor regulator) {
        return null;
    }

    @Override
    public String visit(MaxCompositeRegulator regulator) {
        return null;
    }

    @Override
    public String visit(MinCompositeRegulator regulator) {
        return null;
    }
}
