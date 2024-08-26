# T.S. Eliot's *The Waste Land* in TEI/XML
*The Waste Land* has been in the public domain in the United States since January 1, 1998. For Non-U.S. residents, please look into local copyright regulations.

*The Waste Land* is a landmark 20th-century poem that explores themes of disillusionment in the aftermath of World War I. 
Its rich use of literary references and analysis of the human condition make is so important to perserve it for progeny.


## Running the Project

### Ensure Maven is Installed
Make sure you have Maven installed on your system. You can download it from [Maven's official website](https://maven.apache.org/download.cgi).

### Build the Project
1. Open a terminal.
2. Navigate to the directory containing your project files.
3. Run the following command to build the project:
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

By following these steps, you should be able to index and search the content of `The-Waste-Land.xml` using Apache Lucene, even if all your files are in the same directory.
