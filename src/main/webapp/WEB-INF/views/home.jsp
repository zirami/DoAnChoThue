<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>QUẢN LÍ THIẾT BỊ CHO MƯỢN</title>
<%@include file="/common/loadcss.jsp"%>
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/left-side-bar.jsp"%>
	<div class="mobile-menu-overlay"></div>
	<div class="main-container">
		<div class="pd-ltr-20">
			<div class="card-box pd-20 height-100-p mb-30">
				<div class="row align-items-center">
					<div class="col-md-4">
						<img src="resources/vendors/images/banner-img.png" alt="">
					</div>
					<div class="col-md-8">
						<h4 class="font-20 weight-500 mb-10 text-capitalize">Xin chào
						</h4>
						<h4
							class="font-20 weight-500 mb-10 text-capitalize weight-600 font-30 text-blue">
							Thành Thắng!</h4>
						<p class="font-18 max-width-600">Chúc bạn 1 ngày làm việc thật
							tốt nhé!!!</p>
					</div>
				</div>
			</div>
			<div class="card-box mb-30">
				<h2 class="h4 pd-20">Danh sách thiết bị mượn nhiều nhất</h2>
				<table class="data-table table nowrap">
					<thead>
						<tr>
							<th class="table-plus datatable-nosort">Thiết bị</th>
							<th>Tên</th>
							<th>Màu sắc</th>
							<th>Loại</th>
							<th>Đơn giá</th>
							<th>Tình trạng</th>
							<th class="datatable-nosort">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="table-plus">
								<img src="resources/vendors/images/product-1.jpg" width="70"
									height="70" alt="">
							</td>
							<td>
								<h5 class="font-16">Shirt</h5>
								by John Doe
							</td>
							<td>Black</td>
							<td>M</td>
							<td>$1000</td>
							<td>1</td>
							<td>
								<div class="dropdown">
									<a
										class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
										href="#" role="button" data-toggle="dropdown">
										<i class="dw dw-more"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
										<a class="dropdown-item" href="#">
											<i class="dw dw-eye"></i>
											View
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-edit2"></i>
											Edit
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-delete-3"></i>
											Delete
										</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="table-plus">
								<img src="resources/vendors/images/product-2.jpg" width="70"
									height="70" alt="">
							</td>
							<td>
								<h5 class="font-16">Boots</h5>
								by Lea R. Frith
							</td>
							<td>brown</td>
							<td>9UK</td>
							<td>$900</td>
							<td>1</td>
							<td>
								<div class="dropdown">
									<a
										class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
										href="#" role="button" data-toggle="dropdown">
										<i class="dw dw-more"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
										<a class="dropdown-item" href="#">
											<i class="dw dw-eye"></i>
											View
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-edit2"></i>
											Edit
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-delete-3"></i>
											Delete
										</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="table-plus">
								<img src="resources/vendors/images/product-3.jpg" width="70"
									height="70" alt="">
							</td>
							<td>
								<h5 class="font-16">Hat</h5>
								by Erik L. Richards
							</td>
							<td>Orange</td>
							<td>M</td>
							<td>$100</td>
							<td>4</td>
							<td>
								<div class="dropdown">
									<a
										class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
										href="#" role="button" data-toggle="dropdown">
										<i class="dw dw-more"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
										<a class="dropdown-item" href="#">
											<i class="dw dw-eye"></i>
											View
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-edit2"></i>
											Edit
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-delete-3"></i>
											Delete
										</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="table-plus">
								<img src="resources/vendors/images/product-4.jpg" width="70"
									height="70" alt="">
							</td>
							<td>
								<h5 class="font-16">Long Dress</h5>
								by Renee I. Hansen
							</td>
							<td>Gray</td>
							<td>L</td>
							<td>$1000</td>
							<td>1</td>
							<td>
								<div class="dropdown">
									<a
										class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
										href="#" role="button" data-toggle="dropdown">
										<i class="dw dw-more"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
										<a class="dropdown-item" href="#">
											<i class="dw dw-eye"></i>
											View
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-edit2"></i>
											Edit
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-delete-3"></i>
											Delete
										</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="table-plus">
								<img src="resources/vendors/images/product-5.jpg" width="70"
									height="70" alt="">
							</td>
							<td>
								<h5 class="font-16">Blazer</h5>
								by Vicki M. Coleman
							</td>
							<td>Blue</td>
							<td>M</td>
							<td>$1000</td>
							<td>1</td>
							<td>
								<div class="dropdown">
									<a
										class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
										href="#" role="button" data-toggle="dropdown">
										<i class="dw dw-more"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
										<a class="dropdown-item" href="#">
											<i class="dw dw-eye"></i>
											View
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-edit2"></i>
											Edit
										</a>
										<a class="dropdown-item" href="#">
											<i class="dw dw-delete-3"></i>
											Delete
										</a>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- js -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>