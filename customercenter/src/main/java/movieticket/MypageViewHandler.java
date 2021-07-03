package movieticket;

import movieticket.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCompleted_then_CREATE_1 (@Payload PayCompleted payCompleted) {
        try {

            if (!payCompleted.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setReserveId(payCompleted.getReserveId());
            mypage.setUserId(payCompleted.getUserId());
            mypage.setMovieId(payCompleted.getMovieId());
            mypage.setPayId(payCompleted.getId());
            mypage.setCnt(payCompleted.getCnt());
            mypage.setStatus("Mypage Status : PayCompleted");
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAssigned_then_UPDATE_1(@Payload Assigned assigned) {
        try {
            if (!assigned.validate()) return;
                // view 객체 조회
            List<Mypage> mypageList = mypageRepository.findByReserveId(assigned.getReserveId());
            for(Mypage mypage : mypageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setCinemaId(assigned.getId());
                mypage.setStatus("Mypage Status : assigned");
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCancelled_then_UPDATE_2(@Payload ReserveCancelled reserveCancelled) {
        try {
            if (!reserveCancelled.validate()) return;
                // view 객체 조회
            List<Mypage> mypageList = mypageRepository.findByReserveId(reserveCancelled.getId());
            for(Mypage mypage : mypageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setCinemaId(reserveCancelled.getId());
                mypage.setStatus("Mypage Status : PayCancelled");
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCancelled_then_DELETE_1(@Payload PayCancelled payCancelled) { 
        try {
            if (!payCancelled.validate()) return;
                // view 객체 조회         
            List<Mypage> mypageList = mypageRepository.findByReserveId(payCancelled.getId());
            for(Mypage mypage : mypageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setCinemaId(payCancelled.getId());
                mypage.setStatus("Mypage Status : paymentCancelled");                    
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}