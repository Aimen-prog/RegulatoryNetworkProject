package model.file.serializers.list;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.BooleanActivator;
import model.regulators.Regulator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ListRegulatorSerializerTest {
    @Test
    public void testSerialize() {
        RegulatoryGene gene1 = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        BooleanActivator reg2 = new BooleanActivator(14.,gene2);
        BooleanActivator reg1 = new BooleanActivator(12.,gene1);

        List<Regulator> reglist = new ArrayList<>();
        reglist.add(reg1); reglist.add(reg2);
        assertThat(ListRegulatorSerializer.getInstance().serialize(reglist,new RegulatoryNetworkWriter()))
                .isEqualTo("[BooleanActivator 12.0 INS,BooleanActivator 14.0 P53]");
    }

    @Test
    public void testDeserialize() {
        String rstring = "[BooleanActivator 12.0 INS,BooleanActivator 14.0 P53]";
        RegulatoryGene gene1 = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        BooleanActivator reg2 = new BooleanActivator(14.,gene2);
        BooleanActivator reg1 = new BooleanActivator(12.,gene1);

        List<Regulator> reglist = new ArrayList<>();
        reglist.add(reg1); reglist.add(reg2);
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        reader.addGene(gene1); reader.addGene(gene2);

        ListGeneSerializer.getInstance().deserialize(rstring,new RegulatoryNetworkReader());

        assertEquals(ListRegulatorSerializer.getInstance().deserialize(rstring, reader).get(0), reglist.get(0));
        assertEquals(ListRegulatorSerializer.getInstance().deserialize(rstring, reader).get(1), reglist.get(1));
        assertNotEquals(ListRegulatorSerializer.getInstance().deserialize(rstring, reader).get(1), reglist.get(0));

    }


}
