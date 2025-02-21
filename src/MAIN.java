import controller.BoardController;
import dto.CustomException;

public class MAIN {
    public static void main(String[] args) {
        BoardController myBoard = new BoardController();
        try {
            myBoard.list();
        } catch (CustomException e) {
            System.out.println(e.getErrorCode());
        }
    }
}