/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { GanttChart } from '../../models/gantt-chart';

export interface UpdateGanttChart$Params {
  id: number;
      body: GanttChart
}

export function updateGanttChart(http: HttpClient, rootUrl: string, params: UpdateGanttChart$Params, context?: HttpContext): Observable<StrictHttpResponse<GanttChart>> {
  const rb = new RequestBuilder(rootUrl, updateGanttChart.PATH, 'put');
  if (params) {
    rb.path('id', params.id, {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<GanttChart>;
    })
  );
}

updateGanttChart.PATH = '/gantt-chart/update/{id}';
