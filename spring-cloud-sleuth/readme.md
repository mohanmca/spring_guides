1. Add spring-cloud-starter-sleuth  as dependency
2. logstash-logback-encoder
3. Refer logback-spring.xml
   1. Refer properties 
   2. Refer encoder 

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>

        <dependency>
            <groupId>net.logstash.logback</groupId>
            <artifactId>logstash-logback-encoder</artifactId>
            <version>6.4</version>
        </dependency>

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud-sleuth.version>2.2.3.RELEASE</spring-cloud-sleuth.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-sleuth</artifactId>
                <version>${spring-cloud-sleuth.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>