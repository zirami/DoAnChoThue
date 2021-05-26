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
						<div class="col">
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
						<div class="col-9">
							<form action="thong-ke" method="post">
								<div class="row text-right">
									<div class="col">
										<select class="selectpicker" name="loaiThongke">
											<c:forEach var="loai" items="${loaiThongkes}">
												<option value="${loai}">${loai}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										<input name="date" type="text"
											placeholder="Chọn khoảng thời gian">
										<input hidden="true" id="ngaybatdau" name="ngaybatdau"
											type="date" />
										<input hidden="true" id="ngayketthuc" name="ngayketthuc"
											type="date" />
									</div>
									<div class="col-3 text-right">
										<button class="btn btn-primary" type="submit">LỌC</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<div class="pb-20 container">
						<table class="data-table table stripe hover nowrap" id="myTable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Tháng/Phiếu</th>
									<th>${loaiThongke}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="kq" items="${kqThongke}">
									<tr>
										<th>
											<fmt:formatDate pattern="dd-MM-yyyy" value="${kq[0]}" />
										</th>
										<td>${kq[1]}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div id="chart"></div>
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
	<script
		src="https://cdn.datatables.net/buttons/1.7.0/js/dataTables.buttons.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.7.0/js/buttons.html5.min.js"></script>
	<script type="text/javascript">
		//LOAD TABLE 
		$('#myTable').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'excelHtml5', 'pdfHtml5']
		});

		Highcharts.chart('chart', {
			data : {
				table : 'myTable',
				switchRowsAndColumns : true
			},
			chart : {
				type : 'column'
			},
			title : {
				text : 'Thống kê ' + '${loaiThongke}'
			},
			yAxis : {
				allowDecimals : false,
				title : {
					text : 'Số lượng'
				}
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.series.name + '</b><br/>'
							+ this.point.y + ' '
							+ this.point.name.toLowerCase();
				}
			}
		});
	</script>
	<!-- Date range picker -->
	<script>
		$(function() {
			//Khởi tạo chọn khoảng thời gian: date range picker
			let date = $('input[name="date"]').daterangepicker(
					{
						locale : {
							cancelLabel : 'Xoá',
							applyLabel : 'Áp dụng'
						}
					},
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
			$('#ngaybatdau').val(startDate);
			$('#ngayketthuc').val(startDate);

		});
	</script>
</body>
</html>