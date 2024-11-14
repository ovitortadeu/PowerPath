package br.com.powerpath.bo;

import br.com.powerpath.dao.CarroDAO;
import br.com.powerpath.to.CarroTO;
import br.com.powerpath.Exception.TipoCarroInvalidoException;

import java.util.ArrayList;

public class CarroBO {
    private CarroDAO carroDAO;


    public CarroTO inserir(CarroTO carroTO) throws TipoCarroInvalidoException {
        carroDAO = new CarroDAO();
        try {
            if (!carroTO.getTipo().equalsIgnoreCase("elétrico") && !carroTO.getTipo().equalsIgnoreCase("hibrido")) {
                throw new TipoCarroInvalidoException("Em nosso aplicativo só suportamos carros elétricos ou híbridos. Mude o tipo de seu carro para poder inseri-lo.");
            }
            return carroDAO.inserir(carroTO);
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }

    public CarroTO alterar(CarroTO carroTO) throws TipoCarroInvalidoException {
        carroDAO = new CarroDAO();
        try {
            if (!carroTO.getTipo().equalsIgnoreCase("elétrico") && !carroTO.getTipo().equalsIgnoreCase("hibrido")) {
                throw new TipoCarroInvalidoException("Em nosso aplicativo só suportamos carros elétricos ou híbridos. Mude o tipo de seu carro para poder inseri-lo.");
            }
            return carroDAO.inserir(carroTO);
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }

    public boolean excluir(int idCarro) {
        carroDAO = new CarroDAO();
        // Sem regras de negócio especificas para exclusão de carro
        return carroDAO.excluir(idCarro);
    }

    public ArrayList<CarroTO> listarTodos() {
        carroDAO = new CarroDAO();
        // Sem regras de negócio especificas para listar carros.
        return carroDAO.listarTodos();
    }

    public CarroTO visualizarPeloCodigo(int idCarro) {
        carroDAO = new CarroDAO();
        // Sem regras de negócio especificas para vizualização.
        return carroDAO.vizualizarPeloCodigo(idCarro);
    }

    public CarroTO recarregarCarro(CarroTO carro) {
        try {
            carro.calcularCarbonoComRecarga();
            if (carro.getTipo().equalsIgnoreCase("hibrido")) {
                carro.atualizarQuantidadeCarbonoParaHibrido();
            }
            return carroDAO.recarregarCarro(carro);
        } catch (Exception e) {
            System.out.println("Erro ao recarregar o carro: " + e.getMessage());
            return null;
        }
    }

}
