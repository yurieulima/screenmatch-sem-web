package br.com.yuri.screenmatch;



import br.com.yuri.screenmatch.models.DataSerie;
import br.com.yuri.screenmatch.service.ConsumoApi;
import br.com.yuri.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();

		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=961bbb66");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DataSerie dados = conversor.obterDados(json, DataSerie.class);
		System.out.println(dados);
	}
}
