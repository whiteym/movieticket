package movieticket;

public class PayCompleted extends AbstractEvent {

    private Long id;
    private Long reserveId;
    private String userId;
    private String movieId;
    private Integer cnt;
    private String cancelYN;

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
    public String getCancelYn() {
        return cancelYN;
    }

    public void setCancelYn(String cancelYN) {
        this.cancelYN = cancelYN;
    }
}