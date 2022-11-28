package model.file.writer;


import model.regulators.*;

public interface RegulatorVisitor {
    public String visit(AlwaysOffRegulator regulator ) ;
    public String visit(AlwaysOnRegulator regulator ) ;

    public String visit(BooleanActivator regulator ) ;
    public String visit(BooleanRepressor regulator ) ;

    public String visit(MaxCompositeRegulator regulator ) ;
    public String visit(MinCompositeRegulator regulator ) ;

}
