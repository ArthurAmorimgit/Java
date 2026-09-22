package AluraGrupo1.screenmatch.service;

public interface ICOnverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
