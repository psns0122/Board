package service;

import dto.BoardDTO;
import exception.CustomException;
import enumer.ErrorCode;
import enumer.SelectType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardDAO implements BoardService{
    Scanner sc = new Scanner(System.in);

    @Getter
    private static final BoardDAO INSTANCE = new BoardDAO();
    @Getter
    private ArrayList<BoardDTO> boards = new ArrayList<>();
    private int boardSize = 0;

    public void create(BoardDTO item) {
        item.setBno(++this.boardSize);
        this.boards.add(item);
    }

    public void read(int bno) {
        if (bno < 0 || bno > this.boardSize) {
            throw new CustomException(ErrorCode.OUT_OF_RANGE);
        }

        System.out.println("번호: " + bno);
        bno = this.boardSize-bno;
        System.out.println("제목: " + this.boards.get(bno).getBtitle());
        System.out.println("내용: " + this.boards.get(bno).getBcontent());
        System.out.println("작성자: " + this.boards.get(bno).getBwriter());
        System.out.println("날짜: " + this.boards.get(bno).getBdate());
    }

    public void clear() {
        this.boards.clear();
        this.boardSize = 0;
    }

    public void update(int bno, BoardDTO item) {
        this.boards.get(bno).setBtitle(item.getBtitle());
        this.boards.get(bno).setBcontent(item.getBcontent());
        this.boards.get(bno).setBwriter(item.getBwriter());
        this.boards.get(bno).setBdate(new Date());
    }

    public void delete(int bno) {
        this.boards.remove(bno);
        this.boardSize--;

        int index = this.boardSize;
        for (BoardDTO board : this.boards) {
            board.setBno(index--);
        }
    }
}
