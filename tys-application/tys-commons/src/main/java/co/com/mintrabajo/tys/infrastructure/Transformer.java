package co.com.mintrabajo.tys.infrastructure;

public interface Transformer<I, O> {
    O transform(I var1);
}