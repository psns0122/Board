package service;

public interface BoardService {
    void create();
    void read();
    void clear();
    void update(int bno);
    void delete(int bno);
}
