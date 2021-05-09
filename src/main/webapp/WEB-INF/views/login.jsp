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
												<!-- 												<div class = "title"> -->
												<!-- 												<h2  class="text-info text-center">Quản Lí Thiết Bị Cho Mượn</h2> -->
												<!-- 												<h2 class="text-info text-center">Học Viện Cơ Sở</h2> -->
												<!-- 												</div> -->
												<img
													src="${pageContext.servletContext.contextPath}/resources//vendors/images/login-page-img.png"
													alt="">
											</div>
											<div class="col-lg-4 col-lg-5">
												<div class="login-box bg-while box-shadow border-radius-10">
													<div class="login-title">
														<!-- 											 Chỗ này là Logo -->
														<div class="contact-dire-info text-center">
															<a href="${pageContext.servletContext.contextPath}/login">
																<img
																src="${pageContext.servletContext.contextPath}/resources/vendors/images/logo-inside.png"
																alt="" class="light-logo">

															</a>
														</div>


														<!-- 											<h2 class="text-left text-danger">Đăng nhập</h2> -->
													</div>

													<form action="login" modelAttribute="account" method="post">
														<div class="select-role">
															<div class="btn-group btn-group-toggle"
																data-toggle="buttons">
																<label class="btn active"> <input type="radio"
																	path="mapq">
																	<div class="icon">
																		<img
																			src="${pageContext.servletContext.contextPath}/resources/vendors/images/briefcase.svg"
																			class="svg" alt="">
																	</div> <span>Tôi là</span> Quản lí
																</label> <label class="btn"> <input type="radio"
																	path="mapq">
																	<div class="icon">
																		<img
																			src="${pageContext.servletContext.contextPath}/resources/vendors/images/person.svg"
																			class="svg" alt="">
																	</div> <span>Tôi là</span> Nhân viên
																</label>
															</div>
														</div>
														<div class="input-group custom">
															<input type="text" class="form-control form-control-lg"
																placeholder="Username">
															<div class="input-group-append custom">
																<span class="input-group-text"> <i
																	class="icon-copy dw dw-user1"></i>
																</span>
															</div>
														</div>
														<div class="input-group custom">
															<input type="password"
																class="form-control form-control-lg"
																placeholder="Password">
															<div class="input-group-append custom">
																<span class="input-group-text"> <i
																	class="dw dw-padlock1"></i>
																</span>
															</div>
														</div>
														<div class="row pb-30">
															<div class="col-6">
																<div class="custom-control custom-checkbox text-dark">
																	<input type="checkbox" class="custom-control-input"
																		id="customCheck1"> <label
																		class="custom-control-label" for="customCheck1">Remember</label>
																</div>
															</div>
															<div class="col-6">
																<div class="forgot-password">
																	<a href="forgot-password.html">Forgot Password</a>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-sm-12">
																<div class="input-group mb-0">
																	<!-- use code for form submit -->
																	<input class="btn btn-info btn-lg btn-block"
																		type="submit" value="Đăng nhập" />

																</div>

															</div>
														</div>
													</form>
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
	</div>
	<!-- 		</div> -->



	<!-- js -->
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