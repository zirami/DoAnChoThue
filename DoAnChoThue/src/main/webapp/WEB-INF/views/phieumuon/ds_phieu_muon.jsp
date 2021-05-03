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
								<h4>Danh sách phiếu mượn</h4>
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
								</tr>
							</thead>
							<tbody>
								<c:forEach var="phieumuon" items="${listPhieuMuon}"
									varStatus="row">
									<tr>
<<<<<<< HEAD
										<td class="table-plus">${phieumuon.mapm}</td>
										<td>${phieumuon.nm.ten}</td>
										<td>${phieumuon.manv}</td>
										<td>${phieumuon.thoigianmuon}</td>
										<c:choose>
											<c:when test="${phieumuon.thoigiantra.isEmpty()}">
												<td>${phieumuon.thoigiantra}</td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
=======
										<td class="table-plus">${pm.mapm}</td>
										<td>${pm.manv}</td>
										<td>${pm.nm.manm}</td>
										<td>${pm.thoigianmuon}</td>
										<td>${pm.thoigiantra}</td>
										<td>${pm.ghichu}</td>
>>>>>>> 6743cad2a1fc8bcc2efe59342117a4819f554b31
										<c:choose>
											<c:when test="${phieumuon.ghichu.isEmpty()}">
												<td>${phieumuon.ghichu}</td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
										<!-- 										<td> -->
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
										<!-- 										</td> -->
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
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Thêm phiếu mượn</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="qlphieumuon" modelAttribute="phieumuon_moi"
							method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Mã
									phiếu mượn</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="mapm" /> --%>
									<form:input class="form-control" type="text" path="mapm"
										placeholder="Nhập mã phiếu mượn" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Người
									mượn</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="ho" /> --%>
									<form:input class="form-control" type="text" path="nm"
										placeholder="Nhập người mượn" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Nhân
									viên</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="ten" /> --%>
									<form:input class="form-control" type="text" path="manv"
										placeholder="Nhập nhân viên" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Thiết
									bị</label>
								<div class="col-sm-12 col-md-10">
									<div class="dropdown bootstrap-select show-tick form-control">
										<select class="selectpicker form-control" data-size="5"
											data-style="btn-outline-secondary" multiple=""
											data-max-options="3" tabindex="-98">
											<optgroup label="Hình ảnh">
												<option>Mustard</option>
												<option>Ketchup</option>
												<option>Relish</option>
											</optgroup>
											<optgroup label="Âm thanh">
												<option>Plain</option>
												<option>Steamed</option>
												<option>Toasted</option>
											</optgroup>
										</select>
										<!-- 							<button type="button" class="btn dropdown-toggle btn-outline-secondary" data-toggle="dropdown" role="combobox" aria-owns="bs-select-2" aria-haspopup="listbox" aria-expanded="false" title="Plain"><div class="filter-option"><div class="filter-option-inner"><div class="filter-option-inner-inner">Plain</div></div> </div></button><div class="dropdown-menu" style="max-height: 247px; overflow: hidden;"><div class="inner show" role="listbox" id="bs-select-2" tabindex="-1" aria-multiselectable="true" style="max-height: 247px; overflow-y: auto;"><ul class="dropdown-menu inner show" role="presentation" style="margin-top: 0px; margin-bottom: 0px;"><li class="dropdown-header optgroup-1"><span class="text">Condiments</span></li><li class="optgroup-1"><a role="option" class="opt dropdown-item" id="bs-select-2-1" tabindex="0" aria-selected="false" aria-setsize="6" aria-posinset="1"><span class=" bs-ok-default check-mark"></span><span class="text">Mustard</span></a></li><li class="optgroup-1"><a role="option" class="opt dropdown-item" id="bs-select-2-2" tabindex="0" aria-selected="false" aria-setsize="6" aria-posinset="2"><span class=" bs-ok-default check-mark"></span><span class="text">Ketchup</span></a></li><li class="optgroup-1"><a role="option" class="opt dropdown-item" id="bs-select-2-3" tabindex="0" aria-selected="false" aria-setsize="6" aria-posinset="3"><span class=" bs-ok-default check-mark"></span><span class="text">Relish</span></a></li><li class="dropdown-divider optgroup-1div"></li><li class="dropdown-header optgroup-2"><span class="text">Breads</span></li><li class="optgroup-2 selected"><a role="option" class="opt dropdown-item selected" id="bs-select-2-6" tabindex="0" aria-selected="true" aria-setsize="6" aria-posinset="4"><span class=" bs-ok-default check-mark"></span><span class="text">Plain</span></a></li><li class="optgroup-2"><a role="option" class="opt dropdown-item" id="bs-select-2-7" tabindex="0" aria-selected="false" aria-setsize="6" aria-posinset="5"><span class=" bs-ok-default check-mark"></span><span class="text">Steamed</span></a></li><li class="optgroup-2"><a role="option" class="opt dropdown-item" id="bs-select-2-8" tabindex="0" aria-selected="false" aria-setsize="6" aria-posinset="6"><span class=" bs-ok-default check-mark"></span><span class="text">Toasted</span></a></li></ul></div></div></div> -->
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Thời
									gian mượn</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="ngaysinh" /> --%>
									<form:input class="form-control" path="thoigianmuon"
										placeholder="Chọn thời gian mượn" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Thời
									gian trả</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="ngaysinh" /> --%>
									<form:input class="form-control" path="thoigiantra"
										placeholder="Chọn thời gian trả" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ghi chú</label>
								<div class="col-sm-12 col-md-10">
									<%-- 									<form:errors path="diachi" /> --%>
									<form:input class="form-control" type="text" path="ghichu"
										placeholder="Nhập ghi chú" />
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