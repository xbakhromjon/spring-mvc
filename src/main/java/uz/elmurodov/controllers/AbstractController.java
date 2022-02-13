package uz.elmurodov.controllers;

public abstract class AbstractController<S> {
    public final S service;

    public AbstractController(S service) {
        this.service = service;
    }
}
