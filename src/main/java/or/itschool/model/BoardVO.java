package or.itschool.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class BoardVO {
	 //����� DATABASE TABLE �÷��� 1:1�� ���εǴ� �ʵ���� ĸ��ȭ�� ���� ������Ƽȭ ��Ų��.
    private int boardNo;        //�۹�ȣ board_no
    private String title;        //������
    private String content;        //�۳���
    private String writer;        //�ۼ���
    private Date regDate;        //�ۼ���
    private int viewCnt;        //��ȸ��

}
