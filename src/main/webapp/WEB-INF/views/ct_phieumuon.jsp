<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath }/">
<title>Insert title here</title>
</head>
<body>
	<div class="container center">
			${message }
		<table class="table table-dark" id="table_data">
			<thead>
				<tr>
					<th scope="col">Mã id</th>
					<th scope="col">Mã phiếu mượn</th>
					<th scope="col">Tên Thiết bị</th>
					<th scope="col">Số lượng (cái)</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ct_pm" items="${listCT_Phieumuon}" varStatus="i">
					
						<td scope="row">${ct_pm.id}</td>
						<td scope="row">${ct_pm.phieumuon.mapm}</td>
					<td scope="row">${ct_pm.thietbi_muon.ten}</td>
						<td scope="row">${ct_pm.soluong}</td>
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
		
		<form:form action="ct_phieumuon/insert.htm" modelAttribute="ct_phieumuon_moi">
			<div>Nhập mã thiết bị</div>
			<form:input path="thietbi_muon.matb" class="form-control" value=""/>
			<div>Nhập mã phiếu mượn</div>
			<form:input path="phieumuon.mapm" class="form-control" value=""/>
			<div>Nhập số lượng</div>
			<form:input path="soluong" class="form-control" value=""/>
			<hr>
			<button type="submit" >OK</button>
		</form:form>
	</div>
</body>
</html>