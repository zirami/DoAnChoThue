<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header"
	style="background-image: url('${pageContext.servletContext.contextPath}/resources/files/hinh10.jpg')">
	<div class="header-left"></div>
	<div class="header-right">
		<div class="user-info-dropdown">
			<div class="dropdown">
				<a class="dropdown-toggle" href="#" role="button"
					data-toggle="dropdown">
					
					<h5 class="user-name text-white">
						Xin chào
						<b>${account_db.username}</b>		
					</h5>
					<span class="user-icon 	">
						<img src="resources/files/${account_db.nhanviens.get(0).hinh }"
							alt="" style="height: 90%">
					</span>
				</a>
				<div
					class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
					<a class="dropdown-item" href="changePassword">
						<i class="dw dw-settings2"></i>
						Thay đổi mật khẩu
					</a>
					<a class="dropdown-item" href="dangxuat">
						<i class="dw dw-logout"></i>
						Đăng xuất
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
