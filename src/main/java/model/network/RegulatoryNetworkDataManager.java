package model.network;

import model.events.SetProteinConcentrationEvent;
import model.events.SimulationEvent;
import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.AlwaysOnRegulator;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegulatoryNetworkDataManager {
  public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    bufferedWriter.write("TimeStep " + regulatoryNetwork.getTimeStepLength() + "\n");
    bufferedWriter.write("TimeUpperBound " + regulatoryNetwork.getTimeUpperBound() + "\n");
    writeGenes(bufferedWriter, regulatoryNetwork);
    writeEvents(bufferedWriter, regulatoryNetwork);
  }

  private static void writeEvents(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    for(SimulationEvent event : regulatoryNetwork.getSimulationEvents()){
      StringBuilder eventString = new StringBuilder(event.getClass().getSimpleName() + " ");
      eventString.append(event.getTime()).append(" ");
      List<RegulatoryGene> genes = event.getGenes();
      for(int index = 0; index < genes.size(); index++){
        if(index!=0) {
          eventString.append(",");
        }
        eventString.append(genes.get(index));
      }
      eventString.append(" ");
      eventString.append(event.getInfo()).append("\n");
      bufferedWriter.write(eventString.toString());
    }
  }

  private static void writeGenes(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    for(RegulatoryGene gene : regulatoryNetwork.getGenes()){
      String geneString = gene.getClass().getSimpleName() + " ";
      geneString += gene.getName() + " ";
      geneString += gene.getInitialProteinConcentration() + " ";
      geneString += gene.isSignaled() + "\n";
      bufferedWriter.write(geneString);
    }
  }

  public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
    Map<String,RegulatoryGene> genes = new HashMap<>();
    List<SimulationEvent> events = new ArrayList<>();

    double timeUpperBound = 20;
    double timeStepLength = 0.01;

    int lineNumber = 0;

    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
      String[] tokens = line.split(" ");
      switch (tokens[0]){
        case "TimeStep" -> timeStepLength = Double.parseDouble(tokens[1]);
        case "TimeUpperBound" -> timeUpperBound = Double.parseDouble(tokens[1]);
        case "ConstantRegulatoryGene" -> readConstantRegulatoryGene(genes, tokens);
        default -> throw new IOException("Parse error line " + lineNumber);
      }
      lineNumber++;
    }
    return new RegulatoryNetwork(new ArrayList<>(genes.values()), events, timeStepLength, timeUpperBound);
  }

  private static void readConstantRegulatoryGene(Map<String, RegulatoryGene> genes, String[] tokens) {
    String name = tokens[1];
    double concentration = Double.parseDouble(tokens[2]);
    boolean isSignaled = Boolean.parseBoolean(tokens[3]);
    genes.put(name, new ConstantRegulatoryGene(name, concentration, isSignaled));
  }

//  public RegulatoryNetwork generate() {
//    List<RegulatoryGene> genes = new ArrayList<>();
//    RegulatoryGene x = new ConstantRegulatoryGene("X", 3, true);
//    genes.add(x);
//    RegulatoryGene y = new ConstantRegulatoryGene("Y", 2, true);
//    genes.add(y);
//    RegulatoryGene z = new ConstantRegulatoryGene("Z", 4, false);
//    genes.add(z);
//
//    RegulatoryGene reg = new ConcreteRegulatoryGene("INS",80, 0.9,
//            50, true);
//    reg.setProteinConcentration(reg.getInitialProteinConcentration());
//    genes.add(reg);
//
//
//    List<SimulationEvent> simulationEvents = new ArrayList<>();
//    return new RegulatoryNetwork(genes, simulationEvents, 0.01, 20);
//  }

  public RegulatoryNetwork generate () {
    List < RegulatoryGene > genes = new ArrayList <>() ;
    RegulatoryGene x = new ConcreteRegulatoryGene ( " X " , 3.0 , 0.1 , 2.0 , true ) ;
    x.setRegulator ( new AlwaysOnRegulator() ) ;
    genes.add(x) ;
    RegulatoryGene y = new ConcreteRegulatoryGene ( " Y " , 4.0 , 0.12 , 2.0 , true ) ;
    genes.add (y) ;
    y . setRegulator ( new BooleanActivator(10 ,x )) ;
    RegulatoryGene z = new ConcreteRegulatoryGene ( " Z " , 5.0 , 0.15 , 2.0 , true ) ;
    genes.add (z) ;
    z.setRegulator ( new BooleanRepressor(7 , y ) ) ;
    List < SimulationEvent > simulationEvents = new ArrayList <>() ;
    simulationEvents . add ( new SetProteinConcentrationEvent ( List.of( x ) , 10.0 , 3.0) ) ;
    simulationEvents . add ( new SetProteinConcentrationEvent ( List.of(x , y ) , 5.0 ,
            4.0) ) ;
    return new RegulatoryNetwork ( genes , simulationEvents , 0.01 , 20.0) ;
  }


}
