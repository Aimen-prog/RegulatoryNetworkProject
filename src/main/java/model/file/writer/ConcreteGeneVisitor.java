package model.file.writer;

import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;
import model.network.RegulatoryNetworkWriter;

public class ConcreteGeneVisitor implements GeneVisitor{
    private RegulatoryNetworkWriter writer;

    ConcreteGeneVisitor(RegulatoryNetworkWriter writer){
        this.writer= writer;
    }

    @Override
    public String visit(ConstantRegulatoryGene gene) {
        return null;
    }

    @Override
    public String visit(ConcreteRegulatoryGene gene) {
        return null;
    }
}
