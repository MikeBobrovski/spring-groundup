package com.sulevsky.main;

import com.sulevsky.model.Report;
import com.sulevsky.model.Task;
import com.sulevsky.model.Worker;
import com.sulevsky.service.*;
import com.sulevsky.view.ReportView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class DemoPreparator {
    private final WorkerService workerService;
    private final AssignService assignService;
    private final TaskService taskService;
    private final ReportGenerationServiceImpl reportGenerationService;
    private final ReportView reportView;

    public DemoPreparator(WorkerService workerService, AssignService assignService, TaskService taskService, ReportGenerationServiceImpl reportGenerationService, ReportView reportView) {
        this.workerService = workerService;
        this.assignService = assignService;
        this.taskService = taskService;
        this.reportGenerationService = reportGenerationService;
        this.reportView = reportView;
    }

    public void demo(){
        creteTestWorkers(workerService);
        createTestTasks(taskService);
        assignTasks(taskService, workerService, assignService);
        Report report = generateReport(reportGenerationService);
        showReport(report, reportView);
    }

    private void createTestTasks(TaskService taskService) {
        taskService.createTask("task0",
                LocalDateTime.of(2017, 2, 22, 12, 20),
                LocalDateTime.of(2017, 2, 22, 15, 20),
                BigDecimal.valueOf(100.0)
        );

        taskService.createTask("task1",
                LocalDateTime.of(2017, 2, 22, 12, 20),
                LocalDateTime.of(2017, 2, 22, 18, 20),
                BigDecimal.valueOf(200.0)
        );

        taskService.createTask("task2",
                LocalDateTime.of(2017, 2, 22, 12, 20),
                LocalDateTime.of(2017, 2, 23, 15, 20),
                BigDecimal.valueOf(300.0)
        );

        taskService.createTask("task3",
                LocalDateTime.of(2017, 2, 22, 12, 20),
                LocalDateTime.of(2017, 2, 25, 15, 20),
                BigDecimal.valueOf(400.0)
        );
    }

    private void assignTasks(TaskService taskService, WorkerService workerService, AssignService assignService) {
        List<Task> tasks = taskService.findAllTasks();
        List<Worker> workers = workerService.findAll();
        for (int i = 0; i < 4; i++) {
            assignService.assignTask(workers.get(i), tasks.get(i));
        }
    }

    private void showReport(Report report, ReportView reportView) {
        reportView.showReport(report);
    }

    private void creteTestWorkers(WorkerService workerService) {
        workerService.createWorker("John", "Doe");
        workerService.createWorker("Ivan", "Ivanov");
        workerService.createWorker("Peter", "Petrov");
        workerService.createWorker("SomeOne", "Else");
    }

    private Report generateReport(ReportGenerationService reportGenerationService) {
        return reportGenerationService.generateReport();
    }
}
