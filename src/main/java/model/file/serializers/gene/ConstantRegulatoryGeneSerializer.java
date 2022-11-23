package model.file.serializers.gene;

import model.genes.ConstantRegulatoryGene;
import model.network.RegulatoryNetworkReader;
import model.network.RegulatoryNetworkWriter;

public class ConstantRegulatoryGeneSerializer implements EntitySerializer<ConstantRegulatoryGene> {
    private ConstantRegulatoryGeneSerializer instance;

    private void ConstantRegulatoryGeneSerializer(){

    }
    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String serialize(ConstantRegulatoryGene entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public ConstantRegulatoryGene deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }

    public ConstantRegulatoryGeneSerializer getInstance(){
        return instance;
    }
}
