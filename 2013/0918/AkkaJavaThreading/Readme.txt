Expected Result:

        | bob | alice
Initial | 100 | 50
Step    | 90  | 60
End     | 93  | 57

1) mvn clean compile

2) First execute Java based version. Fire below command. It will create deadlock and program freeze.Press Ctrl + C to exit.
mvn exec:java -Dexec.mainClass="com.jungle.example.java.BankApplication"

3) Now execute Akka version. Fire below command and observe.
mvn exec:java -Dexec.mainClass="com.jungle.example.akka.BankApplication"