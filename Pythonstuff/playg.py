'''
Created on Feb 13, 2020

@author: yazid

'''


if __name__ == '__main__':
  
    a = open("filters.csv", "r") 
    sp = a.read().split("\n")
    ins = []
    del sp[-1]
    for s in sp:
        ins.append(s.split(",")[6])
    while "" in ins:
        ins.remove("")
    for i in ins:
        print('<li><input type="checkbox" name="'+i+'" value="">'+i+"</li>")