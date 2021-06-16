<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>Nhóm 2 - Thống Kê</title>
<%@include file="/common/loadcss.jsp"%>
<!-- Date range picker css -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
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
						<div class="col-2">
							<div class="title">
								<h4>Thống Kê</h4>
							</div>
							<!-- Đường dẫn -->
							<nav aria-label="breadcrumb" role="navigation">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="index.html">Home</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page">Thống
										kê</li>
								</ol>
							</nav>
							<!-- END Đường dẫn -->
						</div>
						<form class="col" action="thong-ke" method="post">
							<div class="row">
								<div class="col-3">
									<select class="btn btn-primary" name="loaiThongke">
										<option value="">-Vui lòng chọn 1-</option>
										<c:forEach var="loai" items="${loaiThongkes}">
											<option value="${loai}">${loai}</option>
										</c:forEach>
									</select>
								</div>
								<%-- <div class="col-2">
									<select class="btn btn-primary" name="option"
										style="display: none">
										<c:forEach var="option" items="${optionPM}">
											<option value="${option}">${option}</option>
										</c:forEach>
									</select>
								</div> --%>
								<div class="col text-center">
									<input name="date" type="text"
										placeholder="Chọn khoảng thời gian">
									<input hidden="true" id="ngaybatdau" name="ngaybatdau"
										type="date" />
									<input hidden="true" id="ngayketthuc" name="ngayketthuc"
										type="date" />
								</div>
								<input type="submit" hidden="true" />
							</div>
						</form>
						<div class="col-1 text-right">
							<button class="btn btn-primary filter-btn" style="display: none">LỌC</button>
						</div>
					</div>
				</div>
				<div id="chart"></div>
				<hr>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<div class="pb-20 container">
						<c:set var="PM" value="Phiếu Mượn" />
						<c:set var="TB" value="Thiết Bị" />
						<c:set var="LNM" value="Loại Người Mượn" />
						<c:if test="${loaiThongke.equals(PM) }">
							<%@include file="/WEB-INF/views/thong_ke/thongke_phieumuon.jsp"%>
						</c:if>
						<c:if test="${loaiThongke.equals(TB) }">
							<%@include file="/WEB-INF/views/thong_ke/thongke_thietbi.jsp"%>
						</c:if>
						<c:if test="${loaiThongke.equals(LNM) }">
							<%@include file="/WEB-INF/views/thong_ke/thongke_nguoimuon.jsp"%>
						</c:if>
					</div>
				</div>
				<!-- END DATATABLE -->
			</div>
		</div>
	</div>
	<!-- js -->
	<%@include file="/common/footer.jsp"%>
	<!-- chart -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/data.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
	<!-- DATE RANGE PICKER -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<!-- DATATABLE EXPORT -->
	<script src="resources/vendors/scripts/datatable-setting.js"></script>
	<script type="text/javascript">
		//LOAD TABLE 
		/* $('#myTable').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'excelHtml5', 'pdfHtml5']
		}); */
		let loaiThongke = '${loaiThongke}'
		if(loaiThongke != 'Người Mượn' )
		Highcharts.chart('chart', {
			data : {
				table : 'myTable',
				switchRowsAndColumns : false
			},
			chart : {
				type : 'spline'
			},
			colors: ['#e00d0d', '#1b00ff'],
			title : {
				text : 'Thống kê ' + loaiThongke
			},
			yAxis : {
				allowDecimals : false,
				title : {
					text : 'Số lượng'
				}
			},
			tooltip: {
		        formatter: function () {
		            return '<b>' + this.series.name + '</b><br/>' +
		                this.point.y + ' ' + loaiThongke.toLowerCase();
		        }
		    }
		});
		
		if(loaiThongke == 'Loại Người Mượn')
			Highcharts.chart('chart', {
				data : {
					table : 'myTable',
					switchRowsAndColumns : false
				},
				chart : {
					type : 'pie'
				},
				colors: ['#00ffaa', '#007bff', '#bababa'],
				title : {
					text : 'Thống kê ' + loaiThongke
				},
				tooltip: {
			        formatter: function () {
			            return '<b>' + this.series.name + ': '+ this.point.y+' </b> '
			                
			        }
			    }
			});
	</script>
	<!-- Date range picker -->
	<script>
		$(function() {
			//Khởi tạo chọn khoảng thời gian: date range picker
			let locale_init = {
				cancelLabel : 'Xoá',
				applyLabel : 'Áp dụng',
				format : 'DD/MM/YYYY',
				daysOfWeek : [ "T2", "T3", "T4", "T5", "T6", "T7", "CN" ],
				monthNames : [ "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4",
						"Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9",
						"Tháng 10", "Tháng 11", "Tháng 12" ],
			}

			let date_init = {
				showDropdowns : true,
				locale : locale_init,
				startDate : '${ngaybatdau}',
				endDate : '${ngayketthuc}'
			}

			if ('${ngaybatdau}' == '' || '${ngayketthuc}' == '')
				date_init = {
					showDropdowns : true,
					locale : locale_init,
				}

			let date = $('input[name="date"]').daterangepicker(
					date_init,
					//Khi có sự kiện mở lịch ra và chọn ngày mới thì set dữ liệu lại cho 2 cái ngày start và end
					function(start, end, label) {
						console.log('New date range selected: '
								+ start.format('YYYY-MM-DD') + ' to '
								+ end.format('YYYY-MM-DD')
								+ ' (predefined range: ' + label + ')');
						$('#ngaybatdau').val(start.format('YYYY-MM-DD'));
						$('#ngayketthuc').val(end.format('YYYY-MM-DD'));
					})

			//Mới load vào thì ngày bắt đầu và ngày kết thúc như nhau			
			let startDate = date.data('daterangepicker').startDate
					.format('YYYY-MM-DD');
			let endDate = date.data('daterangepicker').endDate
					.format('YYYY-MM-DD');
			$('#ngaybatdau').val(startDate);
			$('#ngayketthuc').val(endDate);

		});
	</script>
	<script type="text/javascript">
		//LOAD VÀO THẤY LOẠI THỐNG KÊ CÓ GIÁ TRỊ
		$(function() {
			let loaiThongke = $("select[name='loaiThongke']").val(
					'${loaiThongke}')

			//NẾU LOẠI THỐNG KÊ LÀ RỖNG THÌ ẨN CÁC NÚT OPTION VÀ NÚT LỌC
			if (loaiThongke.val() == "") {
				$('.filter-btn').hide()
			} else {
				$('.filter-btn').show()
			}
		})

		//KHI THAY ĐỔI LOẠI THỐNG KÊ
		$("select[name='loaiThongke']").on(
				'change',
				function() {
					//NẾU LOẠI THỐNG KÊ LÀ RỖNG THÌ ẨN CÁC NÚT OPTION VÀ NÚT LỌC
					if ($(this).val() == "") {
						$('.filter-btn').hide()
					} else {
						$('.filter-btn').show()
					}

				})

		//KHI NHẤN NÚT LỌC
		$('.filter-btn').on('click', function() {
			$(this).parents("div.row").find("input[type='submit']").click()
		})
	</script>
	<!-- KHAI BÁO THÔNG BÁO CƠ BẢN -->
	<script type="text/javascript">
		if (${error})
			show_error("${msg}");

		//THÔNG BÁO LỖI
		function show_error(content) {
			Swal.fire({
				title : 'LỖI',
				text : content,
				icon : 'error',
			})
		}
	</script>
</body>
</html>