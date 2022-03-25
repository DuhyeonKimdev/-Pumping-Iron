<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	
	// 이전 페이지에서 사용자가 클릭한 옵션을 select에 셋팅하기
	$("#i_type").val("${param.i_type}").prop("selected",true);
	
	// textarea 개행처리
	$(".btn-primary").click(function() {
	var str = $('textarea').val();
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	$('textarea').val(str);
	 });
	
	var content = $("#content").val();
	content = content.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
	document.getElementById("textarea").value = content
	
});
</script>
</head>
<body>
	<form action="customerservice.inquiry.answerDo" method="post" id="fileForm" name="inquiryForm" enctype="multipart/form-data" onsubmit="return inquiryCheck()">
			<div class="container mt-5">
			<div class="col-sm-12 col-lg-12">
				<div class="card flex-center" style="border: none;">
					<div class="card-body" style="border: 1px solid gray; width: 800px;">
						<div class="mb-3 mt-3">
							<div class="mt-4">
								<div style="display: flex; justify-content: center;">
									<h4>답변하기</h4>
								</div>
							</div>
						</div>
						<div class="mb-3">
						<div style="display: flex; align-items: center;">
							말머리
						</div>
						<div style="width: 770px;"class="mt-1">
						${idetail.i_type }
						</div>
						</div>
						<div class="mb-3">
						<div style="display: flex; align-items: center;">
							제목
						</div>
						<div style="width: 770px;"class="mt-1">
						${idetail.i_title }
						<input style="width: 770px;"class="mt-1"  name="ia_title" type="hidden" value="${idetail.i_title }" id="title">
						</div>
						</div>
						<div class="mb-3">
						<div style="display: flex; align-items: center;">
							내용
						</div>
						<textarea style="width: 770px; height: 200px;" class="mt-1" name="ia_content" placeholder="내용을 입력해주세요." id="content"></textarea>
						</div>
						<div class="mb-3">
							<div style="display: flex; align-items: center;" class="mb-1">
								<label>첨부파일</label>
								<span class="ms-2" style="color: red;">'png', 'gif', 'jpg', 'jpeg' 파일만 업로드가 가능합니다.</span>
							</div>
							<div>
							<input type="file" name="ia_photo1"  accept="image/*" id="photo1">
							<input type="file" name="ia_photo2"  accept="image/*" id="photo2">				
							</div>
							<div>
							<input type="file" name="ia_photo3"  accept="image/*" id="photo3">
							<input type="file" name="ia_photo4"  accept="image/*" id="photo4">			
							</div>
						</div>
						<input type="hidden" name="ia_to" value="${idetail.i_from }">
						<input type="hidden" name="ia_from" value="admin">
						<input type="hidden" name="i_no" value="${idetail.i_no }">
						<input type="hidden" name="ia_type" value="${idetail.i_type }">
						<div style="display: flex; align-items: center; justify-content: center;">
								<button type="button" class="btn btn-secondary" onclick="history.back(-1)" style="width: 150px;">취소</button>
								<button class="btn btn-primary" style="width: 150px;">작성</button>
						</div>
					</div>
				</div>
			 </div>
			</div>
		</form>
</body>
</html>