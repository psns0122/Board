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

    public void read(int bno) {
        service.read(bno);
    }

    public void clear() {
        service.clear();
    }

    public void update(int bno, BoardDTO item) {
        service.update(bno-1, item);
    }

    public void delete(int bno) {
        service.delete(bno-1);
    }
}
