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
<title>Nhóm 2 - Danh sách Phiếu Thanh Lý</title>
<base href="${pageContext.servletContext.contextPath}/">
<%@include file="/common/loadcss.jsp"%>
<style type="text/css">
.new-row {
	background-color: lightblue
}
</style>
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
								<h3 class="text-purple">Danh sách phiếu nhập</h3>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="index.html">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">Quản lý phiếu nhập</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<div class="col-md-6 col-sm-12 text-right">
							<a href="#modal_pn_them" class="btn btn-info" data-toggle="modal"
								type="button">NHẬP THIẾT BỊ</a>
							<a href="#modal_pn_sua" hidden="hidden" data-toggle="modal"
								type="button" id="update_modal_btn"></a>
						</div>
					</div>
				</div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<c:set var="choXacNhan" value="choXacNhan" />
						<c:set var="daXacNhan" value="daXacNhan" />
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã Phiếu Nhập</th>
									<th>Ngày Nhập</th>
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
										<c:choose>
											<c:when
												test="${ptl.ghichu.isEmpty() || ptl.ghichu.isBlank()}">
												<label>-</label>
											</c:when>
											<c:otherwise>
												<label>${ptl.ghichu}</label>
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
														<div class="col-3">
															<a class="material-icons" data-toggle="tooltip"
																href="phieu-nhap/edit/${pn.mapn}" data-placement="top"
																title="Xem chi tiết" style="color: blue">info</a>
														</div>
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
										value="${newID}" readonly="readonly" />
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
									Viên xử lý:</label>
								<input class="col-sm-4 col-md-8 form-control" type="text"
									name="nhanvien.manv" value="${nv.manv}" readonly="readonly" />
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
						</tbody>
						<tfoot>
							<tr>
								<th></th>
								<th></th>
								<th class="tongcong"></th>
							</tr>
							<tr id="hidden-row" hidden=true>
								<td>
									<select class="form-control" name="matb">
										<c:forEach var="tb" items="${listThietbi}">
											<option value="${tb.matb}">${tb.ten}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="number" min="1" name="soluongnhap"
										class=" form-control"
										onchange="tinhtong($(this).parents('tbody'))" required />
								</td>
								<td>
									<input type="number" min="0" step="0.01" name="dongia"
										class=" form-control"
										onchange="tinhtong($(this).parents('tbody'))" required />
								</td>
								<td>
									<a class="material-icons text-info btn-close-item  text-right"
										type="button"> delete_sweep </a>
								</td>
							</tr>
						</tfoot>
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
					<input type="submit" hidden="hidden" />
					<button type="button" class="btn btn-success them-btn">Thêm</button>
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
				<c:if test="${form_edit}">
					<%@include file="/WEB-INF/views/phieu_nhap/form_edit_pn.jsp"%>
				</c:if>
				<c:if test="${form_info}">
					<%@include file="/WEB-INF/views/phieu_nhap/form_info_pn.jsp"%>
				</c:if>
			</form:form>
		</div>
	</div>
	<!-- js -->
	<%@include file="/common/footer.jsp"%>
	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit || form_info}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
				tinhtong($('#modal_pn_sua').find('tbody'))
				
			});
		</script>
	</c:if>
	<!-- Dùng để tính tổng tiền chi tiết phiếu -->
	<script type="text/javascript">
		function tinhtong(e){
			let soluong = $(this).parents('tr').find("input[name='soluongnhap']").val()
			let dongia = $(this).parents('tr').find("input[name='dongia']").val()
			if (dongia < 0 || soluong < 0) return false;
			
			
			let tongcong = 0;
			console.log(e)
			e.find('tr').each(function(index, tr) {
			      let soluong = $(this).find("input[name='soluongnhap']").val()
			      let dongia = $(this).find("input[name='dongia']").val()
			      
			      tongcong += soluong * dongia
			});
			
			$('.tongcong').text('Tổng cộng: ' + tongcong + ' VNĐ');
		}
		
		$("a[href='#modal_pn_them']").on('click',function(){
			tinhtong($('#modal_pn_them').find('tbody'))
		})
		
	</script>
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
					confirmButtonColor: '#28a745',
					cancelButtonColor: '#d33',
					reverseButtons: true,
					cancelButtonText: 'Huỷ',
					confirmButtonText: 'Xoá'
				}).then((result) => {
					//Nếu nút đồng ý được nhấn
					if (result.isConfirmed) {
						$.ajax({
			                type: "GET",
			                url: "${pageContext.servletContext.contextPath}/phieu-nhap/delete/" + ten,
			              
			                success: function (data){
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
			"aaSorting": [],
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
			Swal.fire({
				title: 'Xác nhận hoàn thành phiếu?',
				text: "Dữ liệu sau khi xác nhận sẽ không thể chỉnh sửa!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#28a745',
				cancelButtonColor: '#d33',
				cancelButtonText: 'Huỷ',
				confirmButtonText: 'Xác Nhận',
				reverseButtons: true
			}).then((result) => {
				//Nếu nút đồng ý được nhấn
				if (result.isConfirmed) {
					trangthai.val("duocXacNhan")
					$('#hidden-row').remove()	
					$(this).parent().find("input[type='submit']").click();
					console.log(trangthai.val())
				}
			})
		})
		
		//CLICK LƯU PHIẾU NHẬP
		$('.luu-btn').on('click', function(){
			let trangthai = $(this).parent().find("input[name='trangthai']")
			trangthai.val("choXacNhan")
			$('#hidden-row').remove()
			console.log(trangthai.val())
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
	<!-- Lấy số lượng tối đa của thiết bị -->
	<select id="listThietbi" hidden="true">
		<c:forEach items="${listThietbi}" var="tb">
			<option value="${tb.matb}">${tb.matb}</option>
		</c:forEach>
	</select>
</body>
</html>