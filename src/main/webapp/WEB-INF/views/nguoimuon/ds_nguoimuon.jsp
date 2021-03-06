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
								<h3 class="text-purple">Danh sách người mượn</h3>
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
							<a href="qlnguoimuon" class="btn btn-info"
								type="button">Làm mới </a> <a href="#" id="insert_btn"
								class="btn btn-info" data-toggle="modal"
								data-target="#bd-example-modal-lg" type="button">Thêm người
								mượn </a>
						</div>
					</div>
				</div>

				<!-- Hiển thị danh sách người mượn -->
				<div class="card-box mb-30">
					${message}
					<hr>
					<div class="pb-20">
						<table class="table hover nowrap" id="myTable">
							<thead class="table-info">
								<tr>
									<th class="table-plus datatable-nosort">Mã NM</th>
									<th>Họ và tên</th>
									<!-- 									<th>Giới tính</th> -->
									<!-- 									<th>Ngày sinh</th> -->
									<th>Địa chỉ</th>
									<th>CMND</th>
									<th>GMAIL</th>
									<th>SDT</th>
									<th>Loại người mượn</th>
									<!-- 									<th>Ghi chú</th> -->
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="nguoimuon" items="${listNguoiMuon}"
									varStatus="row">
									<tr>

										<td class="table-plus">${nguoimuon.manm}</td>
										<td><p style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${nguoimuon.ho} ${nguoimuon.ten}</p></td>
										<%-- 										<td>${nguoimuon.gioitinh}</td> --%>
										<%-- 										<td>${nguoimuon.ngaysinh}</td> --%>
										<td><p style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; ">${nguoimuon.diachi}</p></td>
										<td>${nguoimuon.cmnd}</td>
										<td><p
												style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${nguoimuon.gmail}</p></td>
										<td>${nguoimuon.sdt}</td>
										<td>${nguoimuon.loai}</td>
										<%-- 										<c:choose> --%>
										<%-- 											<c:when test="${nguoimuon.ghichu.isEmpty()==false}"> --%>
										<%-- 												<td><p style="width: 100px;overflow: hidden;text-overflow: ellipsis; white-space: nowrap;">${nguoimuon.ghichu}</p> --%>
										<!-- 												</td> -->
										<%-- 											</c:when> --%>
										<%-- 											<c:otherwise> --%>
										<!-- 												<td> - </td> -->
										<%-- 											</c:otherwise> --%>
										<%-- 										</c:choose> --%>
										<td>
											<div class="row clearfix btn-list">
												<!-- 												<div class=""> -->
												<form action="qlnguoimuon/edit/${nguoimuon.manm}">
													<button class="btn btn-info bg-edit " type="submit"
														data-toggle="tooltip" data-placement="top" title="Sửa">
														<i class="material-icons">edit</i>
													</button>
													<!--  -->
												</form>

												<form action="qlnguoimuon/delete" method="post">
													<!-- Dùng để hiển thị tên lên form -->
													<input type="hidden" name="ten" value="${nguoimuon.ten}" />

													<!-- Dùng để gửi về controller -->
													<input type="hidden" name="manm" value="${nguoimuon.manm}" />
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
	<!-- 	======================   Thêm Người mượn ============================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Thêm người mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="qlnguoimuon" modelAttribute="nguoimuon_moi"
							method="post">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="manm" />
<%-- 											<input class="form-control" value="${maNguoiMuon}" --%>
<!-- 												readonly /> -->
											<form:input class="form-control" type="text" path="manm"
												value="${maNguoiMuon}" placeholder="Nhập mã người mượn"
												required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Họ và
											chữ lót</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ho" />
											<form:input class="form-control" type="text" path="ho"
												placeholder="Nhập họ và chữ lót" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ten" />
											<form:input class="form-control" type="text" path="ten"
												placeholder="Nhập tên" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Giới
											tính</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gioitinh" />
											<form:select path="gioitinh"
												class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${gioiTinhs}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ngày
											sinh</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ngaysinh" />
											<form:input class="form-control" path="ngaysinh"
												placeholder="Chọn ngày sinh" type="date" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">

									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Địa
											chỉ</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="diachi" />
											<form:input class="form-control" type="text" path="diachi"
												placeholder="Nhập địa chỉ" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="cmnd" />
											<form:input path="cmnd" class="form-control" type="number" onkeydown="limit_cmnd(this);" onkeyup="limit_cmnd(this);"
												placeholder="Nhập chứng minh nhân dân" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Email</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gmail" />
											<form:input path="gmail" class="form-control" type="email"
												placeholder="Nhập email" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Số
											điện thoại</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="sdt" />
											<form:input path="sdt" class="form-control" type="number" onkeydown="limit_sdt(this);" onkeyup="limit_sdt(this);"
												placeholder="Nhập số điện thoại" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="loai" />
											<form:select path="loai" class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${loais}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
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
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Cập nhật người mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="qlnguoimuon/update" modelAttribute="nguoimuon_sua"
							method="post">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="manm" />
											<input class="form-control" value="${nguoimuon_sua.manm}"
												readonly />
											<form:input class="form-control" type="text" path="manm"  placeholder="Nhập mã người mượn"
												required="required" style="display: none" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Họ và
											chữ lót</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ho" />
											<form:input class="form-control" type="text" path="ho"
												placeholder="Nhập họ và chữ lót" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ten" />
											<form:input class="form-control" type="text" path="ten"
												placeholder="Nhập tên" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Giới
											tính</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gioitinh" />
											<form:select path="gioitinh"
												class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${gioiTinhs}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ngày
											sinh</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ngaysinh" />
											<form:input class="form-control" path="ngaysinh"
												placeholder="Chọn ngày sinh" type="date" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">

									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Địa
											chỉ</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="diachi" />
											<form:input class="form-control" type="text" path="diachi"
												placeholder="Nhập địa chỉ" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="cmnd" />
											<form:input path="cmnd" class="form-control" type="number" onkeydown="limit_cmnd(this);" onkeyup="limit_cmnd(this);"
												placeholder="Nhập chứng minh nhân dân" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Email</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gmail" />
											<form:input path="gmail" class="form-control" type="email"
												placeholder="Nhập email" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Số
											điện thoại</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="sdt" />
											<form:input path="sdt" class="form-control" type="number" onkeydown="limit_sdt(this);" onkeyup="limit_sdt(this);"
												placeholder="Nhập số điện thoại" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="loai" />
											<form:select path="loai" class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${loais}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
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
		$('#myTable').DataTable({
			scrollCollapse: true,
			autoWidth: false,
			responsive: true,
			order: [[0,"desc"]],
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Tất cả"]],
			"language": {
				"decimal":        "",
			    "emptyTable":     "Không có dữ liệu",
			    "info":           "Đang hiện _START_ đến _END_ của _TOTAL_ dòng",
			    "infoEmpty":      "Đang hiện 0 đến 0 của 0 dòng",
			    "infoFiltered":   "(đã lọc từ _MAX_ dòng)",
			    "thousands":      ",",
			    "lengthMenu":     "Hiện _MENU_ dòng",
			    "loadingRecords": "Đang tải...",
			    "processing":     "Đang xử lý...",
			    "search":         "Tìm kiếm:",
			    "zeroRecords":    "Không có kết quả nào phù hợp",
				paginate: {
					next: '<i class="ion-chevron-right"></i>',
					previous: '<i class="ion-chevron-left"></i>'  
				}
			}
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