/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { User } from '../../models/user';

export interface GetProfile$Params {
  idUser: number;
}

export function getProfile(http: HttpClient, rootUrl: string, params: GetProfile$Params, context?: HttpContext): Observable<StrictHttpResponse<User>> {
  const rb = new RequestBuilder(rootUrl, getProfile.PATH, 'get');
  if (params) {
    rb.path('idUser', params.idUser, {});
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
      return r as StrictHttpResponse<User>;
    })
  );
}

getProfile.PATH = '/users/getUserById/{idUser}';
