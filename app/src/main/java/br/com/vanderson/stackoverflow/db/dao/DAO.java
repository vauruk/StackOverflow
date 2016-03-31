package br.com.vanderson.stackoverflow.db.dao;

import java.util.List;

import br.com.vanderson.stackoverflow.db.model.EntityApp;

/**
 * Created by vauruk on 19/03/16.
 * Padronizacao de metodos a serem implementado
 */
public interface DAO {

    public List<?> listar( EntityApp tag,  String whereClause, String[] whereArgs , String orderBy);
    public void gravar(EntityApp obj);
    public void excluir(EntityApp obj);
    public void alterar(EntityApp obj);
    public EntityApp carregar(int id);
}