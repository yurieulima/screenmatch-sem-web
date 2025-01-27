package br.com.yuri.screenmatch.main;

import br.com.yuri.screenmatch.models.DataEpisode;
import br.com.yuri.screenmatch.models.DataSeason;
import br.com.yuri.screenmatch.models.DataSerie;
import br.com.yuri.screenmatch.service.ConsumoApi;
import br.com.yuri.screenmatch.service.ConverteDados;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para buscar: ");
        String nomeSerie = leitura.nextLine();

        var json  = consumo.obterDados(URL + nomeSerie.replace(" ", "+") + API_KEY);
        DataSerie dadosSerie = conversor.obterDados(json, DataSerie.class);
        System.out.println(dadosSerie);

        List<DataSeason> temporadas = new ArrayList<>();

        for(int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json  = consumo.obterDados(URL + nomeSerie.replace(" ", "+") + "&season=" + i +  API_KEY);
            DataSeason dadosTemporada = conversor.obterDados(json, DataSeason.class);
            temporadas.add(dadosTemporada);

        }
        System.out.println(temporadas);

//        for(int i = 0; dadosSerie.totalTemporadas() > i; i++) {
//            List<DataEpisode> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; episodiosTemporada.size() > j; j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DataEpisode> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTOP 5 Episódios: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DataEpisode::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

    }

}
