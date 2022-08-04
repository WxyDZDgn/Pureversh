#include <iostream>
#include <stdlib.h>
using namespace std;
int main() {
	system("java --module-path \"Pureversh_lib/lib\" --add-modules javafx.controls,javafx.fxml -jar Pureversh.jar");
	return 0;
}
