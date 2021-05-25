<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>
<style type="text/css">
h4 {
	color: red;
}
</style>
<%@include file="/common/loadcss.jsp"%>
</head>
<body id="body">

	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>


	<div class="mobile-menu-overlay"></div>
	<!-- 	Popup -->
	<!-- 	================================ Hiển thị danh sách Phiếu Mượn ================================ -->
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
							<a href="#" id="insert_btn" class="btn bg-purple text-yellow"
								data-toggle="modal" data-target="#bd-insert-modal-lg"
								type="button">Thêm phiếu mượn </a>
						</div>
					</div>
				</div>
				<!-- Hiển thị danh sách người mượn -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20">
						<table
							class="table hover data-table-export nowrap dataTable no-footer collapsed"
							id="myTable" role="grid">
							<thead class="table-info bg-table">
								<tr>
									<th class="table-plus datatable-nosort">Mã phiếu mượn</th>
									<th>Người mượn</th>
									<th>Mã nhân viên</th>
									<th>Thời gian mượn</th>
									<th>Thời gian trả</th>
									<th>Ghi chú</th>
									<th class="pull-right">Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="phieumuon" items="${listPhieuMuon}"
									varStatus="row">
									<tr>
										<td class="table-plus">${phieumuon.mapm}</td>
										<td>${phieumuon.nm.ten}</td>
										<td>${phieumuon.manv}</td>
										<td>${phieumuon.thoigianmuon}</td>
										<td>${phieumuon.thoigiantra}</td>
										<td>${phieumuon.ghichu }</td>
										<td class="pull-right">
											<div class="row clearfix btn-list">
												<form action="phieumuon/edit/${phieumuon.mapm}">
													<button class="btn btn-info bg-purple" type="submit"
														data-toggle="tooltip" data-placement="top" title="Sửa">
														<span class="material-icons ">edit</span>
													</button>
												</form>
												<form action="phieumuon/delete" method="post">
													<!-- 														Dùng để hiển thị tên lên form  -->
													<input type="hidden" name="ten" value="${phieumuon.nm.ten}" />
													<!-- 														Dùng để gửi về controller  -->
													<input type="hidden" name="mapm" value="${phieumuon.mapm}" />
													<button type="submit" style="display: none"
														class="submit_del_btn"></button>
												</form>
												<button class="btn btn-danger delete_btn bg-red"
													data-toggle="tooltip" data-placement="top" title="Xoá"
													type="button">
													<span class="material-icons delete_btn" >delete</span>
												</button>
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
	<!-- 	======================   Thêm phiếu mượn ============================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-insert-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Thêm phiếu mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">

						<form:form action="phieumuon" modelAttribute="phieumuon_moi"
							method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="mapm" />
											<form:input class="form-control" type="text" path="mapm"
												placeholder="Nhập mã phiếu mượn" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Người
											mượn</label>
										<div class="col-sm-4 col-md-8">
											<!-- 											Đối tượng này dễ sai khi không .manm -->
											<form:errors path="nm" />
											<form:select class="selectpicker form-control" path="nm.manm"
												style="width: 100%;height: 38px;">
												<form:option value="" label="Chọn người mượn"></form:option>
												<form:options items="${listNguoiMuons}" itemValue="manm"
													itemLabel="ten"></form:options>
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="manv" />
											<select class="selectpicker form-control" name="manv"
												style="width: 100%; height: 38px;">
												<option value="">Chọn Nhân viên</option>
												<c:forEach var="nv" items="${listNhanViens }">
													<option value="${nv }">${nv}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="thoigianmuon" />
											<form:input class="form-control" path="thoigianmuon"
												placeholder="Chọn thời gian mượn" type="date" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian trả</label>
										<div class="col-sm-4 col-md-8">
											<form:input class="form-control" path="thoigiantra"
												placeholder="Chọn thời gian trả" type="date" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element'>

									<div class="hidden-element" style="display: none">
										<div class="form-group row">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">

												<select class="form-control"
													name="${listThietBi[indexValue]}"
													style="width: 100%; height: 45px;">
													<option value="">Chọn thiết bị</option>
													<c:forEach var="tb1" items="${loaiThietBis}">
														<option value="${tb1.matb}">${tb1.matb}</option>
													</c:forEach>
												</select>
												<%-- 														<form:errors path="loai.id" /> --%>
											</div>
											<div class="col-md-3">

												<input type="number" value=""
													name="${listSoLuong[indexValue]}" class="form-control"
													placeholder="Số lượng" style="width: 100%; height: 45px;" />

											</div>

											<div class="col-md-2">

												<a class="material-icons text-info btn-close-item"
													type="button"> delete_sweep </a>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-6"></div>
								<div class="col-sm-12 col-md-6 align-self-end">
									<button type='button' class="form-control text-info "
										id='btn-add-element'>
										<span class="material-icons text-info">add_circle_outline</span>
										Thêm thiết bị mượn
									</button>
								</div>

							</div>
							<hr>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<input type="hidden" name="indexValue" id="input-index-submit"
									value='0' /> <input type="hidden" name="demValue"
									id="input-dem-submit" value='0' />
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
	<!--======================================================== Sửa phiếu mượn ============================================================ -->
	<div class="modal fade bs-example-modal-lg" id="bd-edit-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Sửa phiếu mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="phieumuon/update"
							modelAttribute="phieumuon_sua" method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu mượn</label>
										<div class="col-sm-4 col-md-8">
											<%-- 											<form:errors path="mapm" /> --%>
											<form:input class="form-control" type="text" path="mapm"
												placeholder="Nhập mã phiếu mượn" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Người
											mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:select class="selectpicker form-control" path="nm.manm"
												style="width: 100%; height: 38px;" items="${listNguoiMuons}"
												itemValue="manm" itemLabel="ten">
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<form:select path="manv" items="${listNhanViens}"
												class="selectpicker form-control"
												style="width: 100%; height: 38px;">
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian mượn</label>
										<div class="col-sm-4 col-md-8">
											<%-- 											<form:errors path="thoigianmuon" /> --%>
											<form:input class="form-control" path="thoigianmuon"
												placeholder="Chọn thời gian mượn" type="date" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian trả</label>
										<div class="col-sm-4 col-md-8">
											<form:input class="form-control" path="thoigiantra"
												placeholder="Chọn thời gian trả" type="date" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element-update'>
									<div class="hidden-element-update" style="display: none">
										<div class="form-group row">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">
												<select class="form-control" name="thietBi0"
													style="width: 100%; height: 45px;">
													<option value="">Chọn thiết bị</option>
													<c:forEach var="tb1" items="${loaiThietBis}">
														<option value="${tb1.matb}">${tb1.matb}</option>
													</c:forEach>
												</select>
												<%-- 														<form:errors path="loai.id" /> --%>
											</div>

											<div class="col-md-3">
												<input type="number" value="0" name="slThietBi0"
													class="form-control" placeholder="Số lượng"
													style="width: 100%; height: 45px;" />

											</div>

											<div class="col-md-2">

												<a class="material-icons text-info btn-close-item"
													type="button"> delete_sweep </a>
											</div>


										</div>
									</div>
								</div>
							</div>

							<div class="row">

								<div class="col-sm-12 col-md-6"></div>
								<div class="col-sm-12 col-md-6 align-self-end">
									<button type='button' class="form-control text-info "
										id='btn-add-element-edit'>
										<span class="material-icons text-info">add_circle_outline</span>
										Thêm thiết bị mượn
									</button>
								</div>


							</div>
							<div class="modal-footer">
								<hr>
								<button type="button" class="btn btn-dark" data-dismiss="modal">Đóng</button>
								<input type="hidden" name="indexValue"
									id="input-index-submit-edit" value='0' /> <input type="hidden"
									name="demValue" id="input-dem-submit-edit" value='0' />
								<button type="submit" class="btn btn-info">Sửa</button>
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
	<!-- HIỂN THỊ THÔNG BÁO KHI SAI ĐỊNH DẠNG DỮ LIỆU LÚC SỬA -->
	<c:if test="${sua_saidinhdang}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Sửa sai định dạng")
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

	<script>
		var index=0;
		var dem = 0;
	</script>



	<!-- 		Thêm 1 dòng thiết bị mỗi lần click -->
	<script>
									
		const btn = document.getElementById('btn-add-element')
		btn.addEventListener('click', function(e){
			
			
			var index1 = index+1 ;
			var thietBiCu = '${listThietBi[indexValue]}';
			var thietBiMoi = "thietBi" + index1;
						
			
			var slThietBiCu = '${listSoLuong[indexValue]}';
			var slThietBiMoi = "slThietBi" + index1;	
 			index=index1;
		
			console.log(${listThietBi[1]});
			const ele = document.querySelector('.hidden-element')
			const parent = document.getElementById('parent-element')
			 
			let htmlEle = ele.innerHTML;
			
			htmlEle = htmlEle.replace(thietBiCu, thietBiMoi)
			htmlEle = htmlEle.replace(slThietBiCu,slThietBiMoi)
			
			const newEle = document.createElement('div')
			newEle.innerHTML = htmlEle

			// add event delete
			
			const item = newEle.childNodes[1].childNodes[7];
			item.addEventListener('click', function(){
				newEle.remove()
				dem = dem - 1;
				
				var input_them = document.getElementById('input-index-submit')
			    input_them.value = index;
				var demValue = document.getElementById('input-dem-submit')
			    demValue.value = dem;
			})
			
			
			
 			parent.appendChild(newEle)  
 			dem = dem + 1;
 			
 			var input_them = document.getElementById('input-index-submit')
		    input_them.value = index;
			var demValue = document.getElementById('input-dem-submit')
		    demValue.value = dem;

			console.log(htmlEle)

		})
			
			
		</script>

	<!-- 	Thêm một dòng thiết bị mỗi lần click trong update -->
	<!-- 	====================================================== SCRIPT UPDATE ================================================================== -->

	<!-- 	Thêm thiết bị khi click edit phiếu mượn -->
	<c:if test="${slThietBiSua!=0}">
		<c:forEach var="sl" items="${phieumuon_sua.ct_phieumuons}">
			<script>
				//Thay đổi name thiết bị 
				var index1 = index+1 ;
				var thietBiCu = 'thietBi0';
				var thietBiMoi = "thietBi" + index1;
					
				//Thay đổi name số lượng
				var slThietBiCu = 'slThietBi0';
				var slThietBiMoi = "slThietBi" + index1;	
	 			index=index1;
			
	 			//Thay đổi giá trị thiết bị với tên thiết bị
	 			var valueTenTbCu = 'value=""'
	 			var valueTenTbMoi = 'value='+'"${sl.thietbi_muon.matb}"'
	 			
	 			var tenTbCu = 'Chọn thiết bị'
	 			var tenTbMoi = '${sl.thietbi_muon.matb}'
	 			
	 			var tenSlCu= 'Số lượng'
	 			var tenSlMoi = '${sl.soluong}'
	 			
	 			var valueSlCu = 'value="0"'
	 			var valueSlMoi = 'value='+'"${sl.soluong}"'
	 			
	 			/// Bắt đầu từ đây, các biến sẽ gán 1 giá trị để cho mỗi vòng lặp lặp thì tên biến sẽ không trùng.
				var  ele${sl.thietbi_muon.matb} = document.querySelector('.hidden-element-update')
				const parent${sl.thietbi_muon.matb} = document.getElementById('parent-element-update')
				 
				let htmlEle${sl.thietbi_muon.matb} = ele${sl.thietbi_muon.matb}.innerHTML;
				
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(thietBiCu, thietBiMoi)
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(slThietBiCu,slThietBiMoi)
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(valueTenTbCu,valueTenTbMoi)
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(tenTbCu,tenTbMoi)
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(tenSlCu,tenSlMoi)
				htmlEle${sl.thietbi_muon.matb} = htmlEle${sl.thietbi_muon.matb}.replace(valueSlCu,valueSlMoi)
				
				const newEle${sl.thietbi_muon.matb} = document.createElement('div')
				newEle${sl.thietbi_muon.matb}.innerHTML = htmlEle${sl.thietbi_muon.matb}

				// add event delete
				
				const item${sl.thietbi_muon.matb} = newEle${sl.thietbi_muon.matb}.childNodes[1].childNodes[7];
				item${sl.thietbi_muon.matb}.addEventListener('click', function(){
					newEle${sl.thietbi_muon.matb}.remove()
					dem = dem - 1;
					var input_them${sl.thietbi_muon.matb} = document.getElementById('input-index-submit-edit')
				    input_them${sl.thietbi_muon.matb}.value = index;
					var demValue${sl.thietbi_muon.matb} = document.getElementById('input-dem-submit-edit')
				    demValue${sl.thietbi_muon.matb}.value = dem;
					
				})
				
					
				
				
	 			parent${sl.thietbi_muon.matb}.appendChild(newEle${sl.thietbi_muon.matb})  
	 			dem = dem + 1;
	 			
	 			var input_them${sl.thietbi_muon.matb} = document.getElementById('input-index-submit-edit')
			    input_them${sl.thietbi_muon.matb}.value = index;
				var demValue${sl.thietbi_muon.matb} = document.getElementById('input-dem-submit-edit')
			    demValue${sl.thietbi_muon.matb}.value = dem;
		
	</script>
		</c:forEach>

	</c:if>

	<script>
									
		const btn1 = document.getElementById('btn-add-element-edit')
		btn1.addEventListener('click', function(e){
			
			var index1 = index+1 ;
			var thietBiCu = 'thietBi0';
			var thietBiMoi = "thietBi" + index1;
						
			
			var slThietBiCu = 'slThietBi0';
			var slThietBiMoi = "slThietBi" + index1;	
 			index=index1;
		
			console.log(${listThietBi[1]});
			const ele = document.querySelector('.hidden-element-update')
			const parent = document.getElementById('parent-element-update')
			 
			let htmlEle = ele.innerHTML;
			
			htmlEle = htmlEle.replace(thietBiCu, thietBiMoi)
			htmlEle = htmlEle.replace(slThietBiCu,slThietBiMoi)
			
			const newEle = document.createElement('div')
			newEle.innerHTML = htmlEle

			// add event delete
			
			const item = newEle.childNodes[1].childNodes[7];
			item.addEventListener('click', function(){
				newEle.remove()
				dem = dem - 1;
				var input_them${sl.thietbi_muon.matb} = document.getElementById('input-index-submit-edit')
			    input_them${sl.thietbi_muon.matb}.value = index;
				var demValue${sl.thietbi_muon.matb} = document.getElementById('input-dem-submit-edit')
			    demValue${sl.thietbi_muon.matb}.value = dem;
				
			})
			
				
			
			
 			parent.appendChild(newEle)  
 			dem = dem + 1;
 			
 			var input_them = document.getElementById('input-index-submit-edit')
		    input_them.value = index;
			var demValue = document.getElementById('input-dem-submit-edit')
		    demValue.value = dem;

			console.log(htmlEle)

		})			
	</script>
	<!-- Xoá thiết bị -->
	<script>
		$('#parent-element').on('click', '.xoa-thiet-bi', function(){
			$(this).parent().remove()
			
		})
	</script>
</body>
</html>