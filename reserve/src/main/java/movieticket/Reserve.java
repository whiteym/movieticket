package movieticket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import movieticket.external.Payment;
import movieticket.external.PaymentService;

@Entity
@Table(name="Reserve_table")
public class Reserve {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userId;
    private String movieId;
    private Integer cnt;
    private String status;

    @PostPersist
    public void onPostPersist(){       
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        Payment payment = new Payment();
        payment.setReserveId(this.getId());
        payment.setMovieId(this.movieId);
        payment.setCnt(this.getCnt());
        payment.setUserId(this.getUserId());
        // mappings goes here
        ReserveApplication.applicationContext.getBean(PaymentService.class)
        .pay(payment);

        //this.setStatus("Reserve Status is " + System.getenv("STATUS"));
                
        Reserved reserved = new Reserved();
        BeanUtils.copyProperties(this, reserved);
        reserved.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove(){
        ReserveCancelled reserveCancelled = new ReserveCancelled();
        BeanUtils.copyProperties(this, reserveCancelled);
        reserveCancelled.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
