package front;

import controller.BoardController;
import dto.BoardDTO;
import enumer.ErrorCode;
import enumer.SelectType;
import exception.CustomException;
import service.BoardDAO;
import service.BoardService;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MAIN {
    static Scanner sc = new Scanner(System.in);
    static BoardController controller = new BoardController(BoardDAO.getINSTANCE());

    // TODO 옵셔널 사용해서 예외처리 없애기
    public static void main(String[] args) {
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

            System.out.println("메인 메뉴: 1.Creat | 2.Read | 3.Clear | 4.Exit");
            System.out.print("메뉴 선택: ");
            int choice = sc.nextInt();

            if (choice == SelectType.CREAT.getNum()) {
                menuCreate();
            } else if (choice == SelectType.READ.getNum()) {
                menuRead();
            } else if (choice == SelectType.CLEAR.getNum()) {
                menuClear();
            } else if (choice == SelectType.EXIT.getNum()) {
                System.out.println();
                System.out.println("** 게시판 종료 **");
                break;
            } else {
                throw new CustomException(ErrorCode.INVALID_INPUT);
            }
        } while (true);
    }

    static void menuCreate() {
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

        if (choice == SelectType.OK.getNum()) {
            controller.create(new BoardDTO(title, content, writer));
        } else if (choice != SelectType.CANCEL.getNum()) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }
    static void menuRead() {
        String bnoInput;
        int bno;
        int choice;

        System.out.println();
        System.out.println("[게시물 읽기]");
        System.out.print("bno: "); bnoInput = sc.next();
        System.out.println("#############");

        try {
            bno = Integer.parseInt(bnoInput);
        } catch (NumberFormatException e) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        controller.read(bno);

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
        System.out.print("메뉴 선택: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        if (choice == SelectType.UPDATE.getNum()) {
            String title, content, writer;

            System.out.println();
            System.out.println("[수정 내용 입력]");
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

            if (choice == SelectType.OK.getNum()) {
                controller.update(bno, new BoardDTO(title, content, writer));
            } else if (choice != SelectType.CANCEL.getNum()) {
                throw new CustomException(ErrorCode.INVALID_INPUT);
            }
        }

        else if (choice == SelectType.DELETE.getNum()) {
            controller.delete(bno);
        } else if (choice != SelectType.LIST.getNum()) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }

    static void menuClear() {
        int choice;

        System.out.println();
        System.out.println("[게시물 전체 삭제]");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        if (choice == SelectType.OK.getNum()) {
            controller.clear();
        } else if (choice != SelectType.CANCEL.getNum()) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
    }

}