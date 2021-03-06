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

function verContrato(){
	var data = new Date(),
    dia  = data.getDate().toString(),
    diaF = (dia.length == 1) ? '0'+dia : dia,
    mes  = (data.getMonth()+1).toString(), //+1 pois no getMonth Janeiro come�a com zero.
    mesF = (mes.length == 1) ? '0'+mes : mes,
    anoF = data.getFullYear(),
	valor = diaF+"/"+mesF+"/"+anoF;
    var dataVal = valor;
	var arr = dataVal.split("/").reverse();
	var teste = new Date(arr[0], arr[1] - 1, arr[2]);
	var dia = teste.getDay();
    valor = valor;
    document.getElementById("dataHojeContrato").innerHTML = 'Data:  '+valor;
    
	matricula = document.getElementById("matricula").value;
	var inicio = 'x';
	var fim = 'x';
	<c:forEach items="${usuarios }" var="u" varStatus="s">
		if(${u.matricula} == matricula){
			document.getElementById("co_matricula").innerHTML = '${u.matricula}';
			document.getElementById("co_nome").innerHTML = '${u.nome}';
			document.getElementById("co_nomeCliente").innerHTML = '${u.nome}';
			document.getElementById("co_cpf").innerHTML = '${u.cpf}';
			var nasc = '${u.dataNascimento}';
			nasc = nasc.substr(8,10)+'/'+nasc.substr(5,2)+'/'+nasc.substr(0,4);
			document.getElementById("co_nasc").innerHTML = nasc ;
			document.getElementById("co_tel").innerHTML = '${u.telefone}';
			document.getElementById("co_cel").innerHTML = '${u.celular}';
			document.getElementById("co_email").innerHTML = '${u.email}';
			document.getElementById("co_endereco").innerHTML = '${u.endereco}';
			document.getElementById("co_bairro").innerHTML = '${u.bairro}';
			document.getElementById("co_cidade").innerHTML = '${u.cidade}';
			document.getElementById("co_estado").innerHTML = '${u.estado}';
			document.getElementById("co_cep").innerHTML = '${u.cep}';
			document.getElementById("co_plano").innerHTML = '${u.plano.nome}';
			<c:forEach items="${u.contrato }" var="c">
				<c:if test="${c.ativo == true}">
					document.getElementById("co_totalContrato").innerHTML = 'R$'+'${c.valorBruto}';
					document.getElementById("co_obs").innerHTML = '${c.observacoes}';
					document.getElementById("co_total").innerHTML = '<b>R$</b>'+'<b>${c.valor}</b>';
					document.getElementById("co_sinal").innerHTML = 'R$'+'${c.sinal}';
					document.getElementById("co_desconto").innerHTML = 'R$'+'${c.desconto}';
					document.getElementById("co_valorDaParcela").innerHTML = 'R$'+'${c.valorDaParcela}';
					document.getElementById("co_vencimento").innerHTML = '${c.vencimento}';
					document.getElementById("co_parcelas").innerHTML = '${c.parcelas}x';
					inicio = '${c.inicio}'.replace('00:00:00','').replace('.0','').replace(' ','')
					fim = '${c.fim}'.replace('00:00:00','').replace('.0','').replace(' ','')
					inicio = inicio.substr(8,10)+'/'+inicio.substr(5,2)+'/'+inicio.substr(0,4);
					fim = fim.substr(8,10)+'/'+fim.substr(5,2)+'/'+fim.substr(0,4);
					document.getElementById("co_inicio").innerHTML = inicio;
					document.getElementById("co_fim").innerHTML = fim;
	
					document.getElementById("co_inicio").disabled = true;
					document.getElementById("co_fim").disabled = true;
					document.getElementById("co_totalContrato").disabled = true;
					document.getElementById("co_sinal").disabled = true;
					document.getElementById("co_desconto").disabled = true;
					document.getElementById("co_total").disabled = true;
					document.getElementById("co_parcelas").disabled = true;
					document.getElementById("co_vencimento").disabled = true;
					document.getElementById("co_valorDaParcela").disabled = true;
					document.getElementById("co_obs").disabled = true;
				</c:if>
			</c:forEach>
		}
	</c:forEach>
	var minhaTabela = document.getElementById('tabelaContrato').innerHTML;
    var win = window.open('', '', 'height=700,width=700');

    var style = "<style>";
    style = style + "table {width: 100%;font: 20px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    style = style + "padding: 2px 3px;text-align: left;}";
    style = style + "</style>";
    win.document.write('<html><head>');
    win.document.write('<title>Empregados</title>');
    win.document.write(style);
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(minhaTabela);
    win.document.write('</body></html>');
    win.document.close();
    win.print();         

}



function fMasc(objeto,mascara) {
	obj=objeto
	masc=mascara
	setTimeout("fMascEx()",1)
}
function fMascEx() {
	obj.value=masc(obj.value)
}
function mTel(tel) {
	tel=tel.replace(/\D/g,"")
	tel=tel.replace(/^(\d)/,"($1")
	tel=tel.replace(/(.{3})(\d)/,"$1)$2")
	if(tel.length == 9) {
		tel=tel.replace(/(.{1})$/,"-$1")
	} else if (tel.length == 10) {
		tel=tel.replace(/(.{2})$/,"-$1")
	} else if (tel.length == 11) {
		tel=tel.replace(/(.{3})$/,"-$1")
	} else if (tel.length == 12) {
		tel=tel.replace(/(.{4})$/,"-$1")
	} else if (tel.length > 12) {
		tel=tel.replace(/(.{4})$/,"-$1")
	}
	return tel;
}
function mCNPJ(cnpj){
	cnpj=cnpj.replace(/\D/g,"")
	cnpj=cnpj.replace(/^(\d{2})(\d)/,"$1.$2")
	cnpj=cnpj.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3")
	cnpj=cnpj.replace(/\.(\d{3})(\d)/,".$1/$2")
	cnpj=cnpj.replace(/(\d{4})(\d)/,"$1-$2")
	return cnpj
}
function mCPF(cpf){
	cpf=cpf.replace(/\D/g,"")
	cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
	cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
	cpf=cpf.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
	return cpf
}
function mCEP(cep){
	cep=cep.replace(/\D/g,"")
	cep=cep.replace(/^(\d{5})(\d)/,"$1-$2")
	return cep
}
function mNum(num){
	num=num.replace(/\D/g,"")
	return num
}

function mudaPlano(){
	var x = document.getElementById("plano").value;
	document.getElementById("contrato_totalContrato").value = 0;
	var valor = 0;
	<c:forEach items="${planos}" var="pl">
		if(x == '${pl.id}'){
			valor = '${pl.valor}';
			document.getElementById("contrato_totalContrato").value = valor;
			calcular();
		}
	</c:forEach>
}

function calcular(){
	var totalContrato = document.getElementById("contrato_totalContrato").value.replace(',','.');
	document.getElementById("contrato_totalContrato").value = totalContrato; 
	var sinal = document.getElementById("contrato_sinal").value.replace(',','.');
	var desconto = document.getElementById("contrato_desconto").value.replace(',','.');
	var parcelas = document.getElementById("contrato_parcelas").value.replace(',','.');
	var valorFinal = totalContrato - sinal - desconto;
	document.getElementById("contrato_valorDaParcela").value = (valorFinal / parcelas).toFixed([2]);
	document.getElementById("contrato_total").value = valorFinal.toFixed([2]);
}

function acao(valor){
	document.getElementById("acao").value = valor;
}

function cancelar(){
	document.getElementById("contrato_inicio").disabled = false;
	document.getElementById("contrato_fim").disabled = false;
	document.getElementById("contrato_totalContrato").disabled = true;
	document.getElementById("contrato_sinal").disabled = false;
	document.getElementById("contrato_desconto").disabled = false;
	document.getElementById("contrato_total").disabled = false;
	document.getElementById("contrato_parcelas").disabled = false;
	document.getElementById("contrato_vencimento").disabled = false;
	document.getElementById("contrato_valorDaParcela").disabled = false;
	document.getElementById("contrato_obs").disabled = false;

	document.getElementById("matricula").value = ${matriculaPadrao };
	document.getElementById("nome").value = '';
	document.getElementById("cpf").value = '';
	document.getElementById("dataNascimento").value = '';
	document.getElementById("telefone").value = '';
	document.getElementById("celular").value = '';
	document.getElementById("email").value = '';
	document.getElementById("endereco").value = '';
	document.getElementById("bairro").value = '';
	document.getElementById("cidade").value = '';
	document.getElementById("estado").value = '';
	document.getElementById("cep").value = '';
	document.getElementById("pathImagem").value = '';

	document.getElementById("acao").value = '';
	document.getElementById("atualizar").style.display = "none";
	document.getElementById("contrato").style.display = "none";
	document.getElementById("salvar").style.display = "block";
	document.getElementById("cancelar").style.display = "none";
}

function editar(id){
	document.getElementById("acao").value = 'atualizar';
	document.getElementById("atualizar").style.display = "block";
	document.getElementById("contrato").style.display = "block";
	document.getElementById("salvar").style.display = "none";
	document.getElementById("cancelar").style.display = "block";
	
	var inicio = 'x';
	var fim = 'x';
	<c:forEach items="${usuarios }" var="u" varStatus="s">
		if(${u.id} == id){
			document.getElementById("matricula").value = '${u.matricula}';
			document.getElementById("nome").value = '${u.nome}';
			document.getElementById("cpf").value = '${u.cpf}';
			document.getElementById("dataNascimento").value = '${u.dataNascimento}';
			document.getElementById("telefone").value = '${u.telefone}';
			document.getElementById("celular").value = '${u.celular}';
			document.getElementById("email").value = '${u.email}';
			document.getElementById("endereco").value = '${u.endereco}';
			document.getElementById("bairro").value = '${u.bairro}';
			document.getElementById("cidade").value = '${u.cidade}';
			document.getElementById("estado").value = '${u.estado}';
			document.getElementById("cep").value = '${u.cep}';
			document.getElementById("pathImagem").value = '${u.pathImagem}';
			document.getElementById("plano").value = '${u.plano.id}';
			<c:forEach items="${u.contrato }" var="c">
				<c:if test="${c.ativo == true}">
					document.getElementById("contrato_totalContrato").value = '${c.valorBruto}';
					document.getElementById("contrato_obs").value = '${c.observacoes}';
					document.getElementById("contrato_total").value = '${c.valor}';
					document.getElementById("contrato_sinal").value = '${c.sinal}';
					document.getElementById("contrato_desconto").value = '${c.desconto}';
					document.getElementById("contrato_valorDaParcela").value = '${c.valorDaParcela}';
					document.getElementById("contrato_vencimento").value = '${c.vencimento}';
					document.getElementById("contrato_parcelas").value = '${c.parcelas}';
					inicio = '${c.inicio}'.replace('00:00:00','').replace('.0','').replace(' ','')
					fim = '${c.fim}'.replace('00:00:00','').replace('.0','').replace(' ','')
					document.getElementById("contrato_inicio").value = inicio;
					document.getElementById("contrato_fim").value = fim;

					document.getElementById("contrato_inicio").disabled = true;
					document.getElementById("contrato_fim").disabled = true;
					document.getElementById("contrato_totalContrato").disabled = true;
					document.getElementById("contrato_sinal").disabled = true;
					document.getElementById("contrato_desconto").disabled = true;
					document.getElementById("contrato_total").disabled = true;
					document.getElementById("contrato_parcelas").disabled = true;
					document.getElementById("contrato_vencimento").disabled = true;
					document.getElementById("contrato_valorDaParcela").disabled = true;
					document.getElementById("contrato_obs").disabled = true;
				</c:if>
			</c:forEach>
			<c:if test="${u.contrato.size() < 1 }">
				document.getElementById("contrato_inicio").disabled = false;
				document.getElementById("contrato_fim").disabled = false;
				document.getElementById("contrato_totalContrato").disabled = true;
				document.getElementById("contrato_sinal").disabled = false;
				document.getElementById("contrato_desconto").disabled = false;
				document.getElementById("contrato_total").disabled = false;
				document.getElementById("contrato_parcelas").disabled = false;
				document.getElementById("contrato_vencimento").disabled = false;
				document.getElementById("contrato_valorDaParcela").disabled = false;
				document.getElementById("contrato_obs").disabled = false;
	
				document.getElementById("contrato_inicio").value = '';
				document.getElementById("contrato_fim").value = '';
				document.getElementById("contrato_obs").value = '';
				document.getElementById("contrato_totalContrato").value = '0';
				document.getElementById("contrato_sinal").value = '0';
				document.getElementById("contrato_desconto").value = '0';
				document.getElementById("contrato_total").value = '0';
				document.getElementById("contrato_parcelas").value = '1';
				document.getElementById("contrato_vencimento").value = '1';
				document.getElementById("contrato_valorDaParcela").value = '0';
			</c:if>
			
			
		}
	</c:forEach>

		
}


</script>








<div id="tabelaContrato" style="display:none;">
<table border="1"> 
            <tr>
                <th colspan="6"><b>CONTRATO ACADEMIA</b><br></br></th>
            </tr>
            <tr>
                <td colspan="6">&nbsp</td>
            </tr>
            <tr>
                <td colspan="6"><b>Cliente</b> </td>
            </tr>
            <tr>
                <td>Matr�cula: </td><td colspan="2" id="co_matricula">-</td>
                <td>CPF: </td><td colspan="2" id="co_cpf">-</td>
            </tr>
            <tr>
                <td>Nome: </td><td colspan="2" id="co_nome">-</td>
                <td>Nasc: </td><td colspan="2" id="co_nasc">-</td>
            </tr>
            <tr>
                <td>Tel: </td><td colspan="2" id="co_tel">-</td>
                <td>Cel: </td><td colspan="2" id="co_cel">-</td>
            </tr>
            <tr>
                <td>Email: </td><td colspan="5" id="co_email">-</td>
            </tr>
            <tr>
                <td>Endere�o: </td><td colspan="2" id="co_endereco">-</td>
                <td>Bairro: </td><td colspan="2" id="co_bairro">-</td>
            </tr>
            <tr>
                <td>Cidade: </td><td colspan="2" id="co_cidade">-</td>
                <td>Estado: </td><td colspan="2" id="co_estado">-</td>
            </tr>
            <tr>
                <td>CEP: </td><td colspan="5" id="co_cep">-</td>
            </tr>
            <tr>
                <td colspan="6">&nbsp</td>
            </tr>
            <tr>
                <td colspan="6"><b>Contrato</b> </td>
            </tr>
            <tr>
                <td>Plano: </td><td colspan="5" id="co_plano">-</td>
            </tr>
            <tr>
                <td>Obs: </td><td colspan="5" id="co_obs">-</td>
            </tr>
            <tr>
                <td>In�cio Contrato: </td><td colspan="2" id="co_inicio">-</td>
                <td>Fim Contrato: </td><td colspan="2" id="co_fim">-</td>
            </tr>
            <tr>
                <td>Total Contrato: </td><td colspan="5" id="co_totalContrato">-</td>
            </tr>
            <tr>
                <td>Sinal: </td><td colspan="2" style="color:red" id="co_sinal">-</td>
                <td>Desconto: </td><td colspan="2" style="color:red" id="co_desconto">-</td>
            </tr>
            <tr>
                <td><b>Total a Pagar: </b></td><td colspan="5" id="co_total"><b>-</b></td>
            </tr>
            <tr>
                <td colspan="6">&nbsp</td>
            </tr>
             <tr>
                <td colspan="6"><b>Formas de Pagamento</b> </td>
            </tr>
            <tr>
                <td>Parcelas: </td><td id="co_parcelas">-</td>
                <td>Vencimento dia: </td><td id="co_vencimento">-</td>
                <td>Valor da Parcela: </td><td id="co_valorDaParcela">-</td>
            </tr>
            <tr>
                <td colspan="6">&nbsp</td>
            </tr>
             <tr>
                <td colspan="6"><b>Assinatura</b> </td>
            </tr>
            <tr>
                <td ><br><br>______________________________________ <br> <div  id="co_nomeCliente" align="center">-</div></td>
                <td colspan="4">&nbsp</td>
                 <td><br><br>______________________________________ <br> <div align="center">${usuario.nome}</div></td>
            </tr>
            <tr>
                <td colspan="6"><div id="dataHojeContrato" align="center"></div> </td>
            </tr>
            
            <tr>
        </table>
</div>



<!-- start: page -->
<c:if test="${usuario.perfil.admin == true}">
<div class="row">
<form action="/alunos" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Cadastrar novo aluno</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3 form-group">
						<input type="number" placeholder="Matr�cula" name="matricula" id="matricula" class="form-control" value="${matriculaPadrao }"required>
					</div>
					<div class="col-md-4 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" required>
					</div>
					<div class="col-md-2 form-group">
						<input type="text" id="cpf" name="cpf" maxlength="14" placeholder="CPF" minlength="14" onkeydown="javascript: fMasc( this, mCPF );" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-birthday-cake"></i>
							</span>
							<input type="date" name="dataNascimento" id="dataNascimento" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="text" id="telefone" name="telefone" placeholder="Telefone" maxlength="14" minlength="13" onkeydown="javascript: fMasc( this, mTel );" class="form-control" >
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="text" id="celular" name="celular" placeholder="Celular" maxlength="14" minlength="14" onkeydown="javascript: fMasc( this, mTel );" class="form-control" >
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-envelope"></i>
							</span>
							<input type="email" name="email" id="email" class="form-control" placeholder="eg.: email@email.com" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<input type="text" placeholder="Endere�o" id="endereco" name="endereco" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Bairro" id="bairro" name="bairro" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Cidade" id="cidade" name="cidade" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="Estado" id="estado" maxlength="2" minlength="2" name="estado" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" id="cep" name="cep" placeholder="99999-999" maxlength="9" minlength="9" onkeydown="javascript: fMasc( this, mCEP );" class="form-control" >
					</div>
					<div class="col-md-10 form-group">
						<input type="text" placeholder="Link da Foto" id="pathImagem" name="pathImagem" class="form-control">
					</div>
					
					
					
					<div class="col-md-4 form-group">
						<select id="plano" onchange="mudaPlano()" name="plano.id" class="form-control">
							<option value="" selected>Escolha um plano</option>
							<c:forEach items="${planos }" var="p">
								<option value="${p.id }">Plano ${p.nome } (${p.descricao})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Observa��es no Contrato"  id="contrato_obs" name="contrato_obs" class="form-control">
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								In�cio Contrato
							</span>
							<input type="date" name="contrato_inicio"   id="contrato_inicio" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Fim Contrato
							</span>
							<input type="date" name="contrato_fim" id="contrato_fim" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Total do Contrato
							</span>
							<input type="text" name="contrato_totalContrato" id="contrato_totalContrato" onkeyup="calcular()" class="form-control" readonly required/>
						</div>
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Sinal
							</span>
							<input type="text" name="contrato_sinal" id="contrato_sinal" onkeyup="calcular()" min="0" value="0" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Desconto
							</span>
							<input type="text" name="contrato_desconto" id="contrato_desconto" onkeyup="calcular()" min="0" value="0" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Total a pagar
							</span>
							<input type="text" name="contrato_total" id="contrato_total" onkeyup="calcular()" min="0" value="0" class="form-control" required/>
						</div>
					</div>
					
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Parcelas
							</span>
							<input type="number" name="contrato_parcelas" id="contrato_parcelas" onkeyup="calcular()" placeholder="1" min="1" value="1" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Vencimento
							</span>
							<input type="number" name="contrato_vencimento" id="contrato_vencimento" placeholder="1" min="1" max="31" value="1" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-6 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Valor da Parcela
							</span>
							<input type="text" name="contrato_valorDaParcela" id="contrato_valorDaParcela" onkeyup="calcular()" value="0" class="form-control" required/>
						</div>
					</div>
					<div class="col-md-2 form-group" id="salvar">
						<input type="submit" class="btn btn-primary" onclick="acao('salvar')" value="Criar">
					</div>
					<div class="col-md-2 form-group" id="atualizar" style="display:none">
						<input type="submit" class="btn btn-primary" onclick="acao('atualizar')" value="Atualizar">
					</div>
					<div class="col-md-2 form-group" id="cancelar" style="display:none">
						<input type="button" class="btn btn-danger" onclick="cancelar()" value="Voltar">
					</div>
					<div class="col-md-2 form-group" id="contrato" style="display:none">
						<input type="button" class="btn btn-success" onclick="verContrato()" value="Ver Contrato">
					</div>
					<input type="hidden" id="acao" name="acao" value="salvar">
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
						
								<h2 class="panel-title">Registro dos Alunos</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<c:if test="${usuario.perfil.admin == true}">
											<th>Editar</th>
											<th>Matr�cula</th>
											<th>Situa��o</th>
											<th>Contrato</th>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Celular</th>
											<th>Email</th>
											<th>Endere�o</th>
											<th>Bairro</th>
											<th>Cidade</th>
											<th>Estado</th>
											<th>CPF</th>
											</c:if>
											<c:if test="${usuario.perfil.funcionario == true || usuario.perfil.professor == true}">
												<th>Matr�cula</th>
												<th>Nome</th>
												<th>Data de Nascimento</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios }" var="u">
											<tr class="gradeX">
												<c:if test="${usuario.perfil.admin == true}">
													<td>
														<i class="fa fa-trash" onclick="modalDeletar('usuario', ${u.id}) "></i> &nbsp
														<i class="fa fa-pencil" onclick="editar(${u.id }) "></i>
													</td>
												<td>${u.matricula }</td>
												<c:set var = "index" value = "0"/>
												<c:forEach items="${u.contrato }" var="c">
													<c:if test="${c.ativo }">
														<c:set var = "index" value = "1"/>
														<c:if test="${c.situacao == 'Regular'}">
															<td style="color:green">${c.situacao }</td>
														</c:if>
														<c:if test="${c.situacao == 'Pendente'}">
															<td style="color:red">${c.situacao }</td>
														</c:if>
														<td>
															<fmt:formatDate pattern="dd/MM/yyyy" value="${c.fim }" />
														</td>
													</c:if>
												</c:forEach>
												<c:if test="${index < 1}">
													<td style="color:blue">Renovar</td>
												</c:if>
												<c:if test="${index < 1}">
													<td style="color:blue">Renovar</td>
												</c:if>
												<td>${u.nome }</td>
												<td>${u.telefone }</td>
												<td>${u.celular }</td>
												<td>${u.email }</td>
												<td>${u.endereco }</td>
												<td>${u.bairro }</td>
												<td>${u.cidade }</td>
												<td>${u.estado }</td>
												<td>${u.cpf }</td>
												</c:if>
												<c:if test="${usuario.perfil.funcionario == true || usuario.perfil.professor == true}">
													<td>${u.matricula }</td>
													<td>${u.nome }</td>
													<td>
													<c:set var="nascimento" value="${fn:substring(u.dataNascimento, 8, 10)}/${fn:substring(u.dataNascimento, 5, 7)}/${fn:substring(u.dataNascimento, 0, 4)}" />
													${nascimento }
													</td>
												</c:if>
											
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