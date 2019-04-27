<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Lista de pedidos">

	<div class="container">
		<h1>Lista de Pedidos</h1>
			<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data pedido</th> 
				<th>Titulos</th>
			</tr>
			<c:forEach items="${response }" var="response">
			          <tr>
				          <td>${response.id}</td>
				          <td>${response.valor}</td>
				          <td><fmt:formatDate pattern="dd/MM/yyyy" value="${response.data}"/></td>
				          <td>
				          	<c:forEach items="${response.produtos }" var="produtos" varStatus="loopStatus"> 
				          		${produtos.titulo}${!loopStatus.last?',':''}
				          	</c:forEach>
				          </td>                    
			          </tr>
			</c:forEach>
		</table>
	
	</div>
</tags:pageTemplate>