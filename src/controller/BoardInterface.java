package controller;

import dto.BoardDTO;

public interface BoardInterface {
    void create(BoardDTO item);
    void read(int bno);
    void clear();
    void update(int bno, BoardDTO item);
    void delete(int bno);
}
