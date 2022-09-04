java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

http://www.hsqldb.org/doc/2.0/guide/running-chapt.html
https://www.tutorialspoint.com/hsqldb/hsqldb_installation.htm#
https://www.javaguides.net/2019/08/java-hsql-tutorial-create-read-update-delete-jdbc-examples.html
add


Run db:
java -classpath lib/hsqldb-jdk8.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb

Run db view:
java -cp lib/hsqldb-jdk8.jar org.hsqldb.util.DatabaseManagerSwing

jdbc:hsqldb:hsql://localhost/tesdb

git push origin HEAD:master