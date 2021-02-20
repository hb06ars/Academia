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


<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

	 var data = google.visualization.arrayToDataTable([
	        ['Descrição', 'Total', 'Meta'],
			<c:forEach items='${avaliacao }' var='a'>
				['Peso', ${a.peso },${a.limite_peso }],
				['Tríceps', ${a.triceps },${a.limite_triceps }],
				['Abdômen Cutânea', ${a.abdomenDobra },${a.limite_abdomenDobra }],
				['Braço direito', ${a.bracoDir },${a.limite_bracoDir }],
				['Braço esquerdo', ${a.bracoEsq },${a.limite_bracoEsq }],
				['Anti Braço direito', ${a.antiBracoDir },${a.limite_antiBracoDir }],
				['Anti Braço esquerdo', ${a.antiBracoEsq },${a.limite_antiBracoEsq }],
				['Abdômen Circunferência', ${a.abdomenCirc },${a.limite_abdomenCirc }],
				['Quadril', ${a.quadril },${a.limite_quadril }],
				['Cintura', ${a.cintura },${a.limite_cintura }],
				['Coxa direita', ${a.coxaDir },${a.limite_coxaDir }],
				['Coxa esquerda', ${a.coxaEsq },${a.limite_coxaEsq }],
				['Perna direita', ${a.pernaDir },${a.limite_pernaDir }],
				['Perna esquerda', ${a.pernaEsq },${a.limite_pernaEsq }],
				['Rádio', ${a.radio },${a.limite_radio }],
				['Fêmur', ${a.femur },${a.limite_femur }],
				['Gordura Corporal', ${a.gorduraCorporal },${a.limite_gorduraCorporal }],
				['Massa Magra', ${a.massaMagra },${a.limite_massaMagra }],
				['Massa Gorda', ${a.massaGorda },${a.limite_massaGorda }],
				['Massa Muscular', ${a.massaMuscular },${a.limite_massaMuscular }],
				['Massa Óssea', ${a.massaOssea },${a.limite_massaOssea }],
				['Massa Residual', ${a.massaResidual },${a.limite_massaResidual }],
				['ICQ', ${a.icq },${a.limite_icq }],
				['IMC', ${a.imc },${a.limite_imc }]
			</c:forEach>
      ]);

      var options = {
        title: 'AVALIAÇÃO FÍSICA',
        chartArea: {width: '50%'},
        hAxis: {
          title: '',
          minValue: 0
        },
        vAxis: {
          title: ''
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }

</script>











<section class="panel">
							<header class="panel-heading bg-primary">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								
								<div class="widget-profile-info">
									<div class="profile-picture">
										<img src="${usuario.pathImagem }">
									</div>
									<div class="profile-info">
										<h4 class="name text-weight-semibold">${usuario.nome }</h4>
										<c:set var = "ultimoDiaExecutado" value = "0"/>
										<h5 class="role">Avaliação</h5>
										<div class="profile-footer">
											<a href="#">${usuario.perfil.nome }</a>
										</div>
									</div>
								</div>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
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






<section class="panel">
	<div class="panel-body" >
		  <div id="chart_div" style="width: 1000px; height: 1000px;" style="overflow:auto"></div>
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
<script src="assets/javascripts/ui-elements/examples.charts.js"></script>
<jsp:include page="includes/footer.jsp" />
<!-- HEADER -->