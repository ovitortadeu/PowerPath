package br.com.powerpath.dao;

import br.com.powerpath.to.CarroTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO extends Repository{

    public CarroTO inserir(CarroTO carroTO) {
        String sql = "insert into T_PW_CARRO(id_usuario,modelo,ano,marca,tipo,quantidade_carbono,recarga) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, carroTO.getIdUsuario());
            ps.setString(2, carroTO.getModelo());
            ps.setDate(3, Date.valueOf(carroTO.getAno()));
            ps.setString(4, carroTO.getMarca());
            ps.setString(5, carroTO.getTipo());
            ps.setLong(6, carroTO.getQuantidadeCarbono());
            ps.setLong(7, carroTO.getRecarga());
            if (ps.executeUpdate() > 0) {
                return carroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public CarroTO alterar(CarroTO carroTO) {
        String sql = "UPDATE T_PW_CARRO SET modelo=?, marca=?, id_usuario=?, ano=?, tipo=?, quantidade_carbono=?, recarga=? WHERE id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, carroTO.getModelo());
            ps.setString(2, carroTO.getMarca());
            ps.setInt(3, carroTO.getIdUsuario());
            ps.setDate(4, Date.valueOf(carroTO.getAno()));
            ps.setString(5, carroTO.getTipo());
            ps.setLong(6, carroTO.getQuantidadeCarbono());
            ps.setLong(7, carroTO.getRecarga());
            ps.setInt(8, carroTO.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return carroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean excluir(int idCarro) {
        String sql = "delete from T_CS_CARRO where id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idCarro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<CarroTO> listarTodos() {
        String sql = "select * from T_PW_CARRO order by id_carro";
        ArrayList<CarroTO> listaCarroTO = new ArrayList<CarroTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CarroTO carroTO = new CarroTO();
                    carroTO.setIdUsuario(rs.getInt("id_usuario"));
                    carroTO.setIdCarro(rs.getInt("id_carro"));
                    carroTO.setModelo(rs.getString("modelo"));
                    carroTO.setMarca(rs.getString("marca"));
                    carroTO.setAno(rs.getDate("ano").toLocalDate());
                    carroTO.setQuantidadeCarbono(rs.getLong("quantidade_carbono"));
                    carroTO.setRecarga(rs.getLong("recarga"));
                    carroTO.setTipo(rs.getString("tipo"));
                    listaCarroTO.add(carroTO);
                }
                return listaCarroTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public CarroTO vizualizarPeloCodigo(int idCarro) {
        CarroTO carroTO = new CarroTO();
        String sql = "select * from T_PW_CARRO where id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idCarro);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                carroTO.setIdUsuario(rs.getInt("id_usuario"));
                carroTO.setModelo(rs.getString("modelo"));
                carroTO.setMarca(rs.getString("marca"));
                carroTO.setAno(rs.getDate("ano").toLocalDate());
                carroTO.setQuantidadeCarbono(rs.getLong("quantidade_carbono"));
                carroTO.setRecarga(rs.getLong("recarga"));
                carroTO.setTipo(rs.getString("tipo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao mostrar problema: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return carroTO;
    }

    public CarroTO recarregarCarro(CarroTO carro) {
        String sql = "UPDATE T_PW_CARRO SET RECARGA = ? WHERE ID_CARRO = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, carro.getRecarga());
            ps.setInt(2, carro.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return carro;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }
}
