package or.itschool.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import or.itschool.mapper.BoardMapper;
import or.itschool.model.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardDAOTest {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardDAO;

	// 게시물 등록 테스트
	@Test
	public void insertTest() throws Exception {

		for (int i = 1; i <= 3000; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle(i + "번째 테스트 게시물!!");
			vo.setContent(i + "번째 게시물 내용입니다~~");
			vo.setWriter("user" + i);
			boardDAO.insert(vo);
			log.info(boardDAO);
		}
	}

	// 단일 게시물 조회 테스트
	// 100번째 게시물 조회
	@Test
	public void selectOneTest() throws Exception {
		log.info(boardDAO.getArticle(100).toString() + "\n");
	}

	// 게시물 수정 테스트
	@Test
	public void updateTest() throws Exception {
		BoardVO article = new BoardVO();

		// 1번 게시물 수정
		article.setBoardNo(1);
		article.setTitle("글 제목 수정~!~!");
		article.setContent("글 내용 수정~!~!");
		boardDAO.update(article);

		log.info(boardDAO.getArticle(1).toString() + "\n");
	}

	// 게시물 삭제 테스트
	@Test
	public void deleteTest() throws Exception {
		boardDAO.delete(10);
	}

	// 모든 게시물 조회 테스트
	@Test
	public void selectAllTest() throws Exception {
		List<BoardVO> articles = boardDAO.getAllArticles();
		for (BoardVO article : articles) {
			log.info(article.toString());
		}
	}

}
