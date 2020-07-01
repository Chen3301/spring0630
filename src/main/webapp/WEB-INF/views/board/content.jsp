<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="../include/header.jsp" />
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="../include/menu-bar.jsp"/> --%>
		<div class="container" style="margin-top: 70px;">
			<div class="wrap_title">
				<h2>
					게시판<small>상세페이지</small>
				</h2>
			</div>
			<div class="content_title">
				<strong>${article.title }</strong> <span> <!-- fmt라이브러리 : 날짜 형식 제공 - 위쪽에 태그라이브러리 선언해야한다. -->
					<fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
						value="${article.regDate }" />
				</span>
			</div>
			<div class="content_content" style="height: 350px;">
				${article.content }</div>
			<div>
				<span class="username">${article.writer }</span>
			</div>


			<form role="form" method="post">
				<input type="hidden" name="boardNo" value="${article.boardNo }">
			</form>

			<button class="btn btn-primary listBtn">
				<i class="fa fa-list"></i> 목록
			</button>
			<button class="btn btn-warning modBtn">
				<i class="fa fa-edit"></i> 수정
			</button>
			<button class="btn btn-danger delBtn">
				<i class="fa fa-trash"></i> 삭제
			</button>

		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			const formObj = $("form[role='form']");

			$(".listBtn").on("click", function() {
				formObj.attr("method", "get");
				formObj.attr("action", "list");
				formObj.submit();
			});

			$(".modBtn").on("click", function() {
				formObj.attr("method", "get");
				formObj.attr("action", "modify");
				formObj.submit();
			});

			$(".delBtn").on("click", function() {
				formObj.attr("method", "post");
				formObj.attr("action", "delete");
				formObj.submit();
			});
		});
	</script>


	<script type="text/javascript">
		const result = "${message}"; //상수처리
		if (result === "modSuccess") {//동등비교 ===사용

			alert("게시글 수정이 완료되었습니다.");
		}
	</script>

</body>
</html>