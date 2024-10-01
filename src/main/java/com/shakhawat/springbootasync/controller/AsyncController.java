package com.shakhawat.springbootasync.controller;

import com.shakhawat.springbootasync.model.Customer;
import com.shakhawat.springbootasync.model.FileData;
import com.shakhawat.springbootasync.service.AsyncService;
import com.shakhawat.springbootasync.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncService asyncService;

    private final FileService fileService;

    @GetMapping("/customers/{name}")
    public CompletableFuture<List<Customer>> getCustomerByName(@PathVariable String name) {
        log.info("Getting customer by name {} ", name);
        CompletableFuture<List<Customer>> listCompletableFuture = asyncService.getCustomerByName(name);
        return listCompletableFuture;
    }

    @PostMapping("/customers/save")
    public CompletableFuture<Customer> addCustomer(@RequestBody Customer customer) {
        log.info("Adding user {} to the Database", customer.getName());
        return asyncService.saveCustomer(customer);
    }

    @GetMapping("/file/read")
    public CompletableFuture<String> readFile() {
        log.info("reading file request");
        return asyncService.readFile();
    }

    @PostMapping("/file/write")
    public CompletableFuture<Boolean> writeFile(@RequestBody FileData fileData) {
        log.info("Write data {} to File", fileData);
        return asyncService.writeFile(fileData);
    }

    @GetMapping("/run-async-task")
    public String runAsyncTask() {
        asyncService.executeAsyncTask();
        return "Task execution started.";
    }

}
