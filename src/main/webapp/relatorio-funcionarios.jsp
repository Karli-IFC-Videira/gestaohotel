<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header-administrador.jsp"/>

<link rel="stylesheet" href="estilos/estilo-index.css">

<h1>Relatório de Funcionários</h1>

<div class="container">
    <table id="tabela-funcionarios" class="display tabela-centralizada">
        <thead>
            <tr>
                <th>CPF</th>
                <th>Nome</th>
                <th>Nível de Acesso</th>
                <th>Email</th>
                <th>Celular</th>
                <th>Data de Nascimento</th>
                <th>Usuário</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.print.min.js"></script>
<script src="js/DataTablesConsultaFuncionarios.js"></script>

<jsp:include page="footer.jsp"/>
