package uz.elmurodov.service;

public abstract class AbstractService<R> {
    public final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }
}
