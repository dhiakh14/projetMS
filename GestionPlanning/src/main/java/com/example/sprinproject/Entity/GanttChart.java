package com.example.sprinproject.Entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> origin/lahmer
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GanttChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private Date startDate;
    private Date endDate;
    private double progress;

<<<<<<< HEAD
    @OneToMany(mappedBy = "ganttChart", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
=======
    @OneToMany(mappedBy = "ganttChart", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
>>>>>>> origin/lahmer
    private List<Task> tasks;

    @Override
    public String toString() {
        return "GanttChart{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", progress=" + progress +
                '}';
    }
}