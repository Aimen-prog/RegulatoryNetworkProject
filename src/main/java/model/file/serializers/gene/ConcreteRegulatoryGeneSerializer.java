package model.file.serializers.gene;

import model.genes.ConcreteRegulatoryGene;
import model.network.RegulatoryNetworkReader;
import model.network.RegulatoryNetworkWriter;

public class ConcreteRegulatoryGeneSerializer implements EntitySerializer<ConcreteRegulatoryGene> {
    private static ConcreteRegulatoryGeneSerializer instance;
    //Singleton: only one object copy exists
    // default private constructor
    private void ConcreteRegulatoryGeneSerializer(){}

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String serialize(ConcreteRegulatoryGene entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public ConcreteRegulatoryGene deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }

    public ConcreteRegulatoryGeneSerializer getInstance(){
        return instance;
    }




}
