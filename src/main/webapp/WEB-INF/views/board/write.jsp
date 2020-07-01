<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../include/header.jsp"/>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="../include/menu-bar.jsp"/> --%>
			<div class="container" style="margin-top: 70px;">
				<div class="wrap_title">
					<h2>게시판<small>작성페이지</small></h2>
				</div>
				<form id="writeForm" method="post" action="<c:url value='/board/write' />">
					<div class="content_title form-group">
						<label for="title">글제목</label>
						<input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" value="">
					</div>
					<div class="content_content form-group">
						<label for="content">내용</label>
							<textarea class="form-control" id="content" name="content" rows="10" placeholder="내용을 입력해주세요" style="resize: none;">글내용</textarea>
					</div>
					<div class="form-group">
						<label for="writer">작성자</label>
						<input class="form-control" id="writer" name="writer" value="작성자를 입력해주세요.">
					</div>
					<div class="list_paging">
						
					</div>
					<button type="button" class="btn btn-primary"><i class="fa fa-list"></i> 목록</button>
				    <button type="reset" class="btn btn-warning"><i class="fa fa-reply"></i> 초기화</button>
				    <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 입력</button>
			    </form>
			</div>
		<jsp:include page="../include/footer.jsp"/>
	</div>
</body>
</html>