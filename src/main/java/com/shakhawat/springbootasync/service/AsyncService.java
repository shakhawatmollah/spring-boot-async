package com.shakhawat.springbootasync.service;

import com.shakhawat.springbootasync.model.Customer;
import com.shakhawat.springbootasync.model.FileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class AsyncService {

    private final CustomerService customerService;

    private final FileService fileService;

    @Async("asyncTaskExecutor")
    public CompletableFuture<List<Customer>> getCustomerByName(String name) {
        log.info("Getting customer {} using Async thread", name);
        log.info("getCustomerByName: " + Thread.currentThread().getName());
        List<Customer> customerList = customerService.getCustomerByName(name);
        return CompletableFuture.completedFuture(customerList);
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<Customer> saveCustomer(Customer customer) {
        log.info("Saving customer {} using Async thread", customer.getName());
        log.info("saveCustomer: " + Thread.currentThread().getName());
        Customer newCustomer = customerService.addCustomer(customer);
        return CompletableFuture.completedFuture(newCustomer);
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<String> readFile() {
        log.info("Reading the file using Async thread: " + Thread.currentThread().getName());
        String fileData = fileService.readFile();
        return CompletableFuture.completedFuture(fileData);
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<Boolean> writeFile(FileData fileData) {
        log.info("Writing to file using Async thread: " + Thread.currentThread().getName());
        boolean result = fileService.writeFile(fileData);
        return CompletableFuture.completedFuture(result);
    }

    @Async("asyncTaskExecutor")
    public void executeAsyncTask() {
        log.info("Executing task in thread: " + Thread.currentThread().getName());
        // Simulate some processing
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Task completed in thread: " + Thread.currentThread().getName());
    }

}
