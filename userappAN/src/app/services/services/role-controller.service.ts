/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addRole } from '../fn/role-controller/add-role';
import { AddRole$Params } from '../fn/role-controller/add-role';
import { getRoles } from '../fn/role-controller/get-roles';
import { GetRoles$Params } from '../fn/role-controller/get-roles';
import { Role } from '../models/role';

@Injectable({ providedIn: 'root' })
export class RoleControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addRole()` */
  static readonly AddRolePath = '/roles/add';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addRole()` instead.
   *
   * This method doesn't expect any request body.
   */
  addRole$Response(params: AddRole$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return addRole(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addRole$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  addRole(params: AddRole$Params, context?: HttpContext): Observable<string> {
    return this.addRole$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `getRoles()` */
  static readonly GetRolesPath = '/roles/getRoles';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getRoles()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles$Response(params?: GetRoles$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Role>>> {
    return getRoles(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getRoles$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles(params?: GetRoles$Params, context?: HttpContext): Observable<Array<Role>> {
    return this.getRoles$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Role>>): Array<Role> => r.body)
    );
  }

}
