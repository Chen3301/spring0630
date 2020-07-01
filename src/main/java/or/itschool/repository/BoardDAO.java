package or.itschool.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.itschool.mapper.BoardMapper;
import or.itschool.model.BoardVO;

//@Repository
public class BoardDAO implements BoardMapper {

	private final SqlSessionFactory sqlSession;
	private static final String NAMESPACE = "BoardMapper";

	@Autowired
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = (SqlSessionFactory) sqlSession;
	}

	// 1. 게시물 등록
	@Override
	public void insert(BoardVO article) throws Exception {
		((SqlSession) sqlSession).insert(NAMESPACE + ".insert", article);
	}

	// 2. 하나의 게시글 조회 기능 메서드 - 하나를 조회하려면 프라이머리키(글번호)를 가져와 전체 정보를 반환한다.
	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		System.out.println("게시글 번호 : " + boardNo);
		return ((SqlSession) sqlSession).selectOne(NAMESPACE + ".getArticle", boardNo);
	}

	// 3. 게시물 수정 기능 메서드 - 게시물 전체 정보를 가져와서 수정한다.
	@Override
	public void update(BoardVO article) throws Exception {
		((SqlSession) sqlSession).update(NAMESPACE + ".update", article);
	}

	// 4. 게시물 삭제 기능 메서드 - 게시물 번호의 글을 삭제한다.
	@Override
	public void delete(int boardNo) throws Exception {
		((SqlSession) sqlSession).delete(NAMESPACE + ".delete", boardNo);
	}

	// 5. 모든 게시물 조회 기능 메서드 - 모든 게시물을 가져와서 리스트에 담는다.
	@Override
	public List<BoardVO> getAllArticles() throws Exception {

		return ((SqlSession) sqlSession).selectList(NAMESPACE + ".getAllArticles");
	}

}