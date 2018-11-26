package hello.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hello")
public class DemoApplication
	{

		// private final MyService myService;

		// public DemoApplication(MyService myService)
		// {
		// this.myService = myService;
		// }

		// @GetMapping("/")
		// public String home()
		// {
		// return myService.message();
		// }

		public static void xmain(String[] args)
			{
				SpringApplication.run(DemoApplication.class, args);
			}
	}
