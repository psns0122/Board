package service;

import dto.BoardDTO;
import dto.CustomException;
import dto.ErrorCode;
import dto.SelectType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardDAO implements BoardService{
    Scanner sc = new Scanner(System.in);

    @Getter
    private static BoardDAO INSTANCE;
    @Getter
    private ArrayList<BoardDTO> boards = new ArrayList<>();
    private int boardSize = 0;

    static {
        BoardDAO.INSTANCE = new BoardDAO();
    }

    /**
     * 메인 메뉴에서 create를 선택하면 새로운 게시물의 제목, 내용, 작성자를
     * 키보드로 입력받고, 보조메뉴에서 ok를 선택하면 boards 테이블에 새로운 게시물이 저장된다
     */
    public void create() {
        String title, content, writer;
        int choice;

        System.out.println();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목: "); title = sc.next();
        System.out.print("내용: "); content = sc.next();
        System.out.print("작성자: "); writer = sc.next();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        SelectType select = switch (choice) {
            case 1 -> SelectType.OK;
            case 2 -> SelectType.CANCEL;
            default -> SelectType.ERROR;
        };

        if (select == SelectType.OK) {
            this.boards.add(new BoardDTO(++this.boardSize, title, content, writer));
        } else if (select != SelectType.CANCEL) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }

    /**
     * 메인 메뉴에서 read를 선택했을 때, 게시물의 번호를 키보드로 입력받고,
     * boards 테이블에 있는 해당 게시물을 가져와서 출력한다
     */
    public void read() {
        String bnoInput;
        int bno;

        System.out.println();
        System.out.println("[게시물 읽기]");
        System.out.print("bno: "); bnoInput = sc.next();
        System.out.println("#############");

        try {
            bno = Integer.parseInt(bnoInput);
        } catch (NumberFormatException e) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        if (bno <= this.boardSize) {
            System.out.println("번호: " + bno);
            bno = this.boardSize-bno;
            System.out.println("제목: " + this.boards.get(bno).getBtitle());
            System.out.println("내용: " + this.boards.get(bno).getBcontent());
            System.out.println("작성자: " + this.boards.get(bno).getBwriter());
            System.out.println("날짜: " + this.boards.get(bno).getBdate());
        } else {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        SelectType select = switch (choice) {
            case "1" -> SelectType.UPDATE;
            case "2" -> SelectType.DELETE;
            case "3" -> SelectType.LIST;
            default -> SelectType.ERROR;
        };

        switch (select) {
            case UPDATE -> this.update(bno);
            case DELETE -> this.delete(bno);
            case ERROR -> throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }

    /**
     * 메인 메뉴에서 read를 선택하고, 보조 메뉴에서 ok를 선택했을 때,
     * boards 테이블의 전체 게시물 정보를 삭제한다
     */
    public void clear() {
        System.out.println();
        System.out.println("[게시물 전체 삭제]");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        SelectType select = switch (choice) {
            case "1" -> SelectType.OK;
            case "2" -> SelectType.CANCEL;
            default -> SelectType.ERROR;
        };

        if (select == SelectType.OK) {
            this.boards.clear();
            this.boardSize = 0;
        } else if (select != SelectType.CANCEL) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }

    /**
     * 보조메뉴에서 update를 선택했을 때 제목, 내용, 작성자의 수정 내용을 입력할 수 있도록 한다
     * @param bno 게시물 번호
     */
    public void update(int bno) {
        String title, content, writer;

        System.out.println();
        System.out.println("[수정 내용 입력]");
        System.out.print("제목: "); title = sc.next();
        System.out.print("내용: "); content = sc.next();
        System.out.print("작성자: "); writer = sc.next();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        SelectType select = switch (choice) {
            case "1" -> SelectType.OK;
            case "2" -> SelectType.CANCEL;
            default -> SelectType.ERROR;
        };

        if (select == SelectType.OK) {
            this.boards.get(bno).setBtitle(title);
            this.boards.get(bno).setBcontent(content);
            this.boards.get(bno).setBwriter(writer);
            this.boards.get(bno).setBdate(new Date());
        } else if (select != SelectType.CANCEL) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }


    /**
     * 보조메뉴에서 delete를 선택했을 때 boards 테이블에서 해당 게시물을 삭제한다
     * @param bno 게시물 번호
     */
    public void delete(int bno) {
        this.boards.remove(bno);
        this.boardSize--;

        int index = this.boardSize;
        for (BoardDTO board : this.boards) {
            board.setBno(index--);
        }
    }
}
