package model.file.reader;

import model.events.SimulationEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.file.serializers.EntitySerializer;
import model.genes.RegulatoryGene;
import model.network.RegulatoryNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends RegulatoryGene>> geneSerializers = new HashMap<>();
    private Map<String, RegulatoryGene> genes ;

    private Map<String, EntitySerializer <? extends SimulationEvent>> eventSerializers = new HashMap<>();
    private List<SimulationEvent> events = new ArrayList<>();;

    public void RegulatoryNetworkReader(){}


    private void addGeneSerializer(EntitySerializer<? extends RegulatoryGene> serializer ){
        geneSerializers.put(serializer.getCode(), serializer);
    }
    private EntitySerializer<? extends RegulatoryGene> getGeneSerializer(String code ){
        return geneSerializers.get(code);
    }

    public void addGene(RegulatoryGene gene){
        genes.put(gene.getName(),gene);
    }
    public RegulatoryGene getGene (String geneName){
        return genes.get(geneName);
    }


    public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
        genes =  new HashMap<>();
        double timeUpperBound = 20;
        double timeStepLength = 0.01;
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
            String[] tokens = line.split(" ");
            if (tokens[0].equals("TimeStep")) {timeStepLength = Double.parseDouble(tokens[1]);}
            if (tokens[0].equals( "TimeUpperBound")){timeUpperBound = Double.parseDouble(tokens[1]);}
            if (tokens[0].equals("ConcreteRegulatoryGene")) {
                addGeneSerializer(ConcreteRegulatoryGeneSerializer.getInstance());
                addGene(ConcreteRegulatoryGeneSerializer.getInstance().deserialize(line,this));}
            if (tokens[0].equals("ConstantRegulatoryGene")) {
                addGeneSerializer(ConcreteRegulatoryGeneSerializer.getInstance());
                addGene(ConstantRegulatoryGeneSerializer.getInstance().deserialize(line,this));
            }
            if (tokens[0].equals("SetSignaledEvent")){
                events.add(SetSignaledEventSerializer.getInstance().deserialize(line,this));
            }
            if (tokens[0].equals("SetProteinConcentrationEvent")){
                events.add(SetProteinConcentrationEventSerializer.getInstance().deserialize(line,this));
            }
        }
        return new RegulatoryNetwork(new ArrayList<>(genes.values()), events, timeStepLength, timeUpperBound);
    }



}
