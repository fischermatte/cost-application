var connect = require('connect');
connect.createServer(
  connect.static('C:/Users/Georg/Development/workspaces/workspace_idea/cost-application/cost-client-angular/app')
).listen(8080);