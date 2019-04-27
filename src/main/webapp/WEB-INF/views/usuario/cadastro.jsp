<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="Cadastro de Usuario">
	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

	<div class="container">
		<h1>Cadastro de Usuario</h1>
		<div>${massage }</div>
		<form:form action="${s:mvcUrl('UC#gravar').build() }" method="post"
			commandName="usuario" >
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome"  />
			</div>
			<div class="form-group">
				<label>Email</label> 
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div>
				<label>Senha</label> 
				<input type="password" name="senha" class="form-control"/>
				<form:errors path="senha" />
			</div>
			<div>
				<label>Confirma Senha</label> 
				<input type="password" name="senhaConfere" class="form-control"/>
				<form:errors path="senhaConfere" />
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>

	</div>
</tags:pageTemplate>