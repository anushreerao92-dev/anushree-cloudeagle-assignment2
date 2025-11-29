# Dynamic‑User‑Fetcher (CloudEagle Assignment)

##  Overview  
This is a Spring Boot application that dynamically fetches user data from external third‑party systems and stores the fetched users in a temporary storage. The API endpoints, request details, and field mappings are fully configurable via database — no need to change code or redeploy when adding/updating external sources.  

**Main goals:**  
- Support multiple external systems.  
- Make API configuration (URL, headers, request-body, response-to‑user mapping) dynamic & database-driven.  
- Use a generic method to call any external API and parse its response.  
- Store fetched users temporarily (in a DB table).  


Post Endpoint to insert the config details of the external systems screenshot: 
<img width="941" height="487" alt="image" src="https://github.com/user-attachments/assets/001eef66-c1cf-40d9-8835-912c1341a8fa" />

Screenshot of the config in the DB:
<img width="955" height="344" alt="image" src="https://github.com/user-attachments/assets/9c65f1d3-0d3c-46de-8fe8-192b98e3f7cf" />


Fetch call to retrieve the details and save it to DB: 
http://localhost:8080/api/users/fetch/calendly

User stored in the DB
<img width="812" height="301" alt="image" src="https://github.com/user-attachments/assets/881d06a3-ea86-492c-8ee6-81fe96a7ba33" />

Currently, the application requires a custom service class to parse responses from each network. Since every third-party system (Calendly, Dropbox, etc.) has a different JSON structure, the generic method only fetches raw responses. Additional parsing logic per network is needed before storing users in the temporary table.
