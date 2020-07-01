<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="../include/header.jsp"/>
<body>
		

	<div class="container-fluid">
		<%-- <jsp:include page="../include/menu-bar.jsp"/> --%>
	
		<div class="container" style="margin-top: 70px;">
			<div class="wrap_title">
				<h2>게시판<small>목록페이지</small></h2>
				<%-- 게시물 보이는 개수 바꾸기 --%>
				<span id="count-per-page" style="float: right;">
					<i class="fa fa-list">목록보기</i>
					<input class="btn" type="button" value="10">
					<input class="btn" type="button" value="20">
					<input class="btn" type="button" value="30">			
				</span>
			</div>
			
			<%-- 게시물 시작 부분 --%>
			<div class="list_content">
				<table class="table table-hover">
			      <thead>
			        <tr>
			          <th width="10%">번 호 </th>
			          <th width="50%">제 목</th>
			          <th width="30%">글쓴이</th>
			          <th width="10%">조회</th>
			        </tr>
			      </thead>
			      
			      <%-- 게시물이 들어갈 공간:컨트롤러에서 articles라는 이름의 모델에 데이터를 담았기 때문에 el태그로 사용할 수 있다. --%>
			      <c:if test="${articles.size() <=0 }">
			      	<!-- 모델에 담은 articles 크기가 0보다 작으면 나타나는 곳 -->
			      	<td colspan="4" align="center"><strong>등록된 게시물이 없습니다 !</strong></td>
			      </c:if>
			      <c:if test="${articles.size() > 0 }">
			      	<!-- 게시물 수가 존재할 때 보여지는 곳 -->
			      	<!-- articles(list타입)를 꺼내서 article(BoardVO타입)에 하나씩 담는다. -->
			      	<c:forEach var="article" items="${articles }">
			      		<tr>
			      			<td>${article.boardNo }</td>
			      			<!-- 글번호로 게시글을 조회해야하니까 글번호를 파라미터로 달아준다. -->
							<td><a href="<c:url value='/board/content?boardNo=${article.boardNo }' />"> ${article.title }</a></td>
							<td>${article.writer }</td>
							<td>${article.viewCnt }</td>
			      		</tr>
			      	</c:forEach>
			      </c:if>
			      
			    </table>
			</div>
			<div class="list_paging">
				
			</div>
			<button type="button" class="btn btn-success btn-flat" id="writeBtn">
			    <i class="fa fa-pencil"></i> 글쓰기
			</button>
		</div>
		<jsp:include page="../include/footer.jsp"/>
	</div>
	
	
	<script type="text/javascript">
		const result = "${message}"; //상수처리
		if(result === "regSuccess") {//동등비교 ===사용
			alert("게시글 등록이 완료되었습니다.");
		} else if (result === "delSuccess") {
			alert("게시글 삭제가 완료되었습니다.");
		}
		
		$(document).ready(function() {
			
			//글쓰기 버튼 클릭 이벤트
			$("#writeBtn").on("click", function(){
				self.location = "/board/write";
			});
		
		});
		
		
	</script>
	
	
</body>
</html>