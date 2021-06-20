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
<body
	style="background-image: url('${pageContext.servletContext.contextPath}/resources/files/hinh11.jpg')">
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
								<h3 class="text-purple">Danh sách thiết bị</h3>
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
								<input type="file" hidden="true" name="file" />
								<input type="submit" hidden="true">
							</form>
							<a href="thiet-bi" class="btn btn-info" type="button">Làm mới
							</a>
							
							<a href="#" class="btn  btn-info"
								data-toggle="modal" data-target="#bd-loaitb-modal-lg"
								type="button">Loại thiết bị </a>
							
							<button class="btn btn-info import-excel">Import Excel</button>
							<a href="#" id="insert_btn" class="btn  btn-info"
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
							<thead class="table-info">
								<tr>
									<th>Hình ảnh</th>
									<th class="table-plus">Mã thiết bị</th>
									<th>Tên Thiết Bị</th>
									<th>Loại</th>
									<th>Số Lượng tồn</th>
									<th>Ghi chú</th>
									<th class="pull-right">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="thietbi" items="${listThietbi}">
									<tr>
										<td>
											<img src="resources/files/${thietbi.hinh }"
												style="width: 100px; height: 100px">
										</td>
										<td class="table-plus">${thietbi.matb}</td>
										<td>
											<p
												style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${thietbi.ten}</p>
										</td>
										<td>${thietbi.loai.ten}</td>
										<td>${thietbi.soluong}</td>
										<td>
											<p
												style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${thietbi.ghichu}</p>
										</td>
										<td class="pull-right">
											<div class="row clearfix">
												<div class="col-sm-12 col-md-6">
													<form action="thiet-bi/edit/${thietbi.matb}">
														<button class="btn  btn-info bg-edit text-white"
															type="submit" data-toggle="tooltip" data-placement="top"
															title="Sửa">
															<i class="material-icons">edit</i>
														</button>
														<!--  -->
													</form>
												</div>
												<div class="col-sm-12 col-md-6">
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
													<c:if test="${!thietbi.trangthai.equals(unlocked)}">
														<button class="btn btn-secondary" data-toggle="tooltip"
															data-placement="top" title="Xoá" type="button">
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
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h3 class="text-info h3">Thêm thiết bị</h3>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="thiet-bi" modelAttribute="thietbi_moi"
							method="post" enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Chọn
											ảnh</label>
										<div class="col-sm-12 col-md-8">
											<input name="photo" class="form-control" type="file"
												placeholder="Chọn ảnh thiết bị" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<input class="form-control" name="matb_temp"
												value="${maThietBi}" readonly />
											<form:input class="form-control" type="text" path="matb"
												value="${maThietBi}" placeholder="Nhập mã thiết bị"
												required="required" style="display: none" />
											<form:errors path="matb" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên thiết bị" />
											<form:errors path="ten" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại</label>
										<div class="col-sm-12 col-md-9">
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
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ghichu" class="form-control" type="text"
												placeholder="Ghi chú" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Thêm</button>
									</div>
								</div>
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
							method="post" enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Chọn
											ảnh</label>
										<div class="col-sm-12 col-md-8">
											<img src="resources/files/${thietbi_sua.hinh}"
												style="width: 250px; height: 200px" />
											<input name="photo" class="form-control" type="file"
												placeholder="Chọn ảnh thiết bị" />
											<form:input path="hinh" class="form-control"
												style="display: none" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<input class="form-control" value="${thietbi_sua.matb}"
												readonly />
											<form:input class="form-control" type="text" path="matb"
												placeholder="Nhập mã thiết bị" style="display: none" />
											<form:errors path="matb" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên thiết bị" />
											<form:errors path="ten" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại</label>
										<div class="col-sm-12 col-md-9">
											<form:select class="selectpicker form-control" path="loai.id"
												style="width: 100%; height: 38px;">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${loaiThietBis}" itemValue="id"
													itemLabel="ten" />
											</form:select>
											<form:errors path="loai.id" />
										</div>
									</div>
									<form:input style="display: none" path="soluong" />
									<form:input style="display: none" path="tinhtrang" />
									<form:input style="display: none" path="trangthai" />
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ghichu" class="form-control" type="text"
												placeholder="Ghi chú" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Sửa</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>
	
	<!--======================================================== Loại Thiết Bị ============================================================ -->
	<div class="modal fade bs-example-modal-lg"
			id="bd-loaitb-modal-lg" tabindex="-1" role="dialog"
			aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-xl modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
							<div class="row">
									<h3 class="text-info">Danh sách các loại thiết bị</h3>
								

							</div>

					</div>
					<div class="modal-body">
					
						
						<!-- Default Basic Forms Start -->
						<div class="pd-20 card-box mb-30">
							<a href="#" class="btn bg-purple text-yellow pull-right"
										data-toggle="modal" data-target="#bd-insertLTB-modal-lg"
										type="button">Thêm loại thiết bị </a>

							<!-- 						<div class="col-md-12 col-sm-12 text-center"> -->
							<table
								class="table nowrap  dataTable collapsed table-hover table-boder-factor tableLoaiThietBi"
								style="width: 100%; border-collapse: collapse;" role="grid">

								<thead class="table-info">
									<tr>
										<th>Mã thiết bị</th>
										<th>Tên thiết bị</th>
										<th class="pull-right">Hành động</th>
									
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ltb" items="${loaiThietBis}"
										varStatus="row">
												<tr>
													<td>${ltb.id}</td>
													<td>${ltb.ten}</td>
													<td class="pull-right">
														<div class="row clearfix">
															<div class="col-sm-12 col-md-6">
																<form action="loaiTB/edit/${ltb.id}" method="get">
																	<button class="btn  btn-info bg-edit text-white"
																		type="submit" data-toggle="tooltip" data-placement="top"
																		title="Sửa">
																		<i class="material-icons">edit</i>
																	</button>
																	<!--  -->
																</form>
															</div>
															<div class="col-sm-12 col-md-6">

																	<form action="loaiTB/delete" method="post" hidden="true">
																		<!-- Dùng để hiển thị tên lên form -->
																		<input type="hidden" name="ten" value="${ltb.ten}" />
																		<!-- Dùng để gửi về controller -->
																		<input type="hidden" name="id" value="${ltb.id}" />
																		<button type="submit" class="submit_del_btn"></button>
																	</form>
																	<button class="btn btn-danger delete_btn"
																		data-toggle="tooltip" data-placement="top" title="Xoá"
																		type="button">
																		<i class="material-icons">delete</i>
																	</button>
																
															</div>
														</div>
										</td>
													
												</tr>
										
									</c:forEach>
								</tbody>
							</table>

						</div>
						<!-- Default Basic Forms Start -->
					</div>
					<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>

							</div>
				</div>
			</div>
		</div>
		
<!-- 	===========================================================================  Thêm Loại Thiết Bị =========================================================================== -->
	<div class="modal fade bs-example-modal-lg" id="bd-insertLTB-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h3 class="text-info h3">Thêm loại thiết bị</h3>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="loaiTB" modelAttribute="loaithietbi_moi" method="post" >
								<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên loại thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên loại thiết bị" required="required" />
										</div>
								</div>
								<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Thêm</button>
								</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>
	<a hidden="true" href="#" class="btn  btn-info" id="dsLoaiTbBtn" data-toggle="modal" data-target="#bd-edit-loaiTB-modal-lg" type="button"></a>
<!-- 	<button hidden="true" id="update_loaiTb_modal_btn" data-toggle="modal" data-target="#bd-edit-loaiTB-modal-lg"></button> -->
	<!--======================================================== Dùng để update ============================================================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-edit-loaiTB-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h3 class="text-info h3">Sửa loại thiết bị</h3>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="loaiTB/update" modelAttribute="loaithietbi_sua" method="post" >
								<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên loại thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="id" class="form-control" type="text"
												placeholder="Mã loại thiết bị" required="required" style="display: none" />
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên loại thiết bị" required="required" />
										</div>
								</div>
								<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Sửa</button>
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
	
	<script type="text/javascript">
		//LOAD TABLE 
		$('.tableLoaiThietBi').DataTable({
			scrollCollapse: true,
			autoWidth: false,
			responsive: true,
			order: [[0,"desc"]]
		});
	</script> 
	
	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit_loaiTB}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#dsLoaiTbBtn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>
	
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
			
		//NẾU CLICK NÚT XOÁ LOẠI THIẾT BỊ
		$('.tableLoaiThietBi').on('click','.delete_btn',function(){
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