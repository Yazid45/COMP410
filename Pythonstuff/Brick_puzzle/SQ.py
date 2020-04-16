'''
Created on Feb 15, 2020

@author: yazid
'''

class square(object):
  
    grid = None
    Brick = None
    pos = 0 , 0
    
    def __init__(self, x, y, g):
        self.pos = y,x
        self.grid = g
    def __str__(self):
        if self.Brick == None:
            return "[ ]"
        return "[%s]" % str(self.Brick.info)
    def move(self, dir):
        if self.Brick == None:
            return False
        self.Brick.mv.append(self)
        if dir == -1:
            x, y = self.pos
            nsq =self.grid.grid[x][y+1]
            if nsq != None and (nsq.Brick == None or nsq.Brick == self.Brick) :
                if nsq.Brick == 
                nsq.Brick = self.Brick
                self.Brick= None
                self.Brick.mv.pop(self)
                return True
        elif dir == 1:
            x, y = self.pos
            nsq =self.grid.grid[x][y-1]
            if y>0 and nsq.Brick == None:
                nsq.Brick = self.Brick
                self.Brick= None
                self.Brick.mv.pop(self)
                return True
        elif dir == 2:
            x, y = self.pos
            nsq =self.grid.grid[x+1][y]
            if nsq != None and nsq.Brick == None:
                nsq.Brick = self.Brick
                self.Brick= None
                self.Brick.mv.pop(self)
                return True
        elif dir == -2:
            x, y = self.pos
            nsq =self.grid.grid[x-1][y]
            if x>0 and nsq.Brick == None:
                nsq.Brick = self.Brick
                self.Brick= None
                self.Brick.mv.pop(self)
                return True     
        return False
     

class grid(object):
    grid = None
    
    def __init__(self, w , h):
        self.grid= [[None for x in range(w)] for y in range(h)]
        for x in range(w):
            for y in range(h):
                self.grid[y][x] = square(x,y,self)
    
    def __str__(self, *args, **kwargs):
        s = ""
        for i in range(len(self.grid[0])):
            for j in range(len(self.grid)):
                s = s + str(self.grid[j][i])
            s= s +"\n"
        return s     
class brick(object):
    sq = []
    info = None
    mv = []
    def __init__(self, i = "x", *sq):
        self.info = i
        for s in sq:
            self.sq.append(s)
    
    def move(self, dir):
        for s in self.sq:
            if s not in self.mv:
                s.move(dir)
                
            
        
        
        
        
        
        
        