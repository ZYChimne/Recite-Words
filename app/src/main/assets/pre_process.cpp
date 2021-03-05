#include <iostream>
#include <fstream>
using namespace std;
int main(){
    ifstream infile; 
    infile.open("lang_dict.txt");
    string data;
    ofstream outfile;
    outfile.open("lang_dict_map.txt");
    outfile<<"{";
    while(infile>>data){
        // Json
        // string res="\""+data+"\": \"";
        // infile>>data;
        // res+=data+"\",";
        // outfile<<res<<endl;
        // Map
        string res="\""+data+"\" to \"";
        infile>>data;
        res+=data+"\",";
        outfile<<res<<endl;
    }
    infile.close();
    outfile<<"}";
    outfile.close();
    return 0;
}