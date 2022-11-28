package model.file.reader;

import model.events.SimulationEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.serializers.regulator.AlwaysOffRegulatorSerializer;
import model.file.serializers.regulator.AlwaysOnRegulatorSerializer;
import model.file.writer.ConcreteGeneVisitor;
import model.genes.RegulatoryGene;
import model.network.RegulatoryNetwork;
import model.regulators.Regulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegulatoryNetworkReader {

    //genes
    private Map<String, EntitySerializer<? extends RegulatoryGene>> geneSerializers = new HashMap<>();
    private Map<String, RegulatoryGene> genes ;
    //events
    private Map<String, EntitySerializer <? extends SimulationEvent>> eventSerializers = new HashMap<>();
    private List<SimulationEvent> events = new ArrayList<>();
    //regulators
    private Map<String, EntitySerializer <? extends Regulator>> regulatorSerializers = new HashMap<>();

    public void RegulatoryNetworkReader(){
        addGeneSerializer(ConstantRegulatoryGeneSerializer.getInstance());
        addGeneSerializer(ConcreteRegulatoryGeneSerializer.getInstance());
        addEventSerializer(SetProteinConcentrationEventSerializer.getInstance());
        addEventSerializer(SetSignaledEventSerializer.getInstance());
        addRegulatorSerializer(AlwaysOnRegulatorSerializer.getInstance());
        addRegulatorSerializer(AlwaysOffRegulatorSerializer.getInstance());
    }


    ////////////////////////////////*********//////////////////////////////////////
    private void addGeneSerializer(EntitySerializer<? extends RegulatoryGene> serializer ){
        geneSerializers.put(serializer.getCode(), serializer);
    }
    private EntitySerializer<? extends RegulatoryGene> getGeneSerializer(String code ){
        return geneSerializers.get(code);
    }

    private void addEventSerializer( EntitySerializer <? extends SimulationEvent> serializer){
        eventSerializers.put(serializer.getCode(), serializer);
    }
    private EntitySerializer <? extends SimulationEvent> getEventSerializer(String code){
        return eventSerializers.get(code);
    }

    private void addRegulatorSerializer( EntitySerializer <? extends Regulator> serializer){
        regulatorSerializers.put(serializer.getCode(), serializer);
    }
    private EntitySerializer <? extends Regulator> getRegulatorSerializer(String code){
        return regulatorSerializers.get(code);
    }
    ////////////////////////////////*********//////////////////////////////////////

    public void addGene(RegulatoryGene gene){
        genes.put(gene.getName(),gene);
    }
    public RegulatoryGene getGene (String geneName){
        return genes.get(geneName);
    }

//TODO: Resolve read with default to take in account the regulator
    public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
        genes =  new HashMap<>();
        double timeUpperBound = 20; double timeStepLength = 0.01;
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
            String[] tokens = line.split(" ");
            switch (tokens[0]){
                case "TimeStep" -> timeStepLength = Double.parseDouble(tokens[1]);
                case "TimeUpperBound" -> timeUpperBound = Double.parseDouble(tokens[1]);
                case "ConstantRegulatoryGene" ->
                        addGene(ConstantRegulatoryGeneSerializer.getInstance().deserialize(line,this));
                case "ConcreteRegulatoryGene" ->
                        addGene(ConcreteRegulatoryGeneSerializer.getInstance().deserialize(line,this));
                case "SetProteinConcentrationEvent" ->
                        events.add(SetProteinConcentrationEventSerializer.getInstance().deserialize(line,this));
                case "SetSignaledEvent" ->
                        events.add(SetSignaledEventSerializer.getInstance().deserialize(line,this));
            }
        }
        return new RegulatoryNetwork(new ArrayList<>(genes.values()), events, timeStepLength, timeUpperBound);
    }
}







//            if (tokens[0].equals("ConstantRegulatoryGene")){
//                    addGene(ConstantRegulatoryGeneSerializer.getInstance().deserialize(line,this));}
//                    if (tokens[0].equals("ConcreteRegulatoryGene")){
//                    addGene(ConcreteRegulatoryGeneSerializer.getInstance().deserialize(line,this));
//                    }
//                    if (tokens[0].equals("SetProteinConcentrationEvent")){
//                    events.add(SetProteinConcentrationEventSerializer.getInstance().deserialize(line,this));
//                    }
//                    if (tokens[0].equals("SetSignaledEvent")){
//                    events.add(SetSignaledEventSerializer.getInstance().deserialize(line,this));
//                    } if (tokens[1].equals("AlwaysOnRegulator")){
//                    AlwaysOnRegulatorSerializer.getInstance().deserialize(line,this);
//                    }


//        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
//                String[] tokens = line.split(" ");
//                if (tokens[0].equals("TimeStep")) { timeStepLength = Double.parseDouble(tokens[1]); }
//                if (tokens[0].equals("TimeUpperBound")){timeUpperBound = Double.parseDouble(tokens[1]);}
//                addGene(getGeneSerializer(tokens[0]).deserialize(line,this));
//                events.add(getEventSerializer(tokens[0]).deserialize(line,this));
//                getRegulatorSerializer(tokens[0]).deserialize(line,this);
//
//                }