/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

export interface Task {
  actual_end_date?: string;
  description?: string;
  durationInDays?: number;
  idTask?: number;
  name?: string;
  planned_end_date?: string;
  projectId?: number;
  startDate?: string;
  status?: 'TO_DO' | 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';
}