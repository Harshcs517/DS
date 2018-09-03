all:
	javac checker.java
	javac RoutingMapTree.java
	java checker

clean:
	rm checker.class RoutingMapTree.class
