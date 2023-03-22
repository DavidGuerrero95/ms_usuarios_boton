package co.com.udea.kafkaclient.adapter.helper;

public abstract class AdapterOperations<R> {
    protected R repository;

    public AdapterOperations(R repository) {
        this.repository = repository;
    }

}
