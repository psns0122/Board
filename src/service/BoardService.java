package service;

import dto.BoardDTO;

public interface BoardService {
    void create(BoardDTO item);
    void read();
    void clear();
    void update(int bno);
    void delete(int bno);
}
