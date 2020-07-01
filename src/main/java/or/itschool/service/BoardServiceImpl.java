package or.itschool.service;

import java.util.List;

import org.springframework.stereotype.Service;
import or.itschool.model.BoardVO;
import or.itschool.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;

	//1. 게시글 등록 기능 메서드 - 게시글을 하나 가져와서 등록한다.
    @Override
    public void insert(BoardVO article) throws Exception {
    	log.info("insert......"+ article);
    	mapper.insert(article);
    }

    //2. 하나의 게시글 조회 기능 메서드 - 하나를 조회하려면 프라이머리키(글번호)를 가져와 전체 정보를 반환한다.
    @Override
    public BoardVO getArticle(int boardNo) throws Exception {
    	log.info("getArticle......"+ boardNo);
        return mapper.getArticle(boardNo);
    }

    //3. 게시물 수정 기능 메서드 - 게시물 전체 정보를 가져와서 수정한다.
    @Override
    public void update(BoardVO article) throws Exception {
    	log.info("update......"+ article);
    	mapper.update(article);
    }

    //4. 게시물 삭제 기능 메서드 - 게시물 번호의 글을 삭제한다.
    @Override
    public void delete(int boardNo) throws Exception {
    	log.info("delete ........"+boardNo);
    	mapper.delete(boardNo);
    }

    //5. 모든 게시물 조회 기능 메서드 - 모든 게시물을 가져와서 리스트에 담는다.
    @Override
    public List<BoardVO> getAllArticles() throws Exception {
    	log.info("getAllArticles()........");
        return mapper.getAllArticles();
    }
}
