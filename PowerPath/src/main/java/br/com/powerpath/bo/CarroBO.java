package br.com.powerpath.bo;

import br.com.powerpath.dao.CarroDAO;
import br.com.powerpath.to.CarroTO;
import br.com.powerpath.Exception.TipoCarroInvalidoException;

import java.util.ArrayList;

/**
 * Classe de negócios responsável pelo gerenciamento das operações relacionadas a carros.
 */
public class CarroBO {
    private CarroDAO carroDAO;

    /**
     * Insere um novo carro no sistema.
     *
     * @param carroTO objeto do tipo CarroTO contendo os dados do carro a ser inserido.
     * @return o objeto CarroTO inserido no sistema, ou {@code null} em caso de erro.
     * @throws TipoCarroInvalidoException se o tipo do carro não for "elétrico" ou "híbrido".
     */
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

    /**
     * Altera os dados de um carro existente no sistema.
     *
     * @param carroTO objeto do tipo CarroTO contendo os dados do carro a serem atualizados.
     * @return o objeto CarroTO atualizado no sistema, ou {@code null} em caso de erro.
     * @throws TipoCarroInvalidoException se o tipo do carro não for "elétrico" ou "híbrido".
     */
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

    /**
     * Exclui um carro do sistema pelo seu identificador.
     *
     * @param idCarro identificador único do carro a ser excluído.
     * @return {@code true} se a exclusão foi realizada com sucesso, ou {@code false} em caso de falha.
     */
    public boolean excluir(int idCarro) {
        carroDAO = new CarroDAO();
        return carroDAO.excluir(idCarro);
    }

    /**
     * Lista todos os carros cadastrados no sistema.
     *
     * @return uma lista de objetos CarroTO contendo os dados de todos os carros cadastrados.
     */
    public ArrayList<CarroTO> listarTodos() {
        carroDAO = new CarroDAO();
        return carroDAO.listarTodos();
    }

    /**
     * Busca um carro no sistema pelo seu identificador.
     *
     * @param idCarro identificador único do carro a ser visualizado.
     * @return o objeto CarroTO correspondente ao identificador fornecido, ou {@code null} se não for encontrado.
     */
    public CarroTO visualizarPeloCodigo(int idCarro) {
        carroDAO = new CarroDAO();
        return carroDAO.vizualizarPeloCodigo(idCarro);
    }

    /**
     * Realiza a recarga de um carro e atualiza suas informações no sistema.
     *
     * @param carro objeto do tipo CarroTO contendo os dados do carro a ser recarregado.
     * @return o objeto CarroTO atualizado após a recarga, ou {@code null} se o carro não foi encontrado ou houve erro na operação.
     */
    public CarroTO recarregarCarro(CarroTO carro) {
        carroDAO = new CarroDAO();
        carro = carroDAO.recarregarCarro(carro);
        if (carro != null) {
            carro.calcularCarbonoComRecarga();
            if ("hibrido".equalsIgnoreCase(carro.getTipo())) {
                carro.atualizarQuantidadeCarbonoParaHibrido();
            }
            carroDAO.atualizarCarro(carro);
        }
        return carro;
    }
}
