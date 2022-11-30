package model.network;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.events.SimulationEvent;
import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class RegulatoryNetworkDataManager {
  public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    bufferedWriter.write("TimeStep " + regulatoryNetwork.getTimeStepLength() + "\n");
    bufferedWriter.write("TimeUpperBound " + regulatoryNetwork.getTimeUpperBound() + "\n");
    writeGenes(bufferedWriter, regulatoryNetwork);
    writeEvents(bufferedWriter, regulatoryNetwork);

  }

  private static void writeEvents(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    for(SimulationEvent event : regulatoryNetwork.getSimulationEvents()){
      // add event name
      StringBuilder eventString = new StringBuilder(event.getClass().getSimpleName() + " ");
      // append time to it
      eventString.append(event.getTime()).append(" ");
      // append genes of the list separated with comma (no white spaces)
      List<RegulatoryGene> genes = event.getGenes();
      for(int index = 0; index < genes.size(); index++){
        if(index!=0) {
          eventString.append(",");
        }
        eventString.append(genes.get(index).getName().replaceAll("\\s+","") );
      }
      // append event
      eventString.append(" ");
      eventString.append(event.getInfo()).append("\n");
      bufferedWriter.write(eventString.toString());
    }
  }

  private static void writeGenes(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    for(RegulatoryGene gene : regulatoryNetwork.getGenes()){
      String geneString = gene.getClass().getSimpleName()+ " "; //class name
      if (geneString.equals("ConcreteRegulatoryGene ")){
        geneString += gene.getName()+ " ";
        geneString += gene.getMaximalProduction()+ " ";
        geneString += gene.getDegradationRate() + " ";
        geneString += gene.getInitialProteinConcentration() + " ";
        geneString += gene.isSignaled()+ "\n";
        //if no regulator for considerate gene
        if ( gene.getRegulator() == null) {geneString +="";}
        // regulator available for the gene append its info
        if ( gene.getRegulator() != null && gene.getRegulator().getInfo() != null) {
          geneString += gene.getRegulator().getClass().getSimpleName() + " "+ gene.getName() + " "+ gene.getRegulator().getInfo() + "\n";
        }
        // avoid writing 'null' for alwaysOnRegulator
        if ( gene.getRegulator() != null && gene.getRegulator().getInfo() == null)  {
          geneString += gene.getRegulator().getClass().getSimpleName() + " "+ gene.getName() + "\n";
        }
        // write
        bufferedWriter.write(geneString);

      } else if (geneString.equals("ConstantRegulatoryGene ")) {
        geneString += gene.getName() + " ";
        geneString += gene.getInitialProteinConcentration() + " ";
        geneString += gene.isSignaled() + "\n";
        bufferedWriter.write(geneString);
      }
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
        case "ConcreteRegulatoryGene" -> readConcreteRegulatoryGene(genes, tokens);
        case "AlwaysOnRegulator" -> readAlwaysOnRegulator(genes, tokens);
        case "BooleanActivator" -> readBooleanActivator(genes, tokens);
        case "BooleanRepressor" -> readBooleanRepressor(genes, tokens);
        case "SetProteinConcentrationEvent" -> readSetProteinConcentrationEvent(events, tokens, genes);
        case "SetSignaledEvent" -> readSetSignaledEvent(events, tokens, genes);
        default -> throw new IOException("Parse error line " + lineNumber);
      }
      lineNumber++;
    }
    return new RegulatoryNetwork(new ArrayList<>(genes.values()), events, timeStepLength, timeUpperBound);
  }

  private static void readSetSignaledEvent(List<SimulationEvent> events , String[] tokens,
                                                       Map<String,RegulatoryGene>  genes) {
    double time = Double.parseDouble(tokens[1]);
    boolean newSignaledValue = Boolean.parseBoolean(tokens[3]);
    // list of regulatory gene names
    String gene_reg =  tokens[2];
    String[] elements = gene_reg.split(",");
    List<String> geneElements = Arrays.asList(elements);
    // retrieve list of regulatory genes from names
    List<RegulatoryGene> genes_list = new ArrayList<>();
    for (String key: genes.keySet()) {
      if(geneElements.contains(key)){
        genes_list.add(genes.get(key));
      }
    }
    // add the event after reading all necessary three elements
    events.add ( new SetSignaledEvent( genes_list, time , newSignaledValue) ) ;
  }


  private static void readSetProteinConcentrationEvent(List<SimulationEvent> events , String[] tokens,
                                                       Map<String,RegulatoryGene>  genes) {
    double time = Double.parseDouble(tokens[1]);
    double newConcentration = Double.parseDouble(tokens[3]);
    // list of regulatory gene names
    String gene_reg =  tokens[2];  // regulatory gene
    String[] elements = gene_reg.split(",");
    List<String> geneElements = Arrays.asList(elements);
    // retrieve list of regulatory genes from names
    List<RegulatoryGene> genes_list = new ArrayList<>();
    for (String key: genes.keySet()) {
      if(geneElements.contains(key)){
        genes_list.add(genes.get(key));
      }
    }
    // add the event after reading all three elements
    events.add ( new SetProteinConcentrationEvent ( genes_list, time , newConcentration) ) ;
  }


  private static void readBooleanRepressor(Map<String,RegulatoryGene>  genes, String[] tokens) {
    String name = tokens[1];  // to be regulated
    double threshold = Double.parseDouble(tokens[2]);
    String name_regulator =  tokens[3];  // regulatory gene
    for (String key: genes.keySet()) {
      if(key.equals(name_regulator)){
        genes.get(name).setRegulator (new BooleanRepressor(threshold, genes.get(key)));
      }
    }

  }

  private static void readBooleanActivator(Map<String,RegulatoryGene>  genes, String[] tokens) {
    String name = tokens[1];  // to be regulated
    double threshold = Double.parseDouble(tokens[2]);
    String name_regulator =  tokens[3];  // regulatory gene

    for (String key: genes.keySet()) {
      if(key.equals(name_regulator)){
        genes.get(name).setRegulator (new BooleanActivator(threshold, genes.get(key)));
      }
    }

  }


  private static void readAlwaysOnRegulator(Map<String,RegulatoryGene>  genes, String[] tokens) {
    String name = tokens[1];
    for (String key: genes.keySet()) {
      if(key.equals(name)){
        genes.get(key).setRegulator (new AlwaysOnRegulator());
      }
    }

  }


  private static void readConcreteRegulatoryGene(Map<String, RegulatoryGene> genes, String[] tokens) {
    String name = tokens[1];
    double maximalProduction = Double.parseDouble(tokens[2]);
    double degradationRate = Double.parseDouble(tokens[3]);
    double initialProteinConcentration = Double.parseDouble(tokens[4]);
    boolean isSignaled = Boolean.parseBoolean(tokens[5]);
    genes.put(name, new ConcreteRegulatoryGene(name,maximalProduction, degradationRate,
            initialProteinConcentration, isSignaled));

  }

  private static void readConstantRegulatoryGene(Map<String, RegulatoryGene> genes, String[] tokens) {
    String name = tokens[1];
    double concentration = Double.parseDouble(tokens[2]);
    boolean isSignaled = Boolean.parseBoolean(tokens[3]);
    genes.put(name, new ConstantRegulatoryGene(name, concentration, isSignaled));
  }



//  public RegulatoryNetwork generate() {
//    List<RegulatoryGene> genes = new ArrayList<>();
//    RegulatoryGene x = new ConcreteRegulatoryGene("X", 3.0, 0.1, 2.0, true);
//    genes.add(x);
//    RegulatoryGene y = new ConcreteRegulatoryGene("Y", 4.0, 0.12, 2.0, true);
//    genes.add(y);
//    RegulatoryGene z = new ConcreteRegulatoryGene("Z", 5.0, 0.15, 2.0, true);
//    genes.add(z);
//    BooleanActivator booleanActivator1 = new BooleanActivator(3, x);
//    BooleanRepressor booleanRepressor1 = new BooleanRepressor(7, y);
//    BooleanActivator booleanActivator2 = new BooleanActivator(1, z);
//    BooleanRepressor booleanRepressor2 = new BooleanRepressor(2, y);
//    BooleanActivator booleanActivator3 = new BooleanActivator(2, x);
//    BooleanRepressor booleanRepressor3 = new BooleanRepressor(2, z);
//    MinCompositeRegulator minCompositeRegulator = new MinCompositeRegulator(List.of(booleanActivator1, booleanActivator2));
//    MaxCompositeRegulator maxCompositeRegulator1 =
//            new MaxCompositeRegulator(List.of(booleanRepressor1, booleanRepressor2));
//    MaxCompositeRegulator maxCompositeRegulator2 =
//            new MaxCompositeRegulator(List.of(booleanRepressor3, booleanActivator3));
//    x.setRegulator(maxCompositeRegulator1);
//    y.setRegulator(maxCompositeRegulator2);
//    z.setRegulator(minCompositeRegulator);
//    List<SimulationEvent> simulationEvents = new ArrayList<>();
//    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x), 10.0, 3.0));
//    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x, y), 5.0, 4.0));
//    simulationEvents.add(new SetSignaledEvent(List.of(x, y), 15.0, false));
//    return new RegulatoryNetwork(genes, simulationEvents, 0.01, 20.0);
//  }


  public RegulatoryNetwork generate() {
    List<RegulatoryGene> genes = new ArrayList<>();
    RegulatoryGene x = new ConcreteRegulatoryGene("X", 3.0, 0.1, 2.0, true);
    x.setRegulator(new AlwaysOnRegulator());
    genes.add(x);
    RegulatoryGene y = new ConcreteRegulatoryGene("Y", 4.0, 0.12, 2.0, true);
    genes.add(y);
    y.setRegulator(new BooleanActivator(10, x));
    RegulatoryGene z = new ConcreteRegulatoryGene("Z", 5.0, 0.15, 2.0, true);
    genes.add(z);
    z.setRegulator(new BooleanRepressor(7, y));
    RegulatoryGene h = new ConcreteRegulatoryGene("H", 7.0, 0.10, 2.0, true);
    genes.add(h);
    h.setRegulator(new HillActivator(2,18,y));
    List<SimulationEvent> simulationEvents = new ArrayList<>();
    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x), 10.0, 3.0));
    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x, y), 5.0, 4.0));
    return new RegulatoryNetwork(genes, simulationEvents, 0.01, 20.0);
  }
}