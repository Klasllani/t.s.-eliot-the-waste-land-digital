# T.S. Eliot's *The Waste Land* in TEI/XML
*The Waste Land* has been in the public domain in the United States since January 1, 1998. For Non-U.S. residents, please look into local copyright regulations.

A landmark 20th-century poem, *The Waste Land* explores themes of disillusionment in the aftermath of World War I. 
Its rich use of literary references and analysis of the human condition make is so important to perserve it for progeny.

## Prerequisites
- Java JDK (8 or newer)
- [Maven](https://maven.apache.org/download.cgi)

## Running the Project

### 1. Build the Project
**Navigate to the directory** containing your project files:
   ```sh
cd [project-directory]
mvn clean install
mvn dependency:copy-dependencies
   ```

### 2. Compile the Java Files
 ```sh
javac -cp ".;target\dependency\*" LuceneIndexer.java LuceneSearcher.java XMLParser.java
   ```

### 3. Run the LuceneIndexer
After the build is successful, run the `LuceneIndexer` class to index the XML content:
```sh
java -cp ".;target\classes;target\dependency\*" LuceneIndexer The-Waste-Land.xml
```

### 4. Run the LuceneSearcher
After indexing, run the `LuceneSearcher` class to search the indexed content:
```sh
java -cp ".;target\classes;target\dependency\*" LuceneSearcher "search term"
```

For example, to search for "April" in the poem:
```sh
java -cp ".;target\classes;target\dependency\*" LuceneSearcher "April"
```

#### *4.1. Note for Linux/Mac Users
If using Linux or Mac, replace semicolons with colons in the classpath:
```sh
java -cp ".:target/classes:target/dependency/*" LuceneSearcher "search term"
```
