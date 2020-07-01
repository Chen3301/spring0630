package or.itschool.mapper;

import java.util.List;

import or.itschool.model.BoardVO;

public interface BoardMapper {
	// 기능을 상세화하기 위한 인터페이스 설계
	// 게시판 기능을 상세한다.

	// 1. 게시글 등록 기능 메서드 - 게시글을 하나 가져와서 등록한다.
	void insert(BoardVO article) throws Exception;

	// 2. 하나의 게시글 조회 기능 메서드 - 하나를 조회하려면 프라이머리키(글번호)를 가져와 전체 정보를 반환한다.
	BoardVO getArticle(int boardNo) throws Exception;

	// 3. 게시물 수정 기능 메서드 - 게시물 전체 정보를 가져와서 수정한다.
	void update(BoardVO article) throws Exception;

	// 4. 게시물 삭제 기능 메서드 - 게시물 번호의 글을 삭제한다.
	void delete(int boardNo) throws Exception;

	// 5. 모든 게시물 조회 기능 메서드
	List<BoardVO> getAllArticles() throws Exception;
}
