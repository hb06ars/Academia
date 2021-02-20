<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<!-- HEADER -->
<!-- TAGS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- TAGS -->
<!-- INICIO BODY -->

<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->


<!-- start: page -->
<c:if test="${usuario.perfil.admin }">
	<div class="row">
	<form action="/pendencias" method="post" accept-charset="utf-8">
		<div class="col-md-12">
			<div data-collapsed="0" class="panel">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>
						<h2 class="panel-title" id="">Pesquisar por usu�rio </h2>
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 form-group">
							<input type="text" placeholder="CPF" name="cpf" id="cpf" class="form-control" required>
						</div>
						<div class="col-md-2 form-group" id="pesq">
							<input type="submit" class="btn btn-primary"  value="Pesquisar">
						</div>
						<input type="hidden" id="acao" name="acao" value="pesquisar">
					</div>
				</div>
			</div>
		</div>
	</form>
	</div>
</c:if>



<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Pend�ncias</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<c:if test="${usuario.perfil.admin }">
												<th>Retirar</th>
											</c:if>
											<th>Matr�cula</th>
											<th>Situa��o</th>
											<th>Ativo</th>
											<th>Fim do Contrato</th>
											<th>Vencimento da Parcela</th>
											<th>Valor da d�vida</th>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Celular</th>
											<th>Email</th>
											<th>Endere�o</th>
											<th>Bairro</th>
											<th>Cidade</th>
											<th>Estado</th>
											<th>CPF</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pendencias }" var="p">
											<tr class="gradeX">
												<c:if test="${usuario.perfil.admin }">
													<td><i class="fa fa-trash" onclick="modalDeletar('parcela', ${p.id}) "></i></td>
												</c:if>
												<td>${p.contrato.cliente.matricula }</td>
												<td style="color:red"> Pendente </td>
												<c:if test="${p.contrato.cliente.ativo == false}">
													<td style="color:red"> Inativo </td>
												</c:if>
												<c:if test="${p.contrato.cliente.ativo == true}">
													<td style="color:blue"> Ativo </td>
												</c:if>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.contrato.fim}" /></td>
												<c:set var="valor" value="${fn:substring(p.vencimento, 8, 10)}/${fn:substring(p.vencimento, 5, 7)}/${fn:substring(p.vencimento, 0, 4)}" />
												<td>${valor}</td>
												<td><fmt:formatNumber type="currency" value="${p.valor}" /></td>
												<td>${p.contrato.cliente.nome}</td>
												<td class="center">${p.contrato.cliente.telefone}</td>
												<td class="center">${p.contrato.cliente.celular}</td>
												<td>${p.contrato.cliente.email}</td>
												<td>${p.contrato.cliente.endereco}</td>
												<td>${p.contrato.cliente.bairro}</td>
												<td>${p.contrato.cliente.cidade}</td>
												<td>${p.contrato.cliente.estado}</td>
												<td>${p.contrato.cliente.cpf}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<button type="button" class="btn btn-primary" onclick="tableToExcel('datatable-default', 'Documento')">Download</button>
							</div>
						</section>





<!-- end: page -->
	</section>
</div>







<!-- FIM BODY -->
</div>
<!-- FIM BODY -->
<!-- MAIN NO HEADER -->
</main>
<!-- HEADER -->
<jsp:include page="includes/footer.jsp" />
<!-- HEADER -->