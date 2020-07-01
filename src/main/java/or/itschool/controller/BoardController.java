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

@Controller // 컨트롤러 빈등록해주는 어노테이션
@Log4j // log4-1.2.17.jar 변경 : pom.xml
//@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	// 컨트롤러와 서비스가 의존관계가 설정되어있으니 의존성주입해준다.
	@Autowired
//	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	///////////////////// 열람 요청 [GET] ////////////////////////
	// 게시글 목록 페이지 열람 요청(모든 게시물 조회) 처리 메서드 -> 이 메서드의 트리거가 무엇인가를 적어줘야한다.
	// '/list'라는 요청이오면 이 메서드가 작동, GET방식만 받도록 메서드속성도 적어준다.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			/*
			 * 값을 전달하는 방법 1. "HttpServletReqeust req" 선언 2.
			 * "@RequestParam("aaa") String aaa, @RequestParam("bbb") String bbb" 선언 3. 커맨드
			 * 객체 이용 : 가져올 커맨드 객체 "Box b" 선언, Model model 선언 (모델은 데이터들을 담을 수 있는 공간으로 view화면에
			 * 뿌려주기 위해 사용한다.)
			 */
			Model model) throws Exception {

		log.info("/board/list : GET 요청 발생!");
		System.out.println("게시글 페이지 열람 요청!");

		// 1. HttpServletReqeust req 방법
//			System.out.println("aaa : " + req.getParameter("aaa"));
//			System.out.println("bbb : " + req.getParameter("bbb"));

		// 2. @RequestParam 방법
//			System.out.println("aaa : " + aaa);
//			System.out.println("bbb : " + bbb);

		// 3. 커맨드 객체 방법
//			System.out.println("aaa : " + b.getAaa());
//			System.out.println("bbb : " + b.getBbb());

		// 모델에 데이터를 담는 과정 - view에서는 ""에 적은 이름을 EL태그를 이용해 사용한다.
//			model.addAttribute("aaaa", b.getAaa());
//			model.addAttribute("bbbb", b.getBbb());

		// 콘솔창에 게시글 목록 띄우기
		List<BoardVO> articles = service.getAllArticles();
		System.out.println("=====================");
		for (BoardVO vo : articles)
			System.out.println(vo);
		System.out.println("=====================");

		// DB에서 가져온 데이터를 articles라는 이름의 모델에 담는다.
		model.addAttribute("articles", service.getAllArticles());

		return "board/list";
	}

	// 게시글 작성화면 열람요청
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {

		log.info("/board/write : GET 요청 발생!");
		return "board/write";
	}

	// 상세 페이지 화면 열람 요청
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		// 게시물의 번호를 requestparam으로 가져온다

		log.info("/board/content : GET 요청 발생!");

		// DB에서 연결해 가져온 게시물을 article이라는 이름의 모델에 담는다.
		model.addAttribute("article", service.getArticle(boardNo));

		return "board/content";
	}

	// 게시글 수정 화면 열람 요청
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo, Model model) throws Exception {

		log.info("/board/modify : GET 요청 발생!");

		// requestParam으로 글번호를 가져온뒤 글번호로 게시글 전체를 조회하고 article이름의 모델에 담는다.
		model.addAttribute("article", service.getArticle(boardNo));

		return "board/modify";
	}

/////////////////// 게시판 기능 요청 [POST] ///////////////////////
//게시물 등록 기능 메서드 요청 (같은 이름으로 함수를 만들수있다. 오버로딩)
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/write : POST 요청 !");
		log.info("가져온 게시글 확인 : " + article.toString());

		service.insert(article); // 게시글 등록 요청

//게시글 등록을 하고 alert으로 잘했다~ 라고 알려주기 위해 리다이렉트를 할 때 임시 데이터(그 순간만)를 쏘는 방법
		redirectAttr.addFlashAttribute("message", "regSuccess");
//list.jsp 에서 자바스크립트로 사용

		/*
		 * 게시글 등록이 끝나면 다시 게시글 목록이 나와야한다. 이때 그냥 jsp파일을 불러오면 안되고 게시글 목록은 항상 DB를 거쳐서 목록을 새로
		 * 가져와야한다. -> 다시 한 번 목록 재요청을 해야함 재요청이 있을 때는 redirect:/
		 */
		return "redirect:/board/list";
	}

//게시물 수정 기능 메서드 요청
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/modify : POST 요청 !");
		service.update(article); // 게시글 수정 요청

		redirectAttr.addFlashAttribute("message", "modSuccess"); // 수정 성공 시 임시데이터

		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}

//게시물 삭제 기능 메서드 요청
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("boardNo") int boardNo, RedirectAttributes redirectAttr) throws Exception {

		log.info("/board/delete : POST 요청 !");
		service.delete(boardNo);

		redirectAttr.addFlashAttribute("message", "delSuccess"); // 삭제 성공 시 임시 데이터

		return "redirect:/board/list";
	}

}
