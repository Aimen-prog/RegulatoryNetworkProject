package model.file.serializers.regulator;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;
import org.junit.jupiter.api.Test;

public class AlwaysOffRegulatorSerializerTest {

    @Test
    public void testSerialize() {
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        AlwaysOffRegulator regulator = new AlwaysOffRegulator();
        assertThat(AlwaysOffRegulatorSerializer.getInstance().serialize(regulator,writer))
                .isEqualTo("AlwaysOffRegulator");

    }

    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        assertEquals("AlwaysOffRegulator", AlwaysOffRegulatorSerializer.getInstance().
                deserialize("", reader).getClass().getSimpleName());
        assertEquals(0., AlwaysOffRegulatorSerializer.getInstance().
                deserialize("", reader).inputFunction());

    }
}
