/**Array sorting demonstration
*author Adam Androulidakis
*date 2010-11-14
**/
#include <iostream>
#include <cstdlib>
#include <cstring>

using namespace std;

void displayValueCounts(const char data[], int numberUsed);
void sortArray(char data[], int numberUsed);
void swap_values(char& v1, char& v2);
char index_of_smallest(const char a[], int start_index, int number_used);

int main()
{
	const int size = 99;
	char data[size];
	int s;
	int count = 0;
	char i;
	cout << "Enter sentence: ";
	for (i = 0; i < size; i++)
	{
		cin.get(data[i]);
		
		if (data[i] == '\n' || data[i] == ' ' )
		{
			count += 1;
		}
		
		if (data[i] == '\n')
		{
			break;
		}
		
	}
	cout<<"\nYou entered "<<data;
	cout<<"\nAmount of words = " <<count<<endl;
    s = strlen(data);
	sortArray(data, s);
	displayValueCounts(data, s);    
         
    cout << "\n\n";
    system("PAUSE");
    return 0;
}

void displayValueCounts(const char data[], int numberUsed)
{
    char currentValue = data[0];
	int count = 1;
    cout << data[0] << " ";
	
    for (int index = 0; index < numberUsed; index++)
    {
			if (data[index] == currentValue)
			{
				count++;
			}
			else
			{
				cout << count << endl;
				currentValue = data[index];
				count = 1;
				cout << data[index] << " ";
			}
    }
    cout << count << endl;
}

void sortArray(char a[], int number_used)
{
    int index_of_next_smallest;
    for (int index = 0; index < number_used - 1; index++)
    {
        index_of_next_smallest = index_of_smallest(a, index, number_used);
        swap_values(a[index], a[index_of_next_smallest]);
    }
}

void swap_values(char& v1, char& v2)
{
    char temp;
    temp = v1;
    v1 = v2;
    v2 = temp;
}

char index_of_smallest(const char a[], int start_index, int number_used)
{
    char min = a[start_index],
    index_of_min = start_index;
	for (int index = start_index + 1; index < number_used; index++) 
	if (a[index] < min)
    {
        min = a[index];
        index_of_min = index;
	}
	return index_of_min;
}
