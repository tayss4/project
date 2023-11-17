package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Gasto;
import br.com.fiap.fintech.exception.DBException;

public interface GastoDAO {

	void cadastrar(Gasto gasto) throws DBException;

	void atualizar(Gasto gasto) throws DBException;

	void remover(int codigo) throws DBException;

	Gasto buscar(int codigo);

	List<Gasto> listar();
}
