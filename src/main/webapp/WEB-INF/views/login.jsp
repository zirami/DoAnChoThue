<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>Nhóm 2 - Quản lí thiết bị cho mượn của học viện Cơ Sở</title>
<base href="${pageContext.servletContext.contextPath }/">
<!-- Site favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="/vendors/images/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="/vendors/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="/vendors/images/favicon-16x16.png">

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
	href="${pageContext.servletContext.contextPath}/resources//vendors/styles/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources//vendors/styles/style.css">

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

<%@include file="/common/loadcss.jsp"%>


</head>
<body class="login-page">

	<!-- 	<div class="pd-20 xs-pd-20-10"> -->
	<!-- 	<div class="col-xl-12 col-lg-12" > -->
	<div class="min-height-200px">
		<div class="da-card">
			<div class="da-card-photo">
				<img
					src="${pageContext.servletContext.contextPath}/resources/vendors/images/img2.jpg"
					alt="">
				<div class="da-overlay">
					<div class="da-social">
						<div class="clearfix">
							<div class="page-header	 col-md-12 col-sm-12 ">
								<h2 class="text-info text-center">Quản Lí Thiết Bị Cho Mượn Học Viện Cơ Sở</h2>
								<hr>
								<div
									class="login-wrap d-flex align-items-center flex-wrap justify-content-center">
									<div class="container">
										<div class="row align-items-center">

											<div class="col-md-4 col-lg-7">
												<img
													src="${pageContext.servletContext.contextPath}/resources//vendors/images/login-page-img.png"
													alt="">
											</div>
											<div class="col-lg-4 col-lg-5">
												<div class="login-box bg-while box-shadow border-radius-10">
													<div class="login-title "
														style="color: rgb(255, 255, 255); background-color: rgb(115, 65, 156);">
														<!-- 											 Chỗ này là Logo -->
														<div class="contact-dire-info text-center">
															<a href="${pageContext.servletContext.contextPath}/login">
																<img
																src="${pageContext.servletContext.contextPath}/resources/vendors/images/icon-nhom2.jpg"
																alt="" class="light-logo">

															</a>
														</div>


													</div>







													<hr>
													<form:form action="login" modelAttribute="account_test"
														method="post">

														<div class="text-dark" class="needs-validation" novalidate>
															<div class="col-sm-4 col-md-12">
																<div class="row">
																	<label class="col-sm-4 col-md-4">Username</label>
																	<div class="input-group custom">
																		<form:input path="username" type="text"
																			class="form-control form-control-lg"
																			placeholder="Username" required="required" />
																		<div class="input-group-append custom">
																			<span class="input-group-text"><i
																				class="icon-copy dw dw-user1"></i></span>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-sm-4 col-md-12">
																<div class="row">
																	<label class="col-sm-4 col-md-4">Password</label>
																	<div class="input-group custom">
																		<form:input path="password" type="password"
																			class="form-control form-control-lg"
																			placeholder="**********" required="required" />
																		<div class="input-group-append custom">
																			<span class="input-group-text"><i
																				class="dw dw-padlock1"></i></span>
																		</div>
																	</div>
																</div>

															</div>


															<div class="col-sm-6 col-md-12">
																<div class="forgot-password">
																	<a href="forgot-password">Forgot Password</a>
																</div>
															</div>

														</div>
														<!-- 							=================================== cột 1 ========================================= -->
														<div class="text-danger col-sm-6 col-md-12">${message}</div>
														<div class="modal-footer">
															<input class="btn btn-info btn-lg btn-block"
																type="submit" value="Đăng nhập">
														</div>
													</form:form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 	</div> -->
	<!-- 	</div> -->
	<!-- 		</div> -->



	<!-- js -->
	<%@include file="/common/footer.jsp"%>

	<c:if test="${failLogin}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_error();

			});
		</script>
	</c:if>
	<script type="text/javascript">
		//THÔNG BÁO LỖI
		function show_error(content="Thao tác") {
			Swal.fire({
				title: 'Đăng nhập',
				text: content+" thất bại!",
				icon: 'error',
			})
		}
	</script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/core.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/script.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/process.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/vendors/scripts/layout-settings.js"></script>
	<!-- 	<script -->
	<%-- 		src="${pageContext.servletContext.contextPath}/resources/scss/_utilities.scss"></script> --%>
</body>
</html>