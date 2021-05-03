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
								<h4>Danh sách người mượn</h4>
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
									<th>Mã nhân viên</th>
									<th>Mã người mượn</th>
									<th>Thời gian mượn</th>
									<th>Thời gian trả</th>
									<th>Ghi chú</th>
								
								</tr>		
							</thead>
							<tbody>
								<c:forEach var="pm" items="${listPhieuMuon}"
									varStatus="row">
									<tr>
										<td class="table-plus">${pm.mapm}</td>
										<td>${pm.manv}</td>
										<td>${pm.manm}</td>
										<td>${pm.thoigianmuon}</td>
										<td>${pm.thoigiantra}</td>
										<td>${pm.ghichu}</td>
										<c:choose>
											<c:when test="${pm.ghichu.isEmpty()}">
												<td>${pm.ghichu}</td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
										<td>
<!-- 											<div class="row clearfix btn-list"> -->
<!-- 																								<div class=""> -->
<%-- 												<form action="qlnguoimuon/edit/${nguoimuon.manm}"> --%>
<!-- 													<button class="btn btn-primary" type="submit" -->
<!-- 														data-toggle="tooltip" data-placement="top" title="Sửa"> -->
<!-- 														<i class="material-icons">edit</i> -->
<!-- 													</button> -->
<!-- 													 -->
<!-- 												</form> -->

<!-- 												<form action="qlnguoimuon/delete" method="post"> -->
<!-- 													Dùng để hiển thị tên lên form -->
<%-- 													<input type="hidden" name="ten" value="${nguoimuon.ten}" /> --%>

<!-- 													Dùng để gửi về controller -->
<%-- 													<input type="hidden" name="manm" value="${nguoimuon.manm}" /> --%>
<!-- 													<button type="submit" style="display: none" -->
<!-- 														class="submit_del_btn"></button> -->
<!-- 												</form> -->
<!-- 												<button class="btn btn-danger delete_btn" -->
<!-- 													data-toggle="tooltip" data-placement="top" title="Xoá" -->
<!-- 													type="button"> -->
<!-- 													<i class="material-icons">delete</i> -->
<!-- 												</button> -->
<!-- 											</div> 											</div> -->
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
<!-- 	<div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg" -->
<!-- 		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" -->
<!-- 		aria-hidden="true"> -->
<!-- 		<div class="modal-dialog modal-lg modal-dialog-centered"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-body"> -->
<!-- 					Default Basic Forms Start -->
<!-- 					<div class="pd-20 card-box mb-30"> -->
<!-- 						<div class="clearfix"> -->
<!-- 							<div class="pull-left"> -->
<!-- 								<h4 class="text-blue h4">Thêm người mượn</h4> -->
<!-- 								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 						<form:form action="qlnguoimuon" modelAttribute="nguoimuon_moi" --%>
<%-- 							method="post"> --%>
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Mã -->
<!-- 									người mượn</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="manm" /> --%>
<%-- 									<form:input class="form-control" type="text" path="manm" --%>
<%-- 										placeholder="Nhập mã người mượn" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Họ và -->
<!-- 									chữ lót</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ho" /> --%>
<%-- 									<form:input class="form-control" type="text" path="ho" --%>
<%-- 										placeholder="Nhập họ và chữ lót" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Tên</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ten" /> --%>
<%-- 									<form:input class="form-control" type="text" path="ten" --%>
<%-- 										placeholder="Nhập tên" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Giới -->
<!-- 									tính</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="gioitinh" /> --%>
<%-- 									<form:select path="gioitinh" class="selectpicker form-control" --%>
<%-- 										style="width: 100%; height: 38px;"> --%>
<%-- 										<form:option value="" label="-Vui lòng chọn 1-" /> --%>
<%-- 										<form:options items="${gioiTinhs}" /> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Ngày -->
<!-- 									sinh</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ngaysinh" /> --%>
<%-- 									<form:input class="form-control" path="ngaysinh" --%>
<%-- 										placeholder="Chọn ngày sinh" type="date" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Địa chỉ</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="diachi" /> --%>
<%-- 									<form:input class="form-control" type="text" path="diachi" --%>
<%-- 										placeholder="Nhập địa chỉ" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">CMND</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="cmnd" /> --%>
<%-- 									<form:input path="cmnd" class="form-control" type="text" --%>
<%-- 										placeholder="Nhập chứng minh nhân dân" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:input class="form-control" type="text" path="ghichu" --%>
<%-- 										placeholder="Nhập ghi chú" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<div class="modal-footer"> -->
<!-- 								<button type="button" class="btn btn-secondary" -->
<!-- 									data-dismiss="modal">Đóng</button> -->
<!-- 								<button type="submit" class="btn btn-success">Thêm</button> -->
<!-- 							</div> -->
<%-- 						</form:form> --%>
<!-- 					</div> -->
<!-- 					Default Basic Forms Start -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->


<!-- 	<button hidden="true" id="update_modal_btn" data-toggle="modal" -->
<!-- 		data-target="#bd-edit-modal-lg"></button> -->
	<!--======================================================== Dùng để update ============================================================ -->

<!-- 	<div class="modal fade bs-example-modal-lg" id="bd-edit-modal-lg" -->
<!-- 		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" -->
<!-- 		aria-hidden="true"> -->
<!-- 		<div class="modal-dialog modal-lg modal-dialog-centered"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-body"> -->
<!-- 										Default Basic Forms Start -->
<!-- 					<div class="pd-20 card-box mb-30"> -->
<!-- 						<div class="clearfix"> -->
<!-- 							<div class="pull-left"> -->
<!-- 								<h4 class="text-blue h4">Sửa thông tin người mượn</h4> -->
<!-- 								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 						<form:form action="qlnguoimuon/update" --%>
<%-- 							modelAttribute="nguoimuon_sua" method="post"> --%>
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Mã -->
<!-- 									người mượn</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="manm" /> --%>
<%-- 									<form:input class="form-control" type="text" path="manm" --%>
<%-- 										placeholder="Nhập mã người mượn" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Họ và -->
<!-- 									chữ lót</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ho" /> --%>
<%-- 									<form:input class="form-control" type="text" path="ho" --%>
<%-- 										placeholder="Nhập họ và chữ lót" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Tên</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ten" /> --%>
<%-- 									<form:input class="form-control" type="text" path="ten" --%>
<%-- 										placeholder="Nhập tên" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Giới -->
<!-- 									tính</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="gioitinh" /> --%>
<%-- 										<form:select path="gioitinh" items="${gioiTinhs}" --%>
<%-- 										class="selectpicker form-control" --%>
<%-- 										style="width: 100%; height: 38px;"> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Ngày -->
<!-- 									sinh</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="ngaysinh" /> --%>
<%-- 									<form:input class="form-control" path="ngaysinh" --%>
<%-- 										placeholder="Chọn ngày sinh" type="date" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Địa chỉ</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="diachi" /> --%>
<%-- 									<form:input class="form-control" type="text" path="diachi" --%>
<%-- 										placeholder="Nhập địa chỉ" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">CMND</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:errors path="cmnd" /> --%>
<%-- 									<form:input path="cmnd" class="form-control" type="text" --%>
<%-- 										placeholder="Nhập chứng minh nhân dân" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group row"> -->
<!-- 								<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label> -->
<!-- 								<div class="col-sm-12 col-md-10"> -->
<%-- 									<form:input class="form-control" type="text" path="ghichu" --%>
<%-- 										placeholder="Nhập ghi chú" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->

<!-- 							<div class="modal-footer"> -->
<!-- 								<button type="button" class="btn btn-secondary" -->
<!-- 									data-dismiss="modal">Đóng</button> -->
<!-- 								<button type="submit" class="btn btn-success">Sửa</button> -->
<!-- 							</div> -->
<%-- 						</form:form> --%>

<!-- 					</div> -->
<!-- 										Default Basic Forms Start -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->



<!-- 	<!-- js --> -->
<%-- 	<%@include file="/common/footer.jsp"%> --%>

<!-- 	<!-- DÙNG ĐỂ SHOW FORM EDIT --> -->
<%-- 	<c:if test="${form_edit}"> --%>
<!-- 		<script type="text/javascript"> -->
// 			$(document).ready(function() {
// 				$('#update_modal_btn').click();
// 				console.log("Hiển thị edit form")
// 			});
<!-- 		</script> -->
<%-- 	</c:if> --%>

<!-- 	<!-- HIỂN THỊ THÔNG BÁO KHI SAI ĐỊNH DẠNG DỮ LIỆU LÚC THÊM --> -->
<%-- 	<c:if test="${them_saidinhdang}"> --%>
<!-- 		<script type="text/javascript"> -->
// 			$(document).ready(function() {
// 				$('#insert_btn').click();
// 				console.log("thêm sai định dạng")

// 			});
<!-- 		</script> -->
<%-- 	</c:if> --%>

<!-- 	<!-- HIỂN THỊ THÔNG BÁO THÊM THÀNH CÔNG / THẤT BẠI --> -->
<%-- 	<c:if test="${insert || update || delete}"> --%>
<!-- 		<script type="text/javascript"> -->
// 			$(document).ready(function() {
// 				show_success();

// 			});
<!-- 		</script> -->
<%-- 	</c:if> --%>
<!-- 	<!-- HIỂN THỊ THÔNG BÁO CÓ LỖI XẢY RA--> -->
<%-- 	<c:if test="${insert == false || update == false || delete == false}"> --%>
<!-- 		<script type="text/javascript"> -->
// 			$(document).ready(function() {	
// 				show_error();
// 			});
<!-- 		</script> -->
<%-- 	</c:if> --%>


<!-- 	<script type="text/javascript"> -->
// 		//LOAD TABLE 
// 		$('#myTable').DataTable();
			
		
// 		//NẾU CLICK NÚT XOÁ
// 		$('#myTable').on('click','.delete_btn',function(){
// 			let ten = $(this).parent().find("input[name='ten']").val();
// 			let delete_btn = $(this).parent().find('.submit_del_btn');
// 			Swal.fire({
// 				title: 'Xoá ' + ten + ' ?',
// 				text: "Dữ liệu không thể khôi phục sau thao tác này!",
// 				icon: 'warning',
// 				showCancelButton: true,
// 				confirmButtonColor: '#3085d6',
// 				cancelButtonColor: '#d33',
// 				confirmButtonText: 'Xoá',
// 				cancelButtonText: 'Huỷ'
// 			}).then((result) => {
// 				//Nếu nút đồng ý được nhấn
// 				if (result.isConfirmed) {
// 					delete_btn.click();
// 				}
// 			})
// 		}) 
		
		
// 		//THÔNG BÁO THÀNH CÔNG
// 		function show_success(content="Thao tác") {
// 			Swal.fire({
// 				title: 'THÀNH CÔNG',
// 				text: content+" thành công!",
// 				icon: 'success',
// 			})
// 		}
		
// 		//THÔNG BÁO LỖI
// 		function show_error(content="Thao tác") {
// 			Swal.fire({
// 				title: 'THẤT BẠI',
// 				text: content+" thất bại!",
// 				icon: 'error',
// 			})
// 		}
<!-- 	</script> -->
</body>
</html>