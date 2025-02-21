package controller;

import exception.CustomException;
import enumer.ErrorCode;
import enumer.SelectType;
import service.BoardDAO;
import service.BoardService;

import java.util.Collections;
import java.util.Scanner;

public class BoardController {
    Scanner sc = new Scanner(System.in);

    /**
     * BoardExample 객체를 생성하고 list()메소드를 호출한다
     *
     * @return 게시판 메뉴 종료 여부
     */
    boolean mainMenu() {
        System.out.println("메인 메뉴: 1.Creat | 2.Read | 3.Clear | 4.Exit");
        System.out.print("메뉴 선택: ");
        String choice = sc.next();

        SelectType select = switch (choice) {
            case "1" -> SelectType.CREAT;
            case "2" -> SelectType.READ;
            case "3" -> SelectType.CLEAR;
            case "4" -> SelectType.EXIT;
            default -> SelectType.ERROR;
        };

        BoardService service = BoardDAO.getINSTANCE();

        switch (select) {
            case CREAT -> service.create();
            case READ -> service.read();
            case CLEAR -> service.clear();
            case EXIT -> {
                System.out.println();
                System.out.println("** 게시판 종료 **");
                return false;
            }
            default -> throw new CustomException(ErrorCode.INVALID_INPUT);
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
