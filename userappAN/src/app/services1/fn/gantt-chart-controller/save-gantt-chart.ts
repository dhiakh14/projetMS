/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { GanttChart } from '../../models/gantt-chart';

export interface SaveGanttChart$Params {
      body: GanttChart
}

export function saveGanttChart(http: HttpClient, rootUrl: string, params: SaveGanttChart$Params, context?: HttpContext): Observable<StrictHttpResponse<GanttChart>> {
  const rb = new RequestBuilder(rootUrl, saveGanttChart.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
<<<<<<< HEAD
    rb.build({ responseType: 'json', accept: '*/*', context })
=======
    rb.build({ responseType: 'blob', accept: '*/*', context })
>>>>>>> origin/lahmer
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<GanttChart>;
    })
  );
}

saveGanttChart.PATH = '/gantt-chart/save';
