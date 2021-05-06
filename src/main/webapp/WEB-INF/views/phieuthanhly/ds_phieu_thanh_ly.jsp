<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath }/">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>

<%@include file="/common/loadcss.jsp"%>
</head>
<body>

	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>

	<div class="mobile-menu-overlay"></div>

	<!-- 	Popup -->
	<!-- 	================================ Hiển thị danh sách Phiếu Thanh Lý ================================ -->
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Danh sách phiếu thanh lý</h4>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">DataTable</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<div class="col-md-6 col-sm-12 text-right">
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-insert-modal-lg"
								type="button">Thêm phiếu thanh lý </a>
						</div>
					</div>
				</div>

				<!-- Hiển thị danh sách thiết bị đã thanh lý trước đó -->
				<div class="card-box mb-30">

					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã phiếu thanh lý</th>
									<th>Mã nhân viên</th>
									<th>Thời gian </th>
									<th>Ghi chú</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="phieumuon" items="${listPhieuThanhLy}"
									varStatus="row">
									<tr>

										<td class="table-plus">${phieuthanhly.maptl}</td>
										<td>${phieuthanhly.manv}</td>
										<td>${phieuthanhly.ghichu}</td>
										<td>${phieuthanhly.thoigian}</td>
										<c:choose>
											<c:when test="${phieuthanhly.thoigian.isEmpty()}">
												<td>${phieuthanhly.thoigian}</td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>

										<td class="table-plus">${ptl.maptl}</td>
										<td>${ptl.manv}</td>
										<td>${ptl.thoigian}</td>
										<td>${ptl.ghichu}</td>

										<c:choose>
											<c:when test="${phieuthanhly.ghichu.isEmpty()}">
												<td>${phieuthanhly.ghichu}</td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 	======================   Thêm phiếu thanh lý ============================ -->
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
								<h4 class="text-blue h4">Thêm phiếu thanh lý</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="phieuthanhly" modelAttribute="phieuthanhly_moi"
							method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu thanh lý</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="mapm" /> --%>
											<form:input class="form-control" type="text" path="maptl"
												placeholder="Nhập mã phiếu thanh lý" />
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<select class="selectpicker form-control" name="manv"	style="width: 100%; height: 38px;">
												<option value="">Chọn Nhân viên</option>
												<c:forEach var="nv" items="${listNhanViens }">
													<option value="nv">${nv}</option>
												</c:forEach>
												
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="ngaysinh" /> --%>
											<form:input class="form-control" path="thoigianmuon"
												placeholder="Chọn thời gian thanh lý" type="date" />
										</div>
									</div>
		
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>

								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6">

									<div class="form-group row">
										<label class="col-sm-4 col-md-3 col-form-label">Thiết
											bị 1</label>
										<div class="col-sm-4 col-md-6	">
											<select class="selectpicker form-control" name="thietBi1"
												style="width: 100%; height: 38px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb1" items="${loaiThietBis}">
													<option value="tb1.matb">${tb1.matb}</option>
												</c:forEach>
											</select>
											<%-- 														<form:errors path="loai.id" /> --%>
										</div>

										<div class="col-sm-6 col-md-3">
										<input type="text" value="" name="slThietBi1" class="form-control" placeholder="Số lượng" />
										</div>

									</div>

									<div class="form-group row">
										<label class="col-sm-4 col-md-3 col-form-label">Thiết
											bị 2</label>
										<div class="col-sm-4 col-md-6	">
											<select class="selectpicker form-control" name="thietBi2"
												style="width: 100%; height: 38px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb2" items="${loaiThietBis}">
													<option value="tb2.matb">${tb2.matb}</option>
												</c:forEach>
											</select>
											<%-- 														<form:errors path="loai.id" /> --%>
										</div>
										<div class="col-sm-6 col-md-3">
										<input type="text" value="" name="slThietBi2" class="form-control" placeholder="Số lượng" />
										</div>

									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-3 col-form-label">Thiết
											bị 3</label>
										<div class="col-sm-4 col-md-6	">
											<select class="selectpicker form-control" name="thietBi3"
												style="width: 100%; height: 38px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb3" items="${loaiThietBis}">
													<option value="tb3.matb">${tb3.matb}</option>
												</c:forEach>
											</select>
											<%-- 														<form:errors path="loai.id" /> --%>
										</div>
										<div class="col-sm-6 col-md-3">
										<input type="text" value="" name="slThietBi3" class="form-control" placeholder="Số lượng" />
										</div>

									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-3 col-form-label">Thiết
											bị 4</label>
										<div class="col-sm-4 col-md-6	">
											<select class="selectpicker form-control" name="thietBi4"
												style="width: 100%; height: 38px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb4" items="${loaiThietBis}">
													<option value="tb4.matb">${tb4.matb}</option>
												</c:forEach>
											</select>
											<%-- 														<form:errors path="loai.id" /> --%>
										</div>
										<div class="col-sm-6 col-md-3">
										<input type="text" value="" name="slThietBi4" class="form-control" placeholder="Số lượng" />
										</div>

									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-3 col-form-label">Thiết
											bị 5</label>
										<div class="col-sm-4 col-md-6	">
											<select class="selectpicker form-control" name="thietBi5"
												style="width: 100%; height: 38px;">
												<option value="">Chọn thiết bị</option>
												<c:forEach var="tb5" items="${loaiThietBis}">
													<option value="tb5.matb">${tb5.matb}</option>
												</c:forEach>
											</select>
											<%-- 														<form:errors path="loai.id" /> --%>
										</div>
										<div class="col-sm-6 col-md-3">
										<input type="text" value="" name="slThietBi5" class="form-control" placeholder="Số lượng" />
										</div>

									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-dark	" data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-info">Thêm</button>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>


	<!-- 	<!-- js -->
	<%@include file="/common/footer.jsp"%>


	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>

	<!-- HIỂN THỊ THÔNG BÁO KHI SAI ĐỊNH DẠNG DỮ LIỆU LÚC THÊM -->
	<c:if test="${them_saidinhdang}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#insert_btn').click();
				console.log("thêm sai định dạng")

			});
		</script>
	</c:if>

	<!-- HIỂN THỊ THÔNG BÁO THÊM THÀNH CÔNG / THẤT BẠI -->
	<c:if test="${insert || update || delete}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_success();

			});
		</script>
	</c:if>
	<!-- HIỂN THỊ THÔNG BÁO CÓ LỖI XẢY RA-->
	<c:if test="${insert == false || update == false || delete == false}">
		<script type="text/javascript">
			$(document).ready(function() {	
				show_error();
			});
		</script>
	</c:if>


	<script type="text/javascript">
		//LOAD TABLE 
		$('#myTable').DataTable();
			
		
		//NẾU CLICK NÚT XOÁ
		$('#myTable').on('click','.delete_btn',function(){
			let ten = $(this).parent().find("input[name='ten']").val();
			let delete_btn = $(this).parent().find('.submit_del_btn');
			Swal.fire({
				title: 'Xoá ' + ten + ' ?',
				text: "Dữ liệu không thể khôi phục sau thao tác này!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Xoá',
				cancelButtonText: 'Huỷ'
			}).then((result) => {
				//Nếu nút đồng ý được nhấn
				if (result.isConfirmed) {
					delete_btn.click();
				}
			})
		}) 
		
		
		//THÔNG BÁO THÀNH CÔNG
		function show_success(content="Thao tác") {
			Swal.fire({
				title: 'THÀNH CÔNG',
				text: content+" thành công!",
				icon: 'success',
			})
		}
		
		//THÔNG BÁO LỖI
		function show_error(content="Thao tác") {
			Swal.fire({
				title: 'THẤT BẠI',
				text: content+" thất bại!",
				icon: 'error',
			})
		}
	</script>
	<!-- 	</script> -->
</body>
</html>