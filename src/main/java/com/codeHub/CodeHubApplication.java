package com.codeHub;

import com.codeHub.models.Blacklist;
import com.codeHub.service.*;
import com.stripe.exception.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;

@SpringBootApplication
@EnableScheduling

public class CodeHubApplication {

	public static void main(String[] args) throws IOException, InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException, Exception

	{
		SpringApplication.run(CodeHubApplication.class, args);

		/**
		 * Threads
		 */
/*
		ExcutorService.listUrls();

//		Get method name using current thread
		System.out.println("Method name1 "+Thread.currentThread().getStackTrace()[1].getMethodName());
		StackTraceElement stackTraceElement[]=(new Throwable()).getStackTrace();
		System.out.println("Method name2 "+stackTraceElement[0].getMethodName());
		System.out.println("Method name3 "+new CodeHubApplication(){}.getClass().getEnclosingMethod().getName());

 */


		/**
		 * Runs this program with Gmail POP3 server
		 */

/*
		String userName = "social.impact.acumen@gmail.com";
		String password = "acumen2016";//
		String saveDirectory = "/home/adhiambo/";//
		EmailAttachment receiver = new EmailAttachment();
		receiver.setSaveDirectory(saveDirectory);
		receiver.downloadEmailAttachments(userName, password);

 */

		/**
		 * UUIDs
		 */
		/*
		RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		String vivian = Long.toHexString(Double.doubleToLongBits(Math.random()));
		System.out.println("vivian "+vivian);
		UUID uniqueKey = UUID.randomUUID();
		System.out.println("UUD "+uniqueKey);
		System.out.println(UUID.randomUUID(9,0,'a'));
		*/


		/**
		 * Stripe
		 */
//		StripePayment.createCharge(StripePayment.createToken());

		/**
		 * Type casting
		 */
		/*
		Object key=233;
		long clientId = ((Number) key).longValue();
		System.out.println(clientId);

		 */

		/**
		 * Dates
		 */
		/*
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println("Cal YEAR::"+year);
		System.out.println("Java 8:Year:: "+ Year.now().getValue());

		String s="Success-Your survey has been scheduled suceessfuly.";
				String[] ss=s.split("-");
				for(String i:ss){
					System.out.println("####"+i);
				}

		LocalDateTime date=LocalDateTime.now();
		System.out.println(date.toString());

		 */



		/**
		 * Rabbitmq , jmx, jedis
		 */
/*
		long start = System.currentTimeMillis();
		JMSProducer.trigger();
		EmailAttachment emailAttachment=new EmailAttachment();
		emailAttachment.setSaveDirectory("/home/adhiambo/data/businessNumber");
		emailAttachment.processEmailSearch();
		System.out.println("Finished:: " + (System.currentTimeMillis() - start));


		ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
		RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
		rabbitTemplate.convertAndSend("incentives", "Hello from RabbitMQ!");

		JedisEx main = new JedisEx();
		main.addSets();
		main.addHash();
		main.setHash();

 */
/**
 * Resttemplate
 */

/*

		final String url = "http://127.0.0.1:8983/solr/participants/select?fl=commId,surveyId,amountSpent&q=accountId:552&rows=-1&wt=csv";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		ResponseEntity<String> response = restTemplate
				.exchange(url, HttpMethod.GET, entity, String.class);
		//System.out.println("####" + response.toString());
		String [] responseArray=response.getBody().split("\n");
		String csvFile = "/home/adhiambo/Downloads/abc.csv";
		FileWriter writer = new FileWriter(csvFile);
		AtomicInteger failed=new AtomicInteger();
		for(String responseString:responseArray){
			//CSVUtils.writeLine(writer, Arrays.asList(responseString.split(",")));
			//CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"));
			try {
				System.out.println("****************** "+responseString);
				writer.write(responseString);
				writer.write("\n"); // newline

			}catch (IOException e){
				failed.getAndIncrement();
				continue;
			}
		}
		writer.flush();
		writer.close();
		System.out.println("Failed count: "+failed);
*/

/**
 * File commands
 */
		CoderService.minor();
        FileService.fileCommands();
//        NetworkService networkService=new NetworkService();
//        networkService.run();
//		NetworkService.networkCommands();

		/**
		 * Get private method at runtime
		 */
/*
		Class classVal=Class.forName("com.codeHub.service.CoderService");
		Object object=classVal.newInstance();
		Method method=classVal.getDeclaredMethod("printMessage",null);
		method.setAccessible(true);
		method.invoke(object,null);
*/

//RMI: server: Run: $ rmic com.codeHub.service.RmiService , Run: $ rmiregistry 8070 , Run this section of the server before client
	/*	Remote remote = new RmiService();
		Naming.rebind("rmi://localhost:8070/lol",remote);
*/
//RMI: client: Run client last
/*
		DNDInterface dnd=(DNDInterface)Naming.lookup("rmi://localhost:8070/lol");

		List<Blacklist> listDnd=dnd.getDndList();
		for(Blacklist person:listDnd){
			System.out.println(person.toString());
		}
*/

//Internationalizer.internationalierCmds();
//DateService.dateCmds();
//	CollectionService.collectionCmd();
	JdbcService.jdbcCmd();

	}



	}
