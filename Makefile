test:
	mvn test
package:
	mvn clean package
install:
	sudo $(ES_HOME)/bin/plugin -remove wordending && sudo $(ES_HOME)/bin/plugin -url file://$(PWD)/target/releases/elasticsearch-wordending-tokenfilter-0.0.1.zip -install wordending && sudo service elasticsearch restart