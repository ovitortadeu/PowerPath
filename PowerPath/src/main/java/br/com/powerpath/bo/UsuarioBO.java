package br.com.powerpath.bo;

import br.com.powerpath.dao.UsuarioDAO;
import br.com.powerpath.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe de negócios responsável pelo gerenciamento das operações relacionadas a usuários.
 */
public class UsuarioBO {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Insere um novo usuário no sistema. Faz uma pequena validação do papel do usuário.
     *
     * @param usuario objeto do tipo UsuarioTO contendo os dados do usuário a ser inserido.
     * @return o objeto UsuarioTO inserido no sistema.
     * @throws Exception em caso de erro durante a inserção.
     */
    public UsuarioTO inserir(UsuarioTO usuario) throws Exception {
        usuarioDAO = new UsuarioDAO();
        try {
            // Valida se o ROLE está presente
            if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
                throw new IllegalArgumentException("O campo 'ROLE' é obrigatório.");
            }
            // Converte o ROLE para letras maiúsculas por padronização
            usuario.setRole(usuario.getRole().toUpperCase());
            // Valida se o ROLE é válido (apenas GUEST ou USER)
            if (!usuario.getRole().equals("GUEST") && !usuario.getRole().equals("USER")) {
                throw new IllegalArgumentException("O campo 'ROLE' deve ser 'GUEST' ou 'USER'.");
            }
            if (usuario.getPontos() == null) {
                usuario.setPontos(0L);
            }
            return usuarioDAO.inserir(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao inserir o usuário: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Altera os dados de um usuário existente no sistema.
     *
     * @param usuarioTO objeto do tipo UsuarioTO contendo os dados atualizados do usuário.
     * @return o objeto UsuarioTO com os dados alterados.
     * @throws Exception em caso de erro durante a alteração.
     */
    public UsuarioTO alterar(UsuarioTO usuarioTO) {
        try {
            if (usuarioTO.getPontos() == null) {
                usuarioTO.setPontos(0L);
            }
            return usuarioDAO.alterar(usuarioTO);
        } catch (Exception e) {
            System.out.println("Erro ao alterar o usuário: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um usuário do sistema pelo seu identificador.
     *
     * @param idUsuario identificador único do usuário a ser excluído.
     * @return {@code true} se a exclusão foi realizada com sucesso, ou {@code false} em caso de falha.
     */
    public boolean excluir(int idUsuario) {
        return usuarioDAO.excluir(idUsuario);
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return uma lista de objetos UsuarioTO contendo os dados de todos os usuários cadastrados.
     */
    public ArrayList<UsuarioTO> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    /**
     * Busca um usuário no sistema pelo seu identificador.
     *
     * @param idUsuario identificador único do usuário a ser visualizado.
     * @return o objeto UsuarioTO correspondente ao identificador fornecido, ou {@code null} se não for encontrado.
     */
    public UsuarioTO visualizarPeloCodigo(int idUsuario) {
        return usuarioDAO.visualizarPeloCodigo(idUsuario);
    }

    /**
     * Atualiza os pontos de um usuário com base no total de recargas realizadas.
     *
     * @param idUsuario identificador único do usuário a ter seus pontos atualizados.
     * @return {@code true} se a atualização foi realizada com sucesso, ou {@code false} em caso de erro.
     */
    public boolean atualizarPontos(int idUsuario) {
        try {
            long totalRecarga = usuarioDAO.calcularTotalRecargaPorUsuario(idUsuario);
            int pontosCalculados = (int) (totalRecarga * 50);
            return usuarioDAO.atualizarPontos(idUsuario, pontosCalculados);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pontos: " + e.getMessage());
            return false;
        }
    }

    public boolean usuarioPermissoes(int id, String requiredRole) {
        try {
            String role = usuarioDAO.usuarioPermissoes(id);
            return role != null && role.equalsIgnoreCase(requiredRole);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar permissões: " + e.getMessage());
            return false;
        }
    }

}
