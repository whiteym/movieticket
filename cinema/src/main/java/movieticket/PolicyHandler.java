package movieticket;

import movieticket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired CinemaRepository cinemaRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCompleted_ReserveRecive(@Payload PayCompleted payCompleted){

        if(payCompleted.isMe()){            
            Cinema cinema = new Cinema();
            cinema.setMovieId(payCompleted.getMovieId());
            cinema.setReserveId(payCompleted.getReserveId());
            cinema.setCnt(payCompleted.getCnt());
            cinema.setUserId(payCompleted.getUserId()); 
            cinemaRepository.save(cinema);
        }            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}
}
