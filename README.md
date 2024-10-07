# T.S. Eliot's *The Waste Land* in TEI/XML
*The Waste Land* has been in the public domain in the United States since January 1, 1998. For Non-U.S. residents, please look into local copyright regulations.

A landmark 20th-century poem, *The Waste Land* explores themes of disillusionment in the aftermath of World War I. 
Its rich use of literary references and analysis of the human condition make is so important to perserve it for progeny.


## Running the Project

Make sure you have Maven installed on your system. You can download it from [Maven's official website](https://maven.apache.org/download.cgi).

**Navigate to the directory** containing your project files, and run the following command:
   ```sh
   mvn clean install
   ```

### Run LuceneIndexer
After the build is successful, run the `LuceneIndexer` class to index the XML content:
```sh
mvn exec:java -Dexec.mainClass="com.example.LuceneIndexer"
```

### Run LuceneSearcher
After indexing, run the `LuceneSearcher` class to search the indexed content:
```sh
mvn exec:java -Dexec.mainClass="com.example.LuceneSearcher"
```
