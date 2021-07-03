package movieticket;

import javax.persistence.*;

@Entity
@Table(name="Mypage_table")
public class Mypage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long reserveId;
        private String userId;
        private String movieId;
        private Integer cnt;
        private String status;
        private Long payId;
        private Long cinemaId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getReserveId() {
            return reserveId;
        }

        public void setReserveId(Long reserveId) {
            this.reserveId = reserveId;
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
        public Long getPayId() {
            return payId;
        }

        public void setPayId(Long payId) {
            this.payId = payId;
        }
        public Long getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(Long cinemaId) {
            this.cinemaId = cinemaId;
        }

}
