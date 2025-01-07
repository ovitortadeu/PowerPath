package br.com.powerpath.dao;

import br.com.powerpath.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends Repository{

    public UsuarioTO inserir(UsuarioTO usuarioTO) {
        String sql = "INSERT INTO T_PW_USUARIO (nome, senha, email, pontos, role) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuarioTO.getNome());
            ps.setString(2, usuarioTO.getSenha());
            ps.setString(3, usuarioTO.getEmail());
            ps.setLong(4, usuarioTO.getPontos());
            ps.setString(5, usuarioTO.getRole()); // Novo campo ROLE
            if (ps.executeUpdate() > 0) {
                return usuarioTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir usuário: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public UsuarioTO alterar(UsuarioTO usuarioTO) {
        String sql = "UPDATE T_PW_USUARIO SET nome = ?, email = ?, senha = ?, pontos = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuarioTO.getNome());
            ps.setString(2, usuarioTO.getEmail());
            ps.setString(3, usuarioTO.getSenha());
            ps.setLong(4, usuarioTO.getPontos());
            ps.setInt(5, usuarioTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return usuarioTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao alterar usuário: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean excluir(int idUsuario) {
        String sql = "DELETE FROM T_PW_USUARIO WHERE id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir usuário: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<UsuarioTO> listarTodos() {
        String sql = "SELECT * FROM T_PW_USUARIO ORDER BY id_usuario";
        ArrayList<UsuarioTO> listaUsuarioTO = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioTO usuarioTO = new UsuarioTO();
                usuarioTO.setIdUsuario(rs.getInt("id_usuario"));
                usuarioTO.setNome(rs.getString("nome"));
                usuarioTO.setEmail(rs.getString("email"));
                usuarioTO.setSenha(rs.getString("senha"));
                usuarioTO.setPontos(rs.getLong("pontos"));
                listaUsuarioTO.add(usuarioTO);
            }
            return listaUsuarioTO;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar usuários: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public UsuarioTO visualizarPeloCodigo(int idUsuario) {
        String sql = "SELECT * FROM T_PW_USUARIO WHERE id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioTO usuarioTO = new UsuarioTO();
                usuarioTO.setIdUsuario(rs.getInt("id_usuario"));
                usuarioTO.setNome(rs.getString("nome"));
                usuarioTO.setEmail(rs.getString("email"));
                usuarioTO.setSenha(rs.getString("senha"));
                usuarioTO.setPontos(rs.getLong("pontos"));
                return usuarioTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao visualizar usuário: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public long calcularTotalRecargaPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT COALESCE(SUM(c.recarga), 0) FROM T_PW_CARRO c WHERE c.id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return 0;
    }

    public boolean atualizarPontos(int idUsuario, int pontos) throws SQLException {
        String sql = "UPDATE T_PW_USUARIO SET pontos = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, pontos);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        }
    }

    public String usuarioPermissoes(int userId) throws SQLException {
        String sql = "SELECT role FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        }
        return null;
    }






}
