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


<script>

function habilitar(){
	document.getElementById("pesqForm").submit();
}

function acao(valor){
	var iniVar = document.getElementById("inicio").value;
	var fimVar = document.getElementById("fim").value;
	if(iniVar != '' && fimVar != ''){
		if(valor == 'salvar'){
			document.getElementById("acao").value="1";
			document.getElementById("pesqForm").submit();
			document.getElementById("avaliacaoPreencher").innerHTML="";
		}
		if(valor == 'atualizar'){
			document.getElementById("acao").value="2";
			document.getElementById("pesqForm").submit();
			document.getElementById("avaliacaoPreencher").innerHTML="";
		}
	} else{
		document.getElementById("avaliacaoPreencher").innerHTML="* Preencha os campos de início e fim da avaliação.";
	}
	
	
}



function editar(id){
	document.getElementById("btAdd").style.display="none";
	document.getElementById("atualizar").style.display="block";
	<c:forEach items="${avaliacao }" var="avl">
		if(${avl.id} == id){
			document.getElementById("gorduraCorporal").value = '${avl.gorduraCorporal}';
			document.getElementById("idade").value='${avl.idade}';
			document.getElementById("peso").value='${avl.peso}';
			document.getElementById("altura").value='${avl.altura}';
			document.getElementById("triceps").value='${avl.triceps}';
			document.getElementById("abdomenDobra").value='${avl.abdomenDobra}';
			document.getElementById("bracoDir").value='${avl.bracoDir}';
			document.getElementById("bracoEsq").value='${avl.bracoEsq}';
			document.getElementById("antiBracoDir").value='${avl.antiBracoDir}';
			document.getElementById("antiBracoEsq").value='${avl.antiBracoEsq}';
			document.getElementById("abdomenCirc").value='${avl.abdomenCirc}';
			document.getElementById("quadril").value='${avl.quadril}';
			document.getElementById("cintura").value='${avl.cintura}';
			document.getElementById("coxaDir").value='${avl.coxaDir}';
			document.getElementById("coxaEsq").value='${avl.coxaEsq}';
			document.getElementById("pernaDir").value='${avl.pernaDir}';
			document.getElementById("pernaEsq").value='${avl.pernaEsq}';
			document.getElementById("radio").value='${avl.radio}';
			document.getElementById("femur").value='${avl.femur}';
			document.getElementById("gorduraCorporal").value='${avl.gorduraCorporal}';
			document.getElementById("classificacao").value='${avl.classificacao}';
			document.getElementById("massaMagra").value='${avl.massaMagra}';
			document.getElementById("massaGorda").value='${avl.massaGorda}';
			document.getElementById("massaMuscular").value='${avl.massaMuscular}';
			document.getElementById("massaOssea").value='${avl.massaOssea}';
			document.getElementById("massaResidual").value='${avl.massaResidual}';
			document.getElementById("icq").value='${avl.icq}';
			document.getElementById("imc").value='${avl.imc}';
			document.getElementById("classificacaoIMC").value='${avl.classificacaoIMC}';
			document.getElementById("classificacaoICQ").value='${avl.classificacaoICQ}';



			document.getElementById("limite_gorduraCorporal").value = '${avl.limite_gorduraCorporal}';
			document.getElementById("limite_peso").value='${avl.limite_peso}';
			document.getElementById("limite_triceps").value='${avl.limite_triceps}';
			document.getElementById("limite_abdomenDobra").value='${avl.limite_abdomenDobra}';
			document.getElementById("limite_bracoDir").value='${avl.limite_bracoDir}';
			document.getElementById("limite_bracoEsq").value='${avl.limite_bracoEsq}';
			document.getElementById("limite_antiBracoDir").value='${avl.limite_antiBracoDir}';
			document.getElementById("limite_antiBracoEsq").value='${avl.limite_antiBracoEsq}';
			document.getElementById("limite_abdomenCirc").value='${avl.limite_abdomenCirc}';
			document.getElementById("limite_quadril").value='${avl.limite_quadril}';
			document.getElementById("limite_cintura").value='${avl.limite_cintura}';
			document.getElementById("limite_coxaDir").value='${avl.limite_coxaDir}';
			document.getElementById("limite_coxaEsq").value='${avl.limite_coxaEsq}';
			document.getElementById("limite_pernaDir").value='${avl.limite_pernaDir}';
			document.getElementById("limite_pernaEsq").value='${avl.limite_pernaEsq}';
			document.getElementById("limite_radio").value='${avl.limite_radio}';
			document.getElementById("limite_femur").value='${avl.limite_femur}';
			document.getElementById("limite_massaMagra").value='${avl.limite_massaMagra}';
			document.getElementById("limite_massaGorda").value='${avl.limite_massaGorda}';
			document.getElementById("limite_massaMuscular").value='${avl.limite_massaMuscular}';
			document.getElementById("limite_massaOssea").value='${avl.limite_massaOssea}';
			document.getElementById("limite_massaResidual").value='${avl.limite_massaResidual}';
			document.getElementById("limite_icq").value='${avl.limite_icq}';
			document.getElementById("limite_imc").value='${avl.limite_imc}';
			
			


			
			
			document.getElementById("matricula").value = '${avl.aluno.matricula}';
			document.getElementById("nome").value = '${avl.aluno.nome}';
			document.getElementById("observacoes").value = '${avl.observacoes}';
			var inicio = '${avl.inicio}';
			var fim = '${avl.fim}';
			inicio = inicio.replace(' 00:00:00.0','');
			fim = fim.replace(' 00:00:00.0','');
			var ano = ''+inicio.substr(0,4);
			var mes = ''+inicio.substr(5,2);
			var dia = ''+inicio.substr(8,9);
			inicio = ano+'-'+mes+'-'+dia;
			ano = ''+fim.substr(0,4);
			mes = ''+fim.substr(5,2);
			dia = ''+fim.substr(8,9);
			fim = ano+'-'+mes+'-'+dia;
			document.getElementById("inicio").value = inicio;
			document.getElementById("fim").value = fim;
		}
	</c:forEach>

}

</script>



<!-- start: page -->
<div class="row">
<form action="/cadastrarAvaliacao" id="pesqForm" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Cadastrar Avaliações</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon" onclick="habilitar()">
								<i class="fa fa-search"></i>
							</span>
							<input type="number" placeholder="Matrícula" name="matricula" id="matricula" class="form-control" value="${usuarioAlterar.matricula }" required>
						</div>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" value="${usuarioAlterar.nome }" disabled>
					</div>
					
					
					<div class="col-md-3 form-group">
						Início:
						<input type="date" name="inicio_avaliacao" id="inicio" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						Fim:
						<input type="date" name="fim_avaliacao" id="fim" class="form-control" required>
					</div>
					<div class="col-md-6 form-group">
					</div>
					<div class="col-md-12 form-group">
					</div>
					
					<div class="col-md-2 form-group">
						Idade:
						<input type="number" name="idade" id="idade" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Peso:
						<input type="number" step="0.010" name="peso" id="peso" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Altura:
						<input type="number" step="0.010" name="altura" id="altura" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Tríceps:
						<input type="number" step="0.010" name="triceps" id="triceps" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group">
						Abdômen (dobra cutânea):
						<input type="number" step="0.010" name="abdomenDobra" id="abdomenDobra" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Braço Direito:
						<input type="number" step="0.010" name="bracoDir" id="bracoDir" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Braço Esquerdo:
						<input type="number" step="0.010" name="bracoEsq" id="bracoEsq" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Antibraço Direito:
						<input type="number" step="0.010" name="antiBracoDir" id="antiBracoDir" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Antibraço Esquerdo:
						<input type="number" step="0.010" name="antiBracoEsq" id="antiBracoEsq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group">
						Abdômen Circunferência:
						<input type="number" step="0.010" name="abdomenCirc" id="abdomenCirc" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Quadril:
						<input type="number" step="0.010" name="quadril" id="quadril" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Cintura:
						<input type="number" step="0.010" name="cintura" id="cintura" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Coxa Direita:
						<input type="number" step="0.010" name="coxaDir" id="coxaDir" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Coxa Esquerda:
						<input type="number" step="0.010" name="coxaEsq" id="coxaEsq" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Perna Direita:
						<input type="number" step="0.010" name="pernaDir" id="pernaDir" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Perna Esquerda:
						<input type="number" step="0.010" name="pernaEsq" id="pernaEsq" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Rádio:
						<input type="number" step="0.010" name="radio" id="radio" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Fêmur:
						<input type="number" step="0.010" name="femur" id="femur" class="form-control" min="0">
					</div>
					<div class="col-md-3 form-group">
						Percentual de Gordura:
						<input type="number" step="0.010" name="gorduraCorporal" id="gorduraCorporal" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Classificação:
						<input type="number" step="0.010" name="classificacao" id="classificacao" class="form-control" min="0">
					</div>
					<div class="col-md-3 form-group">
						Massa Magra:
						<input type="number" step="0.010" name="massaMagra" id="massaMagra" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Massa Gorda:
						<input type="number" step="0.010" name="massaGorda" id="massaGorda" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Massa Muscular:
						<input type="number" step="0.010" name="massaMuscular" id="massaMuscular" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Massa Ossea:
						<input type="number" step="0.010" name="massaOssea" id="massaOssea" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Massa Residual:
						<input type="number" step="0.010" name="massaResidual" id="massaResidual" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						ICQ:
						<input type="number" step="0.010" name="icq" id="icq" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						IMC:
						<input type="number" step="0.010" name="imc" id="imc" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Classificação IMC:
						<input type="number" step="0.010" name="classificacaoIMC" id="classificacaoIMC" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Classificação ICQ:
						<input type="number" step="0.010" name="classificacaoICQ" id="classificacaoICQ" class="form-control" min="0">
					</div>
					
					
					
					
					
					
					
					
					
					
					
					<div class="col-md-12 form-group" style="color:red">
						<b>METAS</b>
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Peso:
						<input type="number" step="0.010" name="limite_peso" id="limite_peso" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Tríceps:
						<input type="number" step="0.010" name="limite_triceps" id="limite_triceps" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Abdômen (dobra cutânea):
						<input type="number" step="0.010" name="limite_abdomenDobra" id="limite_abdomenDobra" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Braço Direito:
						<input type="number" step="0.010" name="limite_bracoDir" id="limite_bracoDir" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Braço Esquerdo:
						<input type="number" step="0.010" name="limite_bracoEsq" id="limite_bracoEsq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Antibraço Direito:
						<input type="number" step="0.010" name="limite_antiBracoDir" id="limite_antiBracoDir" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Antibraço Esquerdo:
						<input type="number" step="0.010" name="limite_antiBracoEsq" id="limite_antiBracoEsq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Abdômen Circunferência:
						<input type="number" step="0.010" name="limite_abdomenCirc" id="limite_abdomenCirc" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Quadril:
						<input type="number" step="0.010" name="limite_quadril" id="limite_quadril" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Cintura:
						<input type="number" step="0.010" name="limite_cintura" id="limite_cintura" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Coxa Direita:
						<input type="number" step="0.010" name="limite_coxaDir" id="limite_coxaDir" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Coxa Esquerda:
						<input type="number" step="0.010" name="limite_coxaEsq" id="limite_coxaEsq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Perna Direita:
						<input type="number" step="0.010" name="limite_pernaDir" id="limite_pernaDir" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Perna Esquerda:
						<input type="number" step="0.010" name="limite_pernaEsq" id="limite_pernaEsq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Rádio:
						<input type="number" step="0.010" name="limite_radio" id="limite_radio" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Fêmur:
						<input type="number" step="0.010" name="limite_femur" id="limite_femur" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Percentual de Gordura:
						<input type="number" step="0.010" name="limite_gorduraCorporal" id="limite_gorduraCorporal" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Massa Magra:
						<input type="number" step="0.010" name="limite_massaMagra" id="limite_massaMagra" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Massa Gorda:
						<input type="number" step="0.010" name="limite_massaGorda" id="limite_massaGorda" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Massa Muscular:
						<input type="number" step="0.010" name="limite_massaMuscular" id="limite_massaMuscular" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de Massa Ossea:
						<input type="number" step="0.010" name="limite_massaOssea" id="limite_massaOssea" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Massa Residual:
						<input type="number" step="0.010" name="limite_massaResidual" id="limite_massaResidual" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de ICQ:
						<input type="number" step="0.010" name="limite_icq" id="limite_icq" class="form-control" min="0">
					</div>
					<div class="col-md-4 form-group" style="color:red">
						Meta de IMC:
						<input type="number" step="0.010" name="limite_imc" id="limite_imc" class="form-control" min="0">
					</div>
					
					
					
					
					
					
					
					
					
					
					
					<div class="col-md-12 form-group">
						Observações:
						<textArea name="observacoes" id="observacoes" class="form-control"></textArea>
					</div>
					
					<div class="col-md-12 form-group">
					</div>
					<c:if test="${matricula != null }">
						<div class="col-md-2 form-group">
							<a class="btn btn-primary" onclick="acao('salvar')" id="btAdd" style="display:block" >Salvar</a>
						</div>
						<div class="col-md-2 form-group" id="atualizar" style="display:none">
							<a class="btn btn-warning" onclick="acao('atualizar')" >Atualizar</a>
						</div>
						<div class="col-md-2 form-group">
							<a class="btn btn-danger" onclick="desfazer()" id="btDesfazer" style="display:block" >Cancelar</a>
						</div>
						<div class="col-md-12 form-group">
						</div>
					</c:if>
					<i id="avaliacaoPreencher" style="color:red"></i>
					<input type="hidden" id="acao" name="acao" value="0">
				</div>
			</div>
		</div>
	</div>
</form>
</div>






<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Avaliação</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Edição</th>
											<th>Matrícula</th>
											<th>Nome</th>
											<th>Celular</th>
											<th>Observações</th>
											<th>Avaliador</th>
											<th>Início</th>
											<th>Fim</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${avaliacao }" var="a">
											<tr class="gradeX">
												<td>
													<i class="fa fa-trash" onclick="modalDeletar('avaliacao', ${a.id}) "></i> &nbsp
													<i class="fa fa-pencil" onclick="editar(${a.id }) "></i>
												</td>
												<td>${a.codigo }</td>
												<td>${a.aluno.nome }</td>
												<td>${a.aluno.celular }</td>
												<td>${a.observacoes }</td>
												<td>${a.avaliador.nome }</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.inicio }" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.fim }" /></td>
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