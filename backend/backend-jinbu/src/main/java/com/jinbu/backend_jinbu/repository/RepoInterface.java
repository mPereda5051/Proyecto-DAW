package com.jinbu.backend_jinbu.repository;

import java.util.List;

public interface RepoInterface<T, ID> {
    public List<T> getObjects();

    public T getObject(ID index);

    public void saveObject(T generic);

    public void updateObject(ID index, T generic);

    public void removeObject(ID index);
}
