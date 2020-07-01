<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../include/header.jsp"/>
<body>
	<div class="container-fluid">
	<%-- 	<jsp:include page="../include/menu-bar.jsp"/> --%>
		<div class="container" style="margin-top: 70px;">
			<div class="wrap_title">
				<h2>게시판<small>수정페이지</small></h2>
			</div>
			<form role="form" id="modifyForm" method="post" action="<c:url value='/board/modify'/>">
				<input type="hidden" id="boardNo" name="boardNo" value="${article.boardNo }">
				
				<div class="content_title form-group">
					<label for="title">글제목</label>
					<input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" value="${article.title}">
				</div>
				<div class="content_content form-group">
					<label for="content">내용</label>
						<textarea class="form-control" id="content" name="content" rows="10" placeholder="내용을 입력해주세요" style="resize: none;">${article.content }</textarea>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input class="form-control" id="writer" name="writer" value="${article.writer}" readonly>
				</div>
				<div class="list_paging">
					
				</div>
				<button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
			    <button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-trash"></i> 취소</button>
			    <button type="submit" class="btn btn-success modBtn"><i class="fa fa-save"></i> 수정 저장</button>
			</form>
		</div>
		<jsp:include page="../include/footer.jsp"/>
	</div>

<script>
    $(document).ready(function () {

        const formObj = $("form[role='form']");        

        $(".modBtn").on("click", function () {
            formObj.submit();
        });

        $(".cancelBtn").on("click", function () {
            history.back();
        });

        $(".listBtn").on("click", function () {
            self.location = "list";
        });

    });
</script>


</body>
</html>