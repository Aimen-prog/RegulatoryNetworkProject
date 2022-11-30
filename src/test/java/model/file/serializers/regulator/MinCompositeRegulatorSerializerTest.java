package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinCompositeRegulatorSerializerTest {
    @Test
    public void testSerialize() {
        List<Regulator> RegulatorList = new ArrayList<>();
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        Regulator regulator2 = new AlwaysOnRegulator();
        //MAKE A LIST OF TWO REGULATORS
        RegulatorList.add(regulator1); RegulatorList.add(regulator2);
        MinCompositeRegulator compMin = new MinCompositeRegulator(RegulatorList);

        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        assertEquals("MinCompositeRegulator [BooleanActivator 14.0 INS,AlwaysOnRegulator]",
                MinCompositeRegulatorSerializer.getInstance().serialize(compMin,writer));


    }
    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        String line = "MinCompositeRegulator [BooleanActivator 14.0 INS,AlwaysOnRegulator]";
        List<Regulator> RegulatorList = new ArrayList<>();
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        Regulator regulator2 = new AlwaysOnRegulator();
        reader.addGene(X);

        RegulatorList.add(regulator1); RegulatorList.add(regulator2);
        MinCompositeRegulator compMin = new MinCompositeRegulator(RegulatorList);
        MinCompositeRegulator actual = MinCompositeRegulatorSerializer.getInstance().deserialize(line,reader);


        assertTrue(actual.getRegulators().get(0).equals(
                compMin.getRegulators().get(0)));
        assertTrue(actual.getRegulators().get(1).getClass().getSimpleName().
                equals(compMin.getRegulators().get(1).getClass().getSimpleName()));


    }
}
