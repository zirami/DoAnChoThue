<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhóm 2 - Danh sách Phiếu Nhập</title>
<base href="${pageContext.servletContext.contextPath}/">
<%@include file="/common/loadcss.jsp"%>
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<div class="mobile-menu-overlay"></div>
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Danh sách thiết bị</h4>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="index.html">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">DataTable</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<div class="col-md-6 col-sm-12 text-right">
							<a href="phieu-nhap" class="btn btn-primary" target="_blank"
								type="button">NHẬP THIẾT BỊ</a>
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM THIẾT BỊ MỚI </a>
						</div>
					</div>
				</div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<c:set var="choXacNhan" value="choXacNhan" />
						<c:set var="daXacNhan" value="daXacNhan" />
						<c:set var="huy" value="huy" />
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã Phiếu Nhập</th>
									<th>Ngày Nhập</th>
									<th>Nhà Cung Cấp</th>
									<th>Nhân Viên Nhập</th>
									<th>Tình Trạng</th>
									<th>Ghi chú</th>
									<th class="datatable-nosort">Hành động</th>
								</tr>
							</thead>
							<c:forEach var="pn" items="${listPhieuNhap}">
								<tr>
									<td class="table-plus">${pn.mapn}</td>
									<td>${pn.thoigiannhap}</td>
									<td>${pn.ncc.ten}</td>
									<td>${pn.nhanvien.ho}${pn.nhanvien.ten}</td>
									<!-- Tình trạng -->
									<c:choose>
										<c:when test="${pn.trangthai.equals(choXacNhan)}">
											<td>Chờ Xác Nhận</td>
										</c:when>
										<c:when test="${pn.trangthai.equals(daXacNhan)}">
											<td>Đã Xác Nhận</td>
										</c:when>
										<c:otherwise>
											<td>HUỶ</td>
										</c:otherwise>
									</c:choose>
									<!-- Ghi chú -->
									<c:choose>
										<c:when test="${pn.ghichu.isEmpty() || pn.ghichu.isBlank()}">
											<td>-</td>
										</c:when>
										<c:otherwise>
											<td>${pn.ghichu}</td>
										</c:otherwise>
									</c:choose>
									<!-- Hành động -->
									<td>
										<div class="row clearfix">
											<div class="col-3">
												<button class="btn btn-primary" type="submit"
													data-toggle="tooltip" data-placement="top" title="Sửa">
													<i class="material-icons">edit</i>
												</button>
											</div>
											<div class="col-6">
												<!-- XOÁ -->
												<c:choose>
													<c:when test="${pn.trangthai.equals(choXacNhan)}">
														<button class="btn btn-danger delete_btn"
															data-toggle="tooltip" data-placement="top" title="Xoá"
															type="button">
															<i class="material-icons">delete</i>
														</button>
													</c:when>
													<c:otherwise>
														<td>-</td>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<!-- Simple Datatable End -->
			</div>
		</div>
	</div>
</body>
</html>