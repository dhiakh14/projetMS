<<<<<<< HEAD
import { Component, OnInit, AfterViewInit, TemplateRef, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from 'src/app/services1/models';
import { TaskControllerService } from 'src/app/services1/services';
import { GanttChartControllerService } from 'src/app/services1/services/gantt-chart-controller.service';
import { gantt } from 'dhtmlx-gantt';
import * as XLSX from 'xlsx';
import { SaveGanttChart$Params } from 'src/app/services1/fn/gantt-chart-controller/save-gantt-chart';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PredictDuration$Params } from 'src/app/services1/fn/task-controller/predict-duration';
 
=======
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from 'src/app/services1/models';
import { TaskControllerService } from 'src/app/services1/services';
import { GanttChartControllerService, SaveGanttChart$Params } from 'src/app/services1/services/gantt-chart-controller.service';
import 'dhtmlx-gantt'; // Import the Gantt library
declare const gantt: any; // Declare the Gantt library
>>>>>>> origin/lahmer

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit, AfterViewInit {
<<<<<<< HEAD

  tasks: Task[] = []; 
  filteredTasks: Task[] = []; 
  selectedTaskIds: Set<number> = new Set(); 
  searchQuery: string = ''; 
  ganttData: any; 
  qrCodeData: string = '';
  currentPage: number = 1; 
  itemsPerPage: number = 4;
  showPredictionForm: boolean = false;    
  predictedDuration: number | null = null; 
  errorMessage: string | null = null;
  taskDescription: string = '';
  private ganttInitialized = false;

 

  constructor(
    private taskService: TaskControllerService,
    private ganttChartService: GanttChartControllerService, 
    private router: Router,
    private modalService: NgbModal,
  ) {}

  ngAfterViewInit(): void {
    this.initializeGantt();

  }

=======
  tasks: Task[] = []; // List of all tasks
  filteredTasks: Task[] = []; // List of filtered tasks based on search
  selectedTaskIds: Set<number> = new Set(); // Set of selected task IDs
  searchQuery: string = ''; // Search query for filtering tasks
  ganttData: any; // Variable to store Gantt chart data

  constructor(
    private taskService: TaskControllerService,
    private ganttChartService: GanttChartControllerService, // Inject Gantt chart service
    private router: Router
  ) {}

  // Initialize the Gantt chart after the view is loaded
  ngAfterViewInit(): void {
    gantt.init('gantt-container');
  }

  // Load tasks when the component initializes
>>>>>>> origin/lahmer
  ngOnInit(): void {
    this.loadTasks();
  }

<<<<<<< HEAD
  private initializeGantt(): void {
    if (typeof gantt === 'undefined') {
      console.error('Gantt library not loaded!');
      setTimeout(() => this.initializeGantt(), 100); 
      return;
    }

    try {
      gantt.config.date_format = "%d-%m-%Y"; 
      gantt.config['scale_unit'] = "day";
      gantt.config['subscales'] = [
        { unit: "day", step: 1, date: "%j, %D" }
      ];
      
      gantt.init("gantt-container");
      this.ganttInitialized = true;
    } catch (error) {
      console.error('Gantt initialization failed:', error);
    }
  }



  predictTaskDuration(name: string, description: string) {
    const params: PredictDuration$Params = {
      body: {
        name: name,
        description: description
      }
    };

    this.taskService.predictDuration(params).subscribe({
      next: (response) => {
        this.predictedDuration = response['The expected duration is '];
        this.errorMessage = null;
      },
      error: (err) => {
        this.errorMessage = 'Failed to predict duration: ' + err.message;
        this.predictedDuration = null;
      }
    });
  }

  

  startVoiceSearch(): void {
    if ('webkitSpeechRecognition' in window) {
      const recognition = new (window as any).webkitSpeechRecognition();
      recognition.continuous = false;
      recognition.interimResults = false;

      recognition.onstart = () => {
        console.log('Voice recognition started. Speak now...');
      };

      recognition.onresult = (event: any) => {
        const transcript = event.results[0][0].transcript;
        this.searchQuery = transcript;
        this.filterTasks();
        recognition.stop();
      };

      recognition.onerror = (event: any) => {
        console.error('Voice recognition error:', event.error);
        recognition.stop();
      };

      recognition.onend = () => {
        console.log('Voice recognition ended.');
      };

      recognition.start();
    } else {
      alert('Your browser does not support voice recognition. Please use Chrome or another supported browser.');
    }
  }

  

  showQRCode(task: any, qrModal: TemplateRef<any>): void {
    const formatDate = (dateString: string): string => {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); 
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    };
  
    this.qrCodeData = `
      Name: ${task.name}
      Description: ${task.description}
      Start Date: ${formatDate(task.startDate)}
      Planned End Date: ${formatDate(task.planned_end_date)}
      Status: ${task.status}
    `;
  
    this.modalService.open(qrModal, { size: 'lg' });
  }

  exportToExcel(): void {
    const data: any[] = [];
    
    const headers = [
      'Task Name',
      'Description',
      'Start Date',
      'Planned End Date',
      'Status'
    ];
    data.push(headers);
  
    this.filteredTasks.forEach(task => {
      const startDate = task.startDate ? new Date(task.startDate).toISOString().split('T')[0] : 'N/A';
      const plannedEndDate = task.planned_end_date ? new Date(task.planned_end_date).toISOString().split('T')[0] : 'N/A';
  
      const row = [
        task.name,
        task.description,
        startDate, 
        plannedEndDate, 
        task.status
      ];
      data.push(row);
    });
  
    const ws: XLSX.WorkSheet = XLSX.utils.aoa_to_sheet(data);
  
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Tasks');
  
    XLSX.writeFile(wb, 'Tasks.xlsx');
  }

  


=======
  // Fetch all tasks from the backend
>>>>>>> origin/lahmer
  loadTasks(): void {
    this.taskService.getAllTasks().subscribe(
      (response) => {
        if (response instanceof Blob) {
          const reader = new FileReader();
          reader.onload = () => {
            try {
              const jsonResponse = JSON.parse(reader.result as string);
              if (Array.isArray(jsonResponse)) {
                this.tasks = jsonResponse;
                this.filteredTasks = [...this.tasks];
              } else {
                console.error('Expected an array of tasks, but received:', jsonResponse);
              }
            } catch (error) {
              console.error('Error parsing JSON from Blob:', error);
            }
          };
          reader.readAsText(response);
        } else if (Array.isArray(response)) {
          this.tasks = response;
          this.filteredTasks = [...this.tasks];
        } else {
          console.error('Unexpected response format:', response);
        }
      },
      (error) => {
        console.error('Error fetching tasks:', error);
      }
    );
  }

<<<<<<< HEAD
=======
  // Toggle selection of a task
>>>>>>> origin/lahmer
  toggleSelection(taskId: number): void {
    if (this.selectedTaskIds.has(taskId)) {
      this.selectedTaskIds.delete(taskId);
    } else {
      this.selectedTaskIds.add(taskId);
    }
  }

  generateGanttChart(): void {
    const selectedTasks = this.tasks.filter(task => this.selectedTaskIds.has(task.idTask!));
  
    if (selectedTasks.length === 0) {
      console.warn('No tasks selected for Gantt chart.');
      alert('Please select at least one task to generate the Gantt chart.');
      return;
    }
  
<<<<<<< HEAD
    this.ganttData = {
      taskName: 'Generated Gantt Chart', 
      startDate: this.convertToBackendDateFormat(selectedTasks[0].startDate || ''), 
      endDate: this.convertToBackendDateFormat(selectedTasks[selectedTasks.length - 1].planned_end_date || ''), 
      progress: this.calculateAverageProgress(selectedTasks), 
      tasks: selectedTasks.map(task => ({
        idTask: task.idTask,
        name: task.name,
        description: task.description || '', 
        startDate: this.convertToBackendDateFormat(task.startDate || ''),
        planned_end_date: this.convertToBackendDateFormat(task.planned_end_date || ''),
        actual_end_date: this.convertToBackendDateFormat(task.actual_end_date || ''),
        status: task.status || 'PENDING' 
=======
    // Prepare the Gantt chart data
    this.ganttData = {
      taskName: 'Generated Gantt Chart', // Example name
      startDate: this.convertToBackendDateFormat(selectedTasks[0].startDate || ''), // Use the first task's start date
      endDate: this.convertToBackendDateFormat(selectedTasks[selectedTasks.length - 1].planned_end_date || ''), // Use the last task's end date
      progress: this.calculateAverageProgress(selectedTasks), // Calculate average progress
      tasks: selectedTasks.map(task => ({
        idTask: task.idTask,
        name: task.name,
        description: task.description || '', // Ensure description is not null
        startDate: this.convertToBackendDateFormat(task.startDate || ''),
        planned_end_date: this.convertToBackendDateFormat(task.planned_end_date || ''),
        actual_end_date: this.convertToBackendDateFormat(task.actual_end_date || ''), // Ensure actual_end_date is not null
        status: task.status || 'PENDING' // Default to 'PENDING' if status is null
>>>>>>> origin/lahmer
      }))
    };
  
    console.log('Generated Gantt Data:', JSON.stringify(this.ganttData, null, 2));
  
    gantt.clearAll();
    gantt.parse({ data: selectedTasks.map(task => ({
      id: task.idTask,
      text: task.name,
      start_date: this.convertToGanttDateFormat(task.startDate || ''),
      duration: this.calculateDuration(task.startDate || '', task.planned_end_date || ''),
      progress: this.calculateProgress(task.status ?? 'PENDING'),
      dependencies: [],
    })) });
  }
  
  private convertToBackendDateFormat(date: string): string {
    if (!date) return '';
    const parsedDate = new Date(date);
  
    if (isNaN(parsedDate.getTime())) {
      console.error('Invalid date:', date);
      return '';
    }
  
    const year = parsedDate.getFullYear();
    const month = (parsedDate.getMonth() + 1).toString().padStart(2, '0');
    const day = parsedDate.getDate().toString().padStart(2, '0');
  
    return `${year}-${month}-${day}`;
  }
  
<<<<<<< HEAD
=======
  // Calculate average progress for selected tasks
>>>>>>> origin/lahmer
  private calculateAverageProgress(tasks: Task[]): number {
    if (tasks.length === 0) return 0;
    const totalProgress = tasks.reduce((sum, task) => sum + this.calculateProgress(task.status ?? 'PENDING'), 0);
    return totalProgress / tasks.length;
  }

 
  saveGanttChart(ganttData: any): void {
    if (!ganttData) {
      alert('No Gantt chart data to save. Please generate the Gantt chart first.');
      return;
    }
  
<<<<<<< HEAD
    const swaggerCompatibleGanttData = {
      id: 0,
=======
    // Prepare the Gantt chart data in the Swagger-compatible format
    const swaggerCompatibleGanttData = {
      id: 0, // Set to 0 or omit if the backend generates the ID
>>>>>>> origin/lahmer
      taskName: ganttData.taskName,
      startDate: ganttData.startDate,
      endDate: ganttData.endDate,
      progress: ganttData.progress,
      tasks: ganttData.tasks.map((task: { idTask: any; name: any; description: any; startDate: any; planned_end_date: any; actual_end_date: any; status: any; }) => ({
        idTask: task.idTask,
        name: task.name,
<<<<<<< HEAD
        description: task.description || '', 
        startDate: task.startDate,
        planned_end_date: task.planned_end_date,
        actual_end_date: task.actual_end_date || null, 
        status: task.status || 'PENDING' 
      }))
    };
  
    console.log('Gantt Data to be saved:', JSON.stringify(swaggerCompatibleGanttData, null, 2)); 
  
    const saveParams: SaveGanttChart$Params = {
      body: swaggerCompatibleGanttData 
    };
  
=======
        description: task.description || '', // Ensure description is not null
        startDate: task.startDate,
        planned_end_date: task.planned_end_date,
        actual_end_date: task.actual_end_date || null, // Ensure actual_end_date is not null
        status: task.status || 'PENDING' // Default to 'PENDING' if status is null
      }))
    };
  
    // Log the Gantt chart data being sent
    console.log('Gantt Data to be saved:', JSON.stringify(swaggerCompatibleGanttData, null, 2)); // Pretty-print the payload
  
    const saveParams: SaveGanttChart$Params = {
      body: swaggerCompatibleGanttData // Pass the Gantt chart data as the request body
    };
  
    // Log the saveParams object
>>>>>>> origin/lahmer
    console.log('Save Params:', JSON.stringify(saveParams, null, 2));
  
    this.ganttChartService.saveGanttChart(saveParams).subscribe({
      next: (response) => {
<<<<<<< HEAD
        console.log('Gantt chart saved successfully:', JSON.stringify(response, null, 2)); 
=======
        console.log('Gantt chart saved successfully:', JSON.stringify(response, null, 2)); // Pretty-print the response
>>>>>>> origin/lahmer
        alert('Gantt chart saved successfully!');
      },
      error: (error) => {
        console.error('Error saving Gantt chart:', error);
<<<<<<< HEAD
        console.error('Full error response:', JSON.stringify(error, null, 2)); 
=======
        console.error('Full error response:', JSON.stringify(error, null, 2)); // Pretty-print the error
>>>>>>> origin/lahmer
        alert('Failed to save Gantt chart. Check the console for details.');
      }
    });
  }

<<<<<<< HEAD
=======
  // Convert date to Gantt chart format (DD-MM-YYYY)
>>>>>>> origin/lahmer
  private convertToGanttDateFormat(date: string): string {
    if (!date) return '';
    const parsedDate = new Date(date);

    if (isNaN(parsedDate.getTime())) {
      console.error('Invalid date:', date);
      return '';
    }

    parsedDate.setHours(0, 0, 0, 0);

    const day = parsedDate.getDate().toString().padStart(2, '0');
    const month = (parsedDate.getMonth() + 1).toString().padStart(2, '0');
    const year = parsedDate.getFullYear();

    return `${day}-${month}-${year}`;
  }

<<<<<<< HEAD
=======
  // Calculate duration between two dates in days
>>>>>>> origin/lahmer
  private calculateDuration(startDate: string, endDate: string): number {
    if (!startDate || !endDate) return 0;
    const start = new Date(startDate);
    const end = new Date(endDate);
<<<<<<< HEAD
    return (end.getTime() - start.getTime()) / (1000 * 3600 * 24); 
  }

  private calculateProgress(status: string): number {
    if (status === 'COMPLETED') return 1; 
    if (status === 'IN_PROGRESS') return 0.5; 
    return 0; 
  }

=======
    return (end.getTime() - start.getTime()) / (1000 * 3600 * 24); // Duration in days
  }

  // Calculate progress based on task status
  private calculateProgress(status: string): number {
    if (status === 'COMPLETED') return 1; // 100% progress
    if (status === 'IN_PROGRESS') return 0.5; // 50% progress
    return 0; // 0% progress
  }

  // Delete a task
>>>>>>> origin/lahmer
  deleteTask(taskId: number): void {
    if (confirm('Are you sure you want to delete this task?')) {
      const originalTasks = [...this.tasks];
      this.tasks = this.tasks.filter(task => task.idTask !== taskId);
      this.filteredTasks = this.filteredTasks.filter(task => task.idTask !== taskId);

      this.taskService.deleteTask({ id: taskId }).subscribe(
        () => {
          console.log(`Task ${taskId} deleted successfully.`);
        },
        (error) => {
          console.error(`Error deleting task ${taskId}:`, error);
<<<<<<< HEAD
=======
          // Rollback if the delete request fails
>>>>>>> origin/lahmer
          this.tasks = originalTasks;
          this.filteredTasks = originalTasks;
          alert('Failed to delete the task. Please try again.');
        }
      );
    }
  }

<<<<<<< HEAD
=======
  // Filter tasks based on search query
>>>>>>> origin/lahmer
  filterTasks(): void {
    if (!this.searchQuery) {
      this.filteredTasks = this.tasks;
    } else {
      this.filteredTasks = this.tasks.filter(task =>
        (task.name?.toLowerCase().includes(this.searchQuery.toLowerCase())) ||
        (task.description?.toLowerCase().includes(this.searchQuery.toLowerCase()))
      );
    }
  }

<<<<<<< HEAD
=======
  // Navigate to the edit task page
>>>>>>> origin/lahmer
  editTask(task: Task): void {
    this.router.navigate(['/update-task', task.idTask]);
  }

  addTask(): void{
    this.router.navigate(['/addtask'])
  }
<<<<<<< HEAD
 
=======
>>>>>>> origin/lahmer
}