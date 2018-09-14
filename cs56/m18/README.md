# ucsb-cs56-gauchogains
https://gauchogains.herokuapp.com/

## Team Members
- Andrew
- Ben
- Clare 
- Howard 
- Joanne 
- Maga

## Agreements
* Communication
* Respect
* Equal Effort
* Integrity

# MongoGains (Mongo/mLab Set up)

https://github.com/ucsb-cs56-pconrad/sparkjava-mongodb-mlab-tutorial

## mLab and MongoDB Set Up
* Follow the tutorial in the link above to set up mLab and MongoDB.
* MongoDB does not need to be installed

## Deployment on Local
Create a file ".env" in your project folder in the following format:
```
MONGODB_USER=dbuser
MONGODB_PASS=dbpassword
MONGODB_NAME=dbname
MONGODB_HOST=dbhosturl
MONGODB_PORT=dbport
```

dbuser: database user created under the Users tab within the database on mLab.
dbpassword: the corresponding password (not your mLab password).
dbmname: the name following the final '/' at the end of the MongoDB URI
dbhosturl: the url between '@' and ':'.
dbport: the port number following  the host url.

To deploy locally run the commands:
```
mvn clean install
heroku local
```
The local site can be found on http://localhost:4567/

# Deployment to Heroku
Go to your Heroku app and under the settings tab change the config vars with the same key/value pair as in the local .env set up above.
e.g. "MONGODB_USER" in Key field and "dbuser" in Value field.

Compile and deploy your web app with the following:
```
mvn compile; mvn package heroku:deploy
```

