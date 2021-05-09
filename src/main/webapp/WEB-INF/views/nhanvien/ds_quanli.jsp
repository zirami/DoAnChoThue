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
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Danh sách người quản lí</h4>
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
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">Thêm người quản lí </a>
						</div>
					</div>
				</div>

				<!-- Hiển thị danh sách người quản lí -->
				<div class="card-box mb-30">
					${message}
					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã người QL</th>
									<th>Họ và Tên</th>
									<th>Ngày sinh</th>
									<th>Giới tính</th>
									<th>Ngày tiếp nhận</th>
									<th>Username</th>
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="quanli" items="${listQuanLi}" varStatus="row">
									<tr>
										<td class="table-plus">${quanli.maql}</td>
										<td>${quanli.ho} ${quanli.ten}</td>
										<td>${quanli.ngaysinh}</td>
										<td>${quanli.gioitinh}</td>
										
										<td>${quanli.ngaytiepnhan}</td>
										<td>${quanli.acc.username}</td>
										<td>
											<div class="row clearfix btn-list">
												<!-- 												<div class=""> -->
												<form action="qlquanli/edit/${quanli.maql}">
													<button class="btn btn-primary" type="submit"
														data-toggle="tooltip" data-placement="top" title="Sửa">
														<i class="material-icons">edit</i>
													</button>
													<!--  -->
												</form>

												<form action="qlquanli/delete" method="post">
													<!-- Dùng để hiển thị tên lên form -->
													<input type="hidden" name="ten" value="${quanli.ten}" />

													<!-- Dùng để gửi về controller -->
													<input type="hidden" name="maql" value="${quanli.maql}" />
													<button type="submit" style="display: none"
														class="submit_del_btn"></button>
												</form>
												<button class="btn btn-danger delete_btn"
													data-toggle="tooltip" data-placement="top" title="Xoá"
													type="button">
													<i class="material-icons">delete</i>
												</button>
											</div> <!-- 											</div> -->
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
	<!-- 	======================   Thêm Người Quản Lý ============================ -->
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
								<h4 class="text-blue h4">Thêm người quản lí</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="qlquanli" modelAttribute="quanli_moi"
							method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Mã
									quản lí</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="maql" />
									<form:input class="form-control" type="text" path="maql"
										placeholder="Nhập mã quản lí" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Họ và
									chữ lót</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ho" />
									<form:input class="form-control" type="text" path="ho"
										placeholder="Nhập họ và chữ lót" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Tên</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ten" />
									<form:input class="form-control" type="text" path="ten"
										placeholder="Nhập tên" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ngày
									sinh</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ngaysinh" />
									<form:input class="form-control" path="ngaysinh"
										placeholder="Chọn ngày sinh" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Giới
									tính</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="gioitinh" />
									<form:select path="gioitinh" class="selectpicker form-control"
										style="width: 100%; height: 38px;">
										<form:option value="" label="-Vui lòng chọn 1-" />
										<form:options items="${gioiTinhs}" />
									</form:select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ngày tiếp nhận</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ngaysinh" />
									<form:input class="form-control" path="ngaytiepnhan"
										placeholder="Chọn ngày sinh" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Username</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="acc.username" />
									<form:input path="acc.username" class="form-control" type="text"
										placeholder="Nhập Username" />
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
					<!-- 					Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Sửa thông tin người mượn</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="qlquanli/update" modelAttribute="quanli_sua"
							method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Mã quản
									lí</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="maql" />
									<form:input class="form-control" type="text" path="maql"
										placeholder="Nhập mã người quản lí" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Họ và
									chữ lót</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ho" />
									<form:input class="form-control" type="text" path="ho"
										placeholder="Nhập họ và chữ lót" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Tên</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ten" />
									<form:input class="form-control" type="text" path="ten"
										placeholder="Nhập tên" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ngày
									sinh</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ngaysinh" />
									<form:input class="form-control" path="ngaysinh"
										placeholder="Chọn ngày sinh" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Giới
									tính</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="gioitinh" />
									<form:select path="gioitinh" items="${gioiTinhs}"
										class="selectpicker form-control"
										style="width: 100%; height: 38px;">
									</form:select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ngày
									tiếp nhận</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="ngaytiepnhan" />
									<form:input class="form-control" path="ngaytiepnhan"
										placeholder="Chọn ngày sinh" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Username</label>
								<div class="col-sm-12 col-md-10">
									<form:errors path="acc.username" />
									<form:input path="acc.username" class="form-control" type="text"
										placeholder="Nhập Username" />
								</div>
							</div>


							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-success">Sửa</button>
							</div>
						</form:form>

					</div>
					<!-- 					Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>



	<!-- js -->
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
</body>
</html>