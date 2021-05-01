<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>

<!-- Site favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="${pageContext.servletContext.contextPath}/resources/vendors/images/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="${pageContext.servletContext.contextPath}/resources/vendors/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.servletContext.contextPath}/resources/vendors/images/favicon-16x16.png">

<!-- Mobile Specific Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/vendors/styles/core.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/vendors/styles/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/vendors/styles/style.css">


<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-119386393-1"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());

	gtag('config', 'UA-119386393-1');
</script>
</head>
<body>

	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>

	<div class="mobile-menu-overlay"></div>

	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<!-- Default Basic Forms Start -->
				<div class="pd-20 card-box mb-30">
					<div class="clearfix">
						<div class="pull-left">
							<h4 class="text-blue h4">Thêm người mượn</h4>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin!</p>
						</div>
					</div>
					<form>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Mã người
								mượn</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập mã người mượn">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Họ và
								chữ lót</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập họ và chữ lót">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Tên</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text" placeholder="Nhập tên">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Giới
								tính</label>
							<div class="col-sm-12 col-md-10">
								<input type= "radio" name = "customRadio">  
								<label>Nam</label>
								<input type= "radio" name = "customRadio">  
								<label >Nữ</label>
							</div>
						</div>


						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Ngày
								sinh</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control date-picker"
									placeholder="Chọn ngày sinh" type="text">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Địa chỉ</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập địa chỉ">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">CMND</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập chứng minh nhân dân">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập ghi chú">
							</div>
						</div>
						<div class="form-group row">

							<div class="btn-list offset-md-9">
								<button type="button" class="btn btn-danger">Hủy</button>
								<button type="button" class="btn btn-success">Xác nhận</button>
							</div>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- js -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/core.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/script.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/process.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/layout-settings.js"></script>
</body>
</html>