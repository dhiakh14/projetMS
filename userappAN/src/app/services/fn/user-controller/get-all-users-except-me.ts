/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { User } from '../../models/user';

export interface GetAllUsersExceptMe$Params {
  currentUserId: number;
}

export function getAllUsersExceptMe(http: HttpClient, rootUrl: string, params: GetAllUsersExceptMe$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<User>>> {
  const rb = new RequestBuilder(rootUrl, getAllUsersExceptMe.PATH, 'get');
  if (params) {
    rb.query('currentUserId', params.currentUserId, {});
  }

  return http.request(
<<<<<<< HEAD
    rb.build({ responseType: 'json', accept: 'application/json', context })
=======
    rb.build({ responseType: 'json', accept: 'application/json ', context })
>>>>>>> origin/lahmer
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<User>>;
    })
  );
}

getAllUsersExceptMe.PATH = '/users/all-except-me';
