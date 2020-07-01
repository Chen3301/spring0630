package or.itschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import or.itschool.model.BoardVO;
import or.itschool.service.BoardService;

@Controller // ��Ʈ�ѷ� �������ִ� ������̼�
@Log4j // log4-1.2.17.jar ���� : pom.xml
//@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	// ��Ʈ�ѷ��� ���񽺰� �������谡 �����Ǿ������� �������������ش�.
	@Autowired
//	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	///////////////////// ���� ��û [GET] ////////////////////////
	// �Խñ� ��� ������ ���� ��û(��� �Խù� ��ȸ) ó�� �޼��� -> �� �޼����� Ʈ���Ű� �����ΰ��� ��������Ѵ�.
	// '/list'��� ��û�̿��� �� �޼��尡 �۵�, GET��ĸ� �޵��� �޼���Ӽ��� �����ش�.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			/*
			 * ���� �����ϴ� ��� 1. "HttpServletReqeust req" ���� 2.
			 * "@RequestParam("aaa") String aaa, @RequestParam("bbb") String bbb" ���� 3. Ŀ�ǵ�
			 * ��ü �̿� : ������ Ŀ�ǵ� ��ü "Box b" ����, Model model ���� (���� �����͵��� ���� �� �ִ� �������� viewȭ�鿡
			 * �ѷ��ֱ� ���� ����Ѵ�.)
			 */
			Model model) throws Exception {

		log.info("/board/list : GET ��û �߻�!");
		System.out.println("�Խñ� ������ ���� ��û!");

		// 1. HttpServletReqeust req ���
//			System.out.println("aaa : " + req.getParameter("aaa"));
//			System.out.println("bbb : " + req.getParameter("bbb"));

		// 2. @RequestParam ���
//			System.out.println("aaa : " + aaa);
//			System.out.println("bbb : " + bbb);

		// 3. Ŀ�ǵ� ��ü ���
//			System.out.println("aaa : " + b.getAaa());
//			System.out.println("bbb : " + b.getBbb());

		// �𵨿� �����͸� ��� ���� - view������ ""�� ���� �̸��� EL�±׸� �̿��� ����Ѵ�.
//			model.addAttribute("aaaa", b.getAaa());
//			model.addAttribute("bbbb", b.getBbb());

		// �ܼ�â�� �Խñ� ��� ����
		List<BoardVO> articles = service.getAllArticles();
		System.out.println("=====================");
		for (BoardVO vo : articles)
			System.out.println(vo);
		System.out.println("=====================");

		// DB���� ������ �����͸� articles��� �̸��� �𵨿� ��´�.
		model.addAttribute("articles", service.getAllArticles());

		return "board/list";
	}

	// �Խñ� �ۼ�ȭ�� ������û
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {

		log.info("/board/write : GET ��û �߻�!");
		return "board/write";
	}

	// �� ������ ȭ�� ���� ��û
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		// �Խù��� ��ȣ�� requestparam���� �����´�

		log.info("/board/content : GET ��û �߻�!");

		// DB���� ������ ������ �Խù��� article�̶�� �̸��� �𵨿� ��´�.
		model.addAttribute("article", service.getArticle(boardNo));

		return "board/content";
	}

	// �Խñ� ���� ȭ�� ���� ��û
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo, Model model) throws Exception {

		log.info("/board/modify : GET ��û �߻�!");

		// requestParam���� �۹�ȣ�� �����µ� �۹�ȣ�� �Խñ� ��ü�� ��ȸ�ϰ� article�̸��� �𵨿� ��´�.
		model.addAttribute("article", service.getArticle(boardNo));

		return "board/modify";
	}

/////////////////// �Խ��� ��� ��û [POST] ///////////////////////
//�Խù� ��� ��� �޼��� ��û (���� �̸����� �Լ��� ������ִ�. �����ε�)
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/write : POST ��û !");
		log.info("������ �Խñ� Ȯ�� : " + article.toString());

		service.insert(article); // �Խñ� ��� ��û

//�Խñ� ����� �ϰ� alert���� ���ߴ�~ ��� �˷��ֱ� ���� �����̷�Ʈ�� �� �� �ӽ� ������(�� ������)�� ��� ���
		redirectAttr.addFlashAttribute("message", "regSuccess");
//list.jsp ���� �ڹٽ�ũ��Ʈ�� ���

		/*
		 * �Խñ� ����� ������ �ٽ� �Խñ� ����� ���;��Ѵ�. �̶� �׳� jsp������ �ҷ����� �ȵǰ� �Խñ� ����� �׻� DB�� ���ļ� ����� ����
		 * �����;��Ѵ�. -> �ٽ� �� �� ��� ���û�� �ؾ��� ���û�� ���� ���� redirect:/
		 */
		return "redirect:/board/list";
	}

//�Խù� ���� ��� �޼��� ��û
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/modify : POST ��û !");
		service.update(article); // �Խñ� ���� ��û

		redirectAttr.addFlashAttribute("message", "modSuccess"); // ���� ���� �� �ӽõ�����

		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}

//�Խù� ���� ��� �޼��� ��û
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("boardNo") int boardNo, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/delete : POST ��û !");
		service.delete(boardNo);

		redirectAttr.addFlashAttribute("message", "delSuccess"); // ���� ���� �� �ӽ� ������

		return "redirect:/board/list";
	}

}
