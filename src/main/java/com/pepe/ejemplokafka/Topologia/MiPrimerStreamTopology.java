package com.pepe.ejemplokafka.Topologia;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MiPrimerStreamTopology {
    public static String MENSAJE = "pruebita";
    public static String OUTPUT_MENSAJE = "output-mensaje";
    @Autowired
    public void procesar (StreamsBuilder streamsBuilder){
        KStream<String,String> mensajeStream = streamsBuilder
                .stream(MENSAJE, Consumed.with(Serdes.String(), Serdes.String()));
        // Mostrar el mensaje original
        mensajeStream.print(Printed.<String,String>toSysOut().withLabel("original"));
        KStream<String,String> mensajeModificadoStream =
                mensajeStream.mapValues((llave, valor)->valor.toUpperCase());
        // Mostrar el mensaje modificado
        mensajeModificadoStream.print(Printed.<String,String>toSysOut().withLabel("modificado"));
        mensajeModificadoStream.to(OUTPUT_MENSAJE, Produced.with(Serdes.String(), Serdes.String()));
        // http://localhost:8080/api/v1/kafka/publicar?mensajito=Pepe
    }

}
