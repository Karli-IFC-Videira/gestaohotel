<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header-administrador.jsp"/>

    <section class="form-container">   
        <h1>Cadastro de Produtos</h1>
        <p class="required-message">(*) São campos obrigatórios para preenchimento</p>

        <form id="cadastro-produtos" action="cadastrar-produto" method="post" accept-charset="UTF-8">
            <div class="input-group">

                <div class="form-item">
                    <label for="produto">*Produto</label>
                    <input type="text" id="produto" name="produto" required>
                </div>

                <div class="form-item">
                    <label for="quantidade">*Quantidade</label>
                    <input type="number" min="1" step="1" id="quantidade" name="quantidade" required>
                </div>

                <div class="form-item">
                    <label for="preco-compra">*Preço de compra</label>
                    <input type="number" min="1" step="0.01" id="preco-compra" name="preco_compra" required>
                </div>

                <div class="form-item">
                    <label for="preco-venda">*Preço de venda</label>
                    <input type="number" min="1" step="0.01" id="preco-venda" name="preco_venda" required>
                </div>

                <div class="form-item">
                    <label for="marca">*Marca</label>
                    <input type="text" id="marca" name="marca" required>
                </div>
            </div>

            <div class="button-group">
                <!-- Botões alinhados à esquerda -->
                <div class="btn-group-right">
                    <button type="submit" class="btn cadastrar-produto">Cadastrar</button>
                </div>
            </div>
        </form>
    </section>

    <section class="container-tabela">
    <table id="tabela-produtos" class="display tabela-centralizada">
        <thead>
            <tr>
            	<th>Código</th>
                <th>Produto</th>
                <th>Quantidade</th>
                <th>Preço de compra</th>
                <th>Preço de venda</th>
                <th>Marca</th>
                <th>Nome do Usuário</th>
                <th>Opções</th>
            </tr>
        </thead>
        <tbody>
            <!-- Os dados serão preenchidos via DataTables -->
        </tbody>
    </table>
	</section>

<!-- Modal de Edição -->
<div class="modal fade" id="editarModal" tabindex="-1" role="dialog" aria-labelledby="editarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarModalLabel">Editar Produto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id"> <!-- Para armazenar o ID do produto -->
                <div class="form-group">
                    <label>Código do Produto</label>
                    <p id="codigoProduto"></p> <!-- Exibe o código do produto, não editável -->
                </div>
                <div class="form-group">
                    <label for="produt">Nome do Produto</label>
                    <input type="text" class="form-control" id="produt" required>
                </div>
                <div class="form-group">
                    <label for="qtd">Quantidade</label>
                    <input type="number" min="1" class="form-control" id="qtd" required>
                </div>
                <div class="form-group">
                    <label for="pCompra">Preço de Compra</label>
                    <input type="number" min="1" step="0.01" class="form-control" id="pCompra" required>
                </div>
                <div class="form-group">
                    <label for="pVenda">Preço de Venda</label>
                    <input type="number" min="1" step="0.01" class="form-control" id="pVenda" required>
                </div>
                <div class="form-group">
                    <label for="marcas">Marca</label>
                    <input type="text" class="form-control" id="marcas" required>
                </div>
                <div class="form-group">
                    <label>Nome do Usuário</label>
                    <p id="usuario"></p> <!-- Exibe o nome do usuário, não editável -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary fechar" data-bs-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="salvarMudancas">Salvar Mudanças</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal de Confirmação -->
<div class="modal fade" id="confirmacaoModal" tabindex="-1" aria-labelledby="confirmacaoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmacaoModalLabel">Confirmação</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Tem certeza de que deseja excluir este registro?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                <button type="button" class="btn btn-danger" id="confirmarExcluir">Sim</button>
            </div>
        </div>
    </div>
</div>

<% 
	String mensagem = (String) request.getSession().getAttribute("msg");
	if (mensagem != null) { 
%>
    <script>
        Swal.fire({
            title: 'Mensagem',
            text: '<%= mensagem %>',
            icon: 'success'
        });
    </script>
<% 
    request.getSession().removeAttribute("msg"); // Remove a mensagem após exibi-la
	} 
%>

    <!-- DataTables-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/2.1.7/js/dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/2.1.7/js/dataTables.bootstrap5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.1.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.bootstrap5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.colVis.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/keytable/2.12.1/js/dataTables.keyTable.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/3.0.3/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/3.0.3/js/responsive.bootstrap5.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/DataTablesProdutos.js"></script>