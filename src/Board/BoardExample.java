package Board;

import java.util.Collections;
import java.util.Scanner;

public class BoardExample {
    final String CREAT = "1";
    final String READ = "2";
    final String CLEAR = "3";
    final String EXIT = "4";
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

        switch (choice) {
            case CREAT -> BoardManager.getINSTANCE().create();
            case READ -> BoardManager.getINSTANCE().read();
            case CLEAR -> BoardManager.getINSTANCE().clear();
            case EXIT -> {
                System.out.println();
                System.out.println("** 게시판 종료 **");
                return false;
            }
            default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
        }

        return true;
    }

    /**
     * 게시판의 목록을 출력하고 mainMenu() 메소드를 호출한다
     */
    void list() {
        do {
            System.out.println();
            System.out.println("[게시물 목록]");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("%-8s%-14s%-16s%-50s\n", "no", "writer", "date", "title");
            System.out.println("----------------------------------------------------------------------------------");

            Collections.sort(BoardManager.getINSTANCE().getBoards());
            BoardManager.getINSTANCE().getBoards().forEach(
                    (x) -> {
                        System.out.println(x.toFormattedString());
                    }
            );

            System.out.println();
            System.out.println("----------------------------------------------------------------------------------");
        } while (this.mainMenu());
    }
}
