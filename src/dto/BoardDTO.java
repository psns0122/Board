package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
public class BoardDTO implements Comparable<BoardDTO> {
    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private Date bdate;

    public BoardDTO(int bno, String btitle, String bcontent, String bwriter) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
        this.bdate = new Date();
    }

    @Override
    public int compareTo(BoardDTO o) {
        return Integer.compare(o.bno, this.bno);
    }

    public String getFormattedBdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        return formatter.format(bdate);
    }

    public String toFormattedString() {
        return String.format("%-8d%-12s%-18s%-50s",
                this.bno, this.bwriter, getFormattedBdate(), this.btitle);
    }
}
