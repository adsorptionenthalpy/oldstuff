/*
Section CSC-160-500 - Computer Science I: C++
File Name: rationals.cpp
Author: Adam Androulidakis
Description:  Calculate rationals using overloaded operators that are not members	
				of the rational class, using objects of the Class rational.
Last Changed:  10/10/2010 9:57 PM
*/


#include <iostream> 
#include <cstdlib> 
using namespace std; 

class Rational 
{
	public: 
		Rational(int numerator, int denominator); 
		Rational(int numerator);
		Rational();
		friend Rational operator+(const Rational&,const Rational&); 
		friend Rational operator-(const Rational&,const Rational&); 
		friend Rational operator*(const Rational&,const Rational&); 
		friend Rational operator/(const Rational&,const Rational&); 
		friend bool operator<(const Rational&,const Rational&); 
		friend bool operator<=(const Rational&,const Rational&); 
		friend bool operator>(const Rational&,const Rational&); 
		friend bool operator >=(const Rational&,const Rational&); 
		friend bool operator ==(const Rational&,const Rational&); 
		friend ostream& operator <<(ostream&,const Rational&); 
		friend istream& operator >>(istream&, Rational&); 
	private: 
		int numerator; 
		int denominator; 
}; 
int get(int m, int numerator);
void normalize(int& numerator, int& denominator);

Rational::Rational(int numer, int denom) 
{  
	normalize(numer, denom); 
	numerator = numer; 
	denominator = denom; 
} 

Rational::Rational(int numer)
{ 
	numerator=numer;
	denominator=1;
} 
 
Rational::Rational()
{ 
	numerator=0; 
	denominator=1;
} 
 
Rational operator +(const Rational& left, const Rational& right) 			
{ 
	int numer = left.numerator * right.denominator + left.denominator * right.numerator; 
	int denom = left.denominator * right.denominator; 
	normalize(numer, denom); 
	Rational local(numer, denom); 
	return local; 
} 
Rational operator -(const Rational& left,const Rational& right) 
{ 
	int numer = left.numerator * right.denominator - left.denominator * right.numerator; 
	int denom = left.denominator * right.denominator; 
	normalize(numer, denom); 
	Rational local (numer, denom);  
	return local; 
} 
Rational operator *(const Rational& left,const Rational& right) 
{ 
	Rational product; 
	int numer = left.numerator * right.numerator; 
	int denom = left.denominator * right.denominator; 
	normalize(numer, denom); 
	product = Rational(numer, denom); 
	return product; 
} 
Rational operator/(const Rational& left,const Rational& right) 
{ 
	Rational quotient; 
	int numer = left.numerator * right.denominator; 
	int denom = left.denominator * right.numerator; 
	normalize(numer, denom); 
	quotient = Rational(numer, denom); 
	return quotient; 
} 

 
bool operator <(const Rational& left, const Rational& right) 
{ 
	bool result;
	result = left.numerator * right.denominator < right.numerator * left.denominator; 
	return result;
} 
 
bool operator <=(const Rational& left,const Rational& right) 
{ 
	bool result;
	result =  left.numerator * right.denominator <= right.numerator * left.denominator; 
	return result;
} 
 
bool operator >(const Rational& left, const Rational& right) 
{ 
	bool result;
	result = left.numerator * right.denominator > right.numerator * left.denominator; 
	return result;
} 
 
bool operator >=(const Rational& left,const Rational& right) 
{ 
	bool result;
	result = left.numerator * right.denominator >= right.numerator * left.denominator; 
	return result;
} 
 
bool operator==(const Rational& left,const Rational& right) 
{ 
	bool result;
	result = left.numerator * right.denominator == right.numerator * left.denominator; 
	return result;
} 

istream& operator >>(istream& inny, Rational& right) 
{ 
	bool pass = false;
	char temp; 
	while (pass == false)
	{
		inny >> right.numerator >> temp >> right.denominator; 
		if (temp != '/')     
		{
			cout << "Incorrect Format, Try Again.\n-->" << endl; 
		} 
		else 
		{
			pass = true;
		}
	}
	normalize(right.numerator, right.denominator); 
	return inny; 
} 

ostream& operator <<(ostream& outty,const Rational& right) 
{ 
	char temp; 
	outty << right.numerator<< '/' << right.denominator; 
	return outty; 
} 

int get(int m, int numerator) 
{ 
	int t;  
	int r; 
	m = abs (m); 
	numerator = abs (numerator); 
	if(numerator < m)    
	{ 
		t = m; 
		m = numerator; 
		numerator = t; 
	} 
	r  = m % numerator; 
	while(r != 0) 
	{ 
		r = m%numerator; 
		m = numerator; 
		numerator = r; 
	} 
	return m; 
} 
 
 void normalize(int& numerator, int& denominator) 
{ 
	int g = get(numerator, denominator); 
	numerator = numerator/g;  
	denominator = denominator/g; 
 
	if(numerator > 0 && denominator < 0 || numerator < 0 && denominator < 0) 
	{ 
		numerator = -numerator; 
		denominator = -denominator; 
	} 
} 

int main() 
{ 

	cout<<"Author: Adam Androulidakis";
	cout<<"\nSolution for Homework #6 Problem #5";
	char r;
	
	Rational test1, test2(10), test3(1,2);
	Rational test4;
	cout << "\nTest1 equals " << test1;
	cout << "\nTest2 equals " << test2;
	cout << "\nTest3 equals " << test3;

	cout <<"\nTest1 + Test2 equals "<< test1+test2;
	cout << "\nTest2 + Test3 equals " << test2+test3;
	cout << "\nTest3 - Test1 equals " << test3-test1;
	cout << "\nTest3 / Test2 equals "<< test3/test2;
	
	if (test1 == test2)
	{
		cout << "\nTest1 is equal to Test2";
	}
	else
	{
		cout << "\nTest1 is not equal to Test2";
	}
	
	if (test1 < test2)
	{
		cout << "\nTest1 is less than Test2";
	}
	else 
	{
		cout << "\nTest1 is not less than Test2";
	}
	
	if (test1 <= test3)
	{
		cout <<"\nTest1 is less than or equal to Test3";
	}
	else
	{
		cout <<"\nTest1 is not less than or equal to Test3";
	}
	
	do
	{
		cout<<"\nEnter values for Test4. "
		<<"\nExample 3/5 "
		<<"\n-->";
	
		cin>>test4;
		cout<<"Test4 is: "<<test4;
	
		if (test4 == test1)
		{
			cout << "\nTest4 is equal to Test1";
		}
		else
		{
			cout << "\nTest4 is not equal to Test1";
		}
	
		cout << "\n\nDo you want to try another problem?";
		cin >> r;    
	} while (r == 'y' || r == 'Y');
	cout<<"Peace!";

  return 0; 
} 
