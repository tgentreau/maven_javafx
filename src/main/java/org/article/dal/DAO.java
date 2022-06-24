package org.article.dal;

import java.util.List;

public interface DAO<T> {
    public T selectById(long id) throws DALException;
    public List<T> selectAll() throws DALException;
    public void update(T data)throws DALException;
    public void insert(T data)throws DALException;
    public void delete(T data)throws DALException;
}
