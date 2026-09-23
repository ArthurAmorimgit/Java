package AluraGrupo1.screenmatch.principal;

import AluraGrupo1.screenmatch.model.DadosEpisodeo;
import AluraGrupo1.screenmatch.model.DadosSerie;
import AluraGrupo1.screenmatch.model.DadosTemporada;
import AluraGrupo1.screenmatch.service.ConsumoApi;
import AluraGrupo1.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<= dados.totalTemporadas(); i++){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season="+ i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodeo> episodeosTemporada = temporadas.get(i).episodeos();
//            for (int j = 0; j< episodeosTemporada.size(); j++){
//                System.out.println(episodeosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.titulo())));



    }
}
