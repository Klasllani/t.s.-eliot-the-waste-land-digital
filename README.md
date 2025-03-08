# T.S. Eliot's *The Waste Land* in TEI/XML
*The Waste Land* has been in the public domain in the United States since January 1, 1998. For Non-U.S. residents, please look into local copyright regulations.

A landmark 20th-century poem, *The Waste Land* explores themes of disillusionment in the aftermath of World War I. 
Its rich use of literary references and analysis of the human condition make is so important to perserve it for progeny.

## Prerequisites
- Java JDK (8 or newer)
- [Maven](https://maven.apache.org/download.cgi)

## Running the Project

**Navigate to the directory** containing your project files:
   ```sh
cd [project-directory]
mvn clean install
mvn dependency:copy-dependencies
   ```

### Run the XML Parser
 ```sh
java -cp target\classes;target\dependency\* XMLParser The-Waste-Land.xml
   ```

### Run the LuceneIndexer
After the build is successful, run the `LuceneIndexer` class to index the XML content:
```sh
java -cp target\classes;target\dependency\* LuceneIndexer The-Waste-Land.xml
```

### Run the LuceneSearcher
After indexing, run the `LuceneSearcher` class to search the indexed content:
```sh
java -cp target\classes;target\dependency\* LuceneSearcher "search term"
```
