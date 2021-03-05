#include <iostream>
#include <fstream>
using namespace std;
int main(){
    ifstream infile; 
    infile.open("lang_dict.txt");
    string data;
    ofstream outfile;
    outfile.open("lang_dict_c.xml");
    outfile<<"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n <string-array name=\"src_lang\">\n";
    while(infile>>data){
        string res="<item>"+data+"</item>";
        infile>>data;
        outfile<<res<<endl;
    }
    infile.close();
    outfile<<"</string-array>\n</resources>\n";
    outfile.close();
    return 0;
}