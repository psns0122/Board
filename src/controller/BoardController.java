package controller;

import dto.BoardDTO;
import service.BoardService;

public class BoardController {


    final BoardService service;
    public BoardController(BoardService service) {
        this.service = service;
    }

    public void create(BoardDTO item) {
        service.create(item);
    }

}
