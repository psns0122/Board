package front;

import controller.BoardController;
import service.BoardDAO;

public class MAIN {

    // TODO 옵셔널 검색해보기
    public static void main(String[] args) {
        BoardController myBoard = new BoardController(BoardDAO.getINSTANCE());
        myBoard.list();
    }
}