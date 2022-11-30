package model.file.serializers.regulator;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxCompositeRegulatorSerializerTest {

    @Test
    public void testSerialize() {
        List<Regulator> RegulatorList = new ArrayList<>();
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        Regulator regulator2 = new AlwaysOnRegulator();
        //MAKE A LIST OF TWO REGULATORS
        RegulatorList.add(regulator1); RegulatorList.add(regulator2);
        MaxCompositeRegulator compMax = new MaxCompositeRegulator(RegulatorList);

        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        assertEquals("MaxCompositeRegulator [BooleanActivator 14.0 INS,AlwaysOnRegulator]",
                MaxCompositeRegulatorSerializer.getInstance().serialize(compMax,writer));


    }

    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        String line = "MaxCompositeRegulator [BooleanActivator 14.0 INS,AlwaysOnRegulator]";
        List<Regulator> RegulatorList = new ArrayList<>();
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        Regulator regulator2 = new AlwaysOnRegulator();
        reader.addGene(X);

        RegulatorList.add(regulator1); RegulatorList.add(regulator2);
        MaxCompositeRegulator compMax = new MaxCompositeRegulator(RegulatorList);
        MaxCompositeRegulator actual = MaxCompositeRegulatorSerializer.getInstance().deserialize(line,reader);


        assertTrue(actual.getRegulators().get(0).equals(
                            compMax.getRegulators().get(0)));
        assertTrue(actual.getRegulators().get(1).getClass().getSimpleName().
                equals(compMax.getRegulators().get(1).getClass().getSimpleName()));


    }


}
