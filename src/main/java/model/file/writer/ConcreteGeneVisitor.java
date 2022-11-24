package model.file.writer;

import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;

public class ConcreteGeneVisitor implements GeneVisitor{
    private RegulatoryNetworkWriter writer;

    ConcreteGeneVisitor(RegulatoryNetworkWriter writer){
        this.writer= writer;
    }

    //methods overloading: different types of genes
    @Override
    public String visit(ConcreteRegulatoryGene gene) {
        return ConcreteRegulatoryGeneSerializer.getInstance().serialize(gene,writer);
    }

    @Override
    public String visit(ConstantRegulatoryGene gene) {
        return ConstantRegulatoryGeneSerializer.getInstance().serialize(gene,writer);

    }

}
