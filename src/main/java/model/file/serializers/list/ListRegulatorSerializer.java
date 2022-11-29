package model.file.serializers.list;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.RegulatoryGene;
import model.regulators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRegulatorSerializer implements EntitySerializer<List<Regulator>> {
    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String serialize(List<Regulator> entity, RegulatoryNetworkWriter writer) {
        String string = "[" ;
        for(int index = 0; index < entity.size(); index++){
            string += entity.get(index).getClass().getSimpleName() + entity.get(index).getInfo() + ",";
        }
        //replace last comma with a closing bracket
        string = string.replaceAll(",$","]");
        return string;
    }

    @Override
    public List<Regulator> deserialize(String string, RegulatoryNetworkReader reader) {
        List<Regulator> rglist = new ArrayList<>();
        String arr [] = string.replace("[", "").replace ("]", "").split (",");
        Arrays.asList(arr);
        for (String regulator : arr){
            String[] tokens = regulator.split(",");
            if (tokens[0]=="BooleanActivator"){rglist.add(
                    new BooleanActivator(Double.parseDouble(tokens[1]), reader.getGene(tokens[2])));}
            if (tokens[0]=="BooleanRepressor"){rglist.add(
                    new BooleanRepressor(Double.parseDouble(tokens[1]), reader.getGene(tokens[2])));}
            if (tokens[0]=="AlwaysOnRegulator"){rglist.add(
                    new AlwaysOnRegulator());}
            if (tokens[0]=="AlwaysOffRegulator"){rglist.add(
                    new AlwaysOffRegulator());}

        }
        return rglist;
    }
}
