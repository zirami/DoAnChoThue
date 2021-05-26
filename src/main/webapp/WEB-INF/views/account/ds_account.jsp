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
								<h4>Danh sách tài khoản</h4>
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
							<!-- <div class="dropdown">
								<a class="btn btn-primary dropdown-toggle" href="#"
									role="button" data-toggle="dropdown"> January 2018 </a>
								<div class="dropdown-menu dropdown-menu-right">
									<a class="dropdown-item" href="#">Export List</a> <a
										class="dropdown-item" href="#">Policies</a> <a
										class="dropdown-item" href="#">View Assets</a>
								</div>
							</div> -->
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM TÀI KHOẢN </a>
						</div>
					</div>
				</div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<%-- 					${message} --%>
					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Username</th>
									<th>Password</th>
									<th>Gmail</th>
									<th>SDT</th>
									<th>Mã Phân Quyền</th>
									<th class="datatable-nosort">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="account" items="${listAccount}" varStatus="row">
									<tr>
										<td class="table-plus">${account.username}</td>
										<td>${account.password}</td>
										<td>${account.gmail}</td>
										<td>${account.sdt}</td>
										<td>${account.phanquyen.mapq}</td>
										
										<td>
											<div class="row clearfix">
												<div class="col ">
													<form action="account/edit/${account.username}">
														<button class="btn btn-primary" type="submit"
															data-toggle="tooltip" data-placement="top" title="Sửa">
															<i class="material-icons">edit</i>
														</button>
														<!--  -->
													</form>
												</div>
												<div class="col">
													<form action="account/delete" method="post" hidden="true">
														<!-- Dùng để hiển thị tên lên form -->
														<input type="hidden" name="id" value="${account.username}" />
														<!-- Dùng để gửi về controller -->
														<input type="hidden" name="id" value="${account.username}" />
														<button type="submit" class="submit_del_btn"></button>
													</form>
													<button class="btn btn-danger delete_btn"
														data-toggle="tooltip" data-placement="top" title="Xoá"
														type="button">
														<i class="material-icons">delete</i>
													</button>
												</div>
												<div class="col">
													<form action="account/lock" method="post" hidden="true">
														<input type="hidden" name="id" value="${account.username}" />
														<button type="submit" class="submit_lock_btn"></button>
													</form>
													<button id="lock_btn" class="btn btn-danger"
														data-toggle="tooltip" data-placement="top" title="Khoá"
														type="button">
														<i class="material-icons">lock</i>
													</button>
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
								<h4 class="text-blue h4">Thêm tài khoản mới</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="account" modelAttribute="account_moi"
							method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Username
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="username" class="form-control" type="text"
										placeholder="Nhập username" />
									<form:errors path="username" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Password
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="password" class="form-control" type="text"
										placeholder="Nhập password" />
									<form:errors path="password" />
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Gmail
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="gmail" class="form-control" type="text"
										placeholder="Nhập Gmail" />
									<form:errors path="gmail" />
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">SDT</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="sdt" class="form-control" type="text"
										placeholder="Số điện thoại" />
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
								<h4 class="text-blue h4">Sửa thông tin tài khoản</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="account/update" modelAttribute="account_sua"
							method="post">
							<form:input path="username" class="form-control" type="hidden" />
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Password
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="password" class="form-control" type="text"
										placeholder="Nhập password" />
									<form:errors path="password" />
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Gmail
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="gmail" class="form-control" type="text"
										placeholder="Nhập địa chỉ gmail" />
									<form:errors path="gmail" />
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">SDT
									</label>
								<div class="col-sm-12 col-md-10">
									<form:input path="sdt" class="form-control" type="text"
										placeholder="Nhập số điện thoại" />
									<form:errors path="sdt" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Mã 
									phân quyền </label>
								<div class="col-sm-12 col-md-10">
									<form:input path="phanquyen.mapq" class="form-control" type="text"
										placeholder="Nhập mã phân quyền" />
									<form:errors path="phanquyen.mapq" />
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
	<!-- <script>
		let input = document.getElementById('maThietbi_modal');
		let btnTest = document.querySelector('.btn-test');

		btnTest.addEventListener('click', function() {

			console.log(input.classList);
		})
	</script> -->
	<c:if test="${action.equals('edit') }">
	</c:if>
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
		//LOAD TABLE 
		$('#myTable').DataTable();
		
		
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
		
		//NẾU CLICK NÚT KHOÁ
		$('#lock_btn').on('click',function(){
			if(confirm_lock()){
				console.log(1)
			}
			console.log("locked")
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