package org.example;

import org.example.dao.PersonDAO;
import org.example.model.Person;
import org.example.model.PersonDTO;

import java.util.Optional;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //Creating Runnable object
        CalculateAverage calculateAverage = new CalculateAverage(1,2,3,4,5,6,7,8,9,10);

        PersonDAO dao = PersonDAO.getInstance();

        ExecutorService service = Executors.newFixedThreadPool(4);

        PersonDTO dto = CompletableFuture.supplyAsync(() -> dao.findById(99999), service)
                .thenApply(Optional::get)
                .thenApply(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getLastName()))
                .exceptionally(throwable -> null)
                .join();
        System.out.println(dto);


        service.shutdown();

    }

    public static void averageWithPolling(CalculateAverage calculateAverage){
        Thread thread = new Thread(calculateAverage);

        //Start the thread
        thread.start();

        //Polling
        while (!calculateAverage.isDone()){
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(calculateAverage.getAverage());

    }

    public static void averageWithExecutorService(CalculateAverage calculateAverage){
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<?> result = executorService.submit(calculateAverage);
            result.get(100, TimeUnit.MILLISECONDS);
            System.out.println(calculateAverage.getAverage());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }
    }

    public static void counterExample(int numberOfThreads){
        ExecutorService service = null;
        CounterExample counterExample = new CounterExample();
        try{
            service = Executors.newFixedThreadPool(numberOfThreads);
            for(int i=0; i<numberOfThreads; i++){
                service.submit(counterExample::incrementAndPrint);
            }
        }finally {
            if(service != null){
                service.shutdown();
            }
        }

    }
}
