file1 := filepath1
file2 := filepath2
run-dist:
	./build/install/app/bin/app $(file1) $(file2)

build:
	./gradlew installDist

report:
	./gradlew jacocoTestReport

.PHONY: build