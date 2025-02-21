package Board;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BoardManager {
    final String OK = "1";
    final String CANCEL = "2";
    final String UPDATE = "1";
    final String DELETE = "2";
    final String LIST = "3";
    Scanner sc = new Scanner(System.in);

    @Getter
    private static BoardManager INSTANCE;
    @Getter
    private ArrayList<BoardDTO> boards = new ArrayList<>();
    private int boardSize = 0;

    static {
        BoardManager.INSTANCE = new BoardManager();
    }

    /**
     * 메인 메뉴에서 create를 선택하면 새로운 게시물의 제목, 내용, 작성자를
     * 키보드로 입력받고, 보조메뉴에서 ok를 선택하면 boards 테이블에 새로운 게시물이 저장된다
     */
    void create() {
        String title, content, writer;

        System.out.println();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목: "); title = sc.next();
        System.out.print("내용: "); content = sc.next();
        System.out.print("작성자: "); writer = sc.next();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        if (choice.equals(OK)) {
            this.boards.add(new BoardDTO(++this.boardSize, title, content, writer));
        } else if (!choice.equals(CANCEL)) {
            System.out.println("잘못된 입력입니다. 게시물 생성을 취소합니다.\n");
        }
    }

    /**
     * 메인 메뉴에서 read를 선택했을 때, 게시물의 번호를 키보드로 입력받고,
     * boards 테이블에 있는 해당 게시물을 가져와서 출력한다
     */
    void read() {
        String bnoInput;
        int bno;

        System.out.println();
        System.out.println("[게시물 읽기]");
        System.out.print("bno: "); bnoInput = sc.next();
        System.out.println("#############");

        try {
            bno = Integer.parseInt(bnoInput);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 게시물 조회를 종료합니다.\n");
            System.out.println("에러메세지 : " + e.getMessage());
            return;
        }

        if (bno <= this.boardSize) {
            System.out.println("번호: " + bno);
            System.out.println("제목: " + this.boards.get(this.boardSize-bno).getBtitle());
            System.out.println("내용: " + this.boards.get(this.boardSize-bno).getBcontent());
            System.out.println("작성자: " + this.boards.get(this.boardSize-bno).getBwriter());
            System.out.println("날짜: " + this.boards.get(this.boardSize-bno).getBdate());
        } else {
            System.out.println("잘못된 입력입니다. 게시물 조회를 종료합니다.\n");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        switch (choice) {
            case UPDATE -> this.update(this.boardSize-bno);
            case DELETE -> this.delete(this.boardSize-bno);
            case LIST -> { break; }
            default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
        }
    }

    /**
     * 메인 메뉴에서 read를 선택하고, 보조 메뉴에서 ok를 선택했을 때,
     * boards 테이블의 전체 게시물 정보를 삭제한다
     */
    void clear() {
        System.out.println();
        System.out.println("[게시물 전체 삭제]");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        if (choice.equals(OK)) {
            this.boards.clear();
            this.boardSize = 0;
        } else if (!choice.equals(CANCEL)) {
            System.out.println("잘못된 입력입니다. 게시물 전체 삭제를 취소합니다.\n");
        }
    }

    /**
     * 보조메뉴에서 update를 선택했을 때 제목, 내용, 작성자의 수정 내용을 입력할 수 있도록 한다
     * @param bno 게시물 번호
     */
    void update(int bno) {
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

        if (choice.equals(OK)) {
            this.boards.get(bno).setBtitle(title);
            this.boards.get(bno).setBcontent(content);
            this.boards.get(bno).setBwriter(writer);
            this.boards.get(bno).setBdate(new Date());
        } else if (!choice.equals(CANCEL)) {
            System.out.println("잘못된 입력입니다. 게시물 생성을 취소합니다.\n");
        }
    }


    /**
     * 보조메뉴에서 delete를 선택했을 때 boards 테이블에서 해당 게시물을 삭제한다
     * @param bno 게시물 번호
     */
    void delete(int bno) {
        this.boards.remove(bno);
        this.boardSize--;

        int index = this.boardSize;
        for (BoardDTO board : this.boards) {
            board.setBno(index--);
        }
    }
}
