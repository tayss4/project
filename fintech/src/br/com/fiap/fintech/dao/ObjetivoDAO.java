package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.exception.DBException;

public interface ObjetivoDAO {

	void cadastrar(Objetivo objetivo) throws DBException;

	void atualizar(Objetivo objetivo) throws DBException;

	void remover(String nomeObjetivo) throws DBException;

	Objetivo buscar(String nomeObjetivo);

	List<Objetivo> listar();
}
