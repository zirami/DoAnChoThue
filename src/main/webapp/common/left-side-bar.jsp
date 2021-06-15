<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/loadcss.jsp"%>
<div class="left-side-bar">
	<div class="brand-logo">
		<a href="home">
			<img src="resources/vendors/images/logo-inside.png" alt=""
				class="dark-logo">
			<img src="resources/vendors/images/logo-inside.png" alt=""
				class="light-logo">
		</a>
		<div class="close-sidebar" data-toggle="left-sidebar-close">
			<i class="ion-close-round"></i>
		</div>
	</div>
	<div class="menu-block customscroll">
		<div class="sidebar-menu">
			<ul id="accordion-menu">

				<li class="dropdown" id="qlpm" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="phieumuon" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="QUẢN LÍ PHIẾU MƯỢN">
						<span class="micon dw dw-file"></span>
						<span class="text-yellow">QUẢN LÝ PHIẾU MƯỢN</span>
					</a>
				</li>
				<li class="dropdown" id="qltb" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="thiet-bi" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="QUẢN LÝ THIẾT BỊ CHO MƯỢN">
						<span class="micon dw dw-ruler"></span>
						<span class="text-yellow">QUẢN LÝ THIẾT BỊ</span>
					</a>
				</li>
				<li class="dropdown" id="qlnm" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="qlnguoimuon" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="QUẢN LÍ NGƯỜI MƯỢN">
						<span class="micon dw dw-user"></span>
						<span class="text-yellow">QUẢN LÍ NGƯỜI MƯỢN</span>
					</a>
				</li>

				<li class="dropdown" id="ntb" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="phieu-nhap" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="NHẬP THIẾT BỊ">
						<span class="micon dw dw-trolley"></span>
						<span class="text-yellow">QUẢN LÍ PHIẾU NHẬP</span>
					</a>
				</li>

				<li class="dropdown" id="tltb" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="phieu-thanhly" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="THANH LÝ THIẾT BỊ">
						<span class="micon dw dw-trolley"></span>
						<span class="text-yellow">QUẢN LÍ PHIẾU THANH LÝ</span>
					</a>
				</li>

				<li class="dropdown" id="qlnv" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="qlnhanvien" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="QUẢN LÍ NHÂN VIÊN">
						<span class="micon dw dw-name"></span>
						<span class="text-yellow"">QUẢN LÍ NHÂN VIÊN</span>
					</a>
				</li>
				<!-- 				<li class="dropdown"> -->
				<!-- 					<a href="qlquanli" class="dropdown-toggle no-arrow" -->
				<!-- 						data-toggle="tooltip" data-placement="right" title="" -->
				<!-- 						data-original-title="QUẢN LÍ "> -->
				<!-- 						<span class="micon dw dw-diamond"></span> -->
				<!-- 						<span class="mtext">QUẢN LÍ</span> -->
				<!-- 					</a> -->
				<!-- 				</li> -->

				<li class="dropdown" id="thongke" style="display: none">
					<div class="dropdown-divider"></div>
					<a href="thong-ke" class="dropdown-toggle no-arrow"
						data-toggle="tooltip" data-placement="right" title=""
						data-original-title="THỐNG KÊ">
						<span class="micon dw dw-analytics-21"></span>
						<span class="text-yellow">THỐNG KÊ</span>
					</a>
				</li>
				<!-- 				<li> -->
				<!-- 					<div class="dropdown-divider"></div> -->
				<!-- 				</li> -->
				<li>
					<div class="dropdown-divider"></div>
				</li>

			</ul>
		</div>
	</div>
</div>
<script>
	window.addEventListener('load', function() {
		if ("${role}" != "admin") {
			document.getElementById("qlpm").style.display = "";
			document.getElementById("qltb").style.display = "";
			document.getElementById("qlnm").style.display = "";
			document.getElementById("ntb").style.display = "";
			document.getElementById("tltb").style.display = "";
			document.getElementById("thongke").style.display = "";
//  			document.getElementById("dangxuat").style.display = "";
		}
		if ("${role}" == "admin") {
			document.getElementById("qlpm").style.display = "";
			document.getElementById("qltb").style.display = "";
			document.getElementById("qlnm").style.display = "";
			document.getElementById("ntb").style.display = "";
			document.getElementById("tltb").style.display = "";
			document.getElementById("thongke").style.display = "";
			document.getElementById("qlnv").style.display = "";
// 			document.getElementById("dangxuat").style.display = "";
		}
	})
</script>