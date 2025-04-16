/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface GetStatisticsByStatus$Params {
}

export function getStatisticsByStatus(http: HttpClient, rootUrl: string, params?: GetStatisticsByStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<{
[key: string]: number;
}>> {
  const rb = new RequestBuilder(rootUrl, getStatisticsByStatus.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/hal+json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<{
      [key: string]: number;
      }>;
    })
  );
}

getStatisticsByStatus.PATH = '/statisticsByStatus';
