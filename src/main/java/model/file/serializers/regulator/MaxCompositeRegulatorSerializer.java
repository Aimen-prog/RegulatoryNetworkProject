package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanRepressor;
import model.regulators.MaxCompositeRegulator;

import java.util.List;

public class MaxCompositeRegulatorSerializer implements EntitySerializer<MaxCompositeRegulator> {

    private static MaxCompositeRegulatorSerializer instance;

    private MaxCompositeRegulatorSerializer(){}


    @Override
    public String getCode() {
        return "MaxCompositeRegulator";
    }



    //TODO: resolve writing compositeRegulator
    @Override
    public String serialize(MaxCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        ListRegulatorSerializer listRegulators = new ListRegulatorSerializer();
        return getCode() + " " +  entity.getRegulators().toString();
                //listRegulators.serialize(entity.getRegulators(),writer) ;
    }

    @Override
    public MaxCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        ListRegulatorSerializer regulators = new ListRegulatorSerializer();
        //get regulators [..,..] => token 2 normally
        int start = string.indexOf("[");
        String sub = string.substring(start);
        // get token 0
        String[] tokens = string.split(" ");
        reader.getGene(tokens[0])
                .setRegulator(new MaxCompositeRegulator(regulators.deserialize(sub,reader)));
        return (MaxCompositeRegulator) reader.getGene(tokens[0]).getRegulator();
    }

    public static MaxCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MaxCompositeRegulatorSerializer();
        }
        return instance;
    }
}
