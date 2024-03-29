package model.file.serializers.list;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.RegulatoryGene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListGeneSerializer implements EntitySerializer<List<RegulatoryGene>> {
    private static ListGeneSerializer instance;
    private ListGeneSerializer(){}

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String serialize(List<RegulatoryGene> entity, RegulatoryNetworkWriter writer) {
        String string = "[" ;
        for(int index = 0; index < entity.size(); index++){
            string += entity.get(index).getName() + ",";
        }
        //replace last comma with a closing bracket
        string = string.replaceAll(",$","]");
        return string;
    }

    // "[X,Y,Z]" to list of reg genes
    @Override
    public List<RegulatoryGene> deserialize(String string, RegulatoryNetworkReader reader) {
        List<RegulatoryGene> rglist = new ArrayList<>();
        String arr [] = string.replace("[", "").replace("]", "").split (",");
        for ( String geneName : arr){
            rglist.add(reader.getGene(geneName));
        }
        return rglist;
    }

    public static ListGeneSerializer getInstance(){
        if (instance == null) {
            instance = new ListGeneSerializer();
        }
        return instance;
    }

}
