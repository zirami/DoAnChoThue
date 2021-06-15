<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="data-table-export table">
		<thead>
			<tr>
				<th>Ngày</th>
				<th>Số lượng ${loaiThongke}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="kq" items="${kqThongke}">
				<tr>
					<th>${kq[0]}</th>
					<th>${kq[1]}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table hidden="hidden" id="myTable">
		<thead>
			<tr>
				<th>Ngày</th>
				<th>${loaiThongke}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="kq" items="${kqThongke}">
				<tr>
					<th>${kq[0]}</th>
					<td>${kq[1]}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>