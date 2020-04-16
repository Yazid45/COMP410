'''
Created on Feb 13, 2020

@author: yazid
'''
from Node import Node

class LinkedList():
    
    
    headCell = None
    lastCell = None
    size =0
    
    def __init__(self):
        pass
   
   
   
    def insert(self, value, ind):
        if ind > self.size or ind < 0:
            return False
        ins = Node(value)
        if self.size == 0:
            self.headCell, self.lastCell = ins, ins
        elif ind == 0:
            ins.next = self.headCell
            self.headCell.prev = ins
            self.headCell = ins
        elif ind == self.size:
            self.lastCell.next = ins
            ins.prev = self.lastCell
            self.lastCell = ins
        else:
            tgt = self.headCell
            for i in range(ind):
                tgt = tgt.next
            tgt.prev.next = ins
            ins.prev = tgt.prev
            tgt.prev = ins
            ins.next = tgt
        self.size+=1
        return True
    
    
    def insort(self, elt):
        if elt < self.get(0):
            self.insert(elt, 0)
            return True
        for i in range(1, self.size):
            if elt > self.get(i-1) or elt == self.get(i-1):
                if elt < self.get(i):
                    self.insert(elt, i)
                    return True
        self.insert(elt, self.size) 
        return True
    
    def remove(self, ind):
        ind = int(ind)
        if ind > self.size-1 or ind < 0:
            return False
        if ind == 0:
            if self.size == 1:
                self.headCell, self.lastCell = None, None
            else:
                self.headCell = self.headCell.next
                self.headCell.prev = None
        elif ind == self.size-1:
            self.lastCell= self.lastCell.prev
            self.lastCell.next = None
        else:
            tgt = self.headCell.next
            for i in range(ind):
                tgt = tgt.next
            mnd = tgt.prev.prev
            tgt.prev = mnd
            mnd.next = tgt
        self.size -= 1
        return True
            
                
                    

                
        
    def get(self, ind):
        if ind > self.size-1 or ind < 0:
            return None
        tgt = self.headCell   
        for i in range(ind):
            tgt = tgt.next
        return tgt.data
    def isEmpty(self):
        return self.size == 0
    def clear(self):
        self.headCell, self.lastCell = None, None
        self.size = 0
    
    def __str__(self):
        tgt = self.headCell
        lists = "List is : "
        while tgt != None:
            lists = lists + "--> %.2f " % tgt.data
            tgt = tgt.next
        return lists
            
            
        
          
                
                
                
            
       
        