<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>
<style type="text/css">
h4 {
	color: red;
}
</style>
<%@include file="/common/loadcss.jsp"%>
</head>
<body id="body">
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>
	<div class="mobile-menu-overlay"></div>
	<!-- 	Popup -->
	<!-- 	================================ Hiển thị danh sách Phiếu Mượn ================================ -->
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Danh sách phiếu mượn</h4>
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
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-insert-modal-lg"
								type="button">Thêm phiếu mượn </a>
						</div>
					</div>
				</div>
				<!-- Hiển thị danh sách người mượn -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã phiếu mượn</th>
									<th>Người mượn</th>
									<th>Mã nhân viên</th>
									<th>Thời gian mượn</th>
									<th>Thời gian trả</th>
									<th>Ghi chú</th>
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="phieumuon" items="${listPhieuMuon}"
									varStatus="row">
									<tr>
										<td class="table-plus">${phieumuon.mapm}</td>
										<td>${phieumuon.nm.ten}</td>
										<td>${phieumuon.manv}</td>
										<td>${phieumuon.thoigianmuon}</td>
										<td>${phieumuon.thoigiantra}</td>
										<td>${phieumuon.ghichu }</td>
										<td>
											<div class="row clearfix btn-list">
												<form action="phieumuon/edit/${phieumuon.mapm}">
													<button class="btn btn-primary" type="submit"
														data-toggle="tooltip" data-placement="top" title="Sửa">
														<i class="material-icons">edit</i>
													</button>
												</form>
												<form action="phieumuon/delete" method="post">
													<!-- 														Dùng để hiển thị tên lên form  -->
													<input type="hidden" name="ten" value="${phieumuon.nm.ten}" />
													<!-- 														Dùng để gửi về controller  -->
													<input type="hidden" name="mapm" value="${phieumuon.mapm}" />
													<button type="submit" style="display: none"
														class="submit_del_btn"></button>
												</form>
												<button class="btn btn-danger delete_btn"
													data-toggle="tooltip" data-placement="top" title="Xoá"
													type="button">
													<i class="material-icons">delete</i>
												</button>
											</div>
										</td>
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
	<!-- 	======================   Thêm phiếu mượn ============================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-insert-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-info">Thêm phiếu mượn</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="test" modelAttribute="phieumuon_moi"
							method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							
							<div class="row">
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element'>
									<div id="hidden-element" style="display: none">
										<div class="form-group row">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">
												<select class="form-control" name="thietBis"
													style="width: 100%; height: 45px;">
													<option value="">Chọn thiết bị</option>
													<c:forEach var="tb" items="${thietBis}">
														<option value="${tb.matb}">${tb.ten}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-3">
												<input type="number" value="1" min="1" name="soLuongTbs"
													class="form-control" placeholder="Số lượng"
													style="width: 100%; height: 45px;" />
											</div>
											<div class="col-md-2">
												<a class="material-icons text-info btn-close-item"
													type="button"> delete_sweep </a>
											</div>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-2 col-form-label">Thiết
											bị </label>
										<div class="col-md-5">
											<select class="form-control" name="thietBis"
												style="width: 100%; height: 45px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb" items="${thietBis}">
													<option value="${tb.matb}">${tb.ten}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-md-3">
											<input type="number" value="1" min="1" name="soLuongTbs"
												class="form-control" placeholder="Số lượng"
												style="width: 100%; height: 45px;" />
										</div>
										<div class="col-md-2">
											<a class="material-icons text-info btn-close-item"
												type="button"> delete_sweep </a>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 col-md-6"></div>
								<div class="col-sm-12 col-md-6 align-self-end">
									<button type='button' class="form-control text-info "
										id='btn-add-element'>
										<span class="material-icons text-info">add_circle_outline</span>
										Thêm thiết bị mượn
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<hr>
								<button type="button" class="btn btn-dark" data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-info">Thêm</button>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>
	<!-- js -->
	<%@include file="/common/footer.jsp"%>
	<!-- 		Thêm 1 dòng thiết bị mỗi lần click -->
	<script>
		/* const btn = document.getElementById('btn-add-element')
		btn.addEventListener('click', function(e){
			

		}) */

		//Đoạn trên có thể viết lại như sau:
		$('#btn-add-element').on('click', function() {
			//Lưu ý là cái hidden-element dùng class thì đổi thành $('.hidden-element')
			let newEle = $('#hidden-element')[0].innerHTML;
			$('#parent-element').append(newEle);
			
		})
	</script>
	<!-- Xoá thiết bị -->
	<script>
		$('#parent-element').on('click', '.btn-close-item', function() {
			$(this).parent().parent().remove()

		})
	</script>
</body>
</html>