package model.file.serializers.gene;

import model.file.serializers.EntitySerializer;
import model.genes.ConstantRegulatoryGene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;

public class ConstantRegulatoryGeneSerializer implements EntitySerializer<ConstantRegulatoryGene> {
    private static ConstantRegulatoryGeneSerializer instance;

    private ConstantRegulatoryGeneSerializer(){}
    @Override
    public String getCode() {
        return "ConstantRegulatoryGene";
    }

    @Override
    public String serialize(ConstantRegulatoryGene entity, RegulatoryNetworkWriter writer) {
        return getCode() + " " +  entity.getName() +" "+ entity.getInitialProteinConcentration()
                + " "+ entity.getInitialIsSignaled();
    }

    @Override
    public ConstantRegulatoryGene deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        return new ConstantRegulatoryGene(tokens[1],
                Double.parseDouble(tokens[2]),
                Boolean.parseBoolean(tokens[3]));
    }


    public static ConstantRegulatoryGeneSerializer getInstance(){
        if (instance == null) {
            instance = new ConstantRegulatoryGeneSerializer();
        }
        return instance;
    }


}
