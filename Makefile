all:
	javac MyException.java
	javac MobilePhone.java
	javac MobilePhoneSet.java
	javac Myset.java
	javac Exchange.java
	javac ExchangeList.java
	javac RoutingMapTree.java
	javac LinkedListimpl.java
	javac assn3checker.java
	java assn3checker

clean:
	rm assn3checker.class RoutingMapTree.class MobilePhone.class MobilePhoneSet.class Myset.class Exchange.class ExchangeList.class MyException.class
