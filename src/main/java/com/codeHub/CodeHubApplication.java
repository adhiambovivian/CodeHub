package com.codeHub;

import com.stripe.exception.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableScheduling

public class CodeHubApplication {


	public static int data;
	public static void main(String[] args) throws IOException, InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException, Exception

	{
		SpringApplication.run(CodeHubApplication.class, args);
		
		//ExcutorService.listUrls();


		//Get method name using current thread
		//System.out.println("Method name1 "+Thread.currentThread().getStackTrace()[1].getMethodName());
		//StackTraceElement stackTraceElement[]=(new Throwable()).getStackTrace();
		//System.out.println("Method name2 "+stackTraceElement[0].getMethodName());
		//System.out.println("Method name3 "+new UploadFileApplication(){}.getClass().getEnclosingMethod().getName());


		/**
		 * Runs this program with Gmail POP3 server
		 */


//		String userName = "social.impact.acumen@gmail.com";
//		String password = "acumen2016";//
//		String saveDirectory = "/home/adhiambo/";//
//		EmailAttachment receiver = new EmailAttachment();
//		receiver.setSaveDirectory(saveDirectory);
//		receiver.downloadEmailAttachments(userName, password);

		//RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		//String vivian = Long.toHexString(Double.doubleToLongBits(Math.random()));
		//System.out.println("vivian "+vivian);
		//UUID uniqueKey = UUID.randomUUID();
		//System.out.println("UUD "+uniqueKey);
		//System.out.println(randomUUID(9,0,'a'));

		//StripePayment.createCharge(StripePayment.createToken());

		//Object key=233;
		//long clientId = ((Number) key).longValue();
		//System.out.println(clientId);

//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		System.out.println("Cal YEAR::"+year);
//		System.out.println("Java 8:Year:: "+ Year.now().getValue());

//		String s="Success-Your survey has been scheduled suceessfuly.";
//				String[] ss=s.split("-");
//				for(String i:ss){
//					System.out.println("####"+i);
//				}

		//LocalDateTime date=LocalDateTime.now();
		//System.out.println(date.toString());






//		for (int i=0;i<20;i++){
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					synchronized (UploadFileApplication.class) {
//						data = new Random().nextInt();
//						System.out.println(Thread.currentThread().getName() + " Gen data: " + data);
//					}
//				}
//			}).start();
//		}
//
//		String [] tasks={"one","two","three","one","two"};
//		for (int i =0;i< tasks.length;i++) {
//			if (tasks[i].equalsIgnoreCase("one")) {
//				System.out.println(Thread.currentThread().getName() + "One");
//			} else if (tasks[i].equalsIgnoreCase("two")) {
//				new Thread(() -> {
//					System.out.println(Thread.currentThread().getName() + "two:: am the sleeping thread");
//					try {
//						Thread.sleep(20000);
//						System.out.println("Done sleeping. "+Thread.currentThread().getName());
//					}catch (InterruptedException e){
//						e.getStackTrace();
//					}
//				}).start();
//
//			} else if (tasks[i].equalsIgnoreCase("three")) {
//				System.out.println(Thread.currentThread().getName() + "three");
//			} else {
//				System.out.println(Thread.currentThread().getName() + "None");
//			}
//		}
//

		//RestConsumer.sendSurvey();
//		Threads threads=new Threads("Thread1");
//		threads.start();
//		Threads threads1=new Threads("Thread2");
//		threads1.start();
		long start = System.currentTimeMillis();
//JMSProducer.trigger();
//		EmailAttachment emailAttachment=new EmailAttachment();
//		emailAttachment.setSaveDirectory("/home/adhiambo/data/businessNumber");
//		emailAttachment.processEmailSearch();
		System.out.println("Finished:: " + (System.currentTimeMillis() - start));


		//ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
		//RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
		//rabbitTemplate.convertAndSend("incentives", "Hello from RabbitMQ!");

		//JedisEx main = new JedisEx();
		//main.addSets();
		//main.addHash();
		//main.setHash();





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



	}



	}
