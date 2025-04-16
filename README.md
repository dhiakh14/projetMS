🔐 Backend Integration with Keycloak for Authentication and Authorization This part of the project focuses on integrating Keycloak for user authentication and authorization on the backend. The Keycloak server will issue JWT (JSON Web Tokens), which will be used to secure API endpoints.

✅ Task Overview Keycloak Setup:

Set up a Keycloak server to manage user authentication and authorization.

Configure a realm (e.g., my-app-realm), client (e.g., my-app-client), and roles (e.g., admin, user) for the application.

Set Keycloak to issue JWT tokens upon successful login.

Backend Configuration:

Secure API Endpoints: Protect sensitive endpoints using JWT authentication to ensure that only authenticated users can access them.

JWT Token Validation: Implement middleware or filters to validate JWT tokens on each request by verifying the signature and ensuring the token is not expired.

Role-based Access Control (RBAC): Use roles defined in Keycloak to restrict access to specific endpoints. For example, only users with the admin role can access certain administrative routes.

Keycloak Adapter: Integrate a Keycloak adapter or use a Keycloak library to manage the communication with the Keycloak server and simplify token validation.

Token Expiration and Refresh:

Implement token expiration handling to reject expired tokens.

If using refresh tokens, allow for token refresh to maintain a valid session.

Backend Security:

Ensure that all requests that require authentication have a valid Authorization header with the JWT token.

Configure your backend to only accept requests that have been authenticated by Keycloak.

🔒 Keycloak Features in Use: JWT Authentication: Secure API endpoints by validating JWT tokens issued by Keycloak.

Role-based Access Control (RBAC): Control access to specific resources based on user roles defined in Keycloak.

✅ Keycloak Integration Steps: Configure Keycloak: Set up realms, clients, and roles.

API Security: Use Keycloak's adapter or a JWT validation library to secure backend routes.

Validate JWT Tokens: Implement middleware to validate JWT tokens on incoming requests.

Role-based Restrictions: Use roles defined in Keycloak to restrict access to specific routes.

⚠️ Issue Resolved: The 401 Unauthorized issue occurred due to invalid or expired tokens being sent by the frontend. This was fixed by ensuring that the backend properly checks and validates the JWT tokens.
