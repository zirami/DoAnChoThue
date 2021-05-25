<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>
<%@include file="/common/loadcss.jsp"%>
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
						<div class="col-md-6 col-sm-12 text-right">
							<a href="#" id="insert_btn" class="btn btn-primary"
								data-toggle="modal" data-target="#bd-example-modal-lg"
								type="button">THÊM THIẾT BỊ </a>
						</div>
					</div>
				</div>
				<div id="chart"></div>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<div class="pb-20">
						<table class="data-table table stripe hover nowrap" id="datatable">
							<thead>
								<tr>
									<th class="table-plus datatable-nosort">Mã thiết bị</th>
									<th>Tên Thiết Bị</th>
									<th>Loại</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Apples</th>
									<td>3</td>
									<td>4</td>
								</tr>
							</tbody>
						</table>
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
	<script type="text/javascript">
		//LOAD TABLE 
		$('#datatable').DataTable();

		Highcharts.chart('chart', {
			data : {
				table : 'datatable'
			},
			chart : {
				type : 'column'
			},
			title : {
				text : 'Data extracted from a HTML table in the page'
			},
			yAxis : {
				allowDecimals : false,
				title : {
					text : 'Units'
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
</body>
</html>