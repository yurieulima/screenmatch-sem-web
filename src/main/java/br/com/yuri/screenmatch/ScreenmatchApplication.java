package br.com.yuri.screenmatch;



import br.com.yuri.screenmatch.main.Main;
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

		Main main = new Main();
		main.exibeMenu();
	}
}
