all:
	javac MobilePhone.java
	javac MobilePhoneSet.java
	javac Myset.java
	javac Exchange.java
	javac ExchangeList.java
	javac RoutingMapTree.java
	javac LinkedListimpl.java
	javac assn2checker.java
	java assn2checker

clean:
	rm assn2checker.class RoutingMapTree.class MobilePhone.class MobilePhoneSet.class Myset.class Exchange.class ExchangeList.class
