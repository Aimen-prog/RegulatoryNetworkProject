package model.file.serializers.gene;

import model.genes.ConstantRegulatoryGene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;

public class ConstantRegulatoryGeneSerializer implements EntitySerializer<ConstantRegulatoryGene> {
    private static ConstantRegulatoryGeneSerializer instance;

    private static void ConstantRegulatoryGeneSerializer(){}
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

    public static ConstantRegulatoryGeneSerializer getInstance(){
        if (instance == null) {
            instance = new ConstantRegulatoryGeneSerializer();
        }
        return instance;
    }
}
