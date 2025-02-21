package Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    BoardDTO(int bno, String btitle, String bcontent, String bwriter) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
        this.bdate = new Date();
    }

    @Override
    public int compareTo(BoardDTO o) {
        if (this.bno > o.bno) return -1;
        else return 0;
    }
}
