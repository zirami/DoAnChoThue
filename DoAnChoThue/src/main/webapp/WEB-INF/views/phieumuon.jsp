<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container center">
		<table class="table table-dark" id="table_data">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Mã phiếu mượn</th>
					<th scope="col">Tên</th>
					<th scope="col">Loại</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="phieumuon" items="${listPhieumuon}" varStatus="i">
					<tr id="t_row_${i.getIndex()}">
						<td scope="row">${i.count}</td>
						<td scope="row">${phieumuon.maPM}</td>
						<td scope="row">${phieumuon.tgMuon}</td>
						<td scope="row">${phieumuon.maNM}</td>
						<td><a href="javascript:void" class="material-icons"
							data-bs-toggle="modal" data-bs-target="#staticBackdrop"
							onclick="edit(${i.count})"> <i class="material-icons">edit</i>
						</a> <a href="javascript:void" class="material-icons"
							data-bs-toggle="modal" data-bs-target="#modalDelete"
							onclick="del(${i.count})"> <i class="material-icons">delete</i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>