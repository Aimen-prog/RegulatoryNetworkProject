package model.file.serializers.list;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.RegulatoryGene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListGeneSerializer implements EntitySerializer<List<RegulatoryGene>> {

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String serialize(List<RegulatoryGene> entity, RegulatoryNetworkWriter writer) {
        return entity.toString();
    }


    // "[X,Y,Z]" to list of reg genes
    @Override
    public List<RegulatoryGene> deserialize(String string, RegulatoryNetworkReader reader) {
        List<RegulatoryGene> rglist = new ArrayList<>();
        String arr [] = string.replace("[", "").replace ("]", "").split (",");
        Arrays.asList(arr);
        for ( String geneName : arr){
            rglist.add(reader.getGene(geneName));
        }
        return rglist;
    }
}
