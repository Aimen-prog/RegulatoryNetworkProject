package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

public class MinCompositeRegulatorSerializer implements EntitySerializer<MinCompositeRegulator> {

    private static MinCompositeRegulatorSerializer instance;

    private MinCompositeRegulatorSerializer(){}

    @Override
    public String getCode() {
        return "MinCompositeRegulator";
    }

    @Override
    public String serialize(MinCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        return getCode() + " " +  entity.getRegulators().toString(); //a changer
    }

    @Override
    public MinCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        ListRegulatorSerializer regulators = new ListRegulatorSerializer();
        //get regulators [..,..] => token 2 normally
        int start = string.indexOf("[");
        String sub = string.substring(start);
        // get token 0
        String[] tokens = string.split(" ");
        reader.getGene(tokens[0])
                .setRegulator(new MinCompositeRegulator(regulators.deserialize(sub,reader)));
        return (MinCompositeRegulator) reader.getGene(tokens[0]).getRegulator();
    }

    public static MinCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MinCompositeRegulatorSerializer();
        }
        return instance;
    }
}
