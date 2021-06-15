<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="data-table-export table" id="myTable">
		<thead>
			<tr>
				<!-- PHIẾU MƯỢN -->
				<th>Ngày</th>
				<th>Phiếu Mượn chưa trả</th>
				<th>Phiếu Mượn đã trả</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="kq" items="${kqThongke}">
				<tr>
					<th>${kq[0]}</th>
					<th>${kq[1]}</th>
					<th>${kq[2]}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
