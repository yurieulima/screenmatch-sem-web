package br.com.yuri.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(@JsonAlias("Season") Integer numero,
                         @JsonAlias("Episodes") List<DataEpisode> episodios) {
}
