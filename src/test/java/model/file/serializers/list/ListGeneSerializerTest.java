package model.file.serializers.list;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ListGeneSerializerTest {

    @Test
    public void testSerialize() {
        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        ConstantRegulatoryGene gene2 = new ConstantRegulatoryGene("Y",
                17.,true);
        List<RegulatoryGene> genelist = new ArrayList<>();
        genelist.add(gene1); genelist.add(gene2);
        assertThat(ListGeneSerializer.getInstance().serialize(genelist,new RegulatoryNetworkWriter()))
                .isEqualTo("[X,Y]");
    }

    @Test
    public void testDeserialize() {
        String gnames = "[X,Y]";
        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        ConstantRegulatoryGene gene2 = new ConstantRegulatoryGene("Y",
                17.,true);
        List<RegulatoryGene> genelist = new ArrayList<>();
        genelist.add(gene1); genelist.add(gene2);

        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        reader.addGene(gene1); reader.addGene(gene2);

        ListGeneSerializer.getInstance().deserialize(gnames,new RegulatoryNetworkReader());

        assertEquals(ListGeneSerializer.getInstance().deserialize(gnames, reader).get(0), genelist.get(0));
        assertEquals(ListGeneSerializer.getInstance().deserialize(gnames, reader).get(1), genelist.get(1));
        assertNotEquals(ListGeneSerializer.getInstance().deserialize(gnames, reader).get(0), genelist.get(1));

    }
}
