package br.com.vanderson.stackoverflow.db.dao;

import java.util.List;

import br.com.vanderson.stackoverflow.db.model.EntidadeApp;

/**
 * Created by vauruk on 19/03/16.
 */
public interface DAO {

    public List<?> listar( EntidadeApp tag,  String whereClause, String[] whereArgs , String orderBy);
    public void gravar(EntidadeApp  obj);
    public void excluir(int id);
    public void alterar(EntidadeApp obj);
    public EntidadeApp carregar(int id);
}