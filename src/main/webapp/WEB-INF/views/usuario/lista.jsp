<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Lista de Usuarios">

	<div class="container">
		
		<a href="${s:mvcUrl('UC#cadastroUsuario').build() }"><h1>Novo usuario</h1></a>
	
		<h1>Lista de Usuario</h1>
		<div>${massage }</div>
			<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th></th>
			</tr>
			<c:forEach items="${listaUsuarios }" var="response">
			          <tr>
				          <td>${response.nome}</td>
				          <td>${response.email}</td>
				          <td>
				          	<c:forEach items="${response.roles}" var="roles" varStatus="loopStatus">
								${roles.nome }${!loopStatus.last?',':''}			          	
				          	</c:forEach>
				          </td>
				          <td><a class="btn btn-default" href="${s:mvcUrl('UC#rolesAlterar').arg(0, response.email).build() }" role="button">+</a></td>
				          
			          </tr>
			</c:forEach>
		</table>
	
	</div>
</tags:pageTemplate>