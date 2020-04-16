'''
Created on Feb 13, 2020

@author: yazid
'''
from BST_Node import BST_Node


class BST(object):
    
    root= None 
    size = int(0)
    


    def __init__(self):
        pass
    
    def findNode(self, s):
        if self.size == 0:
            return None
        tgt = self.root
        while tgt.data != s:
            if(s > tgt.data):
                tgt = tgt.right
            elif s < tgt.data:
                tgt = tgt.left
            if tgt == None:
                return None
        return tgt
        
    def contains(self, s):
        if self.findNode(s) == None:
            return False
        return True
    
    def insert(self, s):
        if s == None:
            return False
        if self.root == None:
            self.root = BST_Node(s)
            self.size += 1
            return True
        if self.contains(s):
            return False 
        ins = BST_Node(s)
        tgt = self.root
        while True:
            if s > tgt.data:
                if tgt.right == None:
                    tgt.right = ins
                    break
                else:
                    tgt = tgt.right
            else:
                if tgt.left == None:
                    tgt.left = ins
                    break
                else:
                    tgt = tgt.left
        self.size += 1
        return True
    def remove(self, s):
        if s == None :
            return False
        node = self.findNode(s)
        tgt = self.root
        left = False
        if not self.contains(s):
            return False
        while s != tgt.data:
            if tgt.left is node or tgt.right is node:
                break
            if s > tgt.data:
                tgt = tgt.right
            else:
                tgt = tgt.left
        if tgt.left == node:
            left = True
        #leaf del
        if node.left == None and node.right == None:
            if node is self.root:
                self.root = None
            else:
                if left:
                    tgt.left = None
                else:
                    tgt.right = None
        elif node.left != None and node.right != None:
            child = self.minNode(node.right)
            tmp = node.data
            self.remove(child.data)
            node.data = tmp 
            return True
        else:
            if node.left != None:
                child = node.left
            else:
                child = node.right
            if node is self.root:
                self.root = child
            else:
                if left:
                    tgt.left = child
                else:
                    tgt.right = child
        self.size -= 1
        return True
            
                
            
    def minNode(self, node):
        while node.left != None:
            node = node.left
        return node
    def findMin(self):
        return self.minNode(self.root).data
    def findMax(self):
        node = self.root
        while node.right != None:
            node = node.right
        return node.data
    
    def dive(self, node):
        if(node == None):
            return 0
        if node.right == None and node.left == None:
            return 1
        return 1 + max(self.dive(node.left), self.dive(node.right))
        
    def height(self):
        
        return self.dive(self.root)-1
     
    def printlvl(self, node, nlvl, lvl , height):
        space = " "* (-(1-2**(height-nlvl+1))-1)
        if (node == None):
            s = "  "
            rnode = None
            lnode = None
        else:
            s= node.data
            rnode = node.right
            lnode= node.left
        if lvl == 0:
            return space +("[%s]" % s )+space
        else:
            s1 =self.printlvl(lnode, nlvl, lvl -1, height)
            s2 =self.printlvl(rnode, nlvl, lvl -1, height)
            return s1 + s2
    
    
    def __str__(self, *args, **kwargs):
        for i in range(self.height()+1):
            print self.printlvl(self.root, i, i, self.height())
        return ""
                
                
        
            
        