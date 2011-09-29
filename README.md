
PojoBuilder -  A code generator for POJO builders
================================================= 

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

Download Page: http://github.com/mkarneim/pojobuilder/archives/master

About
-----

PojoBuilder is a Java 6 compliant annotation processor that generates a builder class for each annotated POJO.

Among other things you can use pojo builders to build test data. For more information on test data builders 
see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html.

Example
-------

Let's have look at the following example POJO:

@GeneratePojoBuilder
public class Contact { 
	private final String name;
	private String email;

	@ConstructorProperties({ "name"})
	public Contact(String aName) {
		this.name = aName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
}

The @GeneratePojoBuilder annotation tells the annotation processor to create a new Java source file with 
the name "ContactBuilder" into the same package.
Have a look at "samples/src/generated/java/pojos/ContactBuilder.java" to see the generated source code.

Here is an example of how you can use the generated "ContactBuilder" from your code:


	Contact james = new ContactBuilder()
		.withName("James Bond")
		.withEmail("007@secretservice.org")
		.build();


For more examples please have a look into the "samples" directory.    

License
-------

Read the COPYING file.

How To Build
------------

For compiling the sources Java 6 is required.
For building the PojoBuilder library you can use the included Ant build script "build.xml". 


How To Use
----------

To generate a builder class for a POJO (Plain Old Java Object) just annotate its class with 
@GeneratePojoBuilder. If you want the generated Builder to use a specific constructor of your POJO
then annotate it with @ConstructorProperties and specify the corresponding field names of the parameters.

Then build your project sources with the PojoBuilder annotation processor enabled.

To enable the annotation processor you either can
* use the apt tool to compile your project (see http://download.oracle.com/javase/6/docs/technotes/guides/apt/index.html).
* use the <javac> ant task with a special configuration (see below)
* or add the PojoBuilder annotation processor to your Eclipse project configuration (see below)

### Using Ant

Here is a code snippet of an ANT build script that runs the PojoBuilder annotation processor within the javac task. 

<pre>
	<target name="compile" depends="init" description="Compile java sources and run annotation processor">
		<mkdir dir="${src.gen.java.dir}" />
		<mkdir dir="${build.classes.dir}" />
		<javac classpathref="class.path" destdir="${build.classes.dir}">
			<src path="${src.main.java.dir}" />
			<!-- This tells the compiler where to place the generated source files -->
			<compilerarg line="-s ${src.gen.java.dir}"/>
		</javac>
	</target>
</pre>

You can find a complete sample build script at "samples/build.xml".

### Using Eclipse

Do the following to enable the PojoBuilder annotation processor for your project in Eclipse:

* Place the PojoBuilder libraries (antlr-*.jar, stringtemplate-*.jar pojobuilder-*.jar) into your project library directory 
* Open your project's properties dialog
* Navigate to "Java Build Path" tree node
* Open the "Libraries" tab
* Add the library pojobuilder-*.jar to your project classpath
* Navigate to "Java Compiler / Annotation Processing" tree node
* Check "Enable project specific settings"
* Check "Enable annotation processing"
* Check "Enable processing in editor"
* Specify the target directory for the generated code in "Generated source directory"
* Navigate to "Java Compiler / Annotation Processing / Factory Path" tree node
* Check "Enable project specific settings"
* Click "Add JARs..."
* Add antlr-*.jar
* Add stringtemplate-*.jar
* Add pojobuilder-*.jar


