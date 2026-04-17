package com.pepe.ejemmplokafka3.Topologia;

import com.pepe.ejemmplokafka3.Modelos.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MiSegundoStreamTopology {
    public static String MENSAJE = "mensajeJson";
    public static String OUTPUT_MENSAJE = "output-mensajeJson";
    @Autowired
    public void procesar (StreamsBuilder streamsBuilder){
        KStream<String, Usuario> mensajeStream = streamsBuilder
                .stream(MENSAJE, Consumed.with(Serdes.String(), new JsonSerde<Usuario>(Usuario.class)));
        // Mostrar el mensaje original (Tiene error durante compilación... falta)
        mensajeStream.print(Printed.<String,Usuario>toSysOut().withLabel("original"));
        KStream<String,String> mensajeModificadoStream =
                mensajeStream.mapValues((llave, valor)->valor.toUpperCase());
        // Mostrar el mensaje modificado
        mensajeModificadoStream.print(Printed.<String,String>toSysOut().withLabel("modificado"));
        mensajeModificadoStream.to(OUTPUT_MENSAJE, Produced.with(Serdes.String(), Serdes.String()));
        // http://localhost:8080/api/v1/kafka/publicar?mensajito=Pepe
    }

}