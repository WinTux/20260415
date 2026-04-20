package com.pepe.ejemplokafka.Configuraciones;

import com.pepe.ejemplokafka.Topologia.MiPrimerStreamTopology;
import com.pepe.ejemplokafka.Topologia.MiSegundoStreamTopology;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MensajeStreamConfiguration {
    @Bean
    public NewTopic mensajeTopic(){
        return TopicBuilder.name(MiPrimerStreamTopology.MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic mensajeSalidaTopic(){
        return TopicBuilder.name(MiPrimerStreamTopology.OUTPUT_MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic mensajeJsonTopic(){
        return TopicBuilder.name(MiSegundoStreamTopology.MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic mensajeSalidaJsonTopic(){
        return TopicBuilder.name(MiSegundoStreamTopology.OUTPUT_MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
