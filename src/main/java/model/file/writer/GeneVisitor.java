package model.file.writer;

import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;

public interface GeneVisitor {

    public String visit(ConstantRegulatoryGene gene ) ;
    public String visit(ConcreteRegulatoryGene gene ) ;

}
