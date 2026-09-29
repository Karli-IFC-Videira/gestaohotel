<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header-administrador.jsp"/>

    <section class="form-container">   
        <h1>Relatório de Vendas</h1>
        <p class="info-message">Visualize o relatório detalhado de todas as vendas</p>
    </section>

    <section class="container-tabela">
        <table id="tabela-relatorio-vendas" class="display tabela-centralizada">
            <thead>
                <tr>
                    <th>Código Venda</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>Atendente</th>
                    <th>Código Produto</th>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <!-- Os dados serão preenchidos via DataTables -->
            </tbody>
        </table>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.2.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.bootstrap5.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.print.min.js"></script>
    <script src="js/DataTablesRelatorioVendas.js"></script>
</body>
</html>
