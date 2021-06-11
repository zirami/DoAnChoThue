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
						<!-- <div class="col-md-3 col-sm-12 text-right form-group "></div> -->
						<div class="col-md-6 col-sm-12 text-right">
							<form action="import-excel" method="post"
								enctype="multipart/form-data">
								<input type="file" hidden="true" name="file"/>
								<input type="submit" hidden="true">
							</form>
							<button class="btn btn-primary import-excel">Import
								Excel</button>
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM THIẾT BỊ MỚI </a>
							<!-- <a href="phieu-nhap" class="btn btn-primary" target="_blank" type="button">NHẬP
								THIẾT BỊ</a> -->
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
									<th>Số Lượng tồn</th>
									<th>Ghi chú</th>
									<th class="datatable-nosort">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="thietbi" items="${listThietbi}">
									<tr>
										<td class="table-plus">${thietbi.matb}</td>
										<td>${thietbi.ten}</td>
										<td>${thietbi.loai.ten}</td>
										<td>${thietbi.soluong}</td>
										<td>${thietbi.ghichu}</td>
										<td>
											<div class="row clearfix">
												<div class="col-3">
													<form action="thiet-bi/edit/${thietbi.matb}">
														<button class="btn btn-primary" type="submit"
															data-toggle="tooltip" data-placement="top" title="Sửa">
															<i class="material-icons">edit</i>
														</button>
														<!--  -->
													</form>
												</div>
												<div class="col-6 text-center">
													<c:set var="unlocked" value="unlocked" />
													<c:if test="${thietbi.trangthai.equals(unlocked)}">
														<form action="thiet-bi/delete" method="post" hidden="true">
															<!-- Dùng để hiển thị tên lên form -->
															<input type="hidden" name="ten" value="${thietbi.ten}" />
															<!-- Dùng để gửi về controller -->
															<input type="hidden" name="id" value="${thietbi.matb}" />
															<button type="submit" class="submit_del_btn"></button>
														</form>
														<button class="btn btn-danger delete_btn"
															data-toggle="tooltip" data-placement="top" title="Xoá"
															type="button">
															<i class="material-icons">delete</i>
														</button>
													</c:if>
												</div>
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
	<!--======================================================== Dùng để insert ============================================================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Thêm thiết bị</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="thiet-bi" modelAttribute="thietbi_moi"
							method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Mã
									thiết bị</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="matb" class="form-control" type="text"
										placeholder="Nhập mã thiết bị" />
									<form:errors path="matb" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Tên
									thiết bị</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="ten" class="form-control" type="text"
										placeholder="Nhập tên thiết bị" />
									<form:errors path="ten" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Loại</label>
								<div class="col-sm-12 col-md-10">
									<form:select class="selectpicker form-control" path="loai.id"
										style="width: 100%; height: 38px;">
										<form:option value="" label="-Vui lòng chọn 1-" />
										<form:options items="${loaiThietBis}" itemValue="id"
											itemLabel="ten" />
									</form:select>
									<form:errors path="loai.id" />
								</div>
							</div>
							<input type="hidden" name="tinhtrang" value="unlocked" />
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="ghichu" class="form-control" type="text"
										placeholder="Ghi chú" />
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-success">Thêm</button>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>
	<button hidden="true" id="update_modal_btn" data-toggle="modal"
		data-target="#bd-edit-modal-lg"></button>
	<!--======================================================== Dùng để update ============================================================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-edit-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Sửa thiết bị</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="thiet-bi/update" modelAttribute="thietbi_sua"
							method="post">
							<form:input path="matb" class="form-control" type="hidden" />
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Tên
									thiết bị</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="ten" class="form-control" type="text"
										placeholder="Nhập tên thiết bị" />
									<form:errors path="ten" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Loại</label>
								<div class="col-sm-12 col-md-10">
									<form:select class="selectpicker form-control" path="loai.id"
										style="width: 100%; height: 38px;" items="${loaiThietBis}"
										itemValue="id" itemLabel="ten">
									</form:select>
									<form:errors path="loai" />
								</div>
							</div>
							<form:input path="tinhtrang" type="hidden" />
							<form:input path="trangthai" type="hidden" />
							<form:input path="soluong" type="hidden" />
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="ghichu" class="form-control" type="text"
										placeholder="Ghi chú" />
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-success">Sửa</button>
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
	<!-- kích hoạt table -->
	<script src="resources/vendors/scripts/datatable-setting.js"></script>
	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>
	<!-- DÙNG ĐỂ SHOW FORM DELETE -->
	<c:if test="${form_del}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị del form")
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
	<!-- HIỂN THỊ THÔNG BÁO KHI SAI ĐỊNH DẠNG DỮ LIỆU LÚC THÊM -->
	<c:if test="${them_saidinhdang}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#insert_btn').click();
				console.log("thêm sai định dạng")

			});
		</script>
	</c:if>
	<!-- HIỂN THỊ THÔNG BÁO KHI SAI ĐỊNH DẠNG DỮ LIỆU LÚC SỬA -->
	<c:if test="${sua_saidinhdang}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Sửa sai định dạng")
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
		
		//NẾU CLICK NÚT XOÁ
		$('#myTable').on('click','.delete_btn',function(){
			let ten = $(this).parent().find("input[name='ten']").val();
			let delete_btn = $(this).parent().find('.submit_del_btn');
			Swal.fire({
				title: 'Xoá [' + ten + '] ?',
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
	<!-- IMPORT EXCEL -->
	<script type="text/javascript">
		$('.import-excel').on('click', function(){
			let inputFile = $(this).parent().find("input[type='file']")
			let btnSubmit = $(this).parent().find("input[type='submit']")
			
			inputFile.on('change', function(){
				btnSubmit.click()
			})
			inputFile.click();
		})
	</script>
</body>
</html>