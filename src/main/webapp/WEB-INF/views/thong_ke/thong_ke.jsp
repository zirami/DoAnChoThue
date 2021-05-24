<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>
<%@include file="/common/loadcss.jsp"%>
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>
	<div class="mobile-menu-overlay"></div>
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Thống Kê</h4>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="index.html">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">Thống kê</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<div class="col-md-6 col-sm-12 text-right">
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM THIẾT BỊ </a>
						</div>
					</div>
				</div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã thiết bị</th>
									<th>Tên Thiết Bị</th>
									<th>Loại</th>
									<th>Số Lượng</th>
									<th>Tình trạng</th>
									<th>Ghi chú</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="thietbi" items="${listThietbi}" >
									<tr>
										<td class="table-plus">${thietbi.matb}</td>
										<td>${thietbi.ten}</td>
										<td>${thietbi.loai.ten}</td>
										<td>${thietbi.soluong}</td>
										<td>${thietbi.tinhtrang}</td>
										<c:choose>
											<c:when
												test="${thietbi.ghichu.isEmpty() || thietbi.ghichu.isBlank()}">
												<td>-</td>
											</c:when>
											<c:otherwise>
												<td>${thietbi.ghichu}</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- Simple Datatable End -->
			</div>
		</div>
	</div>
	
	
	<!-- js -->
	<%@include file="/common/footer.jsp"%>
	
	<script type="text/javascript">
		//LOAD TABLE 
		$('#myTable').DataTable();
	</script>
</body>
</html>