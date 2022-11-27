package model.file.serializers;

import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;

public interface EntitySerializer<E> {
    String getCode () ;
    String serialize (E entity , RegulatoryNetworkWriter writer) ;
    E deserialize (String string , RegulatoryNetworkReader reader) ;

}
