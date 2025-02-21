package controller;

import dto.BoardDTO;
import exception.CustomException;
import enumer.ErrorCode;
import enumer.SelectType;
import service.BoardDAO;
import service.BoardService;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardController {
    Scanner sc = new Scanner(System.in);

    final BoardService service;
    public BoardController(BoardService service) {
        this.service = service;
    }

    /**
     * BoardExample 객체를 생성하고 list()메소드를 호출한다
     *
     * @return 게시판 메뉴 종료 여부
     */
    boolean mainMenu() {
        System.out.println("메인 메뉴: 1.Creat | 2.Read | 3.Clear | 4.Exit");
        System.out.print("메뉴 선택: ");
        int choice = sc.nextInt();

        if (choice == SelectType.CREAT.getNum()) {

            ////////////////////////////////////////
            ////////////////////////////////////////

            String title, content, writer;

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
                if (choice == SelectType.OK.getNum()) {
                    service.create(new BoardDTO(title, content, writer));
                } else if (choice != SelectType.CANCEL.getNum()) {
                    throw new CustomException(ErrorCode.INVALID_INPUT);
                }
            } catch (InputMismatchException e) {
                throw new CustomException(ErrorCode.INVALID_INPUT);
            }

            ////////////////////////////////////////
            ////////////////////////////////////////

        } else if (choice == SelectType.READ.getNum()) {
            service.read();
        } else if (choice == SelectType.CLEAR.getNum()) {
            service.clear();
        } else if (choice == SelectType.EXIT.getNum()) {
            System.out.println();
            System.out.println("** 게시판 종료 **");
            return false;
        } else {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        return true;
    }

    /**
     * 게시판의 목록을 출력하고 mainMenu() 메소드를 호출한다
     */
    public void list() {
        do {
            System.out.println();
            System.out.println("[게시물 목록]");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("%-8s%-14s%-16s%-50s\n", "no", "writer", "date", "title");
            System.out.println("----------------------------------------------------------------------------------");

            Collections.sort(BoardDAO.getINSTANCE().getBoards());
            BoardDAO.getINSTANCE().getBoards().forEach(
                    (x) -> {
                        System.out.println(x.toFormattedString());
                    }
            );

            System.out.println();
            System.out.println("----------------------------------------------------------------------------------");
        } while (this.mainMenu());
    }
}
