package or.itschool.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class BoardVO {
	 //사용할 DATABASE TABLE 컬럼과 1:1로 매핑되는 필드들을 캡슐화를 통해 프로퍼티화 시킨다.
    private int boardNo;        //글번호 board_no
    private String title;        //글제목
    private String content;        //글내용
    private String writer;        //작성자
    private Date regDate;        //작성일
    private int viewCnt;        //조회수

}
