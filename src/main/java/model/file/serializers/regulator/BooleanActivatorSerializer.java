package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;
import model.regulators.BooleanActivator;


public class BooleanActivatorSerializer implements EntitySerializer<BooleanActivator> {

    private static BooleanActivatorSerializer instance;

    private BooleanActivatorSerializer(){}



    @Override
    public String getCode() {
        return "BooleanActivator";
    }

    @Override
    public String serialize(BooleanActivator entity, RegulatoryNetworkWriter writer) {
        return getCode() + " " + entity.getInfo();
    }
    // return threshold + " "+ gene.getName();
    @Override
    public BooleanActivator deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        reader.getGene(tokens[0])
                .setRegulator(new BooleanActivator(Double.parseDouble(tokens[2]),
                                    reader.getGene(tokens[3])));
        return (BooleanActivator) reader.getGene(tokens[0]).getRegulator();
    }

    public static BooleanActivatorSerializer getInstance(){
        if (instance == null) {
            instance = new BooleanActivatorSerializer();
        }
        return instance;
    }
}
