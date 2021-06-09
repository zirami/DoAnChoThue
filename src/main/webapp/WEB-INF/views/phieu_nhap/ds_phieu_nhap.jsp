<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%
	session = request.getSession();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhóm 2 - Danh sách Phiếu Nhập</title>
<base href="${pageContext.servletContext.contextPath}/">
<%@include file="/common/loadcss.jsp"%>
<style type="text/css">
.new-row {
	background-color: lightblue
}
</style>
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
						<div class="col-md-6 col-sm-12 text-right">
							<a href="#modal_pn_them" class="btn btn-primary"
								data-toggle="modal" type="button">NHẬP THIẾT BỊ</a>
							<a href="#modal_pn_sua" hidden="hidden" data-toggle="modal"
								type="button" id="update_modal_btn">SỬA THIẾT BỊ</a>
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM THIẾT BỊ MỚI </a>
						</div>
					</div>
				</div>
				${notify}
				<c:if test="${test}">${test}</c:if>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<c:set var="choXacNhan" value="choXacNhan" />
						<c:set var="daXacNhan" value="daXacNhan" />
						<c:set var="huy" value="huy" />
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã Phiếu Nhập</th>
									<th>Ngày Nhập</th>
									<th>Nhà Cung Cấp</th>
									<th>Nhân Viên Nhập</th>
									<th>Tình Trạng</th>
									<th>Ghi chú</th>
									<th class="datatable-nosort">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pn" items="${listPhieuNhap}">
									<tr>
										<td class="table-plus mapn">${pn.mapn}</td>
										<td>${pn.thoigiannhap}</td>
										<td>${pn.ncc.ten}</td>
										<c:set var="hoten"
											value="${pn.nhanvien.ho} ${pn.nhanvien.ten}" />
										<td>${hoten}</td>
										<!-- Tình trạng -->
										<td>
											<c:choose>
												<c:when test="${pn.trangthai.equals(choXacNhan)}">
													<label>Chờ Xác Nhận</label>
												</c:when>
												<c:when test="${pn.trangthai.equals(daXacNhan)}">
													<label>Đã Xác Nhận</label>
												</c:when>
												<c:otherwise>
													<label>HUỶ</label>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<!-- Ghi chú -->
											<c:choose>
												<c:when test="${pn.ghichu.isEmpty() || pn.ghichu.isBlank()}">
													<label>-</label>
												</c:when>
												<c:otherwise>
													<label>${pn.ghichu}</label>
												</c:otherwise>
											</c:choose>
										</td>
										<!-- Hành động -->
										<td>
											<div class="row clearfix">
												<c:choose>
													<c:when test="${pn.trangthai.equals(choXacNhan)}">
														<div class="col-3">
															<a class="material-icons" data-toggle="tooltip"
																href="phieu-nhap/edit/${pn.mapn}" data-placement="top"
																title="Sửa" style="color: blue">edit</a>
														</div>
														<div class="col-6 text-center">
															<!-- XOÁ -->
															<a class="delete_btn" type="button">
																<i class="material-icons" data-toggle="tooltip"
																	data-placement="top" title="Xoá" style="color: red">delete</i>
															</a>
														</div>
													</c:when>
													<c:otherwise>
														<div class="col-6 text-center">-</div>
													</c:otherwise>
												</c:choose>
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
	<!--====================================THÊM PHIẾU NHẬP==================================== -->
	<div class="modal fade bs-example-modal-lg" id="modal_pn_them"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<form action="phieu-nhap" method="post" class="modal-content">
				<div class="modal-body">
					<div class="invoice-header text-center">
						<h4 class="text-blue h4">THÊM PHIẾU NHẬP</h4>
					</div>
					<!-- Nhập thông tin cơ bản phiếu nhập -->
					<div class=" row">
						<div class="col form-group">
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Mã phiếu
									nhập</label>
								<div class="col-sm-4 col-md-8">
									<input class="form-control" type="text" name="mapn"
										placeholder="Nhập mã phiếu mượn" />
								</div>
							</div>
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Ngày
									nhập: </label>
								<div class="col-sm-4 col-md-8">
									<input name="thoigiannhap" class="form-control" type="date"
										value="${today}" readonly="readonly" />
								</div>
							</div>
						</div>
						<div class=" col form-group">
							<div class="row">
								<label class="col-sm-6 col-md-4 col-form-label">Nhân
									Viên tạo:</label>
								<strong class="col-sm-4 col-md-8 text-left">${nv.manv}</strong>
								<input type="hidden" name="nhanvien.manv" value="${nv.manv}" />
							</div>
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Ghi chú:</label>
								<input class="form-control col-sm-4 col-md-8" name="ghichu"
									placeholder="Nhập ghi chú" />
							</div>
						</div>
					</div>
					<hr>
					<h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT BỊ
						NHẬP</h5>
					<!-- Nhập thông tin cơ bản phiếu nhập -->
					<table class="table dsThietbi_table">
						<thead>
							<tr>
								<th>Thiết bị</th>
								<th>Số lượng</th>
								<th>Đơn giá</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr id="hidden-row" hidden=true>
								<td>
									<select class="form-control" name="matb">
										<option value="">-Chọn thiết bị-</option>
										<c:forEach var="tb" items="${listThietbi}">
											<option value="${tb.matb}">${tb.ten}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="number" min="1" name="soluongnhap"
										class=" form-control" />
								</td>
								<td>
									<input type="number" min="0" step="0.01" name="dongia"
										class=" form-control" />
								</td>
								<td>
									<a class="material-icons text-info btn-close-item  text-right"
										type="button"> delete_sweep </a>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row form-group them-tb-nhap">
						<button type='button' class="form-control text-info ">
							<span class="material-icons text-info">add_circle_outline</span>
							Thêm thiết bị nhập
						</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
					<input type="hidden" name="trangthai" />
					<button type="submit" class="btn btn-primary luu-btn">Lưu
						tạm</button>
					<button type="submit" class="btn btn-success them-btn">Thêm</button>
				</div>
			</form>
		</div>
	</div>
	<!--====================================SỬA PHIẾU NHẬP==================================== -->
	<div class="modal fade bs-example-modal-lg" id="modal_pn_sua"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<form:form action="phieu-nhap/update" method="post"
				class="modal-content" modelAttribute="phieunhap_sua">
				<div class="modal-body">
					<div class="invoice-header text-center">
						<h4 class="text-blue h4">SỬA PHIẾU NHẬP</h4>
					</div>
					<!-- Nhập thông tin cơ bản phiếu nhập -->
					<div class=" row">
						<div class="col form-group">
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Mã phiếu
									nhập</label>
								<div class="col-sm-4 col-md-8">
									<form:input class="form-control" type="text" path="mapn"
										placeholder="Nhập mã phiếu mượn" readonly="true" />
								</div>
							</div>
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Ngày
									nhập: </label>
								<div class="col-sm-4 col-md-8">
									<form:input path="thoigiannhap" class="form-control"
										type="date" value="${today}" />
								</div>
							</div>
						</div>
						<div class=" col form-group">
							<div class="row">
								<label class="col-sm-6 col-md-4 col-form-label">Nhân
									Viên chỉnh sửa:</label>
								<strong class="col-sm-4 col-md-8 text-left">${nv.manv}</strong>
								<input type="hidden" name="nhanvien.manv" value="${nv.manv}" />
							</div>
							<div class="row">
								<label class="col-sm-4 col-md-4 col-form-label">Ghi chú:</label>
								<form:textarea class="col-sm-4 col-md-8" type="text"
									style="border-color: lightgrey;border-radius: 3px;"
									path="ghichu" rows="1" placeholder="Nhập ghi chú" />
							</div>
						</div>
					</div>
					<hr>
					<h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT BỊ
						NHẬP</h5>
					<!-- Nhập thông tin cơ bản phiếu nhập -->
					<table class="table dsThietbi_table">
						<thead>
							<tr>
								<th>Thiết bị</th>
								<th>Số lượng</th>
								<th>Đơn giá</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${phieunhap_sua.ct_phieunhaps}" var="ct_pn">
								<tr>
									<td>
										<select class="form-control" name="matb" >
											<c:set var="matb" value="${ct_pn.thietbi.matb}"/>
											<option value="${ct_pn.thietbi.matb}" label="${ct_pn.thietbi.ten}"/>
											<c:forEach var="tb" items="${listThietbi}">
												<c:if test="${matb.equals(tb.matb) == false }">
													<option value="${tb.matb}">${tb.ten}</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
									<td>
										<input type="number" min="1" name="soluongnhap" value="${ct_pn.soluongnhap}"
											class=" form-control" />
									</td>
									<td>
										<input type="number" min="0" step="0.01" name="dongia" value="${ct_pn.dongia}"
											class=" form-control" />
									</td>
									<td>
										<a class="material-icons text-info btn-close-item  text-right"
											type="button"> delete_sweep </a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row form-group them-tb-nhap">
						<button type='button' class="form-control text-info ">
							<span class="material-icons text-info">add_circle_outline</span>
							Thêm thiết bị nhập
						</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Đóng</button>
					<input type="hidden" name="trangthai" />
					<button type="submit" class="btn btn-primary luu-btn">Lưu
						tạm</button>
					<button type="submit" class="btn btn-success them-btn">Xác
						Nhận</button>
				</div>
			</form:form>
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
	<!-- DÙNG ĐỂ SHOW FORM DELETE -->
	<script type="text/javascript">
		$(document).ready(function() {
			//NẾU CLICK NÚT XOÁ
			$('#myTable').on('click','.delete_btn',function(){
				let ten = $(this).parents('tr').find("td.mapn").text();
				let currentRow = $(this).parents('tr')
				
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
						$.ajax({
			                type: "GET",
			                url: "${pageContext.servletContext.contextPath}/phieu-nhap/delete/" + ten,
			              
			                success: function (data)
			        		{
			                	if(data){
			                		currentRow.remove()
									show_success('Xoá')
			                	}
			                	else show_error('Xoá')		                	
			        		}
						
			            });
						
					}
				})
			}) 			
		});
	</script>
	<!-- HIỂN THỊ THÔNG BÁO THÊM THÀNH CÔNG / THẤT BẠI -->
	<script type="text/javascript">
		$(document).ready(function() {
			if(${notify}){
				show_success();
			}
			else show_error();
		})
	</script>
	<script type="text/javascript">
		//LOAD TABLE 
		$('.data-table').DataTable({
			scrollCollapse: true,
			autoWidth: false,
			responsive: true,
			columnDefs: [{
				targets: "datatable-nosort",
				orderable: false,
			}],
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"language": {
				"info": "_START_-_END_ of _TOTAL_ entries",
				searchPlaceholder: "Search...",
				paginate: {
					next: '<i class="ion-chevron-right"></i>',
					previous: '<i class="ion-chevron-left"></i>'  
				}
			},
		});
		
		
		
		
		//CLICK THÊM DÒNG MỚI
		
		$('.them-tb-nhap').on('click', function(){	
			console.log($(this).parent().find('tbody'))
			let new_row = document.createElement('tr');
			new_row.innerHTML = $('#hidden-row').html()
			$(this).parent().find('tbody').append(new_row);
			console.log(1)
			
		})
		
		//ClICK XOÁ 1 DÒNG THIẾT BỊ
		$('.dsThietbi_table').on('click', '.btn-close-item', function(){
			$(this).parents('tr').remove();
		})
		
		//CLICK NÚT THÊM PHIẾU NHẬP
		$('.them-btn').on('click', function(){		
			let trangthai = $(this).parent().find("input[name='trangthai']")
			trangthai.val("daXacNhan")
			$('#hidden-row').remove()
			console.log($('#trangthai').val())
		})
		
		//CLICK LƯU PHIẾU NHẬP
		$('.luu-btn').on('click', function(){
			let trangthai = $(this).parent().find("input[name='trangthai']")
			trangthai.val("choXacNhan")
			$('#hidden-row').remove()
			console.log($('#trangthai').val())
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