package service;

import dto.BoardDTO;

public interface BoardService {
    void create(BoardDTO item);
    void read(int bno);
    void clear();
    void update(int bno, BoardDTO item);
    void delete(int bno);
}
