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
<body style="background-image: url('${pageContext.servletContext.contextPath}/resources/files/hinh11.jpg')">

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
								<h3 class="text-purple">Danh sách nhân viên</h3>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="phieumua">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">DataTable</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<div class="col-md-6 col-sm-12 text-right">
						<a href="qlnhanvien" class="btn btn-info" type="button">Làm mới</a> 
							<a href="#" id="insert_btn" class="btn btn-info"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">Thêm nhân viên </a>
						</div>
					</div>
				</div>

				<!-- Hiển thị danh sách nhân viên -->
				<div class="card-box mb-30">
					${message}
					<hr>
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead class="table-info" >
								<tr>
									<th>Hình ảnh</th>
									<th class="table-plus datatable-nosort">Mã NV</th>
									<th>Họ và Tên</th>
									<th>Giới tính</th>
									<th>Ngày sinh</th>
									<th>Địa chỉ</th>
									<th>CMND</th>
									<th>Mã QL</th>
									<th>Username</th>
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="nhanvien" items="${listNhanVien}"
									varStatus="row">
									<tr>
										<td><img src="resources/files/${nhanvien.hinh }" style="width: 100px; height: 100px"></td>
										<td class="table-plus">${nhanvien.manv}</td>
										<td><p style="width: 170px;overflow: hidden;text-overflow: ellipsis; white-space: nowrap;">${nhanvien.ho}  ${nhanvien.ten}</p></td>
										<td>${nhanvien.gioitinh}</td>
										<td>${nhanvien.ngaysinh}</td>
										<td><p style="width: 150px;overflow: hidden;text-overflow: ellipsis; white-space: nowrap;">${nhanvien.diachi}</p></td>
										<td>${nhanvien.cmnd}</td>
										<td>${nhanvien.ql.maql}
										<td>${nhanvien.acc.username}</td>
										<td>
											<div class="row clearfix btn-list">
												<!-- 												<div class=""> -->
												<form action="qlnhanvien/edit/${nhanvien.manv}">
													<button class="btn btn-info bg-edit text-white" type="submit"
														data-toggle="tooltip" data-placement="top" title="Sửa">
														<i class="material-icons">edit</i>
													</button>
													<!--  -->
												</form>

												<form action="qlnhanvien/delete" method="post">
													<!-- Dùng để hiển thị tên lên form -->
													<input type="hidden" name="ten" value="${nhanvien.ten}" />

													<!-- Dùng để gửi về controller -->
													<input type="hidden" name="manv" value="${nhanvien.manv}" />
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
	<!-- 	======================   Thêm Nhân viên ============================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Thêm nhân viên</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="qlnhanvien" modelAttribute="nhanvien_moi"
							method="post" enctype="multipart/form-data">
							<div class="row">
							<div class = "col-sm-12 col-md-6">
								<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Hình đại diện</label>
								<div class="col-sm-12 col-md-8">
									<input name="photo" class="form-control" type="file"
										placeholder="Nhập mã quản lí" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Mã QL</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="ql.maql" /> --%>
									<form:input path="ql.maql" class="form-control" type="text" value="${maQuanLi }"
										placeholder="Nhập mã quản lí" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Username
									</label>
								<div class="col-sm-12 col-md-8">
									<input name="username" class="form-control" type="text"
										placeholder="Nhập username" required="required"/>
<%-- 									<form:errors path="username" /> --%>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Password
									</label>
								<div class="col-sm-12 col-md-8">
									<input name="password" class="form-control" type="password"
										placeholder="Nhập password" required="required" />
<%-- 									<form:errors path="password" /> --%>
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Gmail
									</label>
								<div class="col-sm-12 col-md-8">
									<input name="gmail" class="form-control" type="email"
										placeholder="Nhập Gmail" required="required"/>
<%-- 									<form:errors path="gmail" /> --%>
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">SDT</label>
								<div class="col-sm-12 col-md-8">
									<input name="sdt" class="form-control" type="number" onkeydown="limit_sdt(this);" onkeyup="limit_sdt(this);"
										placeholder="Số điện thoại" required="required"/>
								</div>
							</div>
							</div>
							<div class = "col-sm-12 col-md-6">
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Mã nhân viên</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="manv" /> --%>
								<input class="form-control" value="${maNhanVien}" readonly />

								<form:input class="form-control" type="text" path="manv" value="${maNhanVien }"
										placeholder="Nhập mã nhân viên" required="required" style="display: none"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Họ và chữ lót</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="ho" /> --%>
									<form:input class="form-control" type="text" path="ho"
										placeholder="Nhập họ và chữ lót" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="ten" /> --%>
									<form:input class="form-control" type="text" path="ten"
										placeholder="Nhập tên" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Giới
									tính</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="gioitinh" /> --%>
									<form:select path="gioitinh" class="selectpicker form-control"
										style="width: 100%; height: 38px;" required="required">
										<form:option value="" label="-Vui lòng chọn 1-" />
										<form:options items="${gioiTinhs}" />
									</form:select>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Ngày
									sinh</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="ngaysinh" /> --%>
									<form:input class="form-control" path="ngaysinh"
										placeholder="Chọn ngày sinh" type="date" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Địa chỉ</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="diachi" /> --%>
									<form:input class="form-control" type="text" path="diachi"
										placeholder="Nhập địa chỉ" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
								<div class="col-sm-12 col-md-8">
<%-- 									<form:errors path="cmnd" /> --%>
									<form:input path="cmnd" class="form-control" type="number" onkeydown="limit_cmnd(this);" onkeyup="limit_cmnd(this);"
										placeholder="Nhập chứng minh nhân dân" required="required"/>
								</div>
							</div>
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


	<button hidden="true" id="update_modal_btn" data-toggle="modal"
		data-target="#bd-edit-modal-lg"></button>
	<!--======================================================== Dùng để update ============================================================ -->

	<div class="modal fade bs-example-modal-lg" id="bd-edit-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Sửa thông tin nhân viên</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="qlnhanvien/update" modelAttribute="nhanvien_sua"
							method="post" enctype="multipart/form-data">
							<div class="row">
							<div class = "col-sm-12 col-md-6 ">
								<div class="form-group row">
								<label class="col-sm-12 col-md-4 col-form-label">Ảnh đại diện</label>
								<div class="col-sm-12 col-md-6">
									<img src="resources/files/${nhanvien_sua.hinh}"	>
									<input name="photo" class="form-control" type="file" placeholder="Chọn ảnh khác"/>
									<form:input path="hinh" class="form-control"
												style="display: none"/>
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Username
									</label>
								<div class="col-sm-12 col-md-8">
									<form:input path="acc.username" class="form-control" type="text" style="display: none"/>
<%-- 									<form:errors path="username" /> --%>
										<input class="form-control" type="text" placeholder="${nhanvien_sua.acc.username }" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Password
									</label>
								<div class="col-sm-12 col-md-8">
									<form:input path="acc.password" class="form-control" type="password"
										placeholder="Nhập password" required="required"/>
<%-- 									<form:errors path="password" /> --%>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Mã QL</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="ql.maql" />
									<form:input path="ql.maql" class="form-control" type="text"
										placeholder="Nhập mã quản lí" required="required"/>
								</div>
							</div>
							</div>
							<div class = "col-sm-12 col-md-6">
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Mã nhân viên</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="manv" />
									<form:input class="form-control" type="text" path="manv"
										placeholder="Nhập mã nhân viên"  style="display: none" required="required"/>
									<input class="form-control" type="text" placeholder="${nhanvien_sua.manv}" readonly>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Họ và chữ lót</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="ho" />
									<form:input class="form-control" type="text" path="ho"
										placeholder="Nhập họ và chữ lót" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="ten" />
									<form:input class="form-control" type="text" path="ten"
										placeholder="Nhập tên" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Giới
									tính</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="gioitinh" />
									<form:select path="gioitinh" class="selectpicker form-control"
										style="width: 100%; height: 38px;" required="required">
										<form:option value="" label="-Vui lòng chọn 1-" />
										<form:options items="${gioiTinhs}" />
									</form:select>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Ngày
									sinh</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="ngaysinh" />
									<form:input class="form-control" path="ngaysinh"
										placeholder="Chọn ngày sinh" type="date" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Địa chỉ</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="diachi" />
									<form:input class="form-control" type="text" path="diachi"
										placeholder="Nhập địa chỉ" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
								<div class="col-sm-12 col-md-8">
									<form:errors path="cmnd" />
									<form:input path="cmnd" class="form-control" type="number" onkeydown="limit_cmnd(this);" onkeyup="limit_cmnd(this);"
										placeholder="Nhập chứng minh nhân dân" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Gmail
									</label>
								<div class="col-sm-12 col-md-8">
									<form:input path="acc.gmail" class="form-control" type="email"
										placeholder="Nhập Gmail" required="required"/>
<%-- 									<form:errors path="gmail" /> --%>
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">SDT</label>
								<div class="col-sm-12 col-md-8">
									<form:input path="acc.sdt" class="form-control" type="number" onkeydown="limit_sdt(this);" onkeyup="limit_sdt(this);"
										placeholder="Số điện thoại" required="required"/>
								</div>
							</div>
							</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-info">Cập nhật</button>
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
	
		<c:if test="${accountTonTai}">
		<script type="text/javascript">
			$(document).ready(function() {	
				show_error_account();
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
		$('#myTable').DataTable({
			scrollCollapse: true,
			autoWidth: false,
			responsive: true,
			order: [[1,"desc"]]
		});
			
		
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
		function show_error_account(content="Username") {
			Swal.fire({
				title: 'THẤT BẠI',
				text: content+" đã tồn tại!",
				icon: 'error',
			})
		}
		function limit_cmnd(element)
		{
		    var max_chars = 12;

		    if(element.value.length > max_chars) {
		        element.value = element.value.substr(0, max_chars);
		    }
		}
		function limit_sdt(element)
		{
		    var max_chars = 10;

		    if(element.value.length > max_chars) {
		        element.value = element.value.substr(0, max_chars);
		    }
		}
	</script>
</body>
</html>