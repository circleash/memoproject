<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 입력</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>        
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>     
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<div class="w-75 my-4">
				<h1 class="text-center">메모 입력</h1>
				
				<div class="d-flex my-3">
					<label class="mr-3">제목 : </label>
					<input type="text" class="form-control col-11" id="titleInput">
				</div>
				<textarea class="form-control mb-3" rows="5" id="contentInput"></textarea>
				<!-- MIME text/html image/jpeg -->
				<input type="file" accept="image/*" id="fileInput">
				<div class="d-flex justify-content-between my-3">
					<button type="button" class="btn btn-info">목록으로</button>
					<button type="button" class="btn btn-success" id="saveBtn">저장</button>
				</div>
			</div>
		</section>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	
	</div>
	
	<script>
	$(document).ready(function() {
		$("#saveBtn").on("click", function() {
			var title = $("#titleInput").val();
			var content = $("#contentInput").val().trim();
			
			if(title == null || title == "") {
				alert("제목을 입력하세요");
				return;
			}
			if(content == null || content == "") {
				alert("내용을 입력하세요");
				return;
			}
			
			var formData = new FormData();
			formData.append("subject", title);
			formData.append("content", content);
			formData.append("file", $("#fileInput")[0].files[0]);
			
			$.ajax({
				enctype:"multipart/form-data", // 파일 업로드 필수
				processData:false, //파일업로드 필수
				contentType:false, //파일업로드 필수
				type:"post",
				url:"/post/create",
				data:formData,
				success:function(data) {
					if(data.result == "success") {
						alert("삽입성공");
					} else {
						alert("삽입실패");
					}
					
				},
				error:function(e) {
					alert("error");
				}
				
				
			});
			
		});
	})
	
	</script>

</body>
</html>