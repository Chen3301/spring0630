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

	//1. �Խñ� ��� ��� �޼��� - �Խñ��� �ϳ� �����ͼ� ����Ѵ�.
    @Override
    public void insert(BoardVO article) throws Exception {
    	log.info("insert......"+ article);
    	mapper.insert(article);
    }

    //2. �ϳ��� �Խñ� ��ȸ ��� �޼��� - �ϳ��� ��ȸ�Ϸ��� �����̸Ӹ�Ű(�۹�ȣ)�� ������ ��ü ������ ��ȯ�Ѵ�.
    @Override
    public BoardVO getArticle(int boardNo) throws Exception {
    	log.info("getArticle......"+ boardNo);
        return mapper.getArticle(boardNo);
    }

    //3. �Խù� ���� ��� �޼��� - �Խù� ��ü ������ �����ͼ� �����Ѵ�.
    @Override
    public void update(BoardVO article) throws Exception {
    	log.info("update......"+ article);
    	mapper.update(article);
    }

    //4. �Խù� ���� ��� �޼��� - �Խù� ��ȣ�� ���� �����Ѵ�.
    @Override
    public void delete(int boardNo) throws Exception {
    	log.info("delete ........"+boardNo);
    	mapper.delete(boardNo);
    }

    //5. ��� �Խù� ��ȸ ��� �޼��� - ��� �Խù��� �����ͼ� ����Ʈ�� ��´�.
    @Override
    public List<BoardVO> getAllArticles() throws Exception {
    	log.info("getAllArticles()........");
        return mapper.getAllArticles();
    }
}
