package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Recebimento;
import br.com.fiap.fintech.exception.DBException;

public interface RecebimentoDAO {

	void cadastrar(Recebimento recebimento) throws DBException;

	void atualizar(Recebimento recebimento) throws DBException;

	void remover(int codigo) throws DBException;

	Recebimento buscar(int codigo);

	List<Recebimento> listar();
}