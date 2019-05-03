<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de Permissoes">
	<div class="container">
	
		<h1>Cadastro de Permissoes para ${usuario.nome }</h1>
		<form:form action="${s:mvcUrl('UC#rolesUpdate').build() }" method="post" commandName="usuario" >
			<div class="form-group">
			Permissoes:
			<form:checkboxes items="${roles }" path="roles" itemLabel="nome"  />
			<input type="hidden" value="${usuario} " name="email">
			<br><br>
			<button type="submit">Atualizar</button>
		</div>
		</form:form>
	
	</div>
</tags:pageTemplate>