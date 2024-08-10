# Lexical Analyzer for Principles of Programming Languages
This Java program serves as a tool for performing lexical analysis on input text. It includes several functions for identifying different types of characters and strings, such as digits, hexadecimal and binary numbers, letters, brackets, operators, floating-point numbers, and identifiers. The application processes user input and uses a HashMap to store the identified characters and strings together with their corresponding places.

The application determines the nature of each token by tokenizing the input text and iterating through each token in the HashMap one at a time. The token is added to the tOut HashMap as a NUMBER if it is a number. The token is added to the tOut HashMap as an IDENTIFIER if it is an identifier. The program uses a helper function to check the validity of the token if it is a floating-point number and adds it as a NUMBER to the tOut HashMap. An error message is added to the tOut HashMap if the token is invalid.

This application is a helpful tool for performing lexical analysis because it is made to study and identify various characters and string kinds in the input text.

Determine whether a character is a digit, hexadecimal number, binary number, or letter using the isDigit, isHexadecimal, isBinary, and isLetter functions, respectively. If a character is a bracket, the isBracket function determines whether it is one and returns that character. The isOperator function determines whether a character is one of the following operators: *,!, /,:,, =, >, or? The isDigitOperator function checks whether a character is a digit operator such as ., +, or -.

The functions isIdentifierForLetter and isIdentifierForOperator determine if a string is a valid identifier for either letters or operators. If a string begins with a letter and only contains letters, digits, and digit operators, it is a valid identifier.

The hashMap's contents are printed in the console and to a file using the printHashMap function. The function prints and writes the value of a key in a hash map that contains the string "ERROR" to a file before returning.

Whether a string is a valid floating-point number is determined by the floatingPointNumberChecker function. The function determines whether there are digits before and after a dot if the string contains one. The function sets the value of the key in the HashMap to "ERROR: Floating-point number is not valid" if there are no digits either before or after the dot. The function changes the value of the key in the hash map to the string if the string is a valid floating-point number.

Overall, this program serves as a versatile character and string processing tool.

The provided code is a Java program that reads an input file, tokenizes its contents, and creates a new file called output.txt with the output tokens. It takes the name of an input file as an argument.





The program initially accepts the user's command-line parameter for the input filename, reads the file, and initializes a Scanner object to read the file's contents. Additionally, a FileWriter object is initialized in order to write the output to the output.txt file.

The program then tokenizes the data after reading each line in the input file. Using a HashMap named t, it also keeps track of each token's row and column numbers. The final output tokens will be stored in a HashMap called tOut, which is also initialized.

The program examines each character in the input file and categorizes it during the tokenization process. If it is found that the character is a bracket, it is added to the HashMap t along with the row and column numbers that go with it. The software toggles the isDoubleQuote flag to show that it is currently processing a string surrounded in quotes if the character is a double-quote. The program then records the whole string up until the closing quote, including any whitespace or special characters.

The program also toggles the isSingleQuote flag and stores the string encased in the quotes if the character is a single-quote. The program keeps the subsequent character and the backslash together as a single string if the character is a backslash. The software deems the current string to be complete and stores it in the HashMap t when the character is a space and the isDoubleQuote flag is set to false. If not, the character is added to the string that already exists.

The program iterates through each token in the HashMap and identifies its type after the tokenization process is finished. The token is added to the HashMap output as a NUMBER if it is a number. The token is added to the HashMap output as an IDENTIFIER if it is an identifier. The program uses a helper function to check the validity of the token if it is a floating-point number, and if it is, it is added to the HashMap tOut as a NUMBER. An error message is added to the HashMap tOut if the token is invalid.

Finally, the program uses the FileWriter object to write the output tokens to the output.txt file. The generated output file is a useful tool for performing lexical analysis on input text because it includes all of the recognized tokens and their matching kinds.

## How to Run The Program
After putting the input, output, and program file (App.java) into a folder, compile and run App.java and give the input file as indicated below.

```Enter the name of the input file (input.txt): input.txt```




## Examples
Input 1:
![resim](https://user-images.githubusercontent.com/104105674/234921190-667adebf-ab71-46bd-b8b1-df1bc9607061.png)

Output 1: 
![resim](https://user-images.githubusercontent.com/104105674/234921292-8bb4c9f0-ecfa-4303-a120-2834910280ee.png)
![resim](https://user-images.githubusercontent.com/104105674/234921302-406e10da-971c-484a-b7ec-c5279d574e39.png)
![resim](https://user-images.githubusercontent.com/104105674/234921309-44b42c86-24d6-4b40-9df3-69a443065172.png)

Input 2:
![resim](https://user-images.githubusercontent.com/104105674/234921339-4b832e3f-8fc1-4987-a53f-f2065e16e697.png)

Output 2:
![resim](https://user-images.githubusercontent.com/104105674/234921413-d8702610-60d6-4492-9c99-e75b2ddc6ee5.png)
![resim](https://user-images.githubusercontent.com/104105674/234921449-1e8ab5f0-0a54-4f33-8f7c-6714c7203180.png)






  
## Contributors

- [@aiSD2109 ](https://github.com/aiSD2109) (Mustafa Said Çanak).
- [@mtkaragul ](https://github.com/mtkaragul) (Muhammed Talha Karagül).
- [@0xEnsar ](https://github.com/0xEnsar) (Ensar M. Yozgat).
