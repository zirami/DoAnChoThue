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
<!-- switchery css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/src/plugins/switchery/switchery.min.css">
<!-- bootstrap-tagsinput css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/src/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css">
<!-- bootstrap-touchspin css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/src/plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.css">
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
				<div class="pd-20 card-box mb-30">
					<div class="clearfix">
						<div class="pull-left">
							<h4 class="text-blue h4">Thêm thiết bị</h4>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
					<form>
						<div class="form-group row">

							<label class="col-sm-12 col-md-2 col-form-label">Mã thiết bị</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập mã thiết bị">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Tên
								thiết bị</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập tên thiết bị">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Loại</label>
							<div class="col-sm-12 col-md-10">
								<select class="custom-select2 form-control" multiple="multiple"
									style="width: 100%;">
									<optgroup label="Loại">
										<option value="mic">Micro</option>
										<option value="cap">Dây cáp</option>
									</optgroup>

								</select>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Số lượng</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Nhập số lượng thiết bị">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Tình trạng</label>
							<div class="col-sm-12 col-md-10">
								<select class="custom-select2 form-control" multiple="single"
									style="width: 100%;">
									<optgroup label="Tình trạng">
										<option value="tt_moi">Mới</option>
										<option value="tt_sd">Đã từng sử dụng</option>
										<option value="tt_cu">Cũ</option>
									</optgroup>
								</select>
							</div>
						</div>
								<div class="form-group row">
							<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label>
							<div class="col-sm-12 col-md-10">
								<input class="form-control" type="text"
									placeholder="Ghi chú">
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
	<!-- switchery js -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/src/plugins/switchery/switchery.min.js"></script>
	<!-- bootstrap-tagsinput js -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/src/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
	<!-- bootstrap-touchspin js -->
	<script
		src="${pageContext.servletContext.contextPath}/resources/src/plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/advanced-components.js"></script>
</body>
</html>